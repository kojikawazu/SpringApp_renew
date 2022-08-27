package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.common.id.BlogId;

/**
 * ブログメインサービスインターフェース
 * @author nanai
 *
 */
public interface BlogMainService {
	
	void save(BlogMainModel model);
	
	void update(BlogMainModel model);
	
	void delete(BlogId id);
	
	List<BlogMainModel> getAll();
	
	BlogMainModel select(BlogId id);
	
	List<BlogMainModel> select_byTag(String tag);
	
	int thanksIncrement(BlogId id);

}
