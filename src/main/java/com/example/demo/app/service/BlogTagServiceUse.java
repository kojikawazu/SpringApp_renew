package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.BlogTagDao;
import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.app.exception.WebMvcConfig;

@Service
public class BlogTagServiceUse implements BlogTagService {
	
	private final BlogTagDao dao;
	
	@Autowired
	public BlogTagServiceUse(BlogTagDao dao) {
		// TODO コンストラクタ
		this.dao = dao;
	}

	@Override
	public void save(BlogTagModel model) {
		// TODO 保存
		this.dao.insertTag(model);
	}

	@Override
	public void update(BlogTagModel model) {
		// TODO 更新
		if(this.dao.updateTag(model) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public void delete(int id) {
		// TODO 削除
		if(this.dao.deleteTag(id) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public List<BlogTagModel> getAll() {
		// TODO 全て取得
		return this.dao.getAll();
	}
	
	@Override
	public BlogTagModel select(int id) {
		// TODO IDによる選択
		return this.dao.select(id);
	}

	@Override
	public boolean isSelect_byTag(String tag) {
		// TODO タグがあるかチェック
		return this.dao.isSelect_byTag(tag);
	}



}
