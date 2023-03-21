package com.example.demo.app.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.user.SecUserDaoSql;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.exception.SQLNoUpdateException;
import com.example.demo.common.list.WordList;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーサービスクラス(テスト)
 */
class SecUserServiceUseTest {

	/**
	 * DB
	 */
	private final String DB_NAME 			= "security_login_user";
	private final String DB_ROLE_NAME		= "security_roles";
	private final String DB_USER_ROLE_NAME	= "security_user_role";

	/**
	 * パラメータ
	 */
	private final String PARAM_ID			= WebConsts.SQL_ID_NAME;
	private final String PARAM_NAME			= WebConsts.SQL_NAME_NAME;
	private final String PARAM_EMAIL		= WebConsts.SQL_EMAIL_NAME;
	private final String PARAM_PASSWORD		= WebConsts.SQL_PASSWORD_NAME;
	private final String PARAM_CREATED		= WebConsts.SQL_CREATED_NAME;
	private final String PARAM_UPDATED		= WebConsts.SQL_UPDATED_NAME;
	private final String PARAM_ROLE_NAME	= WebConsts.SQL_ROLE_NAME;
	private final String PARAM_ROLE_ID		= WebConsts.SQL_ROLE_ID;
	private final String PARAM_USER_ID		= WebConsts.SQL_USER_ID_NAME;

	/** 追加SQL文 */
	private final String SQL_INSERT = WebConsts.SQL_INSERT + " " 
			+ DB_NAME 
			+ "("
			+ PARAM_NAME		+ ", " 
			+ PARAM_EMAIL		+ ", " 
			+ PARAM_PASSWORD 	+ ", " 
			+ PARAM_CREATED 	+ ", "
			+ PARAM_UPDATED 
			+ ") "
			+ WebConsts.SQL_VALUES + "(?,?,?,?,?)";

	/** 更新SQL文 */
	private final String SQL_UPDATE = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
			+ PARAM_NAME		+ " = ?, "
			+ PARAM_EMAIL		+ " = ?, "
			+ PARAM_PASSWORD	+ " = ?, "
			+ PARAM_UPDATED 	+ " = ? "
			+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

	/** 削除SQL文 */
	private final String SQL_DELETE = WebConsts.SQL_DELETE + " " + DB_NAME + " "
			+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

	/** 全選択SQL文 */
	private final String SQL_SELECT_ALL = WebConsts.SQL_SELECT + " "
			+ DB_NAME + "." + PARAM_ID			+ " " + WebConsts.SQL_AS + " " + PARAM_ID       + ", "
			+ DB_NAME + "." + PARAM_NAME		+ " " + WebConsts.SQL_AS + " " + PARAM_NAME     + ", " 
			+ DB_NAME + "." + PARAM_EMAIL		+ " " + WebConsts.SQL_AS + " " + PARAM_EMAIL    + ", " 
			+ DB_NAME + "." + PARAM_PASSWORD 	+ " " + WebConsts.SQL_AS + " " + PARAM_PASSWORD + ", " 
			+ DB_NAME + "." + PARAM_CREATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_CREATED  + ", "
			+ DB_NAME + "." + PARAM_UPDATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_UPDATED  + ", "
			+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " " + WebConsts.SQL_AS + " " + PARAM_ROLE_NAME + " "
			+ WebConsts.SQL_FROM + " " + DB_NAME + " "
			+ WebConsts.SQL_JOIN + " " + DB_USER_ROLE_NAME + " "
			+ WebConsts.SQL_ON   + " " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
			+ WebConsts.SQL_JOIN + " " + DB_ROLE_NAME + " "
			+ WebConsts.SQL_ON   + " " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID;

	/** テスト対象 */
	private SecUserServiceUse service = null;

	/** モック対象 */
	@Mock
	private JdbcTemplate jdbcTemp = null;
	@Mock
	private IntroAppLogWriter logWriter = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * Mock設定
	 */
	public void setMock() {
		this.jdbcTemp  = mock(JdbcTemplate.class);
		this.logWriter = mock(IntroAppLogWriter.class);
	}

	/**
	 * サービスの設定
	 */
	public void setService() {
		SecUserDaoSql dao	= new SecUserDaoSql(this.jdbcTemp);
		this.service		= new SecUserServiceUse(dao);

		Field fld;
		try {
			fld = this.service.getClass().getDeclaredField("logWriter");
			fld.setAccessible(true);
			fld.set(this.service, this.logWriter);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
			ex.printStackTrace();
		}
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	private void init() {
		this.setMock();
		this.setService();
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 追加テストの準備
	 */
	private void initSave() {
		when(this.jdbcTemp.update(
			SQL_INSERT, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02
			)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
			SQL_INSERT, 
			"テスト2",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02
			)).thenThrow(RuntimeException.class);
	}

	/**
	 * 追加テスト
	 */
	@Test
	void saveTest() {
		initSave();

		SecUserModel model = new SecUserModel(
			new NameWord("テスト"),
			new EmailWord("Eメール"),
			new PasswdWord("パスワード"),
			new WordList(),
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02);

		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
			SQL_INSERT, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02);
	}

