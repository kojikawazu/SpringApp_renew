package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogMainModel;

public interface BlogMainService {
	
	void save(BlogMainModel model);
	
	void update(BlogMainModel model);
	
	void delete(int id);
	
	List<BlogMainModel> getAll();
	
	BlogMainModel select(int id);
	
	List<BlogMainModel> select_byTag(String tag);
	
	int thanksIncrement(int id);

}
