package com.example.demo.common.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 文字列リストクラス(テスト)
 * @author nanai
 *
 */
class WordListTest {

	private static final String TEST_WORD = "a";
	
	/** 対象 */
	private WordList test = null;

	@BeforeEach
	void init() {
		test = null;
	}

	/**
	 * コンストラクタテスト(初回)
	 */
	@Test
	void constractorTest() {
		test = new WordList();

		assertNotNull(test.getList());
		assertEquals(test.getList().size(), 0);
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorNullTest() {
		test = new WordList(null);

		assertNotNull(test.getList());
		assertEquals(test.getList().size(), 0);
	}

	/**
	 * コンストラクタテスト(引数あり)(準備)
	 */
	private void preConstractorObjectTest() {
		List<String> list = new ArrayList<String>();
		list.add(TEST_WORD);
		test = new WordList(list);
	}

	/**
	 * コンストラクタテスト(引数あり)
	 */
	@Test
	void constractorObjectTest() {
		String resultString = "";
		preConstractorObjectTest();

		assertNotNull(test.getList());
		assertEquals(test.getList().size(), 1);
		resultString = test.getList().get(0);
		assertEquals(resultString, TEST_WORD);
	}

	/**
	 * setterテスト(リスト)(準備)
	 */
	private void preSetterTest() {
		test = new WordList(null);
	}

	/**
	 * setterテスト(リスト)
	 */
	@Test
	void setterTest() {
		String resultString = "";
		List<String> list = new ArrayList<String>();
		list.add(TEST_WORD);

		preSetterTest();
		this.test.setList(list);

		assertNotNull(test.getList());
		assertEquals(test.getList().size(), 1);
		resultString = test.getList().get(0);
		assertEquals(resultString, TEST_WORD);
	}

	/**
	 * setterテスト(オブジェクト)(準備)
	 */
	private void preSetterObjectTest() {
		test = new WordList(null);
	}

	/**
	 * setterテスト(オブジェクト)
	 */
	@Test
	void setterObjectTest() {
		String resultString = "";
		WordList obj = new WordList();
		List<String> list = new ArrayList<String>();
		list.add(TEST_WORD);
		obj.setList(list);

		preSetterObjectTest();
		this.test.setList(obj);

		assertNotNull(test.getList());
		assertEquals(test.getList().size(), 1);
		resultString = test.getList().get(0);
		assertEquals(resultString, TEST_WORD);
	}
}
