package com.example.demo.json.word;

import com.example.demo.common.word.SuperWord;

/**
 * イントロ使用文字列
 * @author nanai
 *
 */
public class IntroWord implements SuperWord {
	
	/** 文字列保持 */
	private String word;
	
	/**
	 * コンストラクタ
	 * @param word
	 */
	public IntroWord(String word) {
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
