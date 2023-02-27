package com.example.demo.common.list;

import java.util.List;

/**
 * リストインターフェース(型指定なし)
 * @author nanai
 * @param <T>
 *
 */
public interface ListInterface<T> {

	/**
	 * getter
	 * @return {@link List}
	 */
	public List<T> getList();

	/**
	 * setter
	 * @param list {@link List}
	 */
	public void setList(List<T> list);

}
