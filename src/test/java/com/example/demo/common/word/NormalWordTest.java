package com.example.demo.common.word;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 文字列ラッパークラステスト
 * @author nanai
 *
 */
class NormalWordTest {

	/** テスト対象 */
	private NormalWord test = null;

	/** --------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		test = new NormalWord();

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorTest_null() {
		test = new NormalWord(null);

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}

	/**
	 * コンストラクタテスト(文字列)
	 */
	@Test
	void constractorTest_string() {
		test = new NormalWord("a");

		assertNotNull(test.getWord());
		assertEquals("a", test.getWord());
	}

	/** --------------------------------------------------------------------------------------- */

	/**
	 * setterテスト
	 */
	@Test
	void setterTest() {
		test = new NormalWord();
		test.setWord("a");

		assertNotNull(test.getWord());
		assertEquals("a", test.getWord());
	}

	/**
	 * setterテスト
	 */
	@Test
	void setterTest_obj() {
		test = new NormalWord();
		NormalWord obj = new NormalWord("a");
		test.setWord(obj);

		assertNotNull(test.getWord());
		assertEquals("a", test.getWord());
	}

	/**
	 * setterテスト(null)
	 */
	@Test
	void setterTest_string_null() {
		test = new NormalWord();
		test.setWord((String)null);

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}

	/**
	 * setterテスト(null)
	 */
	@Test
	void setterTest_obj_null() {
		test = new NormalWord();
		test.setWord((NormalWord)null);

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}
}
