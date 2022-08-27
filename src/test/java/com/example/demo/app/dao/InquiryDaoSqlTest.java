package com.example.demo.app.dao;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせDaoクラスのテスト
 * @author nanai
 *
 */
class InquiryDaoSqlTest {
	
	private InquiryDao dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "INSERT INTO inquiry("
				+ "name, email, comment, created) "
				+ "VALUES(?,?,?,?)";
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				dateTime1
				)).thenReturn(1);
		
		this.dao = new InquiryDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		String sql = "INSERT INTO inquiry("
				+ "name, email, comment, created) "
				+ "VALUES(?,?,?,?)";
		InquiryModel model= new InquiryModel(
				new InquiryId(0),
				new NameWord("テストネーム"),
				new NameWord("テストメールアドレス"),
				new NameWord("テストコメント"),
				dateTime1,
				new ArrayList<InquiryReplyModel>()
				);
		
		this.dao.insertInquiry(model);
		verify(this.jdbcTemp, times(1)).update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				dateTime1);
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
				)).thenReturn(1);
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.dao = new InquiryDaoSql(this.jdbcTemp);
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
				dateTime1,
				new ArrayList<InquiryReplyModel>()
				);
		int ret = 0;
		
		ret = this.dao.updateInquiry(model);
		Assertions.assertEquals(ret, 1);
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
				dateTime1,
				new ArrayList<InquiryReplyModel>()
				);
		int ret = 0;
		
		ret = this.dao.updateInquiry(model);
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
				1)).thenReturn(1);
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.dao = new InquiryDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		int ret = 0;
		
		ret = this.dao.deleteInquiry(new InquiryId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteInquiry(new InquiryId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 全選択の準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry";
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		this.dao = new InquiryDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<InquiryModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(), "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
	}
	
	/**
	 * IDによる選択テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM inquiry "
				+ "WHERE id = ?";
		when(this.jdbcTemp.queryForMap(sql, 1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
		
		this.dao = new InquiryDaoSql(this.jdbcTemp);
	}
	
	/**
	 * IDによる選択テスト
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
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		model = this.dao.select(new InquiryId(2));
		Assertions.assertNull(model);
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
