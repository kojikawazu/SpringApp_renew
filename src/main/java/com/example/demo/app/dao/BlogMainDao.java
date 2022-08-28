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
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);

}
