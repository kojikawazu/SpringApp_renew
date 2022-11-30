package com.example.demo.app.dao.blog;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

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
		
		return this.jdbcTemp.update(
					sql,
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
		
		return this.jdbcTemp.update(
				sql, 
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
			
			list = this.setBlogMainModelList(resultList);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}
	
	/**
	 * 全て選択(ブログ返信リストつき)
	 * @param  isDesc
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> getAll_Plus(boolean isDesc) {
		String sql = "SELECT blog_main.*,"
				+ "blog_reply.id AS reply_id,"
				+ "blog_reply.name AS reply_name,"
				+ "blog_reply.comment AS reply_comment,"
				+ "blog_reply.thankscnt AS reply_thankscnt,"
				+ "blog_reply.created AS reply_created "
				+ "from blog_main "
				+ "LEFT OUTER JOIN blog_reply ON "
				+ "blog_main.id = blog_reply.blog_id "
				+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			list = this.setBlogMainModelListPlus(resultList, isDesc);
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
			
			model = this.makeBlogModel(result);
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
			
			list = this.setBlogMainModelList(resultList);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}
	
	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param   tag
	 * @param  isDesc false 昇順 true 降順
	 * @return  ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc){
		String sql = "SELECT blog_main.*,"
				+ "blog_reply.id AS reply_id,"
				+ "blog_reply.name AS reply_name,"
				+ "blog_reply.comment AS reply_comment,"
				+ "blog_reply.thankscnt AS reply_thankscnt,"
				+ "blog_reply.created AS reply_created "
				+ "from blog_main "
				+ "LEFT OUTER JOIN blog_reply ON "
				+ "blog_main.id = blog_reply.blog_id "
				+ "where blog_main.tag = ? "
				+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql, tag);
			
			list = this.setBlogMainModelListPlus(resultList, isDesc);
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
	
	// ----------------------------------------------------------------------------------------
	
	/**
	 * SQL結果からブログメインモデルリストを作成
	 * @param  resultList
	 * @return ブログメインモデルリスト
	 */
	private List<BlogMainModel> setBlogMainModelList(List<Map<String, Object>> resultList){
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		for( Map<String, Object> result : resultList ) {
			BlogMainModel model = this.makeBlogModel(result);
			if(model == null)	continue;
			
			list.add(model);
		}
		
		return list;
	}
	
	/**
	 * SQL結果からブログメインモデルを作成する
	 * (ブログ返信モデルつき)
	 * @param  resultList
	 * @param  isDesc
	 * @return ブログメインモデルリスト
	 */
	private List<BlogMainModel> setBlogMainModelListPlus(List<Map<String, Object>> resultList, boolean isDesc){
		List<BlogMainModel> list = new ArrayList<BlogMainModel>();
		
		for(int idx=0, len=resultList.size(); idx<len; idx++) {
			// ブログモデルの作成
			Map<String, Object> result    = resultList.get(idx);
			BlogMainModel       model     = this.makeBlogModel(result);
			if(model == null)		continue;
			list.add(model);
			
			// ブログ返信モデルの作成
			BlogReplyModel modelReply      = this.makeBlogReplyModel(result);
			if(modelReply == null)	continue;
			
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
	 * @param  resultList
	 * @param  idx
	 * @param  model
	 * @param  isDesc
	 * @return 追加した数
	 */
	private int setBlogReplyModelList(
			List<Map<String, Object>> resultList,
			int                       idx,
			BlogMainModel             model,
			boolean                   isDesc) {
		List<BlogReplyModel> replyList  = model.getReplyList();
		int                  blogMainId = model.getId(); 
		int                  skipCnt    = 0;
		
		for(int replyIdx=1, len=resultList.size(); idx+replyIdx<len; replyIdx++) {
			Map<String, Object> resultReply = resultList.get(idx+replyIdx);
			int                 blogReplyId = (int)resultReply.get(WebConsts.SQL_ID_NAME);
			
			// 違うブログIDだったら終了
			if(blogMainId != blogReplyId)	break;
			
			BlogReplyModel modelSameReply = this.makeBlogReplyModel(resultReply);
			// モデルがなかったら終了
			if(modelSameReply == null)		break;
			// モデル追加カウント
			replyList.add(modelSameReply);
			skipCnt++;
		}
		
		if(isDesc) {
			replyList.sort((a,b) -> b.getId() - a.getId());
		}
		
		return skipCnt;
	}
	
	/**
	 * モデル生成
	 * @param  result マップ
	 * @return ブログメインモデル
	 */
	private BlogMainModel makeBlogModel(Map<String, Object> result) {
		if(result == null)	return null;
		BlogMainModel model = null;
		
		try {
			model = new BlogMainModel(
				new BlogId((int)result.get(
						WebConsts.SQL_ID_NAME)),
				new TittleWord((String)result.get(
						WebConsts.SQL_TITLE_NAME)),
				new TagWord((String)result.get(
						WebConsts.SQL_TAG_NAME)),
				new CommentWord((String)result.get(
						WebConsts.SQL_COMMENT_NAME)),
				new ThanksCntNumber((int)result.get(
						WebConsts.SQL_THANKSCNT_NAME)),
				((Timestamp)result.get(
						WebConsts.SQL_CREATED_NAME)).toLocalDateTime(),
				((Timestamp)result.get(
						WebConsts.SQL_UPDATED_NAME)).toLocalDateTime()
				);
		} catch(NullPointerException ex) {
			model = null;
		}
		
		return model;
	}
	
	/**
	 * 返信モデルの生成
	 * @param  result
	 * @return ブログ返信モデル
	 */
	private BlogReplyModel makeBlogReplyModel(Map<String, Object> result) {
		if(result == null)	return null;
		BlogReplyModel model = null;
		
		try {
			model = new BlogReplyModel(
					new BlogReplyId((int)result.get(
							WebConsts.SQL_REPLY_ID_NAME)),
					new BlogId((int)result.get(
							WebConsts.SQL_ID_NAME)),
					new NameWord((String)result.get(
							WebConsts.SQL_REPLY_NAME_NAME)),
					new CommentWord((String)result.get(
							WebConsts.SQL_REPLY_COMMENT_NAME)),
					new ThanksCntNumber((int)result.get(
							WebConsts.SQL_REPLY_THANKS_CNT_NAME)),
					((Timestamp)result.get(
							WebConsts.SQL_REPLY_CREATED_NAME)).toLocalDateTime()
					);
		} catch(NullPointerException ex) {
			model = null;
		}
		
		return model;
	}
}
