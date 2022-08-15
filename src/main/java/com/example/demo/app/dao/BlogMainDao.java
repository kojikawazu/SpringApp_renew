package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogMainModel;

public interface BlogMainDao {
	
	void insertBlog(BlogMainModel model);
	
	int updateBlog(BlogMainModel model);
	
	int deleteBlog(int id);
	
	List<BlogMainModel> getAll();
	
	BlogMainModel select(int id);
	
	List<BlogMainModel> select_byTag(String tag);
	
	int thanksIncrement(int id);

}
