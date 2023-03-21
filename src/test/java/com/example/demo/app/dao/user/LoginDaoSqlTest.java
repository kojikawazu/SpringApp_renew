package com.example.demo.app.dao.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.ArgumentsException;

/**
 * ログインDaoクラステスト
 * @author nanai
 *
 */
class LoginDaoSqlTest {

	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO login_user("
			+ "user_id, session_id, created, updated) "
			+ "VALUES(?,?,?,?)";

	/** daoクラス */
	private LoginDaoSql dao = null;

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
		this.dao = new LoginDaoSql(this.jdbcTemp);
	}

	/** ------------------------------------------------------------------ */
	
	/**
	 * 追加テストの準備
	 */
	private void initInsert() {
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				"1111",
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

		LoginModel model = new LoginModel(
				new UserId(1),
				new SessionId("1111"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		this.dao.insert(model);

		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				1,
				"1111",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}

	/**
	 * 追加テスト(異常系 - null)
	 */
	@Test
	void insertTest_Null() {
		initInsert();

		assertThrows(ArgumentsException.class, () -> this.dao.insert(null));

		verify(this.jdbcTemp, times(0)).update(
				SQL_INSERT, 
				1,
				"1111",
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * 更新テストの準備
	 */
	private void initUpdate() {
		String sql = "UPDATE login_user SET "
				+ "user_id = ?, session_id = ?, updated = ? "
				+ "WHERE id = ?";
	
		// 正常系
		when(this.jdbcTemp.update(
			sql, 
			1,
			"1111",
			TestConsts.TEST_TIME_02,
			1
			)).thenReturn(TestConsts.RESULT_NUMBER_OK);

		// 異常系
		when(this.jdbcTemp.update(
			sql, 
			1,
			"1111",
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

		LoginModel model = new LoginModel(
				new LoginId(1),
				new UserId(1),
				new SessionId("1111"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		int ret = this.dao.update(model);
		assertEquals(TestConsts.RESULT_NUMBER_OK, ret);
	}

	/**
	 * 更新テスト（異常系)
	 */
	@Test
	public void updateTest_Error() {
		initUpdate();

		LoginModel model = new LoginModel(
				new LoginId(2),
				new UserId(1),
				new SessionId("1111"),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

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
	 * 更新テスト(更新日付)の準備
	 */
	private void initUpdateTime() {
		this.setDao();
	}

	/**
	 * 更新テスト(更新日付)(異常系)(Null)
	 */
	@Test
	public void updateTimeTest() {
		this.initUpdateTime();

		assertThrows(ArgumentsException.class, () -> this.dao.updateTime(null));
	}
	
	/** ------------------------------------------------------------------ */

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		String sql = "DELETE FROM login_user "
				+ "WHERE id = ?";

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

		int ret = this.dao.delete(new LoginId(1));
		assertEquals(TestConsts.RESULT_NUMBER_OK, ret);
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();

		int ret = this.dao.delete(new LoginId(2));
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
	 * 削除テストの準備
	 */
	private void initDeleteByUserId() {
		String sql = "DELETE FROM login_user "
				+ "WHERE user_id = ?";

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
	public void deleteByUserIdTest() {
		initDeleteByUserId();

		int ret = this.dao.delete_byUserId(new UserId(1));
		assertEquals(TestConsts.RESULT_NUMBER_OK, ret);
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteByUserIdTest_Error() {
		initDeleteByUserId();

		int ret = this.dao.delete_byUserId(new UserId(2));
		assertEquals(WebConsts.ERROR_DB_STATUS, ret);
	 }

	/**
	 * 削除テスト(異常系 - null)
	 */
	@Test
	public void deleteByUserIdTest_Null() {
		initDeleteByUserId();

		assertThrows(ArgumentsException.class, () -> this.dao.delete_byUserId(null));
	 }

	/** ------------------------------------------------------------------ */

	/**
	 * 全削除テストの準備
	 */
	private void initDeleteAll() {
		String sql = "DELETE FROM login_user";

		when(this.jdbcTemp.update(sql))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);

		this.setDao();
	}

	/**
	 * 全削除テスト
	 */
	@Test
	public void deleteAllTest() {
		initDeleteAll();

		int ret = this.dao.deleteAll();
		assertEquals(TestConsts.RESULT_NUMBER_OK, ret);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * 全て選択テストの準備
	 */
	private void initSelectAll() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user";

		map.put(WebConsts.SQL_ID_NAME,			1);
		map.put(WebConsts.SQL_USER_ID_NAME,		1);
		map.put(WebConsts.SQL_SESSION_ID_NAME,	"1111");
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

		List<LoginModel> list = this.dao.getAll();

		assertEquals(1, 		list.size());
		assertEquals(1, 		list.get(0).getId());
		assertEquals(1, 		list.get(0).getUserId());
		assertEquals("1111", list.get(0).getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				list.get(0).getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				list.get(0).getUpdated().toString());

		list.clear();
	}
	/** ------------------------------------------------------------------ */

	/**
	 * 全て選択テストの準備(空リスト)
	 */
	private void initSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user";

		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);

		this.setDao();
	}

	/**
	 * 全て選択テスト(空リスト)
	 */
	@Test
	public void selectAllTest_Empty() {
		initSelectAll_Empty();

		List<LoginModel> list = this.dao.getAll();

		assertNotNull(list);
		assertEquals(0, list.size());
	}

	/** ------------------------------------------------------------------ */

	/**
	 * IDによるデータ取得の準備
	 */
	private void initSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user "
				+ "WHERE id = ?";

		map.put(WebConsts.SQL_ID_NAME,			1);
		map.put(WebConsts.SQL_USER_ID_NAME,		1);
		map.put(WebConsts.SQL_SESSION_ID_NAME,	"1111");
		map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));

		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);

		this.setDao();
	}

	/**
	 * IDによるデータ取得のテスト(正常系)
	 */
	@Test
	public void select_byIdTest() {
		initSelect_byId();

		LoginModel model = this.dao.select(new LoginId(1));

		assertEquals(1, 		model.getId());
		assertEquals(1, 		model.getUserId());
		assertEquals("1111", model.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model.getUpdated().toString());
	}

	/**
	 * IDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void select_byIdTest_Error() {
		initSelect_byId();

		LoginModel model = this.dao.select(new LoginId(2));
		assertNull(model);
	}

	/**
	 * IDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void select_byIdTest_ErrorNull() {
		initSelect_byId();

		LoginModel model = this.dao.select((LoginId)null);
		assertNull(model);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * ユーザーIDによるデータ取得の準備
	 */
	private void initSelect_byUserId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user "
				+ "WHERE user_id = ?";

		map.put(WebConsts.SQL_ID_NAME,			1);
		map.put(WebConsts.SQL_USER_ID_NAME,		1);
		map.put(WebConsts.SQL_SESSION_ID_NAME,	"1111");
		map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));

		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);

		this.setDao();
	}

	/**
	 * ユーザーIDによるデータ取得のテスト(正常系)
	 */
	@Test
	public void select_byUserIdTest() {
		initSelect_byUserId();

		LoginModel model = this.dao.select(new UserId(1));

		assertEquals(1, 		model.getId());
		assertEquals(1, 		model.getUserId());
		assertEquals("1111", model.getSessionId());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model.getUpdated().toString());
	}

