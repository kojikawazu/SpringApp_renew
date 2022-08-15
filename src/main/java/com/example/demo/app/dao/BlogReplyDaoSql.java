package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.BlogReplyModel;

@Repository
public class BlogReplyDaoSql implements BlogReplyDao {
	
	private JdbcTemplate jdbcTemp;
	
	@Autowired
	public BlogReplyDaoSql(JdbcTemplate jdbcTemp) {
		// TODO コンストラクタ
		this.jdbcTemp = jdbcTemp;
	}

	@Override
	public void insertReply(BlogReplyModel model) {
		// TODO 追加
		jdbcTemp.update("INSERT INTO blog_reply(commentid, name, comment, thanksCnt, created) VALUES(?,?,?,?,?)",
					model.getCommentid(),
					model.getName(),
					model.getComment(),
					model.getThanksCnt(),
					model.getCreated()
					);
	}

	@Override
	public int deleteReply(int id) {
		// TODO 削除
		return jdbcTemp.update("DELETE FROM blog_reply WHERE id = ?", id);
	}
	
	@Override
	public int deleteReply_byBlog(int blogid) {
		// TODO ブログIDによる削除
		return jdbcTemp.update("DELETE FROM blog_reply WHERE commentid = ?",blogid);
	}

	@Override
	public List<BlogReplyModel> getAll() {
		// TODO 全て選択
		String sql = "SELECT id, commentid, name, comment, thanksCnt, created FROM blog_reply";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
		List<BlogReplyModel> list = new ArrayList<BlogReplyModel>();
		
		for( Map<String, Object> result : resultList ) {
			BlogReplyModel model = new BlogReplyModel();
			model.setId((int)result.get("id"));
			model.setCommentid((int)result.get("commentid"));
			model.setName((String)result.get("name"));
			model.setComment((String)result.get("comment"));
			model.setThanksCnt((int)result.get("thanksCnt"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}
	
	@Override
	public List<BlogReplyModel> select_blogId(int blogid) {
		// TODO ブログIDで返信リストの作成
		String sql = "SELECT id, commentid, name, comment, thanksCnt, created FROM blog_reply WHERE commentid = ?";
		
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql, blogid);
		List<BlogReplyModel> list = new ArrayList<BlogReplyModel>();
		
		for( Map<String, Object> result : resultList ) {
			BlogReplyModel model = new BlogReplyModel();
			model.setId((int)result.get("id"));
			model.setCommentid((int)result.get("commentid"));
			model.setName((String)result.get("name"));
			model.setComment((String)result.get("comment"));
			model.setThanksCnt((int)result.get("thanksCnt"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}

	@Override
	public BlogReplyModel select(int id) {
		// TODO IDで選択
		String sql = "SELECT id, commentid, name, comment, thanksCnt, created FROM blog_reply WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
		
		if(result == null)	return null;
			
		BlogReplyModel model = new BlogReplyModel();
		model.setId((int)result.get("id"));
		model.setCommentid((int)result.get("commentid"));
		model.setName((String)result.get("name"));
		model.setComment((String)result.get("comment"));
		model.setThanksCnt((int)result.get("thanksCnt"));
		model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
		
		return model;
	}

	@Override
	public int thanksIncrement(int id) {
		// TODO いいね数加算
		String sql = "SELECT thanksCnt FROM blog_reply WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
		
		if( result == null) {	return -1; }
		int thanksCnter = (int)result.get("thanksCnt");
		thanksCnter++;
		
		jdbcTemp.update("UPDATE blog_reply SET thanksCnt = ? WHERE id = ?",
				thanksCnter,
				id);
		return thanksCnter;
	}

}