	/**
	 * 追加テスト(エラー)
	 */
	@Test
	void saveTest_Error() {
		initSave();

		SecUserModel model = new SecUserModel(
			new NameWord("テスト2"),
			new EmailWord("Eメール"),
			new PasswdWord("パスワード"),
			new WordList(),
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02);

		this.service.save(model);
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 追加テスト(引数エラー)
	 */
	@Test
	void saveTest_ArgumentsError() {
		initSave();

		this.service.save(null);
		verify(this.jdbcTemp, times(0)).update(
			SQL_INSERT, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 更新テストの準備
	 */
	private void initUpdate() {
		when(this.jdbcTemp.update(
			SQL_UPDATE, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_02,
			1
			)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
			SQL_UPDATE, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_02,
			2
			)).thenThrow(RuntimeException.class);
	}

	/**
	 * 更新テスト
	 */
	@Test
	public void updateTest() {
		initUpdate();

		SecUserModel model = new SecUserModel(
			new UserId(1),
			new NameWord("テスト"),
			new EmailWord("Eメール"),
			new PasswdWord("パスワード"),
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02);

		assertDoesNotThrow(() -> this.service.update(model));
		verify(this.jdbcTemp, times(1)).update(
			SQL_UPDATE, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_02,
			1);
	}

	/**
	 * 更新テスト(エラー)
	 */
	@Test
	public void updateTest_Error() {
		initUpdate();

		SecUserModel model = new SecUserModel(
			new UserId(2),
			new NameWord("テスト"),
			new EmailWord("Eメール"),
			new PasswdWord("パスワード"),
			TestConsts.TEST_TIME_01,
			TestConsts.TEST_TIME_02);

		assertThrows(SQLNoUpdateException.class, () -> this.service.update(model));
	}

	/**
	 * 更新テスト(null)
	 */
	@Test
	public void updateTest_ArgumentsNull() {
		initUpdate();
		assertThrows(SQLNoUpdateException.class, () -> this.service.update(null));
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		when(this.jdbcTemp.update(
				SQL_DELETE, 
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
				SQL_DELETE, 
				2
				)).thenThrow(RuntimeException.class);
	}

	/**
	 * 削除テスト
	 */
	@Test
	public void deleteTest() {
		initDelete();

		UserId userId = new UserId(1);
		assertDoesNotThrow(() -> this.service.delete(userId));
		verify(this.jdbcTemp, times(1)).update(SQL_DELETE, 1);
	}

	/**
	 * 削除テスト(エラー)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();

		UserId userId = new UserId(2);
		assertThrows(SQLNoDeleteException.class, () -> this.service.delete(userId));
	}

	/**
	 * 削除テスト(エラー)
	 */
	@Test
	public void deleteTest_ArgumentsNull() {
		initDelete();

		assertThrows(SQLNoDeleteException.class, () -> this.service.delete(null));
		verify(this.jdbcTemp, times(0)).update(SQL_DELETE, 1);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 全選択テストの準備
	 */
	private void initGetAll() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object>       map        = new HashMap<String, Object>();

		map.put(PARAM_ID, 1);
		map.put(PARAM_NAME, "テスト");
		map.put(PARAM_EMAIL, "Eメール");
		map.put(PARAM_PASSWORD, "パスワード");
		map.put(PARAM_CREATED, Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, Timestamp.valueOf(TestConsts.TEST_TIME_02));
		map.put(PARAM_ROLE_NAME, "ADMIN");
		resultList.add(map);
	
		when(this.jdbcTemp.queryForList(
				SQL_SELECT_ALL
				)).thenReturn(resultList);
	}

	/**
	 * 全選択テスト
	 */
	@Test
	public void getAllTest() {
		initGetAll();

		List<SecUserModel> resultList = this.service.getAll();
		assertNotNull(resultList);
		assertEquals(1, resultList.size());
	}

	/**
	 * 全選択テストの準備(空)
	 */
	private void initGetAll_Empty() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(
				SQL_SELECT_ALL
				)).thenReturn(resultList);
	}

	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void getAllTest_Empty() {
		initGetAll_Empty();

		List<SecUserModel> resultList = this.service.getAll();
		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 後処理
	 */
	@AfterEach
	private void release() {
		this.service = null;
		this.jdbcTemp = null;
		this.logWriter = null;
	}
}