	/**
	 * ユーザーIDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void select_byUserIdTest_Error() {
		initSelect_byUserId();

		LoginModel model = this.dao.select(new UserId(2));
		assertNull(model);
	}

	/**
	 * ユーザーIDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void select_byUserIdTest_ErrorNull() {
		initSelect_byUserId();

		LoginModel model = this.dao.select((UserId)null);
		assertNull(model);
	}

	/** ------------------------------------------------------------------ */

	/**
	 * makeLoginModelテスト
	 */
	@Test
	public void makeLoginModelTest() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeLoginModel", Map.class);
			method.setAccessible(true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(WebConsts.SQL_ID_NAME,			1);
			map.put(WebConsts.SQL_USER_ID_NAME,		1);
			map.put(WebConsts.SQL_SESSION_ID_NAME,	"1111");
			map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));

			LoginModel model = (LoginModel)method.invoke(this.dao, map);

			assertEquals(1, 		model.getId());
			assertEquals(1, 		model.getUserId());
			assertEquals("1111", model.getSessionId());
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					model.getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					model.getUpdated().toString());
		} catch (NoSuchMethodException 
				| SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeLoginModelテスト(NoSuch)
	 */
	@Test
	public void makeLoginModelTest_NoSuch() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("makeLoginModel", Map.class);
			method.setAccessible(true);

			Map<String, Object> map = new HashMap<String, Object>();
			LoginModel model = (LoginModel)method.invoke(this.dao, map);

			assertNull(model);
		} catch (NoSuchMethodException 
				| SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeLoginModelテスト(null)
	 */
	@Test
	public void makeLoginModelTest_Null() {
		this.setDao();

		try {
			Method method = LoginDaoSql.class.getDeclaredMethod("makeLoginModel", Map.class);
			method.setAccessible(true);

			LoginModel model = (LoginModel)method.invoke(this.dao, (Map<String, Object>)null);

			assertNull(model);
		} catch (NoSuchMethodException 
				| SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/** ------------------------------------------------------------------ */

	/**
	 * setLoginModelListテスト
	 */
	@Test
	public void setLoginModelListTest() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setLoginModelList", List.class);
			method.setAccessible(true);

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (int i=0; i<3; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(WebConsts.SQL_ID_NAME,			i+1);
				map.put(WebConsts.SQL_USER_ID_NAME,		i+1);
				map.put(WebConsts.SQL_SESSION_ID_NAME,	"1111");
				map.put(WebConsts.SQL_CREATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_01));
				map.put(WebConsts.SQL_UPDATED_NAME,		Timestamp.valueOf(TestConsts.TEST_TIME_02));
				mapList.add(map);
			}

			@SuppressWarnings("unchecked")
			List<LoginModel> modelList = (List<LoginModel>)method.invoke(this.dao, mapList);

			for (int i=0; i<3; i++) {
				assertEquals(i+1, 		modelList.get(i).getId());
				assertEquals(i+1, 		modelList.get(i).getUserId());
				assertEquals("1111", 	modelList.get(i).getSessionId());
				assertEquals(TestConsts.TEST_TIME_01.toString(),
						modelList.get(i).getCreated().toString());
				assertEquals(TestConsts.TEST_TIME_02.toString(),
						modelList.get(i).getUpdated().toString());
			}
		} catch (NoSuchMethodException 
				| SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setLoginModelListテスト(NoSuch)
	 */
	@Test
	public void setLoginModelListTest_NoSuch() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setLoginModelList", List.class);
			method.setAccessible(true);

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			@SuppressWarnings("unchecked")
			List<LoginModel> modelList = (List<LoginModel>)method.invoke(this.dao, mapList);

			assertNotNull(modelList);
			assertEquals(0, modelList.size());
		} catch (NoSuchMethodException 
				| SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setLoginModelListテスト(null)
	 */
	@Test
	public void setLoginModelListTest_Null() {
		this.setDao();

		try {
			Method method = this.dao.getClass().getDeclaredMethod("setLoginModelList", List.class);
			method.setAccessible(true);

			@SuppressWarnings("unchecked")
			List<LoginModel> modelList = (List<LoginModel>)method.invoke(this.dao, (List<Map<String, Object>>)null);

			assertNotNull(modelList);
			assertEquals(0, modelList.size());
		} catch (NoSuchMethodException 
				| SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
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
