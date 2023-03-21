package com.example.demo.app.entity.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.common.consts.TestConsts;

/**
 * ログインモデルクラステスト
 * @author nanai
 *
 */
class LoginModelTest {

	/**
	 * ログインモデル
	 * {@link LoginModel}
	 */
	LoginModel	test = null;

	/** ------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void init() {
		test = new LoginModel();
	}

	/** ------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void constractorTest() {
		assertNotNull(test.getId());
		assertNotNull(test.getUserId());
		assertNotNull(test.getSessionId());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0,  test.getId());
		assertEquals(0,  test.getUserId());
		assertEquals("", test.getSessionId());
	}

	/** ------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト(値4つ)の初期化
	 */
	public void initConstractorTest_Value4() {
		test = new LoginModel(
				new UserId(1),
				new SessionId("テスト"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}

	/**
	 * コンストラクタテスト(値4つ)
	 */
	@Test
	public void constractorTest_Value4() {
		initConstractorTest_Value4();

		assertEquals(0,  		test.getId());
		assertEquals(1,  		test.getUserId());
		assertEquals("テスト", 	test.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
	}

	/**
	 * コンストラクタテスト(値4つ)の初期化(null)
	 */
	public void initConstractorTest_Value4_Null() {
		test = new LoginModel(
				null,
				null,
				null,
				null
				);
	}

	/**
	 * コンストラクタテスト(値4つ)(null)
	 */
	@Test
	public void constractorTest_Value4_Null() {
		initConstractorTest_Value4_Null();

		assertNotNull(test.getId());
		assertNotNull(test.getUserId());
		assertNotNull(test.getSessionId());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0,  test.getId());
		assertEquals(0,  test.getUserId());
		assertEquals("", test.getSessionId());
	}

	/** ------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト(値5つ)の初期化
	 */
	public void initConstractorTest_Value5() {
		test = new LoginModel(
				new LoginId(1),
				new UserId(1),
				new SessionId("テスト"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}

	/**
	 * コンストラクタテスト(値5つ)
	 */
	@Test
	public void constractorTest_Value5() {
		initConstractorTest_Value5();

		assertEquals(1,  		test.getId());
		assertEquals(1,  		test.getUserId());
		assertEquals("テスト", 	test.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
	}

	/**
	 * コンストラクタテスト(値5つ)の初期化(null)
	 */
	public void initConstractorTest_Value5_Null() {
		test = new LoginModel(
				null,
				null,
				null,
				null,
				null
				);
	}

	/**
	 * コンストラクタテスト(値5つ)(null)
	 */
	@Test
	public void constractorTest_Value5_Null() {
		initConstractorTest_Value5_Null();

		assertNotNull(test.getId());
		assertNotNull(test.getUserId());
		assertNotNull(test.getSessionId());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0,  test.getId());
		assertEquals(0,  test.getUserId());
		assertEquals("", test.getSessionId());
	}

	/** ------------------------------------------------------------------------------- */

	/**
	 * model渡しコンストラクタテスト(null)
	 */
	@Test
	public void constractorTest_ModelNull() {
		test = new LoginModel(null);

		assertNotNull(test.getId());
		assertNotNull(test.getUserId());
		assertNotNull(test.getSessionId());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0,  test.getId());
		assertEquals(0,  test.getUserId());
		assertEquals("", test.getSessionId());
	}
	
	/**
	 * model渡しコンストラクタテスト
	 */
	@Test
	public void constractorTest_Model() {
		LoginModel model = new LoginModel(
				new LoginId(1),
				new UserId(1),
				new SessionId("テスト"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		test = new LoginModel(model);

		assertEquals(1,     	test.getId());
		assertEquals(1,     	test.getUserId());
		assertEquals("テスト", 	test.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
	}

	/** ------------------------------------------------------------------------------- */

	/**
	 * リリース
	 */
	@AfterEach
	public void Release() {
		test = null;
	}
}
