package com.example.demo.app.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.dao.blog.BlogTagDaoSql;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * ブログタグサービスクラス
 * <br>
 * implements {@link SuperService}<{@link BlogTagModel}, {@link BlogTagId}><br>
 *            {@link BlogTagService}
 * @author nanai
 *
 */
@Service
public class BlogTagServiceUse implements SuperService<BlogTagModel, BlogTagId>, BlogTagService {

	/** 
	 * Daoクラス
	 * {@link BlogTagDaoSql}
	 */
	private final BlogTagDaoSql dao;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param dao {@link BlogTagDaoSql}
	 */
	@Autowired
	public BlogTagServiceUse(BlogTagDaoSql dao) {
		this.dao 		= dao;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 保存
	 * @param model {@link BlogTagModel}
	 */
	@Override
	public void save(BlogTagModel model) {
		try {
			this.dao.insert(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}
	}

	/**
	 * 保存
	 * @param model {@link BlogTagModel}
	 * @return id
	 */
	@Override
	public int save_returnId(BlogTagModel model) {
		int id = 0;

		try {
			id = this.dao.insert_returnId(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}

		return id;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param model {@link BlogTagModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(BlogTagModel model) {
		int result = 0;

		try {
			result = this.dao.update(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}

		if (result <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param  id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(BlogTagId id) {
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

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 全選択
	 * @return ブログタグモデルリスト {@link List}({@link BlogTagModel})
	 */
	@Override
	public List<BlogTagModel> getAll() {
		return this.dao.getAll();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 選択
	 * @param  id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログタグモデル {@link BlogTagModel}
	 */
	@Override
	public BlogTagModel select(BlogTagId id) {
		BlogTagModel model = null;

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
	 * タグがあるかどうかチェック
	 * @param tag {@link String}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byTag(String tag) {
		boolean exists = false;

		try {
			exists = this.dao.isSelect_byTag(tag);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}

		return exists;
	}

	/** ------------------------------------------------------------------------------------- */

	@Override
	public boolean isSelect_byId(int targetID) {
		return false;
	}
}
