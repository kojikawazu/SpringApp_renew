package com.example.demo.common.number;

/**
 * いいね数クラス
 * <br>
 * extends {@link NormalNumber}
 * @author nanai
 *
 */
public class ThanksCntNumber extends NormalNumber {

	/**
	 * コンストラクタ
	 */
	public ThanksCntNumber() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param thanksCnt
	 */
	public ThanksCntNumber(int thanksCnt) {
		super(thanksCnt);
	}

	/**
	 * コンストラクタ
	 * @param thanksCnt {@link ThanksCntNumber}
	 */
	public ThanksCntNumber(ThanksCntNumber thanksCnt) {
		super();
		this.setNumber(thanksCnt);
	}

	/**
	 * setter
	 * @param thanksCnt {@link ThanksCntNumber}
	 */
	public void setNumber(ThanksCntNumber thanksCnt) {
		if (thanksCnt == null)	return;
		this.setNumber(thanksCnt.getNumber());
	}
}
