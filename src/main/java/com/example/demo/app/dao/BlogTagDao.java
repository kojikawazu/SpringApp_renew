package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.common.id.BlogTagId;

/**
 * ブログタグDaoインターフェース
 * @author nanai
 *
 */
public interface BlogTagDao {
	
	void insertTag(BlogTagModel model);
	
	int updateTag(BlogTagModel model);
	
	int deleteTag(BlogTagId id);
	
	List<BlogTagModel> getAll();
	
	boolean isSelect_byTag(String target);
	
	BlogTagModel select(BlogTagId id);

}
