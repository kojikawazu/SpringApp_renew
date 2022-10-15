package com.example.demo.app.dao.blog;

import java.util.List;

import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.common.id.blog.BlogId;

/**
 * ブログメインDaoインターフェース
 * @author nanai
 *
 */
public interface BlogMainDao {
	
	/**
	 * 追加
	 * @param model ブログメインモデル
	 */
	void insertBlog(BlogMainModel model);
	
	/**
	 * 更新
	 * @param  ブログメインモデル
	 * @return 0以下 失敗 それ以外 成功
	 */
	int updateBlog(BlogMainModel model);
	
	/**
	 * 削除
	 * @param  id
	 * @return 0以下 失敗 それ以外 成功
	 */
	int deleteBlog(BlogId id);
	
	/**
	 * 全て選択
	 * @return ブログメインモデルリスト
	 */
	List<BlogMainModel> getAll();
	
	/**
	 * 全て選択(ブログ返信リストつき)
	 * @param  isDesc
	 * @return ブログメインモデルリスト
	 */
	List<BlogMainModel> getAll_Plus(boolean isDesc);
	
	/**
	 * IDによるデータ取得
	 * @param  id
	 * @return ブログメインモデル
	 */
	BlogMainModel select(BlogId id);
	
	/**
	 * タグによる選択
	 * @param  tag タグ
	 * @return ブログモデルクラスリスト
	 */
	List<BlogMainModel> select_byTag(String tag);
	
	/**
	 * タグによる選択(ブログ返信リストつき)
	 * @param  tag タグ
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログモデルクラスリスト
	 */
	List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc);
	
	/**
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);

}
