package com.example.demo.app.service.blog;

import java.util.List;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.exception.WebMvcConfig;

/**
 * ブログメインサービスインターフェース
 * @author nanai
 *
 */
public interface BlogMainService {

	/**
	 * 全て選択(ブログ返信モデルリストつき)
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> getAllPlus(boolean isDesc);

	/**
	 * タグによる選択
	 * @param  タグ
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> select_byTag(String tag);

	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param  タグ
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc);

	/**
	 * いいね数加算
	 * @param id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);
}
