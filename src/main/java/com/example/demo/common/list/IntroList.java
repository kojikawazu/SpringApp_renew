package com.example.demo.common.list;

import java.util.ArrayList;
import java.util.List;

/**
 * イントロ用リストクラス
 * @author nanai
 *
 */
public class IntroList {
	
	/** イントロリスト */
	private List<String> list;
	
	/**
	 * コンストラクタ
	 * @param リスト
	 */
	public IntroList() {
		this.list = new ArrayList<String>();
	}
	
	/**
	 * コンストラクタ
	 * @param リスト
	 */
	public IntroList(List<String> list) {
		this.list = list;
	}
	
	public List<String> getList(){
		return list;
	}
}
