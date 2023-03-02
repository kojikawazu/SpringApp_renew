package com.example.demo.common.number;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 通常数値クラス(テスト)
 * @author nanai
 *
 */
class NormalNumberTest {

	/** テスト材料 */
	private static final int TEST_VALUE = 1;

	/** テスト対象 */
	private NormalNumber test = null;

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		this.test = null;
	}

	/**
	 * コンストラクタテスト(初回)
	 */
	@Test
	void constractorTest() {
		test = new NormalNumber();

		assertNotNull(test.getNumber());
		assertEquals(test.getNumber(), 0);
	}

	/**
	 * コンストラクタテスト(引数あり)(準備)
	 */
	private void preConstractorObjectTest() {
		this.test = new NormalNumber(TEST_VALUE);
	}

	/**
	 * コンストラクタテスト(引数あり)
	 */
	@Test
	void constractorObjectTest() {
		preConstractorObjectTest();

		assertNotNull(test.getNumber());
		assertEquals(test.getNumber(), TEST_VALUE);
	}
	
	/**
	 * 初期化テスト(準備)
	 */
	private void preInitTest() {
		this.test = new NormalNumber(TEST_VALUE);
	}

	/**
	 * 初期化テスト
	 */
	@Test
	void initTest() {
		preInitTest();
		this.test.Init();

		assertNotNull(test.getNumber());
		assertEquals(test.getNumber(), 0);
	}

	/**
	 * setterテスト(オブジェクト)(準備)
	 */
	private void preSetterObjectTest() {
		this.test = new NormalNumber();
	}

	/**
	 * setterテスト(オブジェクト)
	 */
	@Test
	void setterObjectTest() {
		NormalNumber obj = new NormalNumber();
		obj.setNumber(TEST_VALUE);

		preSetterObjectTest();
		this.test.setNumber(obj);

		assertNotNull(test.getNumber());
		assertEquals(test.getNumber(), TEST_VALUE);
	}

}
