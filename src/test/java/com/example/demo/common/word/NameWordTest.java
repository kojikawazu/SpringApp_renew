package com.example.demo.common.word;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 名前文字列クラステスト
 * @author nanai
 *
 */
class NameWordTest {

	/** テスト対象 */
	private NameWord test = null;

	/** --------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constactorTest() {
		test = new NameWord();

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constactorTest_null() {
		test = new NameWord(null);

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}

	/**
	 * コンストラクタテスト(obj)
	 */
	@Test
	void constactorTest_obj() {
		test = new NameWord("a");

		assertNotNull(test.getWord());
		assertEquals("a", test.getWord());
	}

	/** --------------------------------------------------------------------------------------- */

	/**
	 * setterテスト
	 */
	@Test
	void setterTest() {
		test = new NameWord();
		NameWord name = new NameWord("a");
		test.setWord(name);

		assertNotNull(test.getWord());
		assertEquals("a", test.getWord());
	}

	/**
	 * setterテスト(obj)
	 */
	@Test
	void setterTest_obj() {
		test = new NameWord();
		test.setWord((NameWord)null);

		assertNotNull(test.getWord());
		assertEquals("", test.getWord());
	}
}
