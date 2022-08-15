package com.example.demo.app.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.app.dao.InquiryReplyDao;
import com.example.demo.app.dao.InquiryReplyDaoSql;
import com.example.demo.app.entity.InquiryReplyModel;

class InquiryReplyServiceUseTest {

	// テスト対象
	InquiryReplyService service = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	public void InitSelectAll() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("inquiry_id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		setService();
	}
	
	@Test
	public void SelectAllTest() {
		// TODO 全選択テスト
		InitSelectAll();
		
		List<InquiryReplyModel> list = service.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getInquiry_id(), 1);
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
		map.put("inquiry_id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		setService();
	}
	
	@Test
	public void Select_byIdTest() {
		// TODO 全選択テスト
		InitSelect_byId();
		
		InquiryReplyModel model = service.select(1);
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getInquiry_id(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		model = service.select(2);
		Assertions.assertNull(model);
	}
	
	public void InitSelect_byInquiryId() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("inquiry_id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any(), eq(1))).thenReturn(mapList);
		when(jdbcTemp.queryForList(any(), eq(2))).thenReturn(mapList2);
		
		setService();
	}
	
	
	@Test
	public void Select_byInquiryId_Test() {
		// TODO 全選択テスト
		InitSelect_byInquiryId();
		
		List<InquiryReplyModel> list = service.select_byInquiryId(1);
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getInquiry_id(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(), "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
		
		list = service.select_byInquiryId(2);
		Assertions.assertEquals(list.size(), 0);
	}
	
	public void InitInsert() {
		// TODO 追加テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq(1),
				eq("テストネーム"),
				eq("テストメールアドレス"),
				eq("テストコメント"),
				eq(dateTime1)
				)).thenReturn(1);
		
		setService();
	}
	
	@Test
	public void InsertTest() {
		// TODO 更新テスト
		InitInsert();
		
		InquiryReplyModel model = new InquiryReplyModel();
		service.save(model);
	}
	
	public void InitUpdate() {
		// TODO 更新テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq(1),
				eq("テストネーム"),
				eq("テストメールアドレス"),
				eq("テストコメント"),
				eq(1)
				)).thenReturn(1);
		
		setService();
	}
	
	@Test
	public void UpdateTest() {
		// TODO 更新テスト
		InitUpdate();
		
		InquiryReplyModel model = new InquiryReplyModel();
		
		// 例外テスト1
		assertThrows(RuntimeException.class, () -> service.update(model));
				
		model.setId(1);
		model.setInquiry_id(1);
		model.setName("テストネーム");
		model.setEmail("テストメールアドレス");
		model.setComment("テストコメント");
		model.setCreated(dateTime1);
		
		// 例外テスト2
		assertDoesNotThrow(() -> service.update(model));
	}
	
	public void InitDelete() {
		// TODO 削除テストの初期化
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		setService();
	}
	
	@Test
	public void DeleteTest() {
		// TODO 削除処理のテスト
		InitDelete();
		
		assertDoesNotThrow(() -> service.delete(1));
		assertThrows(RuntimeException.class, () -> service.delete(2));
	}
	
	public void InitDelete_byInquiryId() {
		// TODO 削除テストの初期化
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		setService();
	}
	
	@Test
	public void Delete_byInquiryId_Test() {
		// TODO 削除処理のテスト
		InitDelete_byInquiryId();
		
		service.delete_byInquiry(1);
		service.delete_byInquiry(2);
	}
	
	public void setService() {
		// TODO サービスのインスタンス化
		InquiryReplyDao dao = new InquiryReplyDaoSql(jdbcTemp);
		service = new InquiryReplyServiceUse(dao);
	}
	
	@AfterEach
	public void Release() {
		jdbcTemp = null;
		service = null;
	}
		
}
