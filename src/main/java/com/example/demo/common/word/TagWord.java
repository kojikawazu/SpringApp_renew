package com.example.demo.common.word;

/**
 * タグ文字列クラス
 * @author nanai
 *
 */
public class TagWord implements SuperWord {

	/** 文字列保持 */
	private String word;

	/**
	 * コンストラクタ
	 * @param word
	 */
	public TagWord(String word) {
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
