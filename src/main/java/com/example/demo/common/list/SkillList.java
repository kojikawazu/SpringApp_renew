package com.example.demo.common.list;

import java.util.List;

/**
 * スキルリストクラス
 * @author nanai
 *
 */
public class SkillList implements ListInterface {

	/** スキルリスト */
	private List<String> list;
	
	/**
	 * コンストラクタ
	 * @param リスト
	 */
	public SkillList(List<String> list) {
		this.list = list;
	}
	
	@Override
	public List<String> getList(){
		return list;
	}
}
