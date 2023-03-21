package com.example.demo.app.dao.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.ArgumentsException;
import com.example.demo.common.list.WordList;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

class SecUserDaoSqlTest {

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

	/** SQL文(追加) */
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

	/** daoクラス */
	SecUserDaoSql dao = null;
	
	/** jdbcクラス */
	@Mock
	JdbcTemplate jdbcTemp = null;

	/** ------------------------------------------------------------------ */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
	}

	/**
	 * daoクラスの設定
	 */
	private void setDao() {
		this.dao = new SecUserDaoSql(this.jdbcTemp);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * 追加テストの準備
	 */
	private void initInsert() {
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				"テスト",
				"Eメール",
				"パスワード",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
		)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		this.setDao();
	}

	/**
	 * 追加テスト(正常系)
	 */
	@Test
	void insertTest() {
		initInsert();

		SecUserModel model = new SecUserModel(
				new NameWord("テスト"),
				new EmailWord("Eメール"),
				new PasswdWord("パスワード"),
				new WordList(),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);

		this.dao.insert(model);

		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				"テスト",
				"Eメール",
				"パスワード",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}

	/**
	 * 追加テスト(Null)
	 */
	@Test
	void insertTest_Null() {
		initInsert();

		assertThrows(ArgumentsException.class, () -> this.dao.insert(null));

		verify(this.jdbcTemp, times(0)).update(
				SQL_INSERT, 
				"テスト",
				"Eメール",
				"パスワード",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * 更新テストの準備
	 */
	private void initUpdate() {
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_NAME		+ " = ?, "
				+ PARAM_EMAIL		+ " = ?, "
				+ PARAM_PASSWORD	+ " = ?, "
				+ PARAM_UPDATED 	+ " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		// 正常系
		when(this.jdbcTemp.update(
			sql, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_02,
			1
			)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		// 異常系
		when(this.jdbcTemp.update(
			sql, 
			"テスト",
			"Eメール",
			"パスワード",
			TestConsts.TEST_TIME_02,
			2
			)).thenReturn(WebConsts.ERROR_DB_STATUS);

		this.setDao();
	}

	/**
	 * 更新テスト（正常系)
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

		int ret = this.dao.update(model);
		assertEquals(TestConsts.RESULT_NUMBER_OK, ret);
	}

	/**
	 * 更新テスト（異常系)
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

		int ret = this.dao.update(model);
		assertEquals(WebConsts.ERROR_DB_STATUS, ret);
	}

	/**
	 * 更新テスト（異常系 - null)
	 */
	@Test
	public void updateTest_Null() {
		initUpdate();

		assertThrows(ArgumentsException.class, () -> this.dao.update(null));
	}

	/** ------------------------------------------------------------------ */

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);

		this.setDao();
	}

	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void deleteTest() {
		initDelete();

		int ret = this.dao.delete(new UserId(1));
		assertEquals(TestConsts.RESULT_NUMBER_OK, ret);
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();

		int ret = this.dao.delete(new UserId(2));
		assertEquals(WebConsts.ERROR_DB_STATUS, ret);
	 }

	/**
	 * 削除テスト(異常系 - null)
	 */
	@Test
	public void deleteTest_Null() {
		initDelete();

		assertThrows(ArgumentsException.class, () -> this.dao.delete(null));
	 }

	/** ------------------------------------------------------------------ */

	/**
	 * 全て選択テストの準備
	 */
	private void initSelectAll() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = WebConsts.SQL_SELECT + " "
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

		map.put(PARAM_ID,						1);
		map.put(PARAM_NAME,						"テスト");
		map.put(PARAM_EMAIL,					"Eメール");
		map.put(PARAM_PASSWORD,					"パスワード");
		map.put(PARAM_ROLE_NAME, 				"ADMIN");
		map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);

		this.setDao();
	}

	/**
	 * 全て選択のテスト
	 */
	@Test
	public void selectAllTest() {
		initSelectAll();

		List<SecUserModel> list = this.dao.getAll();

		assertEquals(1, 		list.size());
		assertEquals(1, 		list.get(0).getId().getId());
		assertEquals("テスト", 	list.get(0).getName());
		assertEquals("Eメール", 	list.get(0).getEmail());
		assertEquals("パスワード", 	list.get(0).getPassword());
		assertEquals(1, 		list.get(0).getRoleList().getList().size());
		assertEquals("ADMIN", 	list.get(0).getRoleList().getList().get(0));
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				list.get(0).getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				list.get(0).getUpdated().toString());

		list.clear();
	}

	/**
	 * 全て選択テストの準備(空)
	 */
	private void initSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = WebConsts.SQL_SELECT + " "
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

		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);

		this.setDao();
	}

	/**
	 * 全て選択のテスト(空)
	 */
	@Test
	public void selectAllTest_Empty() {
		initSelectAll_Empty();
		List<SecUserModel> list = this.dao.getAll();
		assertEquals(0, list.size());
	}

	/** ------------------------------------------------------------------ */

	/**
	 * IDによる選択テストの準備
	 */
	private void initSelect() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = WebConsts.SQL_SELECT + " "
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

		map.put(PARAM_ID,						1);
		map.put(PARAM_NAME,						"テスト");
		map.put(PARAM_EMAIL,					"Eメール");
		map.put(PARAM_PASSWORD,					"パスワード");
		map.put(PARAM_ROLE_NAME, 				"ADMIN");
		map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(sql, 1))
			.thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, 2))
			.thenReturn(null);

		this.setDao();
	}

	/**
	 * IDによる選択テスト
	 */
	@Test
	public void selectTest() {
		initSelect();

		SecUserModel model = this.dao.select(new UserId(1));

		assertNotNull(model);
		assertEquals(1, 		model.getId().getId());
		assertEquals("テスト", 	model.getName());
		assertEquals("Eメール", 	model.getEmail());
		assertEquals("パスワード", 	model.getPassword());
		assertEquals(1, 		model.getRoleList().getList().size());
		assertEquals("ADMIN", 	model.getRoleList().getList().get(0));
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model.getUpdated().toString());
	}

	/**
	 * IDによる選択テスト(空)
	 */
	@Test
	public void selectTest_Empty() {
		initSelect();
		SecUserModel model = this.dao.select(new UserId(2));
		assertNull(model);
	}

	/**
	 * IDによる選択テスト(引数エラー)
	 */
	@Test
	public void selectTest_ArgumentError() {
		this.setDao();
		SecUserModel model = this.dao.select((UserId)null);
		assertNull(model);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * Eメールによる選択テストの準備
	 */
	private void initSelect_byEmail() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = WebConsts.SQL_SELECT + " "
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

		map.put(PARAM_ID,						1);
		map.put(PARAM_NAME,						"テスト");
		map.put(PARAM_EMAIL,					"Eメール");
		map.put(PARAM_PASSWORD,					"パスワード");
		map.put(PARAM_ROLE_NAME, 				"ADMIN");
		map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(sql, "Eメール"))
			.thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, "Eメール2"))
			.thenReturn(null);

		this.setDao();
	}

	/**
	 * Eメールによる選択テスト
	 */
	@Test
	public void select_byEmailTest() {
		initSelect_byEmail();

		SecUserModel model = this.dao.select(new EmailWord("Eメール"));

		assertNotNull(model);
		assertEquals(1, 		model.getId().getId());
		assertEquals("テスト", 	model.getName());
		assertEquals("Eメール", 	model.getEmail());
		assertEquals("パスワード", 	model.getPassword());
		assertEquals(1, 		model.getRoleList().getList().size());
		assertEquals("ADMIN", 	model.getRoleList().getList().get(0));
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model.getUpdated().toString());
	}

	/**
	 * Eメールによる選択テスト(空)
	 */
	@Test
	public void select_byEmailTest_Empty() {
		initSelect_byEmail();
		SecUserModel model = this.dao.select(new EmailWord("Eメール2"));
		assertNull(model);
	}

	/**
	 * Eメールによる選択テスト(引数エラー)
	 */
	@Test
	public void select_byEmailTest_ArgumentError() {
		this.setDao();
		SecUserModel model = this.dao.select((EmailWord)null);
		assertNull(model);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * 後処理
	 */
	@AfterEach
	public void release() {
		this.dao = null;
		this.jdbcTemp = null;
	}
}
