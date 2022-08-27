package com.example.demo.common.word;

/**
 * コメント文字列クラス
 * @author nanai
 *
 */
public class CommentWord implements SuperWord {

	/** 文字列保持 */
	private String word;
	
	/**
	 * コンストラクタ
	 * @param word
	 */
	public CommentWord(String word) {
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
