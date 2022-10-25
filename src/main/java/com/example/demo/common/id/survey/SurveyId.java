package com.example.demo.common.id.survey;

import com.example.demo.common.id.common.SuperId;

/**
 * 調査IDクラス
 * @author nanai
 *
 */
public class SurveyId implements SuperId {

	/** ID */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public SurveyId(int id) {
		this.id = id;
	}

	/**
	 * getter
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}
}
