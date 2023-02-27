package com.example.demo.common.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ワードリストクラス
 * @author nanai
 *
 */
public class WordList implements ListInterface<String> {

	/** ワードリスト */
	private List<String> list;

	/**
	 * コンストラクタ
	 */
	public WordList() {
		this.list = new ArrayList<String>();
	}

	/**
	 * コンストラクタ
	 * @param リスト
	 */
	public WordList(List<String> list) {
		this();
		this.setList(list);
	}

	@Override
	public List<String> getList(){
		return this.list;
	}

	@Override
	public void setList(List<String> list) {
		if (list == null)	return ;

		this.list.clear();
		for (String word : list) {
			this.list.add(word);
		}
	}

	public void setList(WordList obj) {
		if (obj == null)	return ;

		List<String> objList = obj.getList();
		this.setList(objList);
	}
}
