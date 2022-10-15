package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.common.id.blog.BlogId;

/**
 * 基本サービスクラス
 * @author nanai
 *
 */
public interface SuperService<T, S> {
	
	/**
	 * 保存
	 * @param model
	 */
	void save(T model);
	
	/**
	 * 更新
	 * @param model
	 */
	void update(T model);
	
	/**
	 * 削除
	 * @param id
	 */
	void delete(S id);
	
	/**
	 * 全て選択
	 * @return モデルリスト
	 */
	List<T> getAll();
	
	/**
	 * IDによる選択
	 * @param  id
	 * @return モデルクラス
	 */
	T select(S id);

}
