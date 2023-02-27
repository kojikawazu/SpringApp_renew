package com.example.demo.common.number;

/**
 * いいね数クラス
 * @author nanai
 *
 */
public class ThanksCntNumber implements SuperNumber {

	/** いいね数 */
	private int           thanksCnt;

	/**
	 * コンストラクタ
	 * @param thanksCnt
	 */
	public ThanksCntNumber(int thanksCnt) {
		this.thanksCnt = thanksCnt;
	}

	/**
	 * getter
	 * @return thanksCnt
	 */
	@Override
	public int getNumber() {
		return this.thanksCnt;
	}

	@Override
	public void setNumber(int number) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
