package com.example.demo.common.word;

/**
 * タイトルクラス
 * <br>
 * extends {@link NormalWord}
 * @author nanai
 *
 */
public class TittleWord extends NormalWord {

	/**
	 * コンストラクタ
	 */
	public TittleWord() {
		super();
	}
	
	/**
	 * コンストラクタ
	 * @param word {@link String}
	 */
	public TittleWord(String word) {
		super(word);
	}

	/**
	 * setter
	 * @param word {@link TittleWord}
	 */
	public void setWord(TittleWord word) {
		if (word == null)	return;
		this.word = word.getWord();
	}
}
