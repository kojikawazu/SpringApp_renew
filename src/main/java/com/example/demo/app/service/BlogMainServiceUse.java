package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.BlogMainDao;
import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;

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
		List<BlogMainModel> list = this.dao.getAll();
		
		if(list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
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
		List<BlogMainModel> list = this.dao.select_byTag(tag);
		
		if(list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
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
