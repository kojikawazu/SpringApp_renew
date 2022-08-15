package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.BlogMainDao;
import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.exception.WebMvcConfig;

@Service
public class BlogMainServiceUse implements BlogMainService {

	private final BlogMainDao dao;
	
	@Autowired
	public BlogMainServiceUse(BlogMainDao dao) {
		// TODO コンストラクタ
		this.dao = dao;
	}
	
	
	@Override
	public void save(BlogMainModel model) {
		// TODO 保存
		this.dao.insertBlog(model);
	}

	@Override
	public void update(BlogMainModel model) {
		// TODO 更新
		if(this.dao.updateBlog(model) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public void delete(int id) {
		// TODO 削除
		if(this.dao.deleteBlog(id) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public List<BlogMainModel> getAll() {
		// TODO 全て取得
		return this.dao.getAll();
	}


	@Override
	public BlogMainModel select(int id) {
		// TODO idによる取得
		return this.dao.select(id);
	}
	
	@Override
	public List<BlogMainModel> select_byTag(String tag) {
		// TODO 自動生成されたメソッド・スタブ
		return this.dao.select_byTag(tag);
	}

	@Override
	public int thanksIncrement(int id) {
		// TODO いいね数加算
		return this.dao.thanksIncrement(id);
	}	

}
