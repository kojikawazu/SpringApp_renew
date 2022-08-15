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

import com.example.demo.app.entity.BlogReplyModel;

class BlogReplyDaoSqlTest {
	
	BlogReplyDaoSql dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;

	public void InitSelectAll() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("commentid", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	
	@Test
	public void SelectAllTest() {
		// TODO 全選択テスト
		InitSelectAll();
		
		List<BlogReplyModel> list = dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getCommentid(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
	}
	
	public void InitSelect_byCommentId() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("commentid", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any(), eq(1))).thenReturn(mapList);
		when(jdbcTemp.queryForList(any(), eq(2))).thenReturn(mapList2);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	
	@Test
	public void Select_byCommentId_Test() {
		// TODO 全選択テスト
		InitSelect_byCommentId();
		
		List<BlogReplyModel> list = dao.select_blogId(1);
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getCommentid(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
		
		list = dao.select_blogId(2);
		Assertions.assertEquals(list.size(), 0);
	}
	
	public void InitSelect() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("commentid", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	
	@Test
	public void SelectTest() {
		// TODO 全選択テスト
		InitSelect();
		
		BlogReplyModel model = dao.select(1);
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getCommentid(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		model = dao.select(2);
		Assertions.assertNull(model);
	}
	
	public void InitInsert() {
		// TODO 更新テストの初期化
		BlogReplyModel model = new BlogReplyModel();
		model.setId(1);
		model.setCommentid(1);
		model.setName("テストネーム");
		model.setComment("テストコメント");
		model.setThanksCnt(1);
		model.setCreated(dateTime1);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq(1),
				eq("テストネーム"),
				eq("テストコメント"),
				eq(1),
				eq(dateTime1)
				)).thenReturn(1);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	@Test
	public void InsertTest() {
		// TODO 更新テスト
		InitInsert();
		
		BlogReplyModel model = new BlogReplyModel();
		dao.insertReply(model);
	}
	
	public void InitDelete() {
		// TODO 削除テストの初期化
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	@Test
	public void DeleteTest() {
		// TODO 削除処理のテスト
		InitDelete();
		
		int ret = dao.deleteReply(1);
		Assertions.assertEquals(ret, 1);
		
		ret = dao.deleteReply(2);
		Assertions.assertEquals(ret, 0);
	}
	
	public void InitDelete_byCommentId() {
		// TODO 削除テストの初期化
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	@Test
	public void Delete_byCommentId_Test() {
		// TODO 削除処理のテスト
		InitDelete_byCommentId();
		
		int ret = dao.deleteReply_byBlog(1);
		Assertions.assertEquals(ret, 1);
		
		ret = dao.deleteReply_byBlog(2);
		Assertions.assertEquals(ret, 0);
	}
	
	public void InitThanksIncrement() {
		// TODO インクリメントの初期化
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thanksCnt", 1);
		
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.update(any(), eq(2), eq(1))).thenReturn(1);
		
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		dao = new BlogReplyDaoSql(jdbcTemp);
	}
	
	@Test
	public void IncrementTest() {
		// TODO インクリメント処理のテスト
		InitThanksIncrement();
		
		int ret = dao.thanksIncrement(1);
		Assertions.assertEquals(ret, 2);
		
		ret = dao.thanksIncrement(2);
		Assertions.assertEquals(ret, -1);
	}
	
	@AfterEach
	public void Release() {
		jdbcTemp = null;
		dao = null;
	}

}
