package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.InquiryModel;

@Repository
public class InquiryDaoSql implements InquiryDao {
	
	private final JdbcTemplate jdbcTemp;
	
	@Autowired
	public InquiryDaoSql(JdbcTemplate jdbcTemp) {
		// TODO コンストラクタ
		this.jdbcTemp = jdbcTemp;
	}

	@Override
	public void insertInquiry(InquiryModel model) {
		// TODO 追加処理
		jdbcTemp.update("INSERT INTO inquiry(name, email, comment, created) VALUES(?,?,?,?)",
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getCreated()
				);
	}

	@Override
	public int updateInquiry(InquiryModel model) {
		// TODO 更新処理
		return jdbcTemp.update("UPDATE inquiry SET name = ?, email = ?, comment = ? WHERE id = ?",
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getId()
				);
	}

	@Override
	public List<InquiryModel> getAll() {
		// TODO 全て選択処理
		String sql = "SELECT id, name, email, comment, created FROM inquiry";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
		List<InquiryModel> list = new ArrayList<InquiryModel>();
		
		for(Map<String, Object> result : resultList) {
			InquiryModel model = new InquiryModel();
			model.setId((int)result.get("id"));
			model.setName((String)result.get("name"));
			model.setEmail((String)result.get("email"));
			model.setComment((String)result.get("comment"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}

	@Override
	public int deleteInquiry(int id) {
		// TODO 削除
		return jdbcTemp.update("DELETE FROM inquiry WHERE id = ?", id);
	}

	@Override
	public InquiryModel select(int id) {
		// TODO IDによるデータ取得
		String sql = "SELECT id, name, email, comment, created FROM inquiry WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
			
		if( result == null) return null;
		InquiryModel model = new InquiryModel();
		model.setId((int)result.get("id"));
		model.setName((String)result.get("name"));
		model.setEmail((String)result.get("email"));
		model.setComment((String)result.get("comment"));
		model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
		
		return model;
	}

}
