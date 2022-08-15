package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogTagModel;

public interface BlogTagDao {
	
	void insertTag(BlogTagModel model);
	
	int updateTag(BlogTagModel model);
	
	int deleteTag(int id);
	
	List<BlogTagModel> getAll();
	
	boolean isSelect_byTag(String target);
	
	BlogTagModel select(int id);

}
