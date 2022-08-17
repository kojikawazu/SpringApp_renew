package com.example.demo.common.id;

/**
 * 調査IDクラス
 * @author nanai
 *
 */
public class SurveyId implements IntroId {

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
