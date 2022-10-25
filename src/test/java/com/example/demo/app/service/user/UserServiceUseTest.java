package com.example.demo.app.service.user;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.dao.user.UserDaoSql;
import com.example.demo.app.entity.user.UserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.id.user.UserKindId;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * ユーザーサービスクラス
 * @author nanai
 *
 */
class UserServiceUseTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO home_user("
			+ "kind_id, name, email, password, created, updated) "
			+ "VALUES(?,?,?,?,?,?)";

	/** serviceクラス */
	private UserServiceUse service = null;
	
	/** jdbcクラス */
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
	}
	
	/**
	 * サービスのインスタンス化
	 */
	public void setService() {
		UserDaoSql dao = new UserDaoSql(this.jdbcTemp);
		this.service = new UserServiceUse(dao);
	}
	
	/** --------------------------------------------------------------------------- */

	/**
	 * 追加テストの準備
	 */
	private void InitInsert() {
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
				TestConsts.TEST_PASSWORD_NAME,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setService();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	void InsertTest() {
		InitInsert();
		
		UserModel model = new UserModel(
				new UserKindId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new PasswdWord(TestConsts.TEST_PASSWORD_NAME),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
				TestConsts.TEST_PASSWORD_NAME,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * 更新テストの準備
	 */
	private void InitUpdate() {
		String sql = "UPDATE home_user SET "
				+ "kind_id = ?, name = ?, email = ?, password = ?, updated = ? "
				+ "WHERE id = ?";
		// 正常系
		when(this.jdbcTemp.update(
				sql, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
				TestConsts.TEST_PASSWORD_NAME,
				TestConsts.TEST_TIME_02,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		// 異常系
		when(this.jdbcTemp.update(
				sql, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
				TestConsts.TEST_PASSWORD_NAME,
				TestConsts.TEST_TIME_02,
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setService();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		assertDoesNotThrow(
			() -> this.service.update(
				new UserModel(
						new UserId(1),
						new UserKindId(1),
						new NameWord(TestConsts.TEST_NAME_NAME),
						new EmailWord(TestConsts.TEST_EMAIL_NAME),
						new PasswdWord(TestConsts.TEST_PASSWORD_NAME),
						TestConsts.TEST_TIME_01,
						TestConsts.TEST_TIME_02
				)
			));
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTest_Error() {
		InitUpdate();
		
		assertThrows(
			RuntimeException.class,
			() -> this.service.update(
					new UserModel(
						new UserId(2),
						new UserKindId(1),
						new NameWord(TestConsts.TEST_NAME_NAME),
						new EmailWord(TestConsts.TEST_EMAIL_NAME),
						new PasswdWord(TestConsts.TEST_PASSWORD_NAME),
						TestConsts.TEST_TIME_01,
						TestConsts.TEST_TIME_02
					)
			));
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * 削除テストの準備
	 */
	private void InitDelete() {
		String sql = "DELETE FROM home_user "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setService();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		assertDoesNotThrow(() -> 
			this.service.delete(new UserId(1)));
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		assertThrows(RuntimeException.class, 
			() -> this.service.delete(new UserId(2)));
	}
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * 全て選択の準備
	 */
	private void InitSelectAll() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = "SELECT id, kind_id, name, email, password, created, updated FROM home_user";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_KIND_ID_NAME,   1);
		map.put(WebConsts.SQL_NAME_NAME,      TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_EMAIL_NAME,     TestConsts.TEST_EMAIL_NAME);
		map.put(WebConsts.SQL_PASSWORD_NAME,  TestConsts.TEST_PASSWORD_NAME);
		map.put(WebConsts.SQL_CREATED_NAME,   Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,   Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		this.setService();
	}
	
	/**
	 * 全て選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<UserModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(),                1);
		Assertions.assertEquals(list.get(0).getId(),        1);
		Assertions.assertEquals(list.get(0).getKindId(),    1);
		Assertions.assertEquals(list.get(0).getName(),      TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(0).getEmail(),     TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(list.get(0).getPassword(),  TestConsts.TEST_PASSWORD_NAME);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		
		list.clear();
	}
	
	/**
	 * 全て選択の準備(空)
	 */
	private void InitSelectAll_empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = "SELECT id, kind_id, name, email, password, created, updated FROM home_user";
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		this.setService();
	}
	
	/**
	 * 全て選択テスト(空)
	 */
	@Test
	public void SelectAllTest_empty() {
		InitSelectAll_empty();
		
		List<UserModel> list =  this.service.getAll();
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	/** --------------------------------------------------------------------------- */
	
	/**
	 * IDによるデータ選択の準備
	 */
	private void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT * "
				+ "FROM home_user "
				+ "WHERE id = ?";;
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_KIND_ID_NAME,   1);
		map.put(WebConsts.SQL_NAME_NAME,      TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_EMAIL_NAME,     TestConsts.TEST_EMAIL_NAME);
		map.put(WebConsts.SQL_PASSWORD_NAME,  TestConsts.TEST_PASSWORD_NAME);
		map.put(WebConsts.SQL_CREATED_NAME,   Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,   Timestamp.valueOf(TestConsts.TEST_TIME_02));
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		this.setService();
	}
	
	/**
	 * IDによる選択のテスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		UserModel model = this.service.select(new UserId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getKindId(),    1);
		Assertions.assertEquals(model.getName(),      TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),     TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getPassword(),  TestConsts.TEST_PASSWORD_NAME);
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
	}
	
	/**
	 * IDによる選択のテスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		Assertions.assertThrows(RuntimeException.class,
				() ->  this.service.select(new UserId(2)));
	 }
	
	/** --------------------------------------------------------------------------- */
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.service = null;
		this.jdbcTemp = null;
	}

}
