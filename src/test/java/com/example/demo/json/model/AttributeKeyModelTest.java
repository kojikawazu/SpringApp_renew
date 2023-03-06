package com.example.demo.json.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.list.WordList;

/**
 * attributeキーモデル(テスト)
 * @author nanai
 *
 */
class AttributeKeyModelTest {

	/** 対象 */
	private AttributeKeyModel test = null;

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		test = null;
	}

	/**
	 * コンストラクタテスト(初回)
	 */
	@Test
	void constractorTest() {
		test = new AttributeKeyModel();

		assertNotNull(test.getTitleKeyList());
		assertNotNull(test.getBodyKeyList());

		assertEquals(0, test.getTitleKeyList().getList().size());
		assertEquals(0, test.getBodyKeyList().getList().size());
	}

	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorObjTest() {
		test = new AttributeKeyModel(
				new WordList(),
				new WordList());

		assertNotNull(test.getTitleKeyList());
		assertNotNull(test.getBodyKeyList());

		assertEquals(0, test.getTitleKeyList().getList().size());
		assertEquals(0, test.getBodyKeyList().getList().size());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorNullTest() {
		test = new AttributeKeyModel(
				null,
				null);

		assertNotNull(test.getTitleKeyList());
		assertNotNull(test.getBodyKeyList());

		assertEquals(0, test.getTitleKeyList().getList().size());
		assertEquals(0, test.getBodyKeyList().getList().size());
	}

	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		test.getTitleKeyList().getList().clear();
		test.getBodyKeyList().getList().clear();
	}
}
