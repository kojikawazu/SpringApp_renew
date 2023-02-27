package com.example.demo.common.word;

/**
 * 名前クラス
 * @author nanai
 *
 */
public class NameWord implements SuperWord {

	/** 文字列保持 */
	private String word;

	/**
	 * コンストラクタ
	 * @param word
	 */
	public NameWord(String word) {
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
