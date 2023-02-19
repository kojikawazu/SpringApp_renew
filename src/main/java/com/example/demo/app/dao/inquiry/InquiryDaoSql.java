package com.example.demo.app.dao.inquiry;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.common.id.inquiry.InquiryReplyId;
import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせDaoクラス
 * @author nanai
 *
 */
@Repository
public class InquiryDaoSql implements InquiryDao {
	
	/** jdbcドライバー */
	private final JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	@Autowired
	public InquiryDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	/**
	 * 追加
	 * @param model
	 */
	@Override
	public void insertInquiry(InquiryModel model) {
		if(model == null)	return ;
		String sql = "INSERT INTO inquiry("
				+ "name, email, comment, created) "
				+ "VALUES(?,?,?,?)";
		
		try {
			this.jdbcTemp.update(
					sql,
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getCreated()
				);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 更新
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int updateInquiry(InquiryModel model) {
		if(model == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE inquiry SET "
				+ "name = ?, email = ?, comment = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
					sql,
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getId()
				);
	}
	
	/**
	 * 削除
	 * @param id
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int deleteInquiry(InquiryId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM inquiry "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
				sql, 
				id.getId());
	}

	/**
	 * 全選択
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト
	 */
	@Override
	public List<InquiryModel> getAll(boolean isDesc) {
		String sql = "SELECT * FROM inquiry "
					+ "order by id ";
		sql +=  (isDesc ? "desc" : "asc");
		
		List<InquiryModel> list = new ArrayList<InquiryModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for(Map<String, Object> result : resultList) {
				InquiryModel model = this.makeInquiryModel(result);
				if(model == null)	continue;
				
				list.add(model);
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}
	
	/**
	 * 全て選択(問い合わせ返信モデルリストつき)
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト
	 */
	@Override
	public List<InquiryModel> getAll_Plus(boolean isDesc){
		String sql = "SELECT inquiry.*,"
				+ "inquiry_reply.id AS reply_id,"
				+ "inquiry_reply.name AS reply_name,"
				+ "inquiry_reply.email AS reply_email,"
				+ "inquiry_reply.comment AS reply_comment,"
				+ "inquiry_reply.created AS reply_created "
				+ "FROM inquiry "
				+ "LEFT OUTER JOIN inquiry_reply ON "
				+ "inquiry.id = inquiry_reply.inquiry_id "
				+ "order by id ";
		sql +=  (isDesc ? "desc" : "asc");
		List<InquiryModel> list = new ArrayList<InquiryModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for(int idx=0, len=resultList.size(); idx<len; idx++) {
				// 問い合わせモデルの作成
				Map<String, Object> result   = resultList.get(idx);
				InquiryModel model           = this.makeInquiryModel(result);
				if(model == null)		continue;
				list.add(model);
				
				// 問い合わせ返信モデルの作成
				InquiryReplyModel modelReply = this.makeInquiryReplyModel(result);
				if(modelReply == null)	continue;
				
				// 問い合わせ返信モデルリストを作成していく
				List<InquiryReplyModel> replyList = model.getReplyList();
				replyList.add(modelReply);
				int skipCnt = this.setInquiryReplyList(resultList, idx, model, isDesc);
				idx += skipCnt;
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}

	/**
	 * 選択(ID)
	 * @param id
	 * @return 問い合わせモデル
	 */
	@Override
	public InquiryModel select(InquiryId id) {
		if(id == null)	return null;
		
		InquiryModel model = null;
		String sql = "SELECT * "
				+ "FROM inquiry "
				+ "WHERE id = ?";
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(sql, id.getId());
			if(result == null) return null;
			
			model = this.makeInquiryModel(result);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}
	
	// ----------------------------------------------------------------------------------------
	
	/**
	 * 問い合わせ返信モデルリスト作成
	 * @param resultList
	 * @param idx
	 * @param model
	 * @param isDesc false 昇順 true 降順
	 * @return 追加した回数
	 */
	private int setInquiryReplyList(
			List<Map<String, Object>> resultList,
			int                       idx,
			InquiryModel              model,
			boolean                   isDesc) {
		List<InquiryReplyModel> replyList = model.getReplyList();
		int inquiryId                     = model.getId();
		int skipCnt                       = 0;
		
		for(int replyIdx=1, len=resultList.size(); idx+replyIdx<len; replyIdx++) {
			Map<String, Object> resultReply = resultList.get(idx+replyIdx);
			int replyId                     = (int)resultReply.get(WebConsts.SQL_ID_NAME);
			
			// 違う問い合わせIDだったら終了
			if( inquiryId != replyId )	break;
			
			InquiryReplyModel modelSameReply = this.makeInquiryReplyModel(resultReply);
			// モデルがなかったら終了
			if(modelSameReply == null)	break;
			// モデル追加カウント
			replyList.add(modelSameReply);
			skipCnt++;
		}
		
		if(isDesc) {
			replyList.sort((a,b) -> b.getId() - a.getId());
		}
		
		return skipCnt;
	}
	
	
	/**
	 * モデル生成
	 * @param  result
	 * @return 問い合わせモデル
	 */
	private InquiryModel makeInquiryModel(Map<String, Object> result) {
		if(result == null)	return null;
		InquiryModel model = null;
		
		try {
			model = new InquiryModel(
					new InquiryId((int)result.get(
							WebConsts.SQL_ID_NAME)),
					new NameWord((String)result.get(
							WebConsts.SQL_NAME_NAME)),
					new EmailWord((String)result.get(
							WebConsts.SQL_EMAIL_NAME)),
					new CommentWord((String)result.get(
							WebConsts.SQL_COMMENT_NAME)),
					((Timestamp)result.get(
							WebConsts.SQL_CREATED_NAME)).toLocalDateTime()
					);
		} catch(NullPointerException ex) {
			model = null;
		}
		
		return model;
	}
	
	/**
	 * 返信モデル生成
	 * @param  result
	 * @return 問い合わせ返信モデル
	 */
	private InquiryReplyModel makeInquiryReplyModel(Map<String, Object> result) {
		if(result == null)	return null;
		InquiryReplyModel model = null;
		
		try {
			model = new InquiryReplyModel(
					new InquiryReplyId((int)result.get(
							WebConsts.SQL_REPLY_ID_NAME)),
					new InquiryId((int)result.get(
							WebConsts.SQL_ID_NAME)),
					new NameWord((String)result.get(
							WebConsts.SQL_REPLY_NAME_NAME)),
					new EmailWord((String)result.get(
							WebConsts.SQL_REPLY_EMAIL_NAME)),
					new CommentWord((String)result.get(
							WebConsts.SQL_REPLY_COMMENT_NAME)),
					((Timestamp)result.get(
							WebConsts.SQL_REPLY_CREATED_NAME)).toLocalDateTime()
					);
		} catch(NullPointerException ex) {
			model = null;
		}
		
		return model;
	}
}
