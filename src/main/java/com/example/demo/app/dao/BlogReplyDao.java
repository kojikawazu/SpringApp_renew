package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;

/**
 * ブログ返信Daoインターフェース
 * @author nanai
 *
 */
public interface BlogReplyDao {
	
	void insertReply(BlogReplyModel model);
	
	int deleteReply(BlogReplyId id);
	
	int deleteReply_byBlog(BlogId blogid);
	
	List<BlogReplyModel> getAll();
	
	List<BlogReplyModel> select_blogId(BlogId blogid);
	
	BlogReplyModel select(BlogReplyId id);
	
	int thanksIncrement(BlogReplyId id);

}
