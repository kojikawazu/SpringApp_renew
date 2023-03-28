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

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.user.LoginDaoSql;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.DataNotFoundException;
import com.example.demo.common.log.IntroAppLogWriter;

class LoginServiceUseTest2 {

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

	/** SQL文(全選択) */
	private final String SQL_SELECT_ALL = WebConsts.SQL_SELECT + " " + PARAM_ID + ", "
			+ PARAM_USER_ID    + ", "
			+ PARAM_SESSION_ID + ", "
			+ PARAM_CREATED    + ", "
			+ PARAM_UPDATED    + " "
			+ WebConsts.SQL_FROM + " " + DB_NAME;

	/** SQL文(ログインIDによる選択) */
	private final String SQL_SELECT_BY_ID = WebConsts.SQL_SELECT + " " + PARAM_ID + ", "
			+ PARAM_USER_ID    + ", "
			+ PARAM_SESSION_ID + ", "
			+ PARAM_CREATED    + ", "
			+ PARAM_UPDATED    + " "
			+ WebConsts.SQL_FROM + " " + DB_NAME + " "
			+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

	/** SQL文(ユーザーIDによる選択) */
	private final String SQL_SELECT_BY_USER_ID = WebConsts.SQL_SELECT + " " +  PARAM_ID + ", "
			+ PARAM_USER_ID    + ", "
			+ PARAM_SESSION_ID + ", "
			+ PARAM_CREATED    + ", "
			+ PARAM_UPDATED    + " "
			+ WebConsts.SQL_FROM + " " + DB_NAME + " "
			+ WebConsts.SQL_WHERE + " " + PARAM_USER_ID + " = ?";

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
	 * 初期化
	 */
	@BeforeEach
	public void init() {
		this.setMock();
		this.setService();
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 全選択テストの準備
	 */
	private void initGetAll() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PARAM_ID, 1);
		map.put(PARAM_USER_ID, 1);
		map.put(PARAM_SESSION_ID, "1");
		map.put(PARAM_CREATED, Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(
				SQL_SELECT_ALL
				)).thenReturn(mapList);
	}

	/**
	 * 全選択テスト
	 */
	@Test
	public void getAllTest() {
		initGetAll();

		List<LoginModel> resultList = this.service.getAll();

		assertNotNull(resultList);
		assertEquals(1,   resultList.size());
		assertEquals(1,   resultList.get(0).getId());
		assertEquals(1,   resultList.get(0).getUserId());
		assertEquals("1", resultList.get(0).getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				resultList.get(0).getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				resultList.get(0).getUpdated().toString());
	}

	/**
	 * 全選択テストの準備(異常系)
	 */
	private void initGetAll_Empty() {
		when(this.jdbcTemp.queryForList(
				SQL_SELECT_ALL
				)).thenReturn(null);
	}

	/**
	 * 全選択テスト(異常系)
	 */
	@Test
	public void getAllTest_Error() {
		initGetAll_Empty();

		assertDoesNotThrow(() -> this.service.getAll());
		List<LoginModel> resultList = this.service.getAll();

		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * IDによる選択テストの準備
	 */
	private void initSelect_byLoginId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PARAM_ID, 1);
		map.put(PARAM_USER_ID, 1);
		map.put(PARAM_SESSION_ID, "1");
		map.put(PARAM_CREATED, Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, Timestamp.valueOf(TestConsts.TEST_TIME_02));

		when(this.jdbcTemp.queryForMap(
				SQL_SELECT_BY_ID,
				1
				)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(
				SQL_SELECT_BY_ID,
				2
				)).thenReturn(null);
	}

	/**
	 * IDによる選択テスト
	 */
	@Test
	public void select_byLoginIdTest() {
		initSelect_byLoginId();

		LoginId loginId = new LoginId(1);
		assertDoesNotThrow(() -> this.service.select(loginId));

		LoginModel result = this.service.select(loginId);
		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals(1, result.getUserId());
		assertEquals("1", result.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				result.getUpdated().toString());
	}

	/**
	 * IDによる選択テスト(異常系)
	 */
	@Test
	public void select_byLoginIdTest_Error() {
		initSelect_byLoginId();

		LoginId loginId = new LoginId(2);
		assertThrows(DataNotFoundException.class, () -> this.service.select(loginId));
		verify(this.jdbcTemp, times(1)).queryForMap(SQL_SELECT_BY_ID, 2);
	}

	/**
	 * IDによる選択テスト(引数エラー)
	 */
	@Test
	public void select_byLoginIdTest_ArgumentsError() {
		initSelect_byLoginId();

		assertThrows(DataNotFoundException.class, () -> this.service.select((LoginId)null));
		verify(this.jdbcTemp, times(0)).queryForMap(SQL_SELECT_BY_ID, 1);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * ユーザーIDによる選択テストの準備
	 */
	private void initSelect_byUserId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PARAM_ID, 1);
		map.put(PARAM_USER_ID, 1);
		map.put(PARAM_SESSION_ID, "1");
		map.put(PARAM_CREATED, Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, Timestamp.valueOf(TestConsts.TEST_TIME_02));

		when(this.jdbcTemp.queryForMap(
				SQL_SELECT_BY_USER_ID,
				1
				)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(
				SQL_SELECT_BY_USER_ID,
				2
				)).thenReturn(null);
	}

	/**
	 * ユーザーIDによる選択テスト
	 */
	@Test
	public void select_byUserIdTest() {
		initSelect_byUserId();

		UserId userId = new UserId(1);
		assertDoesNotThrow(() -> this.service.select(userId));

		LoginModel result = this.service.select(userId);
		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals(1, result.getUserId());
		assertEquals("1", result.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				result.getUpdated().toString());
	}

	/**
	 * ユーザーIDによる選択テスト(異常系)
	 */
	@Test
	public void select_byUserIdTest_Error() {
		initSelect_byUserId();

		UserId userId = new UserId(2);
		assertThrows(DataNotFoundException.class, () -> this.service.select(userId));
		verify(this.jdbcTemp, times(1)).queryForMap(SQL_SELECT_BY_USER_ID, 2);
	}

	/**
	 * ユーザーIDによる選択テスト(引数エラー)
	 */
	@Test
	public void select_byUserIdTest_ArgumentsError() {
		initSelect_byUserId();

		assertThrows(DataNotFoundException.class, () -> this.service.select((UserId)null));
		verify(this.jdbcTemp, times(0)).queryForMap(SQL_SELECT_BY_USER_ID, 1);
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
