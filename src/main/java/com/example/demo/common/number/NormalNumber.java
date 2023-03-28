package com.example.demo.common.number;

/**
 * 通常数値クラス
 * <br>
 * implements {@link SuperNumber}
 * @author nanai
 *
 */
public class NormalNumber implements SuperNumber {

	/** 数値のデフォルト */
	private final int     DEFAULT_NUMBER = 0;

	/** 数値 */
	private int           number;

	/**
	 * コンストラクタ
	 * @param number
	 */
	public NormalNumber() {
		Init();
	}

	/**
	 * コンストラクタ
	 * @param number
	 */
	public NormalNumber(int number) {
		this.number = number;
	}

	/**
	 * 初期化
	 */
	public void Init() {
		this.number = DEFAULT_NUMBER;
	}

	/**
	 * getter
	 * @return number
	 */
	@Override
	public int getNumber() {
		return this.number;
	}

	/**
	 * setter
	 * @param number
	 */
	@Override
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * setter
	 * @param number {@link NormalNumber}
	 */
	public void setNumber(NormalNumber obj) {
		if (obj == null)	return ;
		this.number = obj.getNumber();
	}
}
