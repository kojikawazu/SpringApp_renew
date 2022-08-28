package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.common.id.BlogId;

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
	 * いいね数加算
	 * @param id
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);

}
