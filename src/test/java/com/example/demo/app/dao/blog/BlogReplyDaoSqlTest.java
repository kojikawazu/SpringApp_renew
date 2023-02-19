package com.example.demo.app.dao.blog;

import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;
/**
 * ブログ返信Daoクラステスト
 * @author nanai
 *
 */
class BlogReplyDaoSqlTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO blog_reply("
			+ "blog_id, name, comment, thanksCnt, created) "
			+ "VALUES(?,?,?,?,?)";
	
	/** daoクラス */
	BlogReplyDaoSql dao = null;
	
	// Mock
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	private void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setDao();
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
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01
				);
		
		this.dao.insertReply(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01);
	}
	
	/**
	 * 削除テストの準備
	 */
	private void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_reply "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql,
				1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql,
				2))
			.thenReturn(WebConsts.ERROR_NUMBER);
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteReply(new BlogReplyId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		int ret = this.dao.deleteReply(new BlogReplyId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * ブログIDによる削除テストの準備
	 */
	private void InitDelete_byCommentId() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_reply "
				+ "WHERE blog_id = ?";
		
		when(this.jdbcTemp.update(
				sql,
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.update(
				sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		this.setDao();
	}
	
	/**
	 * ブログIDによる削除テスト(正常系)
	 */
	@Test
	public void Delete_byCommentId_Test() {
		InitDelete_byCommentId();
		
		int ret = this.dao.deleteReply_byBlog(new BlogId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * ブログIDによる削除テスト(異常系)
	 */
	@Test
	public void Delete_byCommentId_Test_Error() {
		InitDelete_byCommentId();
		
		int ret = this.dao.deleteReply_byBlog(new BlogId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}

	/**
	 * 全て選択の準備
	 */
	private void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_reply";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_BLOG_ID_NAME,   1);
		map.put(WebConsts.SQL_NAME_NAME,      TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME,   TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME,   
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		this.setDao();
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
		
		Assertions.assertEquals(list.size(),                1);
		Assertions.assertEquals(list.get(0).getId(),        1);
		Assertions.assertEquals(list.get(0).getBlogId(),    1);
		Assertions.assertEquals(list.get(0).getName(),      TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(0).getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		list.clear();
	}
	
	/**
	 * 全て選択の準備(空)
	 */
	private void InitSelectAll_empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_reply";
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		this.setDao();
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
	private void InitSelect_byBlogId() {
		Map<String, Object> map            = new HashMap<String, Object>();
		List<Map<String, Object>> mapList  = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_reply "
				+ "WHERE blog_id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_BLOG_ID_NAME,   1);
		map.put(WebConsts.SQL_NAME_NAME,      TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME,   TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(
				sql, 1)).thenReturn(mapList);
		when(this.jdbcTemp.queryForList(
				sql, 2)).thenReturn(mapList2);
		
		this.setDao();
	}
	
	/**
	 * ブログIDによる選択のテスト(正常系)
	 */
	@Test
	public void Select_byBlogd_Test() {
		InitSelect_byBlogId();
		
		List<BlogReplyModel> list = this.dao.select_blogId(new BlogId(1));
		
		Assertions.assertEquals(list.size(),                1);
		Assertions.assertEquals(list.get(0).getId(),        1);
		Assertions.assertEquals(list.get(0).getBlogId(),    1);
		Assertions.assertEquals(list.get(0).getName(),      TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(0).getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		list.clear();
	}
	
	/**
	 * ブログIDによる選択のテスト(異常系)
	 */
	@Test
	public void Select_byBlogd_Test_Error() {
		InitSelect_byBlogId();
		
		List<BlogReplyModel> list = this.dao.select_blogId(new BlogId(2));
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * 選択テストの準備
	 */
	private void InitSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_reply "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_BLOG_ID_NAME,   1);
		map.put(WebConsts.SQL_NAME_NAME,      TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME,   TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		
		
		when(this.jdbcTemp.queryForMap(
				sql, 1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(
				sql, 2)).thenReturn(null);
		
		this.setDao();
	}
	
	/**
	 * 選択テスト(正常系)
	 */
	@Test
	public void SelectTest() {
		InitSelect();
		
		BlogReplyModel model = this.dao.select(new BlogReplyId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getBlogId(),    1);
		Assertions.assertEquals(model.getName(),      TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		
		
	}
	
	/**
	 * 選択テスト(異常系)
	 */
	@Test
	public void SelectTest_Error() {
		InitSelect();
		
		BlogReplyModel model = this.dao.select(new BlogReplyId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * インクリメントテストの準備
	 */
	private void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT thanksCnt "
				+ "FROM blog_reply "
				+ "WHERE id = ?";
		String sql_update = "UPDATE blog_reply "
				+ "SET thanksCnt = ? "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		
		when(this.jdbcTemp.queryForMap(
				sql, 1)).thenReturn(map);
		when(this.jdbcTemp.update(
				sql_update, 2, 1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.queryForMap(
				sql, 2)).thenReturn(null);
		
		this.setDao();
	}
	
	/**
	 * インクリメントテスト(正常系)
	 */
	@Test
	public void IncrementTest() {
		InitThanksIncrement();
		
		int ret = this.dao.thanksIncrement(new BlogReplyId(1));
		Assertions.assertEquals(ret, 2);
	}
	
	/**
	 * インクリメントテスト(異常系)
	 */
	@Test
	public void IncrementTest_Error() {
		InitThanksIncrement();
		
		int ret = this.dao.thanksIncrement(new BlogReplyId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * Dao設定
	 */
	private void setDao() {
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);
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
