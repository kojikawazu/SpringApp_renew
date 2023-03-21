package com.example.demo.app.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.user.SecUserDaoSql;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.DataNotFoundException;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーサービスクラス(テスト2)
 */
class SecUserServiceUseTest2 {

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

	/** IDによる選択SQL文 */
	private final String SQL_SELECT_ID = WebConsts.SQL_SELECT + " "
			+ DB_NAME + "." + PARAM_ID			+ " " + WebConsts.SQL_AS + " " + PARAM_ID       + ", "
			+ DB_NAME + "." + PARAM_NAME		+ " " + WebConsts.SQL_AS + " " + PARAM_NAME     + ", " 
			+ DB_NAME + "." + PARAM_EMAIL		+ " " + WebConsts.SQL_AS + " " + PARAM_EMAIL    + ", " 
			+ DB_NAME + "." + PARAM_PASSWORD 	+ " " + WebConsts.SQL_AS + " " + PARAM_PASSWORD + ", " 
			+ DB_NAME + "." + PARAM_CREATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_CREATED  + ", "
			+ DB_NAME + "." + PARAM_UPDATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_UPDATED  + ", "
			+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " " + WebConsts.SQL_AS + " " + PARAM_ROLE_NAME + " "
			+ WebConsts.SQL_FROM + " "  + DB_NAME + " "
			+ WebConsts.SQL_JOIN + " "  + DB_USER_ROLE_NAME + " "
			+ WebConsts.SQL_ON   + " "  + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
			+ WebConsts.SQL_JOIN + " "  + DB_ROLE_NAME + " "
			+ WebConsts.SQL_ON   + " "  + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
			+ WebConsts.SQL_WHERE + " " + DB_NAME + "." + PARAM_ID + " = ?";

	/** Eメールによる選択SQL文 */
	private final String SQL_SELECT_EMAIL = WebConsts.SQL_SELECT + " "
			+ DB_NAME + "." + PARAM_ID			+ " " + WebConsts.SQL_AS + " " + PARAM_ID       + ", "
			+ DB_NAME + "." + PARAM_NAME		+ " " + WebConsts.SQL_AS + " " + PARAM_NAME     + ", " 
			+ DB_NAME + "." + PARAM_EMAIL		+ " " + WebConsts.SQL_AS + " " + PARAM_EMAIL    + ", " 
			+ DB_NAME + "." + PARAM_PASSWORD 	+ " " + WebConsts.SQL_AS + " " + PARAM_PASSWORD + ", " 
			+ DB_NAME + "." + PARAM_CREATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_CREATED  + ", "
			+ DB_NAME + "." + PARAM_UPDATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_UPDATED  + ", "
			+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " " + WebConsts.SQL_AS + " " + PARAM_ROLE_NAME + " "
			+ WebConsts.SQL_FROM + " "  + DB_NAME + " "
			+ WebConsts.SQL_JOIN + " "  + DB_USER_ROLE_NAME + " "
			+ WebConsts.SQL_ON   + " "  + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
			+ WebConsts.SQL_JOIN + " "  + DB_ROLE_NAME + " "
			+ WebConsts.SQL_ON   + " "  + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
			+ WebConsts.SQL_WHERE + " " + DB_NAME + "." + PARAM_EMAIL + " = ?";

