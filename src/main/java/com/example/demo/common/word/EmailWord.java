package com.example.demo.common.word;

/**
 * Eメール文字列クラス
 * <br>
 * extends {@link NormalWord}
 * @author nanai
 *
 */
public class EmailWord extends NormalWord {

	/**
	 * コンストラクタ
	 */
	public EmailWord() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param word {@link String}
	 */
	public EmailWord(String word) {
		super(word);
	}

	/**
	 * setter
	 * @param emailWord {@link EmailWord}
	 */
	public void setWord(EmailWord emailWord) {
		if (emailWord == null)	return ;
		this.word = emailWord.getWord();
	}
}
