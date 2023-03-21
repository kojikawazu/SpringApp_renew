package com.example.demo.app.entity.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.list.WordList;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

class SecUserModelTest {

	/** テスト対象 */
	private SecUserModel test = null;

	/**
	 * 初期化
	 */
	@BeforeEach
	public void init() {
		test = new SecUserModel();
	}

	// -------------------------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0, test.getId().getId());
		assertEquals("", test.getName());
		assertEquals("", test.getEmail());
		assertEquals("", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
	}

	// -------------------------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタテスト(引数割り当て7個)
	 */
	@Test
	void constractorTest_Obj7() {
		test = new SecUserModel(
				new UserId(1), 
				new NameWord("テスト"), 
				new EmailWord("テスト"),
				new PasswdWord("テスト"), 
				new WordList(), 
				TestConsts.TEST_TIME_01, 
				TestConsts.TEST_TIME_02);

		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(1, test.getId().getId());
		assertEquals("テスト", test.getName());
		assertEquals("テスト", test.getEmail());
		assertEquals("テスト", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
	}

	/**
	 * コンストラクタテスト(引数割り当て7個)(null)
	 */
	@Test
	void constractorTest_Obj7_Null() {
		test = new SecUserModel(
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null);

		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0, test.getId().getId());
		assertEquals("", test.getName());
		assertEquals("", test.getEmail());
		assertEquals("", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
	}

	// -------------------------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタテスト(引数割り当て6個)
	 */
	@Test
	void constractorTest_Obj6() {
		test = new SecUserModel(
				new NameWord("テスト"), 
				new EmailWord("テスト"),
				new PasswdWord("テスト"), 
				new WordList(), 
				TestConsts.TEST_TIME_01, 
				TestConsts.TEST_TIME_02);

		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0, test.getId().getId());
		assertEquals("テスト", test.getName());
		assertEquals("テスト", test.getEmail());
		assertEquals("テスト", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
	}

	/**
	 * コンストラクタテスト(引数割り当て6個)(null)
	 */
	@Test
	void constractorTest_Obj6_Null() {
		test = new SecUserModel(
				(NameWord)null, 
				(EmailWord)null, 
				(PasswdWord)null, 
				(WordList)null, 
				(LocalDateTime)null, 
				(LocalDateTime)null);

		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0, test.getId().getId());
		assertEquals("", test.getName());
		assertEquals("", test.getEmail());
		assertEquals("", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
	}

	// -------------------------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタテスト(引数割り当て6個)
	 */
	@Test
	void constractorTest_Obj6_2() {
		test = new SecUserModel(
				new UserId(1),
				new NameWord("テスト"),
				new EmailWord("テスト"),
				new PasswdWord("テスト"),
				TestConsts.TEST_TIME_01, 
				TestConsts.TEST_TIME_02);

		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(1, test.getId().getId());
		assertEquals("テスト", test.getName());
		assertEquals("テスト", test.getEmail());
		assertEquals("テスト", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
	}

	/**
	 * コンストラクタテスト(引数割り当て6個)(null)
	 */
	@Test
	void constractorTest_Obj6_2_Null() {
		test = new SecUserModel(
				(UserId)null,
				(NameWord)null, 
				(EmailWord)null, 
				(PasswdWord)null,
				(LocalDateTime)null, 
				(LocalDateTime)null);

		assertNotNull(test);
		assertNotNull(test.getId());
		assertNotNull(test.getName());
		assertNotNull(test.getEmail());
		assertNotNull(test.getPassword());
		assertNotNull(test.getRoleList());
		assertNotNull(test.getCreated());
		assertNotNull(test.getUpdated());

		assertEquals(0, test.getId().getId());
		assertEquals("", test.getName());
		assertEquals("", test.getEmail());
		assertEquals("", test.getPassword());
		assertEquals(0, test.getRoleList().getList().size());
	}

	// -------------------------------------------------------------------------------------------------------------------

		/**
		 * コンストラクタテスト(Model)
		 */
		@Test
		void constractorTest_Model() {
			SecUserModel model = new SecUserModel(
					new UserId(1),
					new NameWord("テスト"),
					new EmailWord("テスト"),
					new PasswdWord("テスト"),
					TestConsts.TEST_TIME_01, 
					TestConsts.TEST_TIME_02);
			test = new SecUserModel(model);

			assertNotNull(test);
			assertNotNull(test.getId());
			assertNotNull(test.getName());
			assertNotNull(test.getEmail());
			assertNotNull(test.getPassword());
			assertNotNull(test.getRoleList());
			assertNotNull(test.getCreated());
			assertNotNull(test.getUpdated());

			assertEquals(1, test.getId().getId());
			assertEquals("テスト", test.getName());
			assertEquals("テスト", test.getEmail());
			assertEquals("テスト", test.getPassword());
			assertEquals(0, test.getRoleList().getList().size());
			assertEquals(TestConsts.TEST_TIME_01.toString(), test.getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(), test.getUpdated().toString());
		}

		/**
		 * コンストラクタテスト(Model)(null)
		 */
		@Test
		void constractorTest_Model_Null() {
			test = new SecUserModel(null);

			assertNotNull(test);
			assertNotNull(test.getId());
			assertNotNull(test.getName());
			assertNotNull(test.getEmail());
			assertNotNull(test.getPassword());
			assertNotNull(test.getRoleList());
			assertNotNull(test.getCreated());
			assertNotNull(test.getUpdated());

			assertEquals(0, test.getId().getId());
			assertEquals("", test.getName());
			assertEquals("", test.getEmail());
			assertEquals("", test.getPassword());
			assertEquals(0, test.getRoleList().getList().size());
		}

	// -------------------------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void release() {
		test.getRoleList().getList().clear();
	}
}
