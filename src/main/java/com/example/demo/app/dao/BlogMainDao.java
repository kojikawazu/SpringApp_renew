package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.common.id.BlogId;

/**
 * ブログメインDaoインターフェース
 * @author nanai
 *
 */
public interface BlogMainDao {
	
	void insertBlog(BlogMainModel model);
	
	int updateBlog(BlogMainModel model);
	
	int deleteBlog(BlogId id);
	
	List<BlogMainModel> getAll();
	
	BlogMainModel select(BlogId id);
	
	List<BlogMainModel> select_byTag(String tag);
	
	int thanksIncrement(BlogId id);

}
