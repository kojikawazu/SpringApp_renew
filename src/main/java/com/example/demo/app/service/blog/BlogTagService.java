package com.example.demo.app.service.blog;

import java.util.List;

import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.id.blog.BlogTagId;

/**
 * ブログタグサービスインターフェース
 * @author nanai
 *
 */
public interface BlogTagService {
	
	/**
	 * 保存
	 * @param model
	 */
	void save(BlogTagModel model);
	
	/**
	 * 更新
	 * @param model
	 */
	void update(BlogTagModel model);
	
	/**
	 * 削除
	 * @param id
	 */
	void delete(BlogTagId id);
	
	/**
	 * 全選択
	 * @return ブログタグモデルリスト
	 */
	List<BlogTagModel> getAll();
	
	/**
	 * 選択
	 * @param  id
	 * @return ブログタグモデル
	 */
	BlogTagModel select(BlogTagId id);
	
	/**
	 * タグがあるかどうかチェック
	 * @param tag
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byTag(String tag);

}
