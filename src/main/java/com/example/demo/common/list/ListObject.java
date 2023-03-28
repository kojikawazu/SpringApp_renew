package com.example.demo.common.list;

import java.util.ArrayList;
import java.util.List;

/**
 * T型リストオブジェクトクラス
 * <br>
 * implements {@link ListInterface}
 * @author nanai
 *
 * @param <T>
 */
public class ListObject<T> implements ListInterface<T> {

	/** リスト */
	private List<T> list;

	/**
	 * コンストラクタ
	 */
	public ListObject() {
		this.list = new ArrayList<T>();
	}

	/**
	 * コンストラクタ
	 * @param inputList {@link List}
	 */
	public ListObject(List<T> inputList) {
		this();
		this.setList(inputList);
	}

	/**
	 * コンストラクタ
	 * @param inputList {@link ListObject}
	 */
	public ListObject(ListObject<T> inputList) {
		this();
		this.setList(inputList);
	}
	
	/**
	 * getter
	 * @return list {@link List}
	 */
	@Override
	public List<T> getList() {
		return list;
	}

	/**
	 * setter
	 * @param list {@link List}
	 */
	@Override
	public void setList(List<T> list) {
		if (list == null)	return ;

		this.list.clear();
		for (T obj : list) {
			this.list.add(obj);
		}
	}

	/**
	 * setter
	 * @param list {@link ListObject}
	 */
	public void setList(ListObject<T> list) {
		if (list == null)	return ;
		this.setList(list.getList());
	}
}
