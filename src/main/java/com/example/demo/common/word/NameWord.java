package com.example.demo.common.word;

/**
 * 名前クラス
 * <br>
 * extends {@link NormalWord}
 * @author nanai
 *
 */
public class NameWord extends NormalWord {

	/**
	 * コンストラクタ
	 */
	public NameWord() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param word {@link String}
	 */
	public NameWord(String word) {
		super(word);
	}

	/**
	 * setter
	 * @param nameWord {@link NameWord}
	 */
	public void setWord(NameWord nameWord) {
		if (nameWord == null)	return ;
		this.word = nameWord.getWord();
	}
}
