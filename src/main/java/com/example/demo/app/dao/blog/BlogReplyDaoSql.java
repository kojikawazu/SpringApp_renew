package com.example.demo.app.dao.blog;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.dao.SuperDao;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信Daoクラス
 * <br>
 * implements {@link SuperDao}<{@link BlogReplyModel}, {@link BlogReplyId}><br>
 *            {@link BlogReplyDao}
 * @author nanai
 *
 */
@Repository
@Transactional(readOnly = true)
public class BlogReplyDaoSql implements SuperDao<BlogReplyModel, BlogReplyId>, BlogReplyDao {

	/** DB名 */
	private final String DB_NAME = "blog_reply";

	/** パラム */
	private final String PARAM_REPLY_ID				= "id";
	private final String PARAM_REPLY_BLOG_ID		= "blog_id";
	private final String PARAM_REPLY_NAME			= "name";
	private final String PARAM_REPLY_COMMENT		= "comment";
	private final String PARAM_REPLY_THANTKSCNT		= "thankscnt";
	private final String PARAM_REPLY_CREATED		= "created";

	/** 
	 * Jdbcドライバー 
	 * {@link JdbcTemplate}
	 */
	private JdbcTemplate jdbcTemp;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param jdbcTemp {@link JdbcTemplate}
	 */
	@Autowired
	public BlogReplyDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp 	= jdbcTemp;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 追加
	 * @param モデル {@link BlogReplyModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insert(BlogReplyModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql =  WebConsts.SQL_INSERT + " " + DB_NAME
				+ "("
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED
				+ ") "
				+ "VALUES(?,?,?,?,?)";

		synchronized (BlogReplyDaoSql.class) {
			this.jdbcTemp.update(
				sql,
				model.getBlogId(),
				model.getName(),
				model.getComment(),
				model.getThanksCnt(),
				model.getCreated()
				);
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 * 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delete(BlogReplyId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
					+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";

		synchronized (BlogReplyDaoSql.class) {
			result = this.jdbcTemp.update(
					sql, 
					id.getId());
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ブログIDによる削除
	 * @param blogid {@link BlogId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delete_byBlogId(BlogId blogid) {
		if (blogid == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
					+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_BLOG_ID + " = ?";

		synchronized (BlogReplyDaoSql.class) {
			result = this.jdbcTemp.update(
					sql,
					blogid.getId());
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 全て選択
	 * @return ブログ返信モデルリスト {@link List}<{@link BlogReplyModel}>
	 */
	@Override
	public List<BlogReplyModel> getAll() {
		String sql = WebConsts.SQL_SELECT + " "
					+ PARAM_REPLY_ID 			+ ", "
					+ PARAM_REPLY_BLOG_ID 		+ ", "
					+ PARAM_REPLY_NAME 			+ ", "
					+ PARAM_REPLY_COMMENT 		+ ", "
					+ PARAM_REPLY_THANTKSCNT 	+ ", "
					+ PARAM_REPLY_CREATED 		+ " "
					+ WebConsts.SQL_FROM + " " + DB_NAME;
		List<BlogReplyModel> list = new ArrayList<BlogReplyModel>();

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			if (resultList == null)	return list;

			for (Map<String, Object> result : resultList) {
				BlogReplyModel model = this.makeModel(result);
				if(model == null)	continue;
				list.add(model);
			}
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ブログIDによる選択
	 * @param  blogid {@link BlogId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return ブログ返信モデルリスト {@link List}<{@link BlogReplyModel}>
	 */
	@Override
	public List<BlogReplyModel> select_byBlogId(BlogId blogid) {
		if (blogid == null) throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_REPLY_ID 			+ ", "
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED 		+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_BLOG_ID + " = ?";
		List<BlogReplyModel> list = new ArrayList<BlogReplyModel>();

		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql, blogid.getId());
			if (resultList == null)	return list;

			for (Map<String, Object> result : resultList) {
				BlogReplyModel model = this.makeModel(result);
				if(model == null)	continue;
				list.add(model);
			}
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ブログ返信IDによる選択
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return ブログ返信モデル {@link BlogReplyModel}
	 */
	@Override
	public BlogReplyModel select(BlogReplyId id) {
		if (id == null) throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_REPLY_ID 			+ ", "
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED 		+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";
		BlogReplyModel model = null;

		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(sql, id.getId());
			if(result == null) return null;
			model = this.makeModel(result);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * いいね数加算
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return いいね数
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int thanksIncrement(BlogReplyId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int thanksCnter = 0;
		int ret = 0;

		// select文
		String sql = WebConsts.SQL_SELECT 	+ " "
				+ PARAM_REPLY_THANTKSCNT 	+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";
		// update文
		String sql_update = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_REPLY_THANTKSCNT + " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";

		try {
			// いいね数取得
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if(result == null)	return WebConsts.ERROR_NUMBER; 

			// 加算
			thanksCnter = (int)result.get(PARAM_REPLY_THANTKSCNT);
			thanksCnter++;

			// いいね数更新
			ret = this.jdbcTemp.update(
					sql_update,
					thanksCnter,
					id.getId());
			if (ret <= WebConsts.ERROR_DB_STATUS) thanksCnter = WebConsts.ERROR_NUMBER;
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			thanksCnter = WebConsts.ERROR_NUMBER;
		}

		return thanksCnter;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * モデル生成
	 * @param  result マップ {@link Map}<{@link String}, {@link Object}>
	 * @return ブログ返信モデル {@link BlogReplyModel}
	 */
	private BlogReplyModel makeModel(Map<String, Object> result) {
		BlogReplyModel model = null;
		if(result == null)	return model;

		try {
			model = new BlogReplyModel(
				new BlogReplyId((int)result.get(PARAM_REPLY_ID)),
				new BlogId((int)result.get(PARAM_REPLY_BLOG_ID)),
				new NameWord((String)result.get(PARAM_REPLY_NAME)),
				new CommentWord((String)result.get(PARAM_REPLY_COMMENT)),
				new ThanksCntNumber((int)result.get(PARAM_REPLY_THANTKSCNT)),
				((Timestamp)result.get(PARAM_REPLY_CREATED))
					.toLocalDateTime()
				);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	/** ------------------------------------------------------------------------------------- */

	@Override
	public int update(BlogReplyModel model) {
		return 0;
	}

	@Override
	public boolean isSelect_byId(int targetID) {
		return false;
	}
}
