package com.example.demo.app.service.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.user.LoginDaoSql;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.SQLNoUpdateException;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * ログインサービスのテスト
 * @author nanai
 *
 */
class LoginServiceUseTest {

	/**
	 * DB名
	 */
	private final String DB_NAME = "login_user";

	/**
	 * パラム名
	 */
	private final String PARAM_ID 			= "id";
	private final String PARAM_USER_ID 		= "user_id";
	private final String PARAM_SESSION_ID	= "session_id";
	private final String PARAM_CREATED		= "created";
	private final String PARAM_UPDATED		= "updated";

	/** 追加SQL文 */
	private final String SQL_INSERT = WebConsts.SQL_INSERT + " " + DB_NAME
			+ "("
			+ PARAM_USER_ID    + ", "
			+ PARAM_SESSION_ID + ", "
			+ PARAM_CREATED    + ", "
			+ PARAM_UPDATED
			+ ") "
			+ WebConsts.SQL_VALUES + "(?,?,?,?)";

	/** 更新SQL文 */
	private final String SQL_UPDATE = "UPDATE login_user SET "
			+ "user_id = ?, session_id = ?, updated = ? "
			+ "WHERE id = ?";

	/** 削除SQL文 */
	private final String SQL_DELETE = WebConsts.SQL_DELETE + " " + DB_NAME + " "
			+ "WHERE " + PARAM_ID + " = ?";

	/** 削除SQL文(user_id) */
	private final String SQL_DELETE_WHERE_USER_ID = WebConsts.SQL_DELETE + " " + DB_NAME + " "
			+ "WHERE " + PARAM_USER_ID + " = ?";

	/** 削除SQL文(全削除) */
	private final String SQL_DELETE_ALL = WebConsts.SQL_DELETE + " " + DB_NAME;

	/** テスト対象 */
	private LoginServiceUse service = null;

	/** モック */
	@Mock
	private JdbcTemplate jdbcTemp = null;
	@Mock
	private IntroAppLogWriter logWriter = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * サービスの設定
	 */
	public void setService() {
		LoginDaoSql dao  = new LoginDaoSql(this.jdbcTemp);
		this.service     = new LoginServiceUse(dao);

		Field fld;
		try {
			fld = this.service.getClass().getDeclaredField("logWriter");
			fld.setAccessible(true);
			fld.set(this.service, this.logWriter);
		} catch (IllegalArgumentException | 
				IllegalAccessException | 
				NoSuchFieldException | 
				SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mock設定
	 */
	public void setMock() {
		// Mock化
		this.jdbcTemp  = mock(JdbcTemplate.class);
		this.logWriter = mock(IntroAppLogWriter.class);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 追加テストの準備
	 */
	private void initSave() {
		this.setMock();

		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				"1",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
				SQL_INSERT, 
				2,
				"1",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenThrow(RuntimeException.class);

		this.setService();
	}

	/**
	 * 追加テスト
	 */
	@Test
	public void saveTest() {
		initSave();

		LoginModel model = new LoginModel(
				new LoginId(),
				new UserId(1),
				new SessionId("1"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				1,
				"1",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}

	/**
	 * 追加テスト(error)
	 */
	@Test
	public void saveTest_error() {
		initSave();

		LoginModel model = new LoginModel(
				new LoginId(),
				new UserId(2),
				new SessionId("1"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		this.service.save(model);
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 追加テスト(null)
	 */
	@Test
	public void saveTest_null() {
		initSave();

		this.service.save(null);
		verify(this.jdbcTemp, times(0)).update(
				SQL_INSERT, 
				1,
				"1",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}
	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 更新テストの準備
	 */
	private void initUpdate() {
		this.setMock();

		when(this.jdbcTemp.update(
				SQL_UPDATE, 
				2,
				"1",
				TestConsts.TEST_TIME_01,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
				SQL_UPDATE, 
				2,
				"1",
				TestConsts.TEST_TIME_01,
				2
				)).thenThrow(RuntimeException.class);

		this.setService();
	}

	/**
	 * 更新テスト
	 */
	@Test
	public void updateTest() {
		initUpdate();

		LoginModel model = new LoginModel(
				new LoginId(1),
				new UserId(2),
				new SessionId("1"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_01
				);

		this.service.update(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_UPDATE, 
				2,
				"1",
				TestConsts.TEST_TIME_01,
				1);
	}

	/**
	 * 更新テスト(エラー)
	 */
	@Test
	public void updateTest_error() {
		initUpdate();

		LoginModel model = new LoginModel(
				new LoginId(2),
				new UserId(2),
				new SessionId("1"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_01
				);

		assertThrows(SQLNoUpdateException.class, () -> this.service.update(model));
	}

	/**
	 * 更新テスト(null)
	 */
	@Test
	public void updateTest_null() {
		initUpdate();

		assertThrows(SQLNoUpdateException.class, () -> this.service.update(null));
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		this.setMock();

		when(this.jdbcTemp.update(
				SQL_DELETE, 
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
				SQL_DELETE, 
				2
				)).thenThrow(RuntimeException.class);

		this.setService();
	}

	/**
	 * 削除テスト
	 */
	@Test
	public void deleteTest() {
		initDelete();

		LoginId loginId = new LoginId(1);
		this.service.delete(loginId);
		verify(this.jdbcTemp, times(1)).update(SQL_DELETE, 1);
	}
	
	/**
	 * 削除テスト(エラー)
	 */
	@Test
	public void deleteTest_error() {
		initDelete();

		LoginId loginId = new LoginId(2);
		this.service.delete(loginId);
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 削除テスト(null)
	 */
	@Test
	public void deleteTest_null() {
		initDelete();

		this.service.delete((LoginId)null);
		verify(this.jdbcTemp, times(0)).update(SQL_DELETE, 1);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 削除テストの準備(ユーザーID)
	 */
	private void initDeleteUserId() {
		this.setMock();

		when(this.jdbcTemp.update(
				SQL_DELETE_WHERE_USER_ID, 
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		when(this.jdbcTemp.update(
				SQL_DELETE_WHERE_USER_ID, 
				2
				)).thenThrow(RuntimeException.class);

		this.setService();
	}

	/**
	 * 削除テスト(ユーザーID)
	 */
	@Test
	public void deleteUserIdTest() {
		initDeleteUserId();

		UserId userId = new UserId(1);
		this.service.delete(userId);
		verify(this.jdbcTemp, times(1)).update(SQL_DELETE, 1);
	}
	
	/**
	 * 削除テスト(ユーザーID)(エラー)
	 */
	@Test
	public void deleteUserIdTest_error() {
		initDeleteUserId();

		UserId userId = new UserId(1);
		this.service.delete(userId);
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 削除テスト(ユーザーID)(null)
	 */
	@Test
	public void deleteUserIdTest_null() {
		initDeleteUserId();

		this.service.delete((UserId)null);
		verify(this.jdbcTemp, times(0)).update(SQL_DELETE, 1);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 全削除テストの準備
	 */
	private void initDeleteAll() {
		this.setMock();

		when(this.jdbcTemp.update(
				SQL_DELETE_ALL
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		this.setService();
	}

	@Test
	public void deleteAllTest() {
		initDeleteAll();

		this.service.deleteAll();
		verify(this.jdbcTemp, times(1)).update(SQL_DELETE_ALL);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 後処理
	 */
	@AfterEach
	public void release() {
		this.jdbcTemp  = null;
		this.service   = null;
		this.logWriter = null;
	}
}
