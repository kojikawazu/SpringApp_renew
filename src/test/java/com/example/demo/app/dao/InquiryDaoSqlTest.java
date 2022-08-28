package com.example.demo.app.dao;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせDaoクラスのテスト
 * @author nanai
 *
 */
class InquiryDaoSqlTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO inquiry("
											+ "name, email, comment, created) "
											+ "VALUES(?,?,?,?)";
	
	/** daoクラス */
	private InquiryDao dao = null;
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				TestConsts.TEST_TIME_01
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setDao();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		InquiryModel model= new InquiryModel(
				new InquiryId(0),
				new NameWord("テストネーム"),
				new NameWord("テストメールアドレス"),
				new NameWord("テストコメント"),
				TestConsts.TEST_TIME_01,
				new ArrayList<InquiryReplyModel>()
				);
		
		this.dao.insertInquiry(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				TestConsts.TEST_TIME_01);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE inquiry SET "
				+ "name = ?, email = ?, comment = ? "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		InquiryModel model = new InquiryModel(
				new InquiryId(1),
				new NameWord("テストネーム"),
				new NameWord("テストメールアドレス"),
				new NameWord("テストコメント"),
				TestConsts.TEST_TIME_01,
				new ArrayList<InquiryReplyModel>()
				);
		
		int ret = this.dao.updateInquiry(model);
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTestError() {
		InitUpdate();
		
		InquiryModel model = new InquiryModel(
				new InquiryId(2),
				new NameWord("テストネーム"),
				new NameWord("テストメールアドレス"),
				new NameWord("テストコメント"),
				TestConsts.TEST_TIME_01,
				new ArrayList<InquiryReplyModel>()
				);
		
		int ret = this.dao.updateInquiry(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteInquiry(new InquiryId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		int ret = this.dao.deleteInquiry(new InquiryId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 全選択の準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry";
				
		map.put(WebConsts.SQL_ID_NAME,      1);
		map.put(WebConsts.SQL_NAME_NAME,    "テストネーム");
		map.put(WebConsts.SQL_EMAIL_NAME,   "テストメールアドレス");
		map.put(WebConsts.SQL_COMMENT_NAME, "テストコメント");
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		this.setDao();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<InquiryModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(),              1);
		Assertions.assertEquals(list.get(0).getName(),    "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(),   "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		list.clear();
	}
	
	/**
	 * 全選択の準備(空)
	 */
	public void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry";
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		this.setDao();
	}
	
	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		List<InquiryModel> list = this.dao.getAll();
		
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * IDによる選択テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM inquiry "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,      1);
		map.put(WebConsts.SQL_NAME_NAME,    "テストネーム");
		map.put(WebConsts.SQL_EMAIL_NAME,   "テストメールアドレス");
		map.put(WebConsts.SQL_COMMENT_NAME, "テストコメント");
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		this.setDao();
	}
	
	/**
	 * IDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryModel model = this.dao.select(new InquiryId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * IDによる選択テスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		InquiryModel model = this.dao.select(new InquiryId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * dao設定
	 */
	private void setDao() {
		this.dao = new InquiryDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.dao = null;
		this.jdbcTemp = null;
	}

}
