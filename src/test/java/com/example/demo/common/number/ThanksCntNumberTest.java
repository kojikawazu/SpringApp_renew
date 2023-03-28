package com.example.demo.common.number;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * いいね数クラス(テスト)
 * @author nanai
 *
 */
class ThanksCntNumberTest {

	/** テスト対象 */
	ThanksCntNumber test = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		test = new ThanksCntNumber();
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		assertNotNull(test.getNumber());
		assertEquals(0, test.getNumber());
	}

	/**
	 * コンストラクタテスト(値)
	 */
	@Test
	void constractorTest_Value() {
		test = new ThanksCntNumber(1);
		assertNotNull(test.getNumber());
		assertEquals(1, test.getNumber());
	}

	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorTest_Obj() {
		ThanksCntNumber obj = new ThanksCntNumber(2);

		test = new ThanksCntNumber(obj);
		assertNotNull(test.getNumber());
		assertEquals(2, test.getNumber());
	}

	/**
	 * setterテスト(オブジェクト)
	 */
	@Test
	void setterTest_Obj() {
		ThanksCntNumber obj = new ThanksCntNumber(2);

		test.setNumber(obj);
		assertNotNull(test.getNumber());
		assertEquals(2, test.getNumber());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		test = null;
	}
}
