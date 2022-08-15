package com.example.demo.common.list;

import java.util.List;

/**
 * スキルリストクラス
 * @author nanai
 *
 */
public class SkillList {

	/** スキルリスト */
	private List<String> list;
	
	/**
	 * コンストラクタ
	 * @param リスト
	 */
	public SkillList(List<String> list) {
		this.list = list;
	}
	
	public List<String> getList(){
		return list;
	}
}
