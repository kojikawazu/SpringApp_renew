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
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログメインDaoクラス
 * <br>
 * implements {@link BlogMainDao}
 * @author nanai
 *
 */
@Repository
@Transactional(readOnly = true)
public class BlogMainDaoSql implements SuperDao<BlogMainModel, BlogId>, BlogMainDao {

	/** DB名 */
	private final String DB_NAME = "blog_main";

	/** DB名[blog_reply] */
	private final String DB_BLOG_REPLY_NAME = "blog_reply";

	/**
	 * パラム名
	 */
	private final String PARAM_ID 			= "id";
	private final String PARAM_TITLE 		= "title";
	private final String PARAM_TAG			= "tag";
	private final String PARAM_COMMENT		= "comment";
	private final String PARAM_THANKSCNT	= "thanksCnt";
	private final String PARAM_CREATED		= "created";
	private final String PARAM_UPDATED		= "updated";

	private final String PARAM_REPLY_ID				= "id";
	private final String PARAM_REPLY_BLOG_ID		= "blog_id";
	private final String PARAM_REPLY_NAME			= "name";
	private final String PARAM_REPLY_COMMENT		= "comment";
	private final String PARAM_REPLY_THANTKSCNT		= "thanksCnt";
	private final String PARAM_REPLY_CREATED		= "created";

