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

class InquiryDaoSqlTest {
	
	private InquiryDao dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;

	public void InitSelectAll() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		dao = new InquiryDaoSql(jdbcTemp);
	}
	
	
	@Test
	public void SelectAllTest() {
		// TODO 全選択テスト
		InitSelectAll();
		
		List<InquiryModel> list = dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(), "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
	}
	
	public void InitSelect_byId() {
		// TODO IDによるデータ取得初期化
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		dao = new InquiryDaoSql(jdbcTemp);
	}
	
	@Test
	public void Select_byIdTest() {
		// TODO 全選択テスト
		InitSelect_byId();
		
		InquiryModel model = dao.select(1);
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		model = dao.select(2);
		Assertions.assertNull(model);
	}
	
	public void InitInsert() {
		// TODO 追加テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストネーム"),
				eq("テストメールアドレス"),
				eq("テストコメント"),
				eq(1)
				)).thenReturn(1);
		
		dao = new InquiryDaoSql(jdbcTemp);
	}
	
	@Test
	public void InsertTest() {
		// TODO 更新テスト
		InitInsert();
		
		InquiryModel model = new InquiryModel();
		dao.insertInquiry(model);
	}
	
	public void InitUpdate() {
		// TODO 更新テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストネーム"),
				eq("テストメールアドレス"),
				eq("テストコメント"),
				eq(1)
				)).thenReturn(1);
		
		dao = new InquiryDaoSql(jdbcTemp);
	}
	
	@Test
	public void UpdateTest() {
		// TODO 更新テスト
		InitUpdate();
		
		InquiryModel model = new InquiryModel();
		model.setId(1);
		model.setName("テストネーム");
		model.setEmail("テストメールアドレス");
		model.setComment("テストコメント");
		model.setCreated(dateTime1);
		
		int ret = dao.updateInquiry(model);
		Assertions.assertEquals(ret, 1);
	}
	
	public void InitDelete() {
		// TODO 削除テストの初期化
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		dao = new InquiryDaoSql(jdbcTemp);
	}
	
	@Test
	public void DeleteTest() {
		// TODO 削除処理のテスト
		InitDelete();
		
		int ret = dao.deleteInquiry(1);
		Assertions.assertEquals(ret, 1);
		
		ret = dao.deleteInquiry(2);
		Assertions.assertEquals(ret, 0);
	}
	
	@AfterEach
	public void Release() {
		dao = null;
		jdbcTemp = null;
	}

}
