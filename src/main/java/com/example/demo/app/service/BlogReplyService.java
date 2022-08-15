package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogReplyModel;

public interface BlogReplyService {
	
	void save(BlogReplyModel model);
	
	void delete(int id);
	
	void delete_byBlogid(int blogid);
	
	List<BlogReplyModel> getAll();
	
	List<BlogReplyModel> select_byBlogId(int id);
	
	BlogReplyModel select(int id);
	
	int thanksIncrement(int id);

}
