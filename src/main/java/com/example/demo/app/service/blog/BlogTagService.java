package com.example.demo.app.service.blog;

import java.util.List;

import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.id.blog.BlogTagId;

/**
 * ブログタグサービスインターフェース
 * @author nanai
 *
 */
public interface BlogTagService {
	
	/**
	 * 保存
	 * @param model {@link BlogTagModel}
	 */
	void save(BlogTagModel model);
	
	/**
	 * 更新
	 * @param model {@link BlogTagModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	void update(BlogTagModel model);
	
	/**
	 * 削除
	 * @param id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	void delete(BlogTagId id);
	
	/**
	 * 全選択
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログタグモデルリスト {@link List}({@link BlogTagModel})
	 */
	List<BlogTagModel> getAll();
	
	/**
	 * 選択
	 * @param  id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログタグモデル {@link BlogTagModel}
	 */
	BlogTagModel select(BlogTagId id);
	
	/**
	 * タグがあるかどうかチェック
	 * @param tag
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byTag(String tag);

}
