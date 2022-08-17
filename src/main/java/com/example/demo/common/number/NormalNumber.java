package com.example.demo.common.number;

/**
 * 通常数値クラス
 * @author nanai
 *
 */
public class NormalNumber implements SuperNumber {

	/** いいね数 */
	private int           number;
	
	/**
	 * コンストラクタ
	 * @param number
	 */
	public NormalNumber(int number) {
		this.number = number;
	}

	/**
	 * getter
	 * @return number
	 */
	@Override
	public int getNumber() {
		return this.number;
	}
}
