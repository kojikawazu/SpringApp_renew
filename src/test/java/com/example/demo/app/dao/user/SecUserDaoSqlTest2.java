package com.example.demo.app.dao.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.PasswdWord;

class SecUserDaoSqlTest2 {

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
	 * Eメール、パスワードによる選択テストの準備
	 */
	private void initSelect_byEmail_Password() {
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
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_JOIN  + " " + DB_USER_ROLE_NAME + " "
				+ WebConsts.SQL_ON    + " " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
				+ WebConsts.SQL_JOIN  + " " + DB_ROLE_NAME + " "
				+ WebConsts.SQL_ON    + " " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
				+ WebConsts.SQL_WHERE + " " + DB_NAME + "." + PARAM_EMAIL    + " = ? and "
				+                             DB_NAME + "." + PARAM_PASSWORD + " = ?";

		map.put(PARAM_ID,						1);
		map.put(PARAM_NAME,						"テスト");
		map.put(PARAM_EMAIL,					"Eメール");
		map.put(PARAM_PASSWORD,					"パスワード");
		map.put(PARAM_ROLE_NAME, 				"ADMIN");
		map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(sql, "Eメール", "パスワード"))
			.thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, "Eメール", "パスワード2"))
			.thenReturn(null);

		this.setDao();
	}

	/**
	 * Eメール、パスワードによる選択テスト
	 */
	@Test
	public void select_byEmail_PasswordTest() {
		initSelect_byEmail_Password();

		SecUserModel model = this.dao.select(new EmailWord("Eメール"), new PasswdWord("パスワード"));

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
	 * Eメール、パスワードによる選択テスト(空)
	 */
	@Test
	public void select_byEmail_PasswordTest_Empty() {
		initSelect_byEmail_Password();
		SecUserModel model = this.dao.select(new EmailWord("Eメール"), new PasswdWord("パスワード2"));
		assertNull(model);
	}

	/**
	 * Eメール、パスワードによる選択テスト(引数エラー)
	 */
	@Test
	public void select_byEmail_PasswordTest_ArgumentError() {
		this.setDao();
		SecUserModel model = this.dao.select(null, null);
		assertNull(model);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * setSecUserModelListテスト
	 */
	@Test
	public void setSecUserModelListTest() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setSecUserModelList", List.class);
			method.setAccessible(true);

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (int idx=0; idx<3; idx++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(PARAM_NAME,						"テスト");
				map.put(PARAM_EMAIL,					"Eメール");
				map.put(PARAM_PASSWORD,					"パスワード");
				map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
				map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));

				if (idx < 2) {
					map.put(PARAM_ID,						1);
					if (idx < 1) {
						map.put(PARAM_ROLE_NAME, "ADMIN");
					} else {
						map.put(PARAM_ROLE_NAME, "GENERAL");
					}
				} else {
					map.put(PARAM_ID,						2);
					map.put(PARAM_ROLE_NAME, 				"ADMIN");
				}

				mapList.add(map);
			}
			@SuppressWarnings("unchecked")
			List<SecUserModel> modelList = (List<SecUserModel>)method.invoke(this.dao, mapList);

			assertEquals(2, modelList.size());

			assertEquals(1, modelList.get(0).getId().getId());
			assertEquals("テスト", modelList.get(0).getName());
			assertEquals("Eメール", modelList.get(0).getEmail());
			assertEquals("パスワード", modelList.get(0).getPassword());
			assertEquals(2, modelList.get(0).getRoleList().getList().size());
			assertEquals("ADMIN", modelList.get(0).getRoleList().getList().get(0));
			assertEquals("GENERAL", modelList.get(0).getRoleList().getList().get(1));
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					modelList.get(0).getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					modelList.get(0).getUpdated().toString());

			assertEquals(2, modelList.get(1).getId().getId());
			assertEquals("テスト", modelList.get(1).getName());
			assertEquals("Eメール", modelList.get(1).getEmail());
			assertEquals("パスワード", modelList.get(1).getPassword());
			assertEquals(1, modelList.get(1).getRoleList().getList().size());
			assertEquals("ADMIN", modelList.get(1).getRoleList().getList().get(0));
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					modelList.get(1).getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					modelList.get(1).getUpdated().toString());
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setSecUserModelListテスト(引数エラー)
	 */
	@Test
	public void setSecUserModelListTest_ArgumentsError() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setSecUserModelList", List.class);
			method.setAccessible(true);

			@SuppressWarnings("unchecked")
			List<SecUserModel> modelList = (List<SecUserModel>)method.invoke(this.dao, (List<Map<String, Object>>)null);

			assertEquals(0, modelList.size());
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/** ------------------------------------------------------------------ */

	/**
	 * setRoleListテスト
	 */
	@Test
	public void setRoleListTest() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setRoleList", List.class, int.class, SecUserModel.class);
			method.setAccessible(true);

			SecUserModel model = new SecUserModel();
			model.setId(1);
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (int idx=0; idx<3; idx++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(PARAM_NAME,						"テスト");
				map.put(PARAM_EMAIL,					"Eメール");
				map.put(PARAM_PASSWORD,					"パスワード");
				map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
				map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));

				if (idx < 2) {
					map.put(PARAM_ID,						1);
					if (idx < 1) {
						map.put(PARAM_ROLE_NAME, "ADMIN");
					} else {
						map.put(PARAM_ROLE_NAME, "GENERAL");
					}
				} else {
					map.put(PARAM_ID,						2);
					map.put(PARAM_ROLE_NAME, 				"ADMIN");
				}

				mapList.add(map);
			}
			int cnt = (int)method.invoke(this.dao, mapList, 0, model);

			assertEquals(1, cnt);
			assertEquals(1, model.getRoleList().getList().size());
			assertEquals("GENERAL", model.getRoleList().getList().get(0));
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setRoleListテスト(引数エラー)
	 */
	@Test
	public void setRoleListTest_ArgumentsError() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setRoleList", List.class, int.class, SecUserModel.class);
			method.setAccessible(true);

			int cnt = (int)method.invoke(this.dao, (List<Map<String, Object>>)null, 0, (SecUserModel)null);

			assertEquals(0, cnt);
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/** ------------------------------------------------------------------ */

	/**
	 * makeSecUserModelテスト
	 */
	@Test
	public void makeSecUserModelTest() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeSecUserModel", Map.class);
			method.setAccessible(true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_ID,						1);
			map.put(PARAM_NAME,						"テスト");
			map.put(PARAM_EMAIL,					"Eメール");
			map.put(PARAM_PASSWORD,					"パスワード");
			map.put(PARAM_ROLE_NAME, 				"ADMIN");
			map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));

			SecUserModel model = (SecUserModel)method.invoke(this.dao, map);

			assertNotNull(model);
			assertEquals(1, 		model.getId().getId());
			assertEquals("テスト", 	model.getName());
			assertEquals("Eメール", 	model.getEmail());
			assertEquals("パスワード", 	model.getPassword());
			assertEquals(0, 		model.getRoleList().getList().size());
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					model.getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					model.getUpdated().toString());
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeSecUserModelテスト(引数エラー)
	 */
	@Test
	public void makeSecUserModelTest_ArgumentsError() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeSecUserModel", Map.class);
			method.setAccessible(true);

			SecUserModel model = (SecUserModel)method.invoke(this.dao, (Map<String, Object>)null);

			assertNull(model);
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/** ------------------------------------------------------------------ */

	/**
	 * makeRoleテスト
	 */
	@Test
	public void makeRoleTest() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeRole", Map.class);
			method.setAccessible(true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_ROLE_NAME, "ADMIN");

			String role = (String)method.invoke(this.dao, map);

			assertNotNull(role);
			assertEquals("ADMIN", role);
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeRoleテスト(異常系)
	 */
	@Test
	public void makeRoleTest_Error() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeRole", Map.class);
			method.setAccessible(true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_ROLE_NAME + "2", "ADMIN");

			String role = (String)method.invoke(this.dao, map);

			assertNotNull(role);
			assertEquals("", role);
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeRoleテスト(異常系)
	 */
	@Test
	public void makeRoleTest_ArgumentsError() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeRole", Map.class);
			method.setAccessible(true);

			String role = (String)method.invoke(this.dao, (Map<String, Object>)null);

			assertNotNull(role);
			assertEquals("", role);
		} catch (NoSuchMethodException 
				| SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
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
