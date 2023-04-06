package com.example.demo.app.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.dao.blog.BlogReplyDaoSql;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * ブログ返信サービスクラス
 * <br>
 * implements {@link SuperService}<{@link BlogReplyModel}, {@link BlogReplyId}><br>
 *            {@link BlogReplyService}
 * @author nanai
 *
 */
@Service
public class BlogReplyServiceUse implements SuperService<BlogReplyModel, BlogReplyId>, BlogReplyService {

	/** 
	 * Daoクラス
	 * {@link BlogReplyDaoSql}
	 */
	private final BlogReplyDaoSql dao;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param dao {@link BlogReplyDaoSql}
	 */
	@Autowired
	public BlogReplyServiceUse(BlogReplyDaoSql dao) {
		this.dao 		= dao;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 保存
	 * @param model {@link BlogReplyModel}
	 */
	@Override
	public void save(BlogReplyModel model) {
		try {
			this.dao.insert(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(BlogReplyId id) {
		int result = 0;

		try {
			result = this.dao.delete(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
		if (result <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}
	
	/**
	 * ブログIDによる削除
	 * @param  blogid {@link BlogId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete_byBlogid(BlogId blogid) {
		int result = 0;

		try {
			result = this.dao.delete_byBlogId(blogid);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
		if (result <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 全て選択
	 * @return ブログ返信モデルリスト {@link List}({@link BlogReplyModel})
	 */
	@Override
	public List<BlogReplyModel> getAll() {
		return this.dao.getAll();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * ブログIDによる選択
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデルリスト {@link List}({@link BlogReplyModel})
	 */
	@Override
	public List<BlogReplyModel> select_byBlogId(BlogId id) {
		List<BlogReplyModel> list = null;

		try {
			list = this.dao.select_byBlogId(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}

		return list;
	}

	/**
	 * IDによる選択
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデル {@link BlogReplyModel}
	 */
	@Override
	public BlogReplyModel select(BlogReplyId id) {
		BlogReplyModel model = null;

		try {
			model = this.dao.select(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}

		return model;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * いいね数加算
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return いいね数
	 */
	@Override
	public int thanksIncrement(BlogReplyId id) {
		int number = 0;

		try {
			number = this.dao.thanksIncrement(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}
		if (number == WebConsts.ERROR_NUMBER) {
			throw WebMvcConfig.NOT_FOUND();
		}

		return number;
	}

	/** ------------------------------------------------------------------------------------- */

	@Override
	public void update(BlogReplyModel model) {
		
	}

	@Override
	public boolean isSelect_byId(int targetID) {
		return false;
	}
}
