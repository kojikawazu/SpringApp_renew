package com.example.demo.common.word;

/**
 * タイトルクラス
 * @author nanai
 *
 */
public class TittleWord implements SuperWord {

	/** 文字列保持 */
	private String word;

	/**
	 * コンストラクタ
	 * @param word
	 */
	public TittleWord(String word) {
		this.word = word;
	}

	/**
	 * getter
	 * @return 文字列
	 */
	@Override
	public String getWord() {
		return this.word;
	}
}