	/** Eメールandパスワードによる選択SQL文 */
	private final String SQL_SELECT_EMAIL_PASSWORD = WebConsts.SQL_SELECT + " "
			+ DB_NAME + "." + PARAM_ID			+ " " + WebConsts.SQL_AS + " " + PARAM_ID       + ", "
			+ DB_NAME + "." + PARAM_NAME		+ " " + WebConsts.SQL_AS + " " + PARAM_NAME     + ", " 
			+ DB_NAME + "." + PARAM_EMAIL		+ " " + WebConsts.SQL_AS + " " + PARAM_EMAIL    + ", " 
			+ DB_NAME + "." + PARAM_PASSWORD 	+ " " + WebConsts.SQL_AS + " " + PARAM_PASSWORD + ", " 
			+ DB_NAME + "." + PARAM_CREATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_CREATED  + ", "
			+ DB_NAME + "." + PARAM_UPDATED 	+ " " + WebConsts.SQL_AS + " " + PARAM_UPDATED  + ", "
			+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " " + WebConsts.SQL_AS + " " + PARAM_ROLE_NAME + " "
			+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
			+ WebConsts.SQL_JOIN  + " " + DB_USER_ROLE_NAME + " "
			+ WebConsts.SQL_ON    + " " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
			+ WebConsts.SQL_JOIN  + " " + DB_ROLE_NAME + " "
			+ WebConsts.SQL_ON    + " " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
			+ WebConsts.SQL_WHERE + " " + DB_NAME + "." + PARAM_EMAIL    + " = ? and "
			+                             DB_NAME + "." + PARAM_PASSWORD + " = ?";

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
	 * IDによる選択テストの準備
	 */
	private void initSelectId() {
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
				SQL_SELECT_ID,
				1
				)).thenReturn(resultList);
	}

	/**
	 * IDによる選択テスト
	 */
	@Test
	public void selectIdTest() {
		initSelectId();
		assertDoesNotThrow(() -> this.service.select(new UserId(1)));
	}

	/**
	 * IDによる選択テスト2
	 */
	@Test
	public void selectIdTest2() {
		initSelectId();

		SecUserModel result = this.service.select(new UserId(1));
		assertNotNull(result);
		assertEquals(1,										result.getId().getId());
		assertEquals("テスト",									result.getName());
		assertEquals("Eメール",								result.getEmail());
		assertEquals("パスワード", 								result.getPassword());
		assertEquals(1, 									result.getRoleList().getList().size());
		assertEquals("ADMIN", 								result.getRoleList().getList().get(0));
		assertEquals(TestConsts.TEST_TIME_01.toString(), 	result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), 	result.getUpdated().toString());
	}

	/**
	 * Eメールによる選択テストの準備(空)
	 */
	private void initSelectId_Empty() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		when(this.jdbcTemp.queryForList(
				SQL_SELECT_ID,
				1
				)).thenReturn(resultList);
	}

	/**
	 * IDによる選択テスト(空)
	 */
	@Test
	public void selectIdTest_Empty() {
		initSelectId_Empty();
		assertThrows(DataNotFoundException.class, () -> this.service.select(new UserId(1)));
	}

	/**
	 * IDによる選択テスト(引数null)
	 */
	@Test
	public void selectIdTest_ArgumentsNull() {
		assertThrows(DataNotFoundException.class, () -> this.service.select((UserId)null));
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * Eメールによる選択テストの準備
	 */
	private void initSelectEmail() {
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
				SQL_SELECT_EMAIL,
				"Eメール"
				)).thenReturn(resultList);
	}

	/**
	 * Eメールによる選択テスト
	 */
	@Test
	public void selectEmailTest() {
		initSelectEmail();
		assertDoesNotThrow(() -> this.service.select(new EmailWord("Eメール")));
	}

	/**
	 * Eメールによる選択テスト2
	 */
	@Test
	public void selectEmailTest2() {
		initSelectEmail();

		SecUserModel result = this.service.select(new EmailWord("Eメール"));
		assertNotNull(result);
		assertEquals(1,										result.getId().getId());
		assertEquals("テスト",									result.getName());
		assertEquals("Eメール",								result.getEmail());
		assertEquals("パスワード", 								result.getPassword());
		assertEquals(1, 									result.getRoleList().getList().size());
		assertEquals("ADMIN", 								result.getRoleList().getList().get(0));
		assertEquals(TestConsts.TEST_TIME_01.toString(), 	result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), 	result.getUpdated().toString());
	}

	/**
	 * Eメールによる選択テストの準備(空)
	 */
	private void initSelectEmail_Empty() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		when(this.jdbcTemp.queryForList(
				SQL_SELECT_EMAIL,
				"Eメール"
				)).thenReturn(resultList);
	}

	/**
	 * Eメールによる選択テスト(空)
	 */
	@Test
	public void selectEmailTest_Empty() {
		initSelectEmail_Empty();
		assertThrows(DataNotFoundException.class, () -> this.service.select(new EmailWord("Eメール")));
	}

	/**
	 * Eメールによる選択テスト(引数null)
	 */
	@Test
	public void selectEmailTest_ArgumentsNull() {
		assertThrows(DataNotFoundException.class, () -> this.service.select((EmailWord)null));
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * Eメールandパスワードによる選択テストの準備
	 */
	private void initSelectEmailAndPassword() {
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
				SQL_SELECT_EMAIL_PASSWORD,
				"Eメール",
				"パスワード"
				)).thenReturn(resultList);
	}

	/**
	 * Eメールandパスワードによる選択テスト
	 */
	@Test
	public void selectEmailAndPasswordTest() {
		initSelectEmailAndPassword();
		assertDoesNotThrow(() -> this.service.select(new EmailWord("Eメール"), new PasswdWord("パスワード")));
	}

	/**
	 * Eメールandパスワードによる選択テスト2
	 */
	@Test
	public void selectEmailAndPasswordTest2() {
		initSelectEmailAndPassword();

		SecUserModel result = this.service.select(new EmailWord("Eメール"), new PasswdWord("パスワード"));
		assertNotNull(result);
		assertEquals(1,										result.getId().getId());
		assertEquals("テスト",									result.getName());
		assertEquals("Eメール",								result.getEmail());
		assertEquals("パスワード", 								result.getPassword());
		assertEquals(1, 									result.getRoleList().getList().size());
		assertEquals("ADMIN", 								result.getRoleList().getList().get(0));
		assertEquals(TestConsts.TEST_TIME_01.toString(), 	result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), 	result.getUpdated().toString());
	}

	/**
	 * Eメールandパスワードによる選択テストの準備(空)
	 */
	private void initSelectEmailAndPassword_Empty() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		when(this.jdbcTemp.queryForList(
				SQL_SELECT_EMAIL,
				"Eメール",
				"パスワード"
				)).thenReturn(resultList);
	}

	/**
	 * Eメールandパスワードによる選択テスト(空)
	 */
	@Test
	public void selectEmailAndPasswordTest_Empty() {
		initSelectEmailAndPassword_Empty();
		assertThrows(DataNotFoundException.class, () -> this.service.select(new EmailWord("Eメール"), new PasswdWord("パスワード")));
	}

	/**
	 * Eメールandパスワードによる選択テスト(引数null)
	 */
	@Test
	public void selectEmailAndPasswordTest_ArgumentsNull() {
		assertThrows(DataNotFoundException.class, () -> this.service.select((EmailWord)null, (PasswdWord)null));
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * Eメールによる選択テストの準備
	 */
	private void initLoadUserByUsername() {
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
				SQL_SELECT_EMAIL,
				"Eメール"
				)).thenReturn(resultList);
	}

	/**
	 * Eメールによる選択テスト
	 */
	@Test
	public void loadUserByUsernameTest() {
		initLoadUserByUsername();
		UserDetails result = this.service.loadUserByUsername("Eメール");
		assertNotNull(result);
	}

	/**
	 * Eメールによる選択テスト(エラー)
	 */
	@Test
	public void loadUserByUsernameTest_Error() {
		assertThrows(UsernameNotFoundException.class, () -> this.service.loadUserByUsername("Eメール"));
	}

	/**
	 * Eメールによる選択テスト(引数null)
	 */
	@Test
	public void loadUserByUsernameTest_ArgumentsNull() {
		assertThrows(UsernameNotFoundException.class, () -> this.service.loadUserByUsername(null));
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
