package com.example.demo.common.id;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ノーマルIDクラステスト
 * @author nanai
 *
 */
class NormalIdTest {

	/** テスト対象 */
	private NormalId test = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void init() {
		test = new NormalId();
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		assertEquals(0, test.getId());
	}

	/**
	 * コンストラクタテスト2
	 */
	@Test
	void constractorTest2() {
		test = new NormalId(1);
		assertEquals(1, test.getId());
	}

	/**
	 * setterテスト(オブジェクト)
	 */
	@Test
	void setterTest_obj() {
		test.setId(new NormalId(2));
		assertEquals(2, test.getId());
	}

	/**
	 * setterテスト(オブジェクト - null)
	 */
	@Test
	void setterTest_objNull() {
		test.setId((NormalId)null);
		assertEquals(0, test.getId());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 後処理
	 */
	@AfterEach
	public void release() {
		test = null;
	}
}
