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

	/** Jdbcドライバー */
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
		String sql = "INSERT INTO blog_main("
				+ "title, tag, comment, thanksCnt, created, updated) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			this.jdbcTemp.update(
					sql,
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
		String sql = "UPDATE blog_main SET "
				+ "title = ?, tag = ?, comment = ?, thanksCnt = ?, updated = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(sql,
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
		String sql = "DELETE FROM blog_main "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(sql, 
				id.getId());
	}
	
	/**
	 * 全て選択
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> getAll() {
		String sql = "SELECT * FROM blog_main";
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for( Map<String, Object> result : resultList ) {
				BlogMainModel model = this.makeModel(result);
				if(model == null) continue;
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
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, id.getId());
			if(result == null) return null;
			
			model = this.makeModel(result);
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
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql, tag);
			for( Map<String, Object> result : resultList ) {
				BlogMainModel model = this.makeModel(result);
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
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	@Override
	public int thanksIncrement(BlogId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		int thanksCnter = 0;
		int ret = 0;
		
		String sql = "SELECT thanksCnt "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		
		String sql_update = "UPDATE blog_main SET "
				+ "thanksCnt = ? "
				+ "WHERE id = ?";
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if(result == null) return WebConsts.ERROR_NUMBER;
			
			// いいね加算
			thanksCnter = (int)result.get(WebConsts.SQL_THANKSCNT_NAME);
			thanksCnter++;
			
			ret = this.jdbcTemp.update(
					sql_update,
					thanksCnter,
					id.getId());
			
			if(ret <= WebConsts.ERROR_DB_STATUS) {
				thanksCnter = WebConsts.ERROR_NUMBER;
			}
			
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			thanksCnter = WebConsts.ERROR_NUMBER;
		}
		
		return thanksCnter;
	}
	
	/**
	 * モデル生成
	 * @param  result マップ
	 * @return ブログメインモデル
	 */
	private BlogMainModel makeModel(Map<String, Object> result) {
		if(result == null)	return null;
		
		BlogMainModel model = new BlogMainModel(
				new BlogId((int)result.get(WebConsts.SQL_ID_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_TITLE_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_TAG_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_COMMENT_NAME)),
				new ThanksCntNumber((int)result.get(WebConsts.SQL_THANKSCNT_NAME)),
				((Timestamp)result.get(WebConsts.SQL_CREATED_NAME)).toLocalDateTime(),
				((Timestamp)result.get(WebConsts.SQL_UPDATED_NAME)).toLocalDateTime()
				);
		
		return model;
	}
}
