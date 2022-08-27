package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;

/**
 * ブログ返信サービスインターフェース
 * @author nanai
 *
 */
public interface BlogReplyService {
	
	void save(BlogReplyModel model);
	
	void delete(BlogReplyId id);
	
	void delete_byBlogid(BlogId blogid);
	
	List<BlogReplyModel> getAll();
	
	List<BlogReplyModel> select_byBlogId(BlogId id);
	
	BlogReplyModel select(BlogReplyId id);
	
	int thanksIncrement(BlogReplyId id);

}
