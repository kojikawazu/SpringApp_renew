package com.example.demo.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

import com.example.demo.app.dao.blog.BlogReplyDao;
import com.example.demo.app.dao.blog.BlogReplyDaoSql;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.service.blog.BlogReplyServiceUse;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.id.blog.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信サービステスト
 * @author nanai
 *
 */
class BlogReplyServiceUseTest {
	
	private static final String SQL_INSERT = "INSERT INTO blog_reply("
			+ "blog_id, name, comment, thanksCnt, created) "
			+ "VALUES(?,?,?,?,?)";
	
	/** テスト対象 */
	BlogReplyDao        dao     = null;
	BlogReplyServiceUse service = null;
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				"テストネーム",
				"テストコメント",
				1,
				TestConsts.TEST_TIME_01
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
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
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01 
				);
		
		assertDoesNotThrow(() -> 
			this.service.save(model));
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
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_reply "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		setService();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		assertDoesNotThrow(() -> 
			this.service.delete(new BlogReplyId(1)));
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.delete(new BlogReplyId(2)));
	}
	
	/**
	 * ブログIDによる削除テストの準備
	 */
	public void InitDelete_byBlogId() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_reply "
				+ "WHERE blog_id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(
				sql, 
				2)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * ブログIDによる削除テスト(正常系)
	 */
	@Test
	public void Delete_byBlogId_Test() {
		InitDelete_byBlogId();
		
		assertDoesNotThrow(() -> 
			this.service.delete_byBlogid(new BlogId(1)));
	}
	
	/**
	 * ブログIDによる削除テスト(異常系)
	 */
	@Test
	public void Delete_byBlogId_Test_Error() {
		InitDelete_byBlogId();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.delete_byBlogid(new BlogId(2)));
	}
	
	/**
	 * 全て選択テストの準備
	 */
	public void InitSelectAll() {
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
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogReplyModel> list = this.service.getAll();
		
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
	 * 選択の準備
	 */
	public void InitSelect() {
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
		
		when(this.jdbcTemp.queryForMap(sql, 
				1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 
				2)).thenReturn(null);
		
		setService();
	}
	
	/**
	 * 選択テスト(正常系)
	 */
	@Test
	public void SelectTest() {
		InitSelect();
		
		assertDoesNotThrow(() -> 
			this.service.select(new BlogReplyId(1)));
		BlogReplyModel model = this.service.select(new BlogReplyId(1));
		
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
	 * 選択テスト(正常系)
	 */
	@Test
	public void SelectTest_Error() {
		InitSelect();
		
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
		
		when(this.jdbcTemp.queryForList(sql, 
				1)).thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, 
				2)).thenReturn(mapList2);
		
		setService();
	}
	
	/**
	 * ブログIDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byBlogId_Test() {
		InitSelect_byBlogId();
		
		List<BlogReplyModel> list = this.service.select_byBlogId(new BlogId(1));
		
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
	 *  ブログIDによる選択テスト(異常系)
	 */
	@Test
	public void Select_byBlogId_Test_Error() {
		InitSelect_byBlogId();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select_byBlogId(new BlogId(2)));
	}
	
	/**
	 * いいね数加算の準備
	 */
	public void InitThanksIncrement() {
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
		
		setService();
	}
	
	/**
	 * いいね数加算(正常系)
	 */
	@Test
	public void IncrementTest() {
		InitThanksIncrement();
		
		int ret = this.service.thanksIncrement(new BlogReplyId(1));
		Assertions.assertEquals(ret, 2);
	}
	
	/**
	 * いいね数加算(異常系)
	 */
	@Test
	public void IncrementTest_Error() {
		InitThanksIncrement();
		
		assertThrows(RuntimeException.class, () ->
			this.service.thanksIncrement(new BlogReplyId(2)));
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		BlogReplyDao dao = new BlogReplyDaoSql(this.jdbcTemp);
		this.service     = new BlogReplyServiceUse(dao);
	}

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp = null;
		this.service  = null;
	}

}
