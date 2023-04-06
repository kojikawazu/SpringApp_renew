package com.example.demo.common.word;

/**
 * タグ文字列クラス
 * <br>
 * extends {@link NormalWord}
 * @author nanai
 *
 */
public class TagWord extends NormalWord {

	/**
	 * コンストラクタ
	 */
	public TagWord() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param word {@link String}
	 */
	public TagWord(String word) {
		super(word);
	}

	/**
	 * setter
	 * @param word {@link TagWord}
	 */
	public void setWord(TagWord word) {
		if (word == null)	return;
		this.word = word.getWord();
	}
}
