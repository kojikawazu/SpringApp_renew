package com.example.demo.app.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログメインDaoクラステスト
 * @author nanai
 *
 */
class BlogMainDaoSqlTest {
	
	private BlogMainDao dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	LocalDateTime dateTime2 = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		
		
	}
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				"INSERT INTO blog_main("
						+ "title, tag, comment, thanksCnt, created, updated) "
						+ "VALUES(?,?,?,?,?,?)", 
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				dateTime1,
				dateTime2
				)).thenReturn(1);
		
		this.dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		// 準備
		InitInsert();
		
		BlogMainModel model = new BlogMainModel(
				new NameWord("テストタイトル"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime1,
				dateTime2
				);
		
		// テスト
		this.dao.insertBlog(model);
		verify(this.jdbcTemp, times(1)).update(
				anyString(), 
				anyString(), 
				anyString(), 
				anyString(), 
				anyInt(), 
				any(), 
				any());
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE blog_main SET "
				+ "title = ?, tag = ?, comment = ?, thanksCnt = ?, updated = ? "
				+ "WHERE id = ?";
		// 正常系
		when(jdbcTemp.update(
				sql, 
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				dateTime2,
				1
				)).thenReturn(1);
		
		// 異常系
		when(jdbcTemp.update(
				sql, 
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				dateTime2,
				2
				)).thenReturn(WebConsts.ERROR_NUMBER);
		
		dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * 更新テスト（正常系)
	 */
	@Test
	public void UpdateTest() {
		// 準備
		InitUpdate();
		
		int ret = 0;
		
		BlogMainModel model = new BlogMainModel(
				new BlogId(1),
				new NameWord("テストタイトル"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime1,
				dateTime2
				);
		
		// 正常系テスト
		ret = dao.updateBlog(model);
		Assertions.assertEquals(ret, 1);
	}
	
	/**
	 * 更新テスト（異常系)
	 */
	@Test
	public void UpdateTestError() {
		// 準備
		InitUpdate();
		
		int ret = 0;
		
		BlogMainModel 	model = new BlogMainModel(
				new BlogId(2),
				new NameWord("テストタイトル"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime1,
				dateTime2
				);
		
		// 異常系テスト
		ret = dao.updateBlog(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(this.jdbcTemp.update(any(), eq(2))).thenReturn(WebConsts.ERROR_NUMBER);
		
		this.dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		// 準備
		InitDelete();
		int ret = 0;
		
		// テスト
		ret = this.dao.deleteBlog(new BlogId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteBlog(new BlogId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * 全て選択メソッドの準備
	 */
	public void InitSelectAll() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("title", "テストタイトル");
		map.put("tag", "テストタグ");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		map.put("updated", Timestamp.valueOf(dateTime2));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * 全て選択のテスト
	 */
	@Test
	public void SelectAllTest() {
		// 前準備
		InitSelectAll();
		
		// メソッド実行
		List<BlogMainModel> list = dao.getAll();
		
		// チェック
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTitle(), "テストタイトル");
		Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), dateTime2.toString());
		
		// 後処理
		list.clear();
	}
	
	/**
	 * IDによるデータ取得の準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("title", "テストタイトル");
		map.put("tag", "テストタグ");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		map.put("updated", Timestamp.valueOf(dateTime2));
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * IDによるデータ取得のテスト
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogMainModel model = dao.select(new BlogId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTitle(), "テストタイトル");
		Assertions.assertEquals(model.getTag(), "テストタグ");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		Assertions.assertEquals(model.getUpdated().toString(), dateTime2.toString());
		
		model = dao.select(new BlogId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * タグ名によるデータ取得の準備
	 */
	public void InitSelect_byTag() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("title", "テストタイトル");
		map.put("tag", "テストタグ");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		map.put("updated", Timestamp.valueOf(dateTime2));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any(), eq("テスト"))).thenReturn(mapList);
		when(jdbcTemp.queryForList(any(), eq("バグ"))).thenReturn(mapList2);
		
		dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * タグ名による選択のテスト
	 */
	@Test
	public void Select_byTagTest() {
		// 準備
		InitSelect_byTag();
		
		// テスト
		List<BlogMainModel> list = dao.select_byTag("テスト");
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTitle(), "テストタイトル");
		Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), dateTime2.toString());
		list.clear();
		
		list = dao.select_byTag("バグ");
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * インクリメントテストの準備
	 */
	public void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thanksCnt", 1);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(this.jdbcTemp.update(any(), eq(2), eq(1))).thenReturn(1);
		
		when(this.jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		this.dao = new BlogMainDaoSql(jdbcTemp);
	}
	
	/**
	 * インクリメントテスト
	 */
	@Test
	public void IncrementTest() {
		// 準備
		InitThanksIncrement();
		int ret = 0;
		
		// テスト開始
		ret = this.dao.thanksIncrement(new BlogId(1));
		Assertions.assertEquals(ret, 2);
		
		ret = this.dao.thanksIncrement(new BlogId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
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
