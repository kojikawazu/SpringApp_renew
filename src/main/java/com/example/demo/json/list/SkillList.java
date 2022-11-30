package com.example.demo.json.list;

import java.util.List;

import com.example.demo.common.list.ListInterface;

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
