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
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogTagId;
import com.example.demo.common.word.NameWord;

/**
 * ブログタグDaoクラステスト
 * @author nanai
 *
 */
class BlogTagDaoSqlTest {
	
	private BlogTagDao dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT id "
				+ "FROM blog_tag "
				+ "WHERE tag = ?";
		when(this.jdbcTemp.update(
				sql,
				"テストタグ")).thenReturn(1);
		
		this.dao = new BlogTagDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(0),
				new NameWord("テストタグ")
				);
		this.dao.insertTag(model);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				"UPDATE blog_tag SET "
					+ "tag = ? WHERE id = ?", 
				"テストタグ",
				1
				)).thenReturn(1);
		
		when(this.jdbcTemp.update(
				"UPDATE blog_tag SET "
					+ "tag = ? WHERE id = ?", 
				"テストタグ",
				2
				)).thenReturn(WebConsts.ERROR_NUMBER);
		
		this.dao = new BlogTagDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 更新テスト
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		int ret = 0;
		BlogTagModel model = null;
		
		model = new BlogTagModel(
				new BlogTagId(1),
				new NameWord("テストタグ")
				);
		
		ret = this.dao.updateTag(model);
		Assertions.assertEquals(ret, 1);
		
		model = new BlogTagModel(
				new BlogTagId(2),
				new NameWord("テストタグ")
				);
		
		ret = this.dao.updateTag(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				"DELETE FROM blog_tag "
				+ "WHERE id = ?",
				1)).thenReturn(1);
		when(jdbcTemp.update(
				"DELETE FROM blog_tag "
				+ "WHERE id = ?", 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		this.dao = new BlogTagDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteTag(new BlogTagId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteTag(new BlogTagId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}

	/**
	 * 全選択テストの準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("tag", "テストタグ");
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		this.dao = new BlogTagDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogTagModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
		list.clear();
	}
	
	/**
	 * IDによるデータ取得テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT * "
				+ "FROM blog_tag "
				+ "WHERE id = ?";
		
		map.put("id", 1);
		map.put("tag", "テストタグ");
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(sql, 
				1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 
				2)).thenReturn(null);
		
		this.dao = new BlogTagDaoSql(this.jdbcTemp);
	}
	
	/**
	 * IDによるデータ取得のテスト
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogTagModel model = this.dao.select(new BlogTagId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTag(), "テストタグ");
		
		model = this.dao.select(new BlogTagId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		dao = null;
		jdbcTemp = null;
	}

}
