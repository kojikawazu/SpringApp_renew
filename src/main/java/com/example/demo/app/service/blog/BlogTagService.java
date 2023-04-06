package com.example.demo.app.service.blog;

import com.example.demo.app.entity.blog.BlogTagModel;

/**
 * ブログタグサービスインターフェース
 * @author nanai
 *
 */
public interface BlogTagService {

	/**
	 * 保存
	 * @param model {@link BlogTagModel}
	 * @return id
	 */
	int save_returnId(BlogTagModel model);

	/**
	 * タグがあるかどうかチェック
	 * @param tag
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byTag(String tag);

}
