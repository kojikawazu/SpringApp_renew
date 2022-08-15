package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.BlogMainModel;

@Repository
public class BlogMainDaoSql implements BlogMainDao {

	private JdbcTemplate jdbcTemp;
	
	@Autowired
	public BlogMainDaoSql(JdbcTemplate jdbcTemp) {
		// TODO コンストラクタ
		this.jdbcTemp = jdbcTemp;
	}
	
	
	@Override
	public void insertBlog(BlogMainModel model) {
		// TODO 追加
		jdbcTemp.update("INSERT INTO blog_main(title, tag, comment, thanksCnt, created, updated) VALUES(?,?,?,?,?,?)",
					model.getTitle(),
					model.getTag(),
					model.getComment(),
					model.getThanksCnt(),
					model.getCreated(),
					model.getUpdated()
					);
	}

	@Override
	public int updateBlog(BlogMainModel model) {
		// TODO 更新
		return jdbcTemp.update("UPDATE blog_main SET title = ?, tag = ?, comment = ?, thanksCnt = ?, updated = ? WHERE id = ?",
					model.getTitle(),
					model.getTag(),
					model.getComment(),
					model.getThanksCnt(),
					model.getUpdated(),
					model.getId()
				);
	}

	@Override
	public int deleteBlog(int id) {
		// TODO 削除
		return jdbcTemp.update("DELETE FROM blog_main WHERE id = ?", id);
	}

	@Override
	public List<BlogMainModel> getAll() {
		// TODO データ全取得
		String sql = "SELECT id, title, tag, comment, thanksCnt, created, updated FROM blog_main";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		for( Map<String, Object> result : resultList ) {
			BlogMainModel model = new BlogMainModel();
			model.setId((int)result.get("id"));
			model.setTitle((String)result.get("title"));
			model.setTag((String)result.get("tag"));
			model.setComment((String)result.get("comment"));
			model.setThanksCnt((int)result.get("thanksCnt"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			model.setUpdated(((Timestamp)result.get("updated")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}


	@Override
	public BlogMainModel select(int id) {
		// TODO IDによるデータ取得
		String sql = "SELECT id, title, tag, comment, thanksCnt, created, updated FROM blog_main WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
		
		BlogMainModel model = null;
		if(result != null) {
			model = new BlogMainModel();
			model.setId((int)result.get("id"));
			model.setTitle((String)result.get("title"));
			model.setTag((String)result.get("tag"));
			model.setComment((String)result.get("comment"));
			model.setThanksCnt((int)result.get("thanksCnt"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			model.setUpdated(((Timestamp)result.get("updated")).toLocalDateTime());
		}
		return model;
	}
	
	@Override
	public List<BlogMainModel> select_byTag(String tag) {
		// TODO IDによるデータ取得
		String sql = "SELECT id, title, tag, comment, thanksCnt, created, updated FROM blog_main WHERE tag = ?";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql, tag);
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		for( Map<String, Object> result : resultList ) {
			BlogMainModel model = new BlogMainModel();
			model.setId((int)result.get("id"));
			model.setTitle((String)result.get("title"));
			model.setTag((String)result.get("tag"));
			model.setComment((String)result.get("comment"));
			model.setThanksCnt((int)result.get("thanksCnt"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			model.setUpdated(((Timestamp)result.get("updated")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}

	@Override
	public int thanksIncrement(int id) {
		// TODO いいね数加算
		String sql = "SELECT thanksCnt FROM blog_main WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
		
		if( result == null) {	return -1; }
		int thanksCnter = (int)result.get("thanksCnt");
		thanksCnter++;
		
		jdbcTemp.update("UPDATE blog_main SET thanksCnt = ? WHERE id = ?",
				thanksCnter,
				id);
		return thanksCnter;
	}




}
