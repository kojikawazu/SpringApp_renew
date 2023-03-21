package com.example.demo.common.word;

/**
 * 文字列ラッパークラス
 * <br>
 * implements {@link SuperWord}
 * @author nanai
 *
 */
public class NormalWord implements SuperWord {

	/** 文字列保持 */
	protected String word;

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
		this();
		this.setWord(word);
	}

	/**
	 * getter
	 * @return 文字列
	 */
	@Override
	public String getWord() {
		return this.word;
	}

	/**
	 * setter
	 * @param word {@link String}
	 */
	@Override
	public void setWord(String word) {
		if (word == null)	return;
		this.word = word;
	}

	/**
	 * setter
	 * @param word {@link NormalWord}
	 */
	public void setWord(NormalWord word) {
		if (word == null)	return;
		this.word = word.getWord();
	}
}
