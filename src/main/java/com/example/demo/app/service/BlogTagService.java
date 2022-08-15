package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogTagModel;

public interface BlogTagService {
	
	void save(BlogTagModel model);
	
	void update(BlogTagModel model);
	
	void delete(int id);
	
	List<BlogTagModel> getAll();
	
	boolean isSelect_byTag(String tag);
	
	BlogTagModel select(int id);

}
