package com.example.demo.app.entity.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.id.user.UserKindId;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * ユーザーモデルテスト
 * @author nanai
 *
 */
class UserModelTest {

	/** 
	 * ユーザーモデル
	 * {@link UserModel}
	 */
	UserModel	model = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new UserModel(
				new UserId(0),
				new UserKindId(0),
				new NameWord(""),
				new EmailWord(""),
				new PasswdWord(""),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	void InitTest() {
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getKindId(), 		0);
		Assertions.assertEquals(model.getName(), 		"");
		Assertions.assertEquals(model.getEmail(),	 	"");
		Assertions.assertEquals(model.getPassword(), 	"");
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	void InitNull() {
		model = new UserModel(
				null,
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getKindId());
		Assertions.assertNotNull(model.getName());
		Assertions.assertNotNull(model.getEmail());
		Assertions.assertNotNull(model.getPassword());
		Assertions.assertNotNull(model.getCreated());
		Assertions.assertNotNull(model.getUpdated());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	void InitModel() {
		UserModel test = new UserModel(
				new UserId(1),
				new UserKindId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new PasswdWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		model = new UserModel(test);
		
		Assertions.assertEquals(model.getId(), 			1);
		Assertions.assertEquals(model.getKindId(), 		1);
		Assertions.assertEquals(model.getName(), 		TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),	 	TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getPassword(), 	TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
	}

}
