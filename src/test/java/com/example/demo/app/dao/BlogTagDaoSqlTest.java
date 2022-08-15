package com.example.demo.app.dao;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

import com.example.demo.app.entity.BlogTagModel;

class BlogTagDaoSqlTest {
	
	private BlogTagDao dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;

	public void InitSelectAll() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("tag", "テストタグ");
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		dao = new BlogTagDaoSql(jdbcTemp);
	}
	
	
	@Test
	public void SelectAllTest() {
		// TODO 全選択テスト
		InitSelectAll();
		
		List<BlogTagModel> list = dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
		list.clear();
	}
	
	public void InitSelect_byId() {
		// TODO IDによるデータ取得初期化
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("tag", "テストタグ");
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		dao = new BlogTagDaoSql(jdbcTemp);
	}
	
	@Test
	public void Select_byIdTest() {
		// TODO 全選択テスト
		InitSelect_byId();
		
		BlogTagModel model = dao.select(1);
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTag(), "テストタグ");
		
		model = dao.select(2);
		Assertions.assertNull(model);
	}
	
	public void InitInsert() {
		// TODO 更新テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(),
				eq("テストタグ"))).thenReturn(1);
		
		dao = new BlogTagDaoSql(jdbcTemp);
	}
	
	@Test
	public void InsertTest() {
		// TODO 更新テスト
		InitInsert();
		
		BlogTagModel model = new BlogTagModel();
		dao.insertTag(model);
	}
	
	public void InitUpdate() {
		// TODO 更新テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストタグ"),
				eq(1)
				)).thenReturn(1);
		
		dao = new BlogTagDaoSql(jdbcTemp);
	}
	
	@Test
	public void UpdateTest() {
		// TODO 更新テスト
		InitUpdate();
		
		BlogTagModel model = new BlogTagModel();
		model.setId(1);
		model.setTag("テストタグ");
		
		int ret = dao.updateTag(model);
		Assertions.assertEquals(ret, 1);
	}
	
	public void InitDelete() {
		// TODO 削除テストの初期化
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		dao = new BlogTagDaoSql(jdbcTemp);
	}
	
	@Test
	public void DeleteTest() {
		// TODO 削除処理のテスト
		InitDelete();
		
		int ret = dao.deleteTag(1);
		Assertions.assertEquals(ret, 1);
		
		ret = dao.deleteTag(2);
		Assertions.assertEquals(ret, 0);
	}
	
	@AfterEach
	public void Release() {
		dao = null;
		jdbcTemp = null;
	}

}
