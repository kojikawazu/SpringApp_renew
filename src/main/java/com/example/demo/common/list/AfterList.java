package com.example.demo.common.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 今後やりたいことリスト
 * @author nanai
 *
 */
public class AfterList implements ListInterfaceS {

	/** 今後やりたいことリスト */
	private List<String> afterList;
	
	/**
	 * コンストラクタ
	 */
	public AfterList() {
		this.afterList = new ArrayList<String>();
	}
	
	/**
	 * コンストラクタ
	 * @param afterList
	 */
	public AfterList(List<String> afterList) {
		this.afterList = (afterList == null ?
			new ArrayList<String>() :
			afterList);
	}
	
	@Override
	public List<String> getList() {
		return this.afterList;
	}
}
