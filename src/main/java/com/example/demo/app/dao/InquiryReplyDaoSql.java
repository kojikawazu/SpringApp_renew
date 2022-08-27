package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.id.InquiryReplyId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信Daoクラス
 * @author nanai
 *
 */
@Repository
public class InquiryReplyDaoSql implements InquiryReplyDao {
	
	/** jdbcドライバー */
	private final JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	@Autowired
	public InquiryReplyDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	/**
	 * 追加
	 * @param model
	 */
	@Override
	public void insertReplyInquiry(InquiryReplyModel model) {
		if(model == null)	return ;
		String sql = "INSERT INTO inquiry_reply("
				+ "inquiry_id, name, email, comment, created) "
				+ "VALUES(?,?,?,?,?)";
		
		try {
			this.jdbcTemp.update(sql,
						model.getInquiry_id(),
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
	 * @param
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int updateReplyInquiry(InquiryReplyModel model) {
		if(model == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE inquiry_reply SET "
				+ "inquiry_id = ?, name = ?, email = ?, comment = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(sql,
					model.getInquiry_id(),
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getId()
				);
	}

	/**
	 * 削除(問い合わせ返信ID)
	 * @param id
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int deleteReplyInquiry(InquiryReplyId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM inquiry_reply "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(sql, id.getId());
	}
	
	/**
	 * 削除(問い合わせID)
	 * @param inquiryId
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int deleteReply_byInquiry(InquiryId inquiryId) {
		if(inquiryId == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM inquiry_reply "
				+ "WHERE inquiry_id = ?";
		
		return this.jdbcTemp.update(sql, inquiryId.getId());
	}

	/**
	 * 全選択
	 * @return 問い合わせ返信モデルリスト
	 */
	@Override
	public List<InquiryReplyModel> getAll() {
		String sql = "SELECT * FROM inquiry_reply";
		List<InquiryReplyModel> list = new ArrayList<InquiryReplyModel>();
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for(Map<String, Object> result : resultList) {
				InquiryReplyModel model = new InquiryReplyModel(
						new InquiryReplyId((int)result.get("id")),
						new InquiryId((int)result.get("inquiry_id")),
						new NameWord((String)result.get("name")),
						new EmailWord((String)result.get("email")),
						new CommentWord((String)result.get("comment")),
						((Timestamp)result.get("created")).toLocalDateTime()
						);
				list.add(model);
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}
	
	/**
	 * 問い合わせIDによる選択
	 * @param 問い合わせID
	 * @return 問い合わせ返信モデルリスト
	 */
	@Override
	public List<InquiryReplyModel> select_byInquiryId(InquiryId inquiryId) {
		String sql = "SELECT * FROM inquiry_reply "
				+ "WHERE inquiry_id = ?";
		List<InquiryReplyModel> list = new ArrayList<InquiryReplyModel>();
		
		try {
			List<Map<String, Object>> resultList = 
					this.jdbcTemp.queryForList(sql, inquiryId.getId());
			
			for(Map<String, Object> result : resultList) {
				InquiryReplyModel model = new InquiryReplyModel(
						new InquiryReplyId((int)result.get("id")),
						new InquiryId((int)result.get("inquiry_id")),
						new NameWord((String)result.get("name")),
						new EmailWord((String)result.get("email")),
						new CommentWord((String)result.get("comment")),
						((Timestamp)result.get("created")).toLocalDateTime()
						);
				list.add(model);
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}

	/**
	 * IDによる選択
	 * @param 問い合わせ返信ID
	 * @return 問い合わせ返信モデル
	 */
	@Override
	public InquiryReplyModel select(InquiryReplyId id) {
		String sql = "SELECT * FROM inquiry_reply "
				+ "WHERE id = ?";
		InquiryReplyModel model = null;
		
		try {
			Map<String, Object> result = jdbcTemp.queryForMap(sql, id.getId());
			if(result == null)	return null;
			
			model = new InquiryReplyModel(
					new InquiryReplyId((int)result.get("id")),
					new InquiryId((int)result.get("inquiry_id")),
					new NameWord((String)result.get("name")),
					new EmailWord((String)result.get("email")),
					new CommentWord((String)result.get("comment")),
					((Timestamp)result.get("created")).toLocalDateTime()
					);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}

}
