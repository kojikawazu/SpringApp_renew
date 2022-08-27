package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログメインDaoクラス
 * @author nanai
 *
 */
@Repository
public class BlogMainDaoSql implements BlogMainDao {

	/**
	 * Jdbcドライバー
	 */
	private JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	@Autowired
	public BlogMainDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	/**
	 * 追加
	 * @param model ブログメインモデル
	 */
	@Override
	public void insertBlog(BlogMainModel model) {
		if(model == null)	return ;
		
		try {
			jdbcTemp.update("INSERT INTO blog_main("
				+ "title, tag, comment, thanksCnt, created, updated) "
				+ "VALUES(?,?,?,?,?,?)",
					model.getTitle(),
					model.getTag(),
					model.getComment(),
					model.getThanksCnt(),
					model.getCreated(),
					model.getUpdated()
					);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 更新
	 * @param  ブログメインモデル
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int updateBlog(BlogMainModel model) {
		if(model == null)	return WebConsts.ERROR_NUMBER;
		
		return jdbcTemp.update("UPDATE blog_main SET "
				+ "title = ?, tag = ?, comment = ?, thanksCnt = ?, updated = ? "
				+ "WHERE id = ?",
					model.getTitle(),
					model.getTag(),
					model.getComment(),
					model.getThanksCnt(),
					model.getUpdated(),
					model.getId()
				);
	}

	/**
	 * 削除
	 * @param  id
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int deleteBlog(BlogId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		
		return jdbcTemp.update("DELETE FROM blog_main "
				+ "WHERE id = ?", 
				id.getId());
	}

	/**
	 * 全て選択
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> getAll() {
		String sql = "SELECT * "
				+ "FROM blog_main";
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		try {
			List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
			
			for( Map<String, Object> result : resultList ) {
				BlogMainModel model = new BlogMainModel(
						new BlogId((int)result.get("id")),
						new NameWord((String)result.get("title")),
						new NameWord((String)result.get("tag")),
						new NameWord((String)result.get("comment")),
						new ThanksCntNumber((int)result.get("thanksCnt")),
						((Timestamp)result.get("created")).toLocalDateTime(),
						((Timestamp)result.get("updated")).toLocalDateTime()
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
	 * IDによるデータ取得
	 * @param  id
	 * @return ブログメインモデル
	 */
	@Override
	public BlogMainModel select(BlogId id) {
		if(id == null) return null;
		
		BlogMainModel model = null;
		String sql = "SELECT * "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		
		try {
			Map<String, Object> result = jdbcTemp.queryForMap(sql, id.getId());
			
			if(result != null) {
				model = new BlogMainModel(
						new BlogId((int)result.get("id")),
						new NameWord((String)result.get("title")),
						new NameWord((String)result.get("tag")),
						new NameWord((String)result.get("comment")),
						new ThanksCntNumber((int)result.get("thanksCnt")),
						((Timestamp)result.get("created")).toLocalDateTime(),
						((Timestamp)result.get("updated")).toLocalDateTime()
						);
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}
	
	/**
	 * タグによる選択
	 * @param  tag タグ
	 * @return ブログモデルクラスリスト
	 */
	@Override
	public List<BlogMainModel> select_byTag(String tag) {
		String sql = "SELECT * "
				+ "FROM blog_main "
				+ "WHERE tag = ?";
		
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		try {
			List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql, tag);
			for( Map<String, Object> result : resultList ) {
				BlogMainModel model = new BlogMainModel(
						new BlogId((int)result.get("id")),
						new NameWord((String)result.get("title")),
						new NameWord((String)result.get("tag")),
						new NameWord((String)result.get("comment")),
						new ThanksCntNumber((int)result.get("thanksCnt")),
						((Timestamp)result.get("created")).toLocalDateTime(),
						((Timestamp)result.get("updated")).toLocalDateTime()
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
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	@Override
	public int thanksIncrement(BlogId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "SELECT thanksCnt "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		int thanksCnter = 0;
		
		try {
			Map<String, Object> result = jdbcTemp.queryForMap(sql, id.getId());
			if(result == null) return WebConsts.ERROR_NUMBER;
			
			// いいね加算
			thanksCnter = (int)result.get("thanksCnt");
			thanksCnter++;
			
			jdbcTemp.update("UPDATE blog_main SET "
					+ "thanksCnt = ? "
					+ "WHERE id = ?",
					thanksCnter,
					id.getId());
			
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			thanksCnter = WebConsts.ERROR_NUMBER;
		}
		
		return thanksCnter;
	}
}
