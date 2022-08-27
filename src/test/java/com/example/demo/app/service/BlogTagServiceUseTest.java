package com.example.demo.app.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.app.dao.BlogTagDao;
import com.example.demo.app.dao.BlogTagDaoSql;
import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.app.exception.InquiryNotFoundException;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogTagId;
import com.example.demo.common.word.NameWord;

/**
 * ブログタグサービスのテスト
 * @author nanai
 *
 */
class BlogTagServiceUseTest {
	
	/** テスト対象 */
	BlogTagService service = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "INSERT INTO blog_tag(tag) "
					+ "VALUES(?)";
		
		when(this.jdbcTemp.update(
				sql,
				"テストタグ")).thenReturn(1);
		
		setService();
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
		
		this.service.save(model);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE blog_tag SET "
				+ "tag = ? WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				"テストタグ",
				1
				)).thenReturn(1);
		
		when(this.jdbcTemp.update(
				sql, 
				"テストタグ",
				2
				)).thenReturn(WebConsts.ERROR_NUMBER);
		
		setService();
	}
	
	/**
	 * 更新テスト
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		assertDoesNotThrow(
			() -> this.service.update(
				new BlogTagModel(
					new BlogTagId(1),
					new NameWord("テストタグ")
				)
			));
		
		assertThrows(
				RuntimeException.class, 
			() -> this.service.update(
				new BlogTagModel(
					new BlogTagId(2),
					new NameWord("テストタグ")
				)
			));
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_tag "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 
				1)).thenReturn(1);
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		setService();
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		InitDelete();		

		assertDoesNotThrow(
				() -> this.service.delete(new BlogTagId(1)));
		assertThrows(RuntimeException.class, 
				() -> this.service.delete(new BlogTagId(2)));
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
		
		setService();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogTagModel> list = this.service.getAll();
		
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
		
		setService();
	}
	
	/**
	 * 選択テスト
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogTagModel model = this.service.select(new BlogTagId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTag(), "テストタグ");
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select(new BlogTagId(2)));
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		BlogTagDao dao = new BlogTagDaoSql(jdbcTemp);
		this.service = new BlogTagServiceUse(dao);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp = null;
		this.service = null;
	}

}
