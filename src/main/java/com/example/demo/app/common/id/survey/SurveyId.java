package com.example.demo.app.common.id.survey;

import com.example.demo.common.id.NormalId;

/**
 * 調査IDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class SurveyId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public SurveyId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public SurveyId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link SurveyId}
	 */
	public void setId(SurveyId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
