package com.example.demo.app.dao.blog;

import java.util.List;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.entity.blog.BlogMainModel;

/**
 * ブログメインDaoインターフェース
 * @author nanai
 *
 */
public interface BlogMainDao {

	/**
	 * 全て選択(ブログ返信リストつき)
	 * @param  isDesc
	 * @return ブログメインモデルリスト {@link List}<{@link BlogMainModel}>
	 */
	List<BlogMainModel> getAll_Plus(boolean isDesc);

	/**
	 * タグによる選択
	 * @param  tag タグ {@link String}
	 * @return ブログモデルクラスリスト {@link List}<{@link BlogMainModel}>
	 */
	List<BlogMainModel> select_byTag(String tag);

	/**
	 * タグによる選択(ブログ返信リストつき)
	 * @param  tag タグ {@link String}
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログモデルクラスリスト {@link List}<{@link BlogMainModel}>
	 */
	List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc);

	/**
	 * いいね数加算
	 * @param  id {@link BlogId}
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);
}
