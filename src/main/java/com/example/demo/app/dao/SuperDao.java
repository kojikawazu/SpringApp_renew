package com.example.demo.app.dao;

import java.util.List;

/**
 * 基本Daoインターフェース
 * @author nanai
 *
 */
public interface SuperDao<T, S> {

	/**
	 * 追加
	 * @param model
	 */
	void insert(T model);
	
	/**
	 * 更新
	 * @param model
	 * @return 0以下 失敗 それ以外 成功 
	 */
	int update(T model);
	
	/**
	 * 削除
	 * @param  id
	 * @return 0以下 失敗 それ以外 成功 
	 */
	int delete(S id);
	
	/**
	 * 全選択
	 * @param <T>
	 * @return <T>リスト
	 */
	List<T> getAll();
	
	/**
	 * IDによる選択
	 * @param <T>
	 * @param <S>
	 * @param id
	 * @return
	 */
	T select(S id);
	
}
