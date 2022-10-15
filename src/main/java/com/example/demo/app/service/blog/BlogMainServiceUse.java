package com.example.demo.app.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.blog.BlogMainDao;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;

/**
 * ブログメインサービスクラス
 * @author nanai
 *
 */
@Service
public class BlogMainServiceUse implements BlogMainService {

	/** Daoクラス */
	private final BlogMainDao dao;
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Autowired
	public BlogMainServiceUse(BlogMainDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 保存
	 * @param model
	 */
	@Override
	public void save(BlogMainModel model) {
		this.dao.insertBlog(model);
	}

	/**
	 * 更新
	 * @param model
	 */
	@Override
	public void update(BlogMainModel model) {
		if(this.dao.updateBlog(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 削除
	 * @param id
	 */
	@Override
	public void delete(BlogId id) {
		if(this.dao.deleteBlog(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全て選択
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> getAll() {
		return this.dao.getAll();
	}
	
	/**
	 * 全て選択(ブログ返信モデルリストつき)
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> getAllPlus(boolean isDesc) {
		return this.dao.getAll_Plus(isDesc);
	}

	/**
	 * IDによる選択
	 * @param  id
	 * @return ブログメインモデルクラス
	 */
	@Override
	public BlogMainModel select(BlogId id) {
		BlogMainModel model = this.dao.select(id);
		
		if(model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}
	
	/**
	 * タグによる選択
	 * @param  タグ
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> select_byTag(String tag) {
		return this.dao.select_byTag(tag);
	}
	
	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param  タグ
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト
	 */
	@Override
	public List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc) {
		return this.dao.select_byTagPlus(tag, isDesc);
	}

	/**
	 * いいね数加算
	 * @param id
	 * @return いいね数
	 */
	@Override
	public int thanksIncrement(BlogId id) {
		int number = this.dao.thanksIncrement(id);
		
		if(number == WebConsts.ERROR_NUMBER) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return number;
	}
}
