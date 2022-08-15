package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogReplyModel;

public interface BlogReplyDao {
	
	void insertReply(BlogReplyModel model);
	
	int deleteReply(int id);
	
	int deleteReply_byBlog(int blogid);
	
	List<BlogReplyModel> getAll();
	
	List<BlogReplyModel> select_blogId(int blogid);
	
	BlogReplyModel select(int id);
	
	int thanksIncrement(int id);

}
