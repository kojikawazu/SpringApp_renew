package com.example.demo.json.list;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.list.ListInterface;

/**
 * イントロ用リストクラス
 * @author nanai
 *
 */
public class IntroList implements ListInterface {
	
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
	
	@Override
	public List<String> getList(){
		return list;
	}
}
