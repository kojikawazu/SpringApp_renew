package com.example.demo.common.word;

/**
 * イントロ使用文字列
 * @author nanai
 *
 */
public class NormalWord implements SuperWord {

	/** 文字列保持 */
	private String word;

	/**
	 * コンストラクタ
	 */
	public NormalWord() {
		this.word = "";
	}

	/**
	 * コンストラクタ
	 * @param word
	 */
	public NormalWord(String word) {
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

	public void setWord(String word) {
		this.word = word;
	}

	public void setWord(NormalWord obj) {
		this.word = obj.getWord();
	}
}
