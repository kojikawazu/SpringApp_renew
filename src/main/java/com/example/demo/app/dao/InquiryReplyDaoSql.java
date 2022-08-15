package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.InquiryReplyModel;

@Repository
public class InquiryReplyDaoSql implements InquiryReplyDao {
	
	private final JdbcTemplate jdbcTemp;
	
	@Autowired
	public InquiryReplyDaoSql(JdbcTemplate jdbcTemp) {
		// TODO コンストラクタ
		this.jdbcTemp = jdbcTemp;
	}
	
	@Override
	public void insertReplyInquiry(InquiryReplyModel model) {
		// TODO 追加処理
		jdbcTemp.update("INSERT INTO inquiry_reply(inquiry_id, name, email, comment, created) VALUES(?,?,?,?,?)",
					model.getInquiry_id(),
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getCreated()
				);
	}

	@Override
	public int updateReplyInquiry(InquiryReplyModel model) {
		// TODO 更新処理
		return jdbcTemp.update("UPDATE inquiry_reply SET inquiry_id = ?, name = ?, email = ?, comment = ? WHERE id = ?",
					model.getInquiry_id(),
					model.getName(),
					model.getEmail(),
					model.getComment(),
					model.getId()
				);
	}

	@Override
	public int deleteReplyInquiry(int id) {
		// TODO 削除
		return jdbcTemp.update("DELETE FROM inquiry_reply WHERE id = ?", id);
	}
	
	@Override
	public int deleteReply_byInquiry(int inquiryId) {
		// TODO 問合せIDによる削除
		return jdbcTemp.update("DELETE FROM inquiry_reply WHERE inquiry_id = ?", inquiryId);
	}

	@Override
	public List<InquiryReplyModel> getAll() {
		// TODO 全て選択処理
		String sql = "SELECT id, inquiry_id, name, email, comment, created FROM inquiry_reply";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
		List<InquiryReplyModel> list = new ArrayList<InquiryReplyModel>();
		
		for(Map<String, Object> result : resultList) {
			InquiryReplyModel model = new InquiryReplyModel();
			model.setId((int)result.get("id"));
			model.setInquiry_id((int)result.get("inquiry_id"));
			model.setName((String)result.get("name"));
			model.setEmail((String)result.get("email"));
			model.setComment((String)result.get("comment"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}
	
	@Override
	public List<InquiryReplyModel> select_byInquiryId(int inquiryId) {
		// TODO 問合せIDによる返信リストの作成
		String sql = "SELECT id, inquiry_id, name, email, comment, created FROM inquiry_reply WHERE inquiry_id = ?";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql, inquiryId);
		List<InquiryReplyModel> list = new ArrayList<InquiryReplyModel>();
		
		for(Map<String, Object> result : resultList) {
			InquiryReplyModel model = new InquiryReplyModel();
			model.setId((int)result.get("id"));
			model.setInquiry_id((int)result.get("inquiry_id"));
			model.setName((String)result.get("name"));
			model.setEmail((String)result.get("email"));
			model.setComment((String)result.get("comment"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}

	@Override
	public InquiryReplyModel select(int id) {
		// TODO IDによるデータ取得
		String sql = "SELECT id, inquiry_id, name, email, comment, created FROM inquiry_reply WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
		
		if(result == null)	return null;	
		InquiryReplyModel model = new InquiryReplyModel();
		model.setId((int)result.get("id"));
		model.setInquiry_id((int)result.get("inquiry_id"));
		model.setName((String)result.get("name"));
		model.setEmail((String)result.get("email"));
		model.setComment((String)result.get("comment"));
		model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
		
		return model;
	}

}
