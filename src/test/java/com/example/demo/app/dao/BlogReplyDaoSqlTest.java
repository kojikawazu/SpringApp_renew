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
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;
/**
 * ブログ返信Daoクラステスト
 * @author nanai
 *
 */
class BlogReplyDaoSqlTest {
	
	BlogReplyDaoSql dao = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);

	// Mock
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
		
		when(this.jdbcTemp.update(sql, 
				1,
				"テストネーム",
				"テストコメント",
				1,
				dateTime1
				)).thenReturn(1);
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 追加のテスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		BlogReplyModel model = new BlogReplyModel(
				new BlogReplyId(1),
				new BlogId(1),
				new NameWord("テストネーム"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime1
				);
		
		this.dao.insertReply(model);
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
				1))
			.thenReturn(1);
		when(this.jdbcTemp.update(sql,
				2))
			.thenReturn(WebConsts.ERROR_NUMBER);
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteReply(new BlogReplyId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteReply(new BlogReplyId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * ブログIDによる削除テストの準備
	 */
	public void InitDelete_byCommentId() {
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
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * ブログIDによる削除テスト
	 */
	@Test
	public void Delete_byCommentId_Test() {
		InitDelete_byCommentId();
		
		int ret = this.dao.deleteReply_byBlog(new BlogId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteReply_byBlog(new BlogId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}

	/**
	 * 全て選択の準備
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
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 全て選択テスト
	 */
	@Test
	public void SelectAllTest() {
		// 準備
		InitSelectAll();
		
		// テスト
		List<BlogReplyModel> list = this.dao.getAll();
		
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
	 * 全て選択の準備(空)
	 */
	public void InitSelectAll_empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 全て選択テスト(空)
	 */
	@Test
	public void SelectAll_empty_Test() {
		// 準備
		InitSelectAll_empty();
		
		// テスト
		List<BlogReplyModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(), 0);
		list.clear();
	}
	
	/**
	 * ブログIDによる選択テストの準備
	 */
	public void InitSelect_byBlogId() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("blog_id", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(
				"SELECT * "
					+ "FROM blog_reply "
					+ "WHERE blog_id = ?", 
					1)).thenReturn(mapList);
		when(this.jdbcTemp.queryForList(
				"SELECT * "
					+ "FROM blog_reply "
					+ "WHERE blog_id = ?",
				2)).thenReturn(mapList2);
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * ブログIDによる選択のテスト
	 */
	@Test
	public void Select_byBlogd_Test() {
		InitSelect_byBlogId();
		
		List<BlogReplyModel> list = this.dao.select_blogId(new BlogId(1));
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getBlogId(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
		
		list = this.dao.select_blogId(new BlogId(2));
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * 選択テストの準備
	 */
	public void InitSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("blog_id", 1);
		map.put("name", "テストネーム");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(
				"SELECT * "
					+ "FROM blog_reply "
					+ "WHERE id = ?",
					1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(
				"SELECT * "
					+ "FROM blog_reply "
					+ "WHERE id = ?",
					2)).thenReturn(null);
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 選択テスト
	 */
	@Test
	public void SelectTest() {
		InitSelect();
		
		BlogReplyModel model = this.dao.select(new BlogReplyId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getBlogId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		model = this.dao.select(new BlogReplyId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * インクリメントテストの準備
	 */
	public void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thanksCnt", 1);
		
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(
				"SELECT thanksCnt "
					+ "FROM blog_reply "
					+ "WHERE id = ?"
					, 1)).thenReturn(map);
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
		
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * インクリメントテスト
	 */
	@Test
	public void IncrementTest() {
		InitThanksIncrement();
		
		int ret = this.dao.thanksIncrement(new BlogReplyId(1));
		Assertions.assertEquals(ret, 2);
		
		ret = this.dao.thanksIncrement(new BlogReplyId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp = null;
		this.dao = null;
	}

}
