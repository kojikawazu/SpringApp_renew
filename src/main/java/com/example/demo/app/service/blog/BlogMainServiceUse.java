package com.example.demo.app.service.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.dao.blog.BlogMainDaoSql;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * ブログメインサービスクラス
 * @author nanai
 *
 */
@Service
public class BlogMainServiceUse implements SuperService<BlogMainModel, BlogId>,  BlogMainService {

	/** 
	 * Daoクラス
	 * {@link BlogMainDaoSql}
	 */
	private final BlogMainDaoSql dao;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param dao {@link BlogMainDaoSql}
	 */
	@Autowired
	public BlogMainServiceUse(BlogMainDaoSql dao) {
		this.dao 		= dao;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 保存
	 * @param model {@link BlogMainModel}
	 */
	@Override
	public void save(BlogMainModel model) {
		try {
			this.dao.insert(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param  model {@link BlogMainModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(BlogMainModel model) {
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
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(BlogId id) {
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
	 * 全て選択
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	@Override
	public List<BlogMainModel> getAll() {
		return this.dao.getAll();
	}

	/**
	 * 全て選択(ブログ返信モデルリストつき)
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	@Override
	public List<BlogMainModel> getAllPlus(boolean isDesc) {
		return this.dao.getAll_Plus(isDesc);
	}

	/**
	 * IDによる選択
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログメインモデルクラス {@link BlogMainModel}
	 */
	@Override
	public BlogMainModel select(BlogId id) {
		BlogMainModel model = null;

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

	/**
	 * タグによる選択
	 * @param  タグ
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	@Override
	public List<BlogMainModel> select_byTag(String tag) {
		List<BlogMainModel> list = null;

		try {
			list = this.dao.select_byTag(tag);;
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			return new ArrayList<BlogMainModel>();
		}

		return list;
	}

	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param  タグ
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	@Override
	public List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc) {
		List<BlogMainModel> list = null;

		try {
			list = this.dao.select_byTagPlus(tag, isDesc);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			return new ArrayList<BlogMainModel>();
		}

		return list;
	}

	/**
	 * いいね数加算
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 * @return いいね数 or -1(異常値)
	 */
	@Override
	public int thanksIncrement(BlogId id) {
		int number = 0;

		try {
			number = this.dao.thanksIncrement(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}

		if (number == WebConsts.ERROR_NUMBER) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
		return number;
	}

	@Override
	public boolean isSelect_byId(int targetID) {
		return false;
	}
}
