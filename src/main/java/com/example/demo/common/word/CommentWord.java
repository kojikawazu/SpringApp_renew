package com.example.demo.common.word;

/**
 * コメント文字列クラス
 * <br>
 * extends {@link NormalWord}
 * @author nanai
 *
 */
public class CommentWord extends NormalWord {

	/**
	 * コンストラクタ
	 */
	public CommentWord() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param word {@link String}
	 */
	public CommentWord(String word) {
		super(word);
	}

	/**
	 * setter
	 * @param word {@link CommentWord}
	 */
	public void setWord(CommentWord word) {
		if (word == null)	return;
		this.word = word.getWord();
	}
}
