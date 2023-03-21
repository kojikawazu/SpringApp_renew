package com.example.demo.common.word;

/**
 * パスワード文字列クラス
 * <br>
 * extends {@link NameWord}
 * @author nanai
 *
 */
public class PasswdWord extends NameWord {

	/**
	 * コンストラクタ
	 */
	public PasswdWord() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param word {@link String}
	 */
	public PasswdWord(String word) {
		this.word = word;
	}

	/**
	 * setter
	 * @param passwdWord {@link PasswdWord}
	 */
	public void setWord(PasswdWord passwdWord) {
		if (passwdWord == null)	return ;
		this.word = passwdWord.getWord();
	}
}
