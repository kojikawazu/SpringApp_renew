package com.example.demo.app.dao.blog;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.id.blog.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信Daoクラス
 * @author nanai
 *
 */
@Repository
public class BlogReplyDaoSql implements BlogReplyDao {
	
	/** jdbcドライバー */
	private JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	@Autowired
	public BlogReplyDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	/**
	 * 追加
	 * @param モデル
	 */
	@Override
	public void insertReply(BlogReplyModel model) {
		if(model == null)	return ;
		String sql = "INSERT INTO blog_reply("
				+ "blog_id, name, comment, thanksCnt, created) "
				+ "VALUES(?,?,?,?,?)";
		
		try {
			this.jdbcTemp.update(sql,
					model.getBlogId(),
					model.getName(),
					model.getComment(),
					model.getThanksCnt(),
					model.getCreated()
					);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 削除
	 * @param id
	 * @return 0以下 失敗 それ以外 成功
	 * 
	 */
	@Override
	public int deleteReply(BlogReplyId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM blog_reply "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
				sql, 
				id.getId());
	}
	
	/**
	 * ブログIDによる削除
	 * @param blogid
	 * @return
	 */
	@Override
	public int deleteReply_byBlog(BlogId blogid) {
		if(blogid == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM blog_reply "
				+ "WHERE blog_id = ?";
		
		return this.jdbcTemp.update(
				sql,
				blogid.getId());
	}

	/**
	 * 全て選択
	 * @return ブログ返信モデルリスト
	 */
	@Override
	public List<BlogReplyModel> getAll() {
		String sql = "SELECT * FROM blog_reply";
		List<BlogReplyModel> list = new ArrayList<BlogReplyModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for( Map<String, Object> result : resultList ) {
				BlogReplyModel model = this.makeModel(result);
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
	 * ブログIDによる選択
	 * @param  blogid
	 * @return ブログ返信モデルリスト
	 */
	@Override
	public List<BlogReplyModel> select_blogId(BlogId blogid) {
		List<BlogReplyModel> list = new ArrayList<BlogReplyModel>();
		if(blogid == null)	return list;
		
		String sql = "SELECT * "
				+ "FROM blog_reply "
				+ "WHERE blog_id = ?";
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql, blogid.getId());
			
			for( Map<String, Object> result : resultList ) {
				BlogReplyModel model = this.makeModel(result);
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
	 * ブログ返信IDによる選択
	 * @param  id
	 * @return ブログ返信モデル
	 */
	@Override
	public BlogReplyModel select(BlogReplyId id) {
		if(id == null)	return null;
		
		String sql = "SELECT * "
				+ "FROM blog_reply "
				+ "WHERE id = ?";
		BlogReplyModel model = null;
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
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	@Override
	public int thanksIncrement(BlogReplyId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		int thanksCnter = 0;
		int ret = 0;
		
		String sql = "SELECT thanksCnt "
				+ "FROM blog_reply "
				+ "WHERE id = ?";
		
		String sql_update = "UPDATE blog_reply "
				+ "SET thanksCnt = ? "
				+ "WHERE id = ?"
				;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if(result == null)	return WebConsts.ERROR_NUMBER; 
			
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
	 * @return ブログ返信モデル
	 */
	private BlogReplyModel makeModel(Map<String, Object> result) {
		if(result == null)	return null;
		
		BlogReplyModel model = new BlogReplyModel(
				new BlogReplyId((int)result.get(WebConsts.SQL_ID_NAME)),
				new BlogId((int)result.get(WebConsts.SQL_BLOG_ID_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_NAME_NAME)),
				new CommentWord((String)result.get(WebConsts.SQL_COMMENT_NAME)),
				new ThanksCntNumber((int)result.get(WebConsts.SQL_THANKSCNT_NAME)),
				((Timestamp)result.get(WebConsts.SQL_CREATED_NAME))
					.toLocalDateTime()
				);
		
		return model;
	}

}
