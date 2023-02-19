package com.example.demo.app.entity.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
	LoginModel	model = null;
	
	/** ------------------------------------------------------------------------------- */
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new LoginModel(
				new LoginId(0),
				new UserId(0),
				new SessionId(""),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}
	
	/** ------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest() {
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getUserId(), 		0);
		Assertions.assertEquals(model.getSessionId(), 	"");
		Assertions.assertEquals(model.getCreated().toString(), 	TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), 	TestConsts.TEST_TIME_02.toString());
	}
	
	/** ------------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタテスト2の初期化
	 */
	public void Init2() {
		model = new LoginModel(
				new UserId(1),
				new SessionId("テスト"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}
	
	/**
	 * コンストラクタテスト2
	 */
	@Test
	public void InitTest2() {
		Init2();
		
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getUserId(), 		1);
		Assertions.assertEquals(model.getSessionId(), 	"テスト");
		Assertions.assertEquals(model.getCreated().toString(), 	TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), 	TestConsts.TEST_TIME_02.toString());
	}
	
	/** ------------------------------------------------------------------------------- */
	
	/**
	 * nullコンストラクタテストの初期化
	 */
	public void InitNull() {
		model = new LoginModel(
				null,
				null,
				null,
				null,
				null
				);
	}
	
	/**
	 * nullコンストラクタテスト
	 */
	@Test
	public void InitNull_Test() {
		InitNull();
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getUserId());
		Assertions.assertNotNull(model.getSessionId());
		Assertions.assertNotNull(model.getCreated());
		Assertions.assertNotNull(model.getUpdated());
	}
	
	/** ------------------------------------------------------------------------------- */
	
	/**
	 * model渡しコンストラクタテストの初期化
	 */
	@Test
	public void InitModel_Test() {
		LoginModel test = new LoginModel(
				new LoginId(1),
				new UserId(1),
				new SessionId("テスト"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		model = new LoginModel(test);
		
		Assertions.assertEquals(model.getId(), 			1);
		Assertions.assertEquals(model.getUserId(), 		1);
		Assertions.assertEquals(model.getSessionId(), 	"テスト");
		Assertions.assertEquals(model.getCreated().toString(), 	TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), 	TestConsts.TEST_TIME_02.toString());
	}
	/** ------------------------------------------------------------------------------- */
	
	/**
	 * リリース
	 */
	@AfterEach
	public void Release() {
		model = null;
	}

}
