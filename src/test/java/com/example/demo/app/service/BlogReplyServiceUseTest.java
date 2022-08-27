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

import com.example.demo.app.dao.BlogReplyDao;
import com.example.demo.app.dao.BlogReplyDaoSql;
import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信サービステスト
 * @author nanai
 *
 */
class BlogReplyServiceUseTest {
	
	/** テスト対象 */
	BlogReplyDao        dao     = null;
	BlogReplyServiceUse service = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "INSERT INTO blog_reply("
				+ "blog_id, name, comment, thanksCnt, created) "
				+ "VALUES(?,?,?,?,?)";
		
		when(this.jdbcTemp.update(
				sql, 
				1,
				"テストネーム",
				"テストコメント",
				1,
				dateTime1
				)).thenReturn(1);
		
		setService();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		BlogReplyModel model = new BlogReplyModel(
				new BlogId(1),
				new NameWord("テストネーム"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime1 
				);
		
		assertDoesNotThrow(() -> 
			this.service.save(model));
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_reply "
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
		
		assertDoesNotThrow(() -> 
			this.service.delete(new BlogReplyId(1)));
		assertThrows(RuntimeException.class, () -> 
			this.service.delete(new BlogReplyId(2)));
	}
	
	/**
	 * ブログIDによる削除テストの準備
	 */
	public void InitDelete_byBlogId() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.update(
				"DELETE FROM blog_reply "
				+ "WHERE blog_id = ?", 
				1)).thenReturn(1);
		when(this.jdbcTemp.update(
				"DELETE FROM blog_reply "
				+ "WHERE blog_id = ?", 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		setService();
	}
	
	/**
	 * ブログIDによる削除テスト
	 */
	@Test
	public void Delete_byBlogId_Test() {
		InitDelete_byBlogId();
		
		assertDoesNotThrow(() -> 
			this.service.delete_byBlogid(new BlogId(1)));
		assertThrows(RuntimeException.class, () -> 
			this.service.delete_byBlogid(new BlogId(2)));
	}
	
	/**
	 * 全て選択テストの準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("blog_id", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
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
		
		List<BlogReplyModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getBlogId(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
	}
	
	/**
	 * 選択の準備
	 */
	public void InitSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT * "
				+ "FROM blog_reply "
				+ "WHERE id = ?";
		
		map.put("id", 1);
		map.put("blog_id", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		
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
	public void SelectTest() {
		InitSelect();
		
		assertDoesNotThrow(() -> 
			this.service.select(new BlogReplyId(1)));
		BlogReplyModel model = this.service.select(new BlogReplyId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getBlogId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select(new BlogReplyId(2)));
	}
	
	/**
	 * ブログIDによる選択テストの準備
	 */
	public void InitSelect_byBlogId() {
		Map<String, Object> map            = new HashMap<String, Object>();
		List<Map<String, Object>> mapList  = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * "
				+ "FROM blog_reply "
				+ "WHERE blog_id = ?";
		
		map.put("id", 1);
		map.put("blog_id", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(sql, 
				1)).thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, 
				2)).thenReturn(mapList2);
		
		setService();
	}
	
	/**
	 * ブログIDによる選択テスト
	 */
	@Test
	public void Select_byBlogId_Test() {
		InitSelect_byBlogId();
		
		assertDoesNotThrow( () -> 
		this.service.select_byBlogId(new BlogId(1)));
		List<BlogReplyModel> list = this.service.select_byBlogId(new BlogId(1));
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getBlogId(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select_byBlogId(new BlogId(2)));
	}
	
	/**
	 * いいね数加算の準備
	 */
	public void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thanksCnt", 1);
		
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(
				"SELECT thanksCnt "
				+ "FROM blog_reply "
				+ "WHERE id = ?", 1)).thenReturn(map);
		when(this.jdbcTemp.update(
				"UPDATE blog_reply "
				+ "SET thanksCnt = ? "
				+ "WHERE id = ?", 
				2, 1)).thenReturn(1);
		
		when(this.jdbcTemp.queryForMap(
				"SELECT thanksCnt "
				+ "FROM blog_reply "
				+ "WHERE id = ?", 
				2)).thenReturn(null);
		
		setService();
	}
	
	/**
	 * いいね数加算
	 */
	@Test
	public void IncrementTest() {
		InitThanksIncrement();
		
		int ret = this.service.thanksIncrement(new BlogReplyId(1));
		Assertions.assertEquals(ret, 2);
		
		assertThrows(RuntimeException.class, () ->
			this.service.thanksIncrement(new BlogReplyId(2)));
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		BlogReplyDao dao = new BlogReplyDaoSql(this.jdbcTemp);
		this.service = new BlogReplyServiceUse(dao);
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
