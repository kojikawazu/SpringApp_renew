package com.example.demo.app.service.blog;

import java.util.List;

import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.common.id.blog.BlogId;

/**
 * ブログメインサービスインターフェース
 * @author nanai
 *
 */
public interface BlogMainService {
	
	/**
	 * 保存
	 * @param model
	 */
	void save(BlogMainModel model);
	
	/**
	 * 更新
	 * @param model
	 */
	void update(BlogMainModel model);
	
	/**
	 * 削除
	 * @param id
	 */
	void delete(BlogId id);
	
	/**
	 * 全て選択
	 * @return ブログメインモデルリスト
	 */
	List<BlogMainModel> getAll();
	
	/**
	 * 全て選択(ブログ返信モデルリストつき)
	 * @return ブログメインモデルリスト
	 */
	List<BlogMainModel> getAllPlus(boolean isDesc);
	
	/**
	 * IDによる選択
	 * @param  id
	 * @return ブログメインモデルクラス
	 */
	BlogMainModel select(BlogId id);
	
	/**
	 * タグによる選択
	 * @param  タグ
	 * @return ブログメインモデルリスト
	 */
	List<BlogMainModel> select_byTag(String tag);
	
	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param  タグ
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト
	 */
	List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc);
	
	/**
	 * いいね数加算
	 * @param id
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);

}
