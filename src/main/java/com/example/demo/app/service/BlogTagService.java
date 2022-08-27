package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.common.id.BlogTagId;

/**
 * ブログタグサービスインターフェース
 * @author nanai
 *
 */
public interface BlogTagService {
	
	void save(BlogTagModel model);
	
	void update(BlogTagModel model);
	
	void delete(BlogTagId id);
	
	List<BlogTagModel> getAll();
	
	boolean isSelect_byTag(String tag);
	
	BlogTagModel select(BlogTagId id);

}
