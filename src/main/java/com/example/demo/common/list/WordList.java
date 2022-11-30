package com.example.demo.common.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ワードリストクラス
 * @author nanai
 *
 */
public class WordList implements ListInterface {

	/** ワードリスト */
	private List<String> list;
	
	/**
	 * コンストラクタ
	 * @param リスト
	 */
	public WordList(List<String> list) {
		this.list = list;
	}
	
	/**
	 * コンストラクタ
	 */
	public WordList() {
		this.list = new ArrayList<String>();
	}
	
	@Override
	public List<String> getList(){
		return list;
	}
	
}
