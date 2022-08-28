package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;
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
	 * @return 問い合わせモデルリスト
	 */
	@Override
	public List<InquiryModel> getAll() {
		String sql = "SELECT * FROM inquiry";
		List<InquiryModel> list = new ArrayList<InquiryModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for(Map<String, Object> result : resultList) {
				InquiryModel model = this.makeModel(result);
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
			
			model = this.makeModel(result);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}
	
	/**
	 * モデル生成
	 * @param  result
	 * @return 問い合わせモデル
	 */
	private InquiryModel makeModel(Map<String, Object> result) {
		if(result == null)	return null;
		
		InquiryModel model = new InquiryModel(
				new InquiryId((int)result.get(WebConsts.SQL_ID_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_NAME_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_EMAIL_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_COMMENT_NAME)),
				((Timestamp)result.get(WebConsts.SQL_CREATED_NAME)).toLocalDateTime()
				);
		
		return model;
	}
}