	private final String PARAM_AS_REPLY_ID			= "reply_id";
	private final String PARAM_AS_REPLY_NAME		= "reply_name";
	private final String PARAM_AS_REPLY_COMMENT		= "reply_comment";
	private final String PARAM_AS_REPLY_THANKSCNT	= "reply_thanksCnt";
	private final String PARAM_AS_REPLY_CREATED		= "reply_created";

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
	public BlogMainDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp 	= jdbcTemp;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 追加
	 * @param model ブログメインモデル {@link BlogMainModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insert(BlogMainModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql =  WebConsts.SQL_INSERT + " " + DB_NAME
				+ "("
				+ PARAM_TITLE 		+ ", "
				+ PARAM_TAG 		+ ", "
				+ PARAM_COMMENT 	+ ", "
				+ PARAM_THANKSCNT 	+ ", "
				+ PARAM_CREATED 	+ ", "
				+ PARAM_UPDATED
				+ ") "
				+ "VALUES(?,?,?,?,?,?)";

		synchronized (BlogMainDaoSql.class) {
			this.jdbcTemp.update(
				sql,
				model.getTitle(),
				model.getTag(),
				model.getComment(),
				model.getThanksCnt(),
				model.getCreated(),
				model.getUpdated()
				);
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param  ブログメインモデル {@link BlogMainModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int update(BlogMainModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_TITLE 		+ " = ?, "
				+ PARAM_TAG 		+ " = ?, "
				+ PARAM_COMMENT 	+ " = ?, "
				+ PARAM_THANKSCNT 	+ " = ?, "
				+ PARAM_UPDATED 	+ " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		synchronized (BlogMainDaoSql.class) {
			result = this.jdbcTemp.update(
					sql,
					model.getTitle(),
					model.getTag(),
					model.getComment(),
					model.getThanksCnt(),
					model.getUpdated(),
					model.getId()
				);
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delete(BlogId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		synchronized (BlogMainDaoSql.class) {
			result = this.jdbcTemp.update(
					sql, 
					id.getId()
				);
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 全て選択
	 * @return ブログメインモデルリスト {@link List}<{@link BlogMainModel}>
	 */
	@Override
	public List<BlogMainModel> getAll() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_ID + ", "
				+ PARAM_TITLE + ", "
				+ PARAM_TAG + ", "
				+ PARAM_COMMENT + ", "
				+ PARAM_THANKSCNT + ", "
				+ PARAM_CREATED + ", "
				+ PARAM_UPDATED + " " 
				+ WebConsts.SQL_FROM + " " + DB_NAME;
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			list = this.setBlogMainModelList(resultList);
			resultList.clear();
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/**
	 * 全て選択(ブログ返信リストつき)
	 * @param  isDesc
	 * @return ブログメインモデルリスト {@link List}<{@link BlogMainModel}>
	 */
	@Override
	public List<BlogMainModel> getAll_Plus(boolean isDesc) {
		String sql = WebConsts.SQL_SELECT + " "
				+ DB_NAME + "." + PARAM_ID + ","
				+ DB_NAME + "." + PARAM_TITLE + ","
				+ DB_NAME + "." + PARAM_TAG + ","
				+ DB_NAME + "." + PARAM_COMMENT + ","
				+ DB_NAME + "." + PARAM_THANKSCNT + ","
				+ DB_NAME + "." + PARAM_CREATED + ","
				+ DB_NAME + "." + PARAM_UPDATED + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_ID         + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_ID + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_NAME       + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_NAME + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_COMMENT    + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_COMMENT + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_THANTKSCNT + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_THANKSCNT + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_CREATED    + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_CREATED + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME + " "
				+ WebConsts.SQL_LEFT_OUTER_JOIN + " " + DB_BLOG_REPLY_NAME + " " + WebConsts.SQL_ON + " "
				+ DB_NAME + "." + PARAM_ID + " = " + DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_BLOG_ID + " "
				+ WebConsts.SQL_ORDER_BY + " " + PARAM_ID + " ";
		sql += (isDesc ? WebConsts.SQL_DESC : WebConsts.SQL_ASC);
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			list = this.setBlogMainModelListPlus(resultList, isDesc);
			resultList.clear();
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * IDによるデータ取得
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return ブログメインモデル {@link BlogMainModel}
	 */
	@Override
	public BlogMainModel select(BlogId id) {
		if (id == null) throw WebMvcConfig.ARGUMENTS_ERROR();
		BlogMainModel model = null;

		String sql = WebConsts.SQL_SELECT + " "
				+ DB_NAME + "." + PARAM_ID + ","
				+ DB_NAME + "." + PARAM_TITLE + ","
				+ DB_NAME + "." + PARAM_TAG + ","
				+ DB_NAME + "." + PARAM_COMMENT + ","
				+ DB_NAME + "." + PARAM_THANKSCNT + ","
				+ DB_NAME + "." + PARAM_CREATED + ","
				+ DB_NAME + "." + PARAM_UPDATED + " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME  + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if(result == null) return null;

			model = this.makeBlogModel(result);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	/**
	 * タグによる選択
	 * @param  tag タグ {@link String}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return ブログモデルクラスリスト {@link List}<{@link BlogMainModel}>
	 */
	@Override
	public List<BlogMainModel> select_byTag(String tag) {
		if (tag == null) throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_SELECT + " "
				+ DB_NAME + "." + PARAM_ID + ","
				+ DB_NAME + "." + PARAM_TITLE + ","
				+ DB_NAME + "." + PARAM_TAG + ","
				+ DB_NAME + "." + PARAM_COMMENT + ","
				+ DB_NAME + "." + PARAM_THANKSCNT + ","
				+ DB_NAME + "." + PARAM_CREATED + ","
				+ DB_NAME + "." + PARAM_UPDATED + " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME   + " "
				+ WebConsts.SQL_WHERE + " "	+ PARAM_TAG + " = ?";
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql, 
					tag);
			list = this.setBlogMainModelList(resultList);
			resultList.clear();
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param   tag {@link String}
	 * @param  isDesc false 昇順 true 降順
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return  ブログメインモデルリスト {@link List}<{@link BlogMainModel}>
	 */
	@Override
	public List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc) {
		if (tag == null) throw WebMvcConfig.ARGUMENTS_ERROR();
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();

		String sql = WebConsts.SQL_SELECT + " "
				+ DB_NAME + "." + PARAM_ID + ","
				+ DB_NAME + "." + PARAM_TITLE + ","
				+ DB_NAME + "." + PARAM_TAG + ","
				+ DB_NAME + "." + PARAM_COMMENT + ","
				+ DB_NAME + "." + PARAM_THANKSCNT + ","
				+ DB_NAME + "." + PARAM_CREATED + ","
				+ DB_NAME + "." + PARAM_UPDATED + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_ID         + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_ID + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_NAME       + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_NAME + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_COMMENT    + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_COMMENT + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_THANTKSCNT + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_THANKSCNT + ","
				+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_CREATED    + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_CREATED + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME + " "
				+ WebConsts.SQL_LEFT_OUTER_JOIN + " " + DB_BLOG_REPLY_NAME + " " + WebConsts.SQL_ON + " "
				+ DB_NAME + "." + PARAM_ID + " = " + DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_BLOG_ID + " "
				+ WebConsts.SQL_WHERE + " "	+ DB_NAME + "." + PARAM_TAG + " = ? "
				+ WebConsts.SQL_ORDER_BY + " " + PARAM_ID + " ";
		sql += (isDesc ? WebConsts.SQL_DESC : WebConsts.SQL_ASC);

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql, 
					tag);
			list = this.setBlogMainModelListPlus(resultList, isDesc);
			resultList.clear();
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/**
	 * いいね数加算
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return いいね数
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int thanksIncrement(BlogId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int thanksCnter = 0;
		int ret = 0;

		// select文
		String sql = WebConsts.SQL_SELECT + " " 
				+ PARAM_THANKSCNT + " "
				+ WebConsts.SQL_FROM + " "  + DB_NAME  + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";
		// update文
		String sql_update = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_THANKSCNT + " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		try {
			// 選択
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if(result == null) 	return WebConsts.ERROR_NUMBER;

			// いいね加算
			thanksCnter = (int)result.get(WebConsts.SQL_THANKSCNT_NAME);
			thanksCnter++;

			// いいね数更新
			synchronized (BlogMainDaoSql.class) {
				ret = this.jdbcTemp.update(
					sql_update,
					thanksCnter,
					id.getId());
			}
			if (ret <= WebConsts.ERROR_DB_STATUS)	return WebConsts.ERROR_NUMBER;
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			thanksCnter = WebConsts.ERROR_NUMBER;
		}

		return thanksCnter;
	}

	// ----------------------------------------------------------------------------------------

	/**
	 * SQL結果からブログメインモデルリストを作成
	 * @param  resultList {@link List}<{@link Map}<{@link String}, {@link Object}>>
	 * @return ブログメインモデルリスト {@link List}<{@link BlogMainModel}>
	 */
	private List<BlogMainModel> setBlogMainModelList(List<Map<String, Object>> resultList) {
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		if (resultList == null)	return list;

		for (Map<String, Object> result : resultList) {
			BlogMainModel model = this.makeBlogModel(result);
			if(model == null)	continue;
			list.add(model);
		}

		return list;
	}

	/**
	 * SQL結果からブログメインモデルを作成する
	 * (ブログ返信モデルつき)
	 * @param  resultList {@link List}<{@link Map}<{@link String}, {@link Object}>>
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}<{@link BlogMainModel}>
	 */
	private List<BlogMainModel> setBlogMainModelListPlus(List<Map<String, Object>> resultList, boolean isDesc) {
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		if (resultList == null)	return list;

		for (int idx=0, len=resultList.size(); idx<len; idx++) {
			// ブログモデルの作成
			Map<String, Object> result    	= resultList.get(idx);
			BlogMainModel       model     	= this.makeBlogModel(result);
			if (model == null)					continue;
			list.add(model);

			// ブログ返信モデル無しの場合はスキップ
			if (!this.isBlogReplyModel(result))	continue;
			// ブログ返信モデルの作成
			BlogReplyModel modelReply      = this.makeBlogReplyModel(result);
			if (modelReply == null)				continue;

			// ブログ返信モデルリストを作成していく
			List<BlogReplyModel> replyList = model.getReplyList();
			replyList.add(modelReply);
			int skipCnt = this.setBlogReplyModelList(resultList, idx, model, isDesc);
			idx += skipCnt;
		}

		return list;
	}

	/**
	 * 返信モデルを追加
	 * @param  resultList {@link List}<{@link Map}<{@link String}, {@link Object}>>
	 * @param  idx ループ番号
	 * @param  model {@link BlogMainModel}
	 * @param  isDesc false 昇順 true 降順
	 * @return 追加した数
	 */
	private int setBlogReplyModelList(
			List<Map<String, Object>> resultList,
			int                       idx,
			BlogMainModel             model,
			boolean                   isDesc) {
		int skipCnt = 0;
		if (resultList == null || model == null)	return skipCnt;

		List<BlogReplyModel> replyList  = model.getReplyList();
		int                  blogMainId = model.getId(); 

		for (int replyIdx=1, len=resultList.size(); idx+replyIdx<len; replyIdx++) {
			Map<String, Object> resultReply = resultList.get(idx+replyIdx);
			int                 blogReplyId = (int)resultReply.get(PARAM_REPLY_ID);

			// 違うブログIDだったら終了
			if (blogMainId != blogReplyId)				break;
			if (!this.isBlogReplyModel(resultReply))	break;
			BlogReplyModel modelSameReply = this.makeBlogReplyModel(resultReply);
			// モデルがなかったら終了
			if (modelSameReply == null)					break;
			// モデル追加カウント
			replyList.add(modelSameReply);
			skipCnt++;
		}

		if (isDesc) {
			replyList.sort((a,b) -> b.getId() - a.getId());
		}

		return skipCnt;
	}

	/**
	 * ブログ返信モデル存在するか?
	 * @param result マップ {@link Map}<{@link String}, {@link Object}>
	 * @return true 存在する false 存在しない
	 */
	private boolean isBlogReplyModel(Map<String, Object> result) {
		boolean exists = false;
		Object obj     = null;
		if (result == null || !result.containsKey(PARAM_AS_REPLY_ID))	return exists;

		obj = result.get(PARAM_AS_REPLY_ID);
		return (obj != null);
	}

	/**
	 * モデル生成
	 * @param  result マップ {@link Map}<{@link String}, {@link Object}>
	 * @return ブログメインモデル {@link BlogMainModel}
	 */
	private BlogMainModel makeBlogModel(Map<String, Object> result) {
		if(result == null)	return null;
		BlogMainModel model = null;

		try {
			model = new BlogMainModel(
				new BlogId((int)result.get(
						PARAM_ID)),
				new TittleWord((String)result.get(
						PARAM_TITLE)),
				new TagWord((String)result.get(
						PARAM_TAG)),
				new CommentWord((String)result.get(
						PARAM_COMMENT)),
				new ThanksCntNumber((int)result.get(
						PARAM_THANKSCNT)),
				((Timestamp)result.get(
						PARAM_CREATED)).toLocalDateTime(),
				((Timestamp)result.get(
						PARAM_UPDATED)).toLocalDateTime()
				);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	/**
	 * 返信モデルの生成
	 * @param  result {@link Map}<{@link String}, {@link Object}>
	 * @return ブログ返信モデル {@link BlogReplyModel}
	 */
	private BlogReplyModel makeBlogReplyModel(Map<String, Object> result) {
		BlogReplyModel model = null;
		if(result == null)	return null;

		try {
			model = new BlogReplyModel(
					new BlogReplyId((int)result.get(
							PARAM_AS_REPLY_ID)),
					new BlogId((int)result.get(
							PARAM_REPLY_ID)),
					new NameWord((String)result.get(
							PARAM_AS_REPLY_NAME)),
					new CommentWord((String)result.get(
							PARAM_AS_REPLY_COMMENT)),
					new ThanksCntNumber((int)result.get(
							PARAM_AS_REPLY_THANKSCNT)),
					((Timestamp)result.get(
							PARAM_AS_REPLY_CREATED)).toLocalDateTime()
					);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	@Override
	public boolean isSelect_byId(int targetID) {
		return false;
	}
}
