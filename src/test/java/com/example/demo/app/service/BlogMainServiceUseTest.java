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

import com.example.demo.app.dao.BlogMainDao;
import com.example.demo.app.dao.BlogMainDaoSql;
import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.exception.InquiryNotFoundException;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログメインサービスクラス
 * @author nanai
 *
 */
class BlogMainServiceUseTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO blog_main("
			+ "title, tag, comment, thanksCnt, created, updated) "
			+ "VALUES(?,?,?,?,?,?)";
	
	/** テスト対象 */
	BlogMainService service = null;
	
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
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setService();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		BlogMainModel model = new BlogMainModel(
				new NameWord("テストタイトル"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}
	
	/**
	 * 更新テストの準備
	 */
	private void InitUpdate() {		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE blog_main SET "
				+ "title = ?, tag = ?, comment = ?, thanksCnt = ?, updated = ? "
				+ "WHERE id = ?";
		
		// 正常系
		when(this.jdbcTemp.update(
				sql, 
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				TestConsts.TEST_TIME_02,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		// 異常系
		when(this.jdbcTemp.update(
				sql, 
				"テストタイトル",
				"テストタグ",
				"テストコメント",
				1,
				TestConsts.TEST_TIME_02,
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setService();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		assertDoesNotThrow(
			() -> this.service.update(
				new BlogMainModel(
					new BlogId(1),
					new NameWord("テストタイトル"),
					new NameWord("テストタグ"),
					new NameWord("テストコメント"),
					new ThanksCntNumber(1),
					TestConsts.TEST_TIME_01,
					TestConsts.TEST_TIME_02
				)
			));
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTest_Error() {
		InitUpdate();
		
		assertThrows(
			RuntimeException.class,
			() -> this.service.update(
				new BlogMainModel(
					new BlogId(2),
					new NameWord("テストタイトル"),
					new NameWord("テストタグ"),
					new NameWord("テストコメント"),
					new ThanksCntNumber(1),
					TestConsts.TEST_TIME_01,
					TestConsts.TEST_TIME_02
				)
			));
	}
	
	/**
	 * 削除テストの準備
	 */
	private void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_main "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		assertDoesNotThrow(() -> 
			this.service.delete(new BlogId(1)));
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		assertThrows(RuntimeException.class, 
			() -> this.service.delete(new BlogId(2)));
	}
	
	/**
	 * 全て選択の準備
	 */
	private void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_main";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_TITLE_NAME,     "テストタイトル");
		map.put(WebConsts.SQL_TAG_NAME,       "テストタグ");
		map.put(WebConsts.SQL_COMMENT_NAME,   "テストコメント");
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME, Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME, Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全て選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogMainModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(),                1);
		Assertions.assertEquals(list.get(0).getId(),        1);
		Assertions.assertEquals(list.get(0).getTitle(),     "テストタイトル");
		Assertions.assertEquals(list.get(0).getTag(),       "テストタグ");
		Assertions.assertEquals(list.get(0).getComment(),   "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		list.clear();
	}
	
	/**
	 * 全て選択の準備(空)
	 */
	private void InitSelectAll_empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_main";
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全て選択テスト(空)
	 */
	@Test
	public void SelectAllTest_empty() {
		InitSelectAll_empty();
		
		assertThrows(RuntimeException.class, () ->
			this.service.getAll());
	}
	
	/**
	 * IDによるデータ選択の準備
	 */
	private void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_TITLE_NAME,     "テストタイトル");
		map.put(WebConsts.SQL_TAG_NAME,       "テストタグ");
		map.put(WebConsts.SQL_COMMENT_NAME,   "テストコメント");
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_02));
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		setService();
	}
	
	/**
	 * IDによる選択のテスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogMainModel model = this.service.select(new BlogId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getTitle(),     "テストタイトル");
		Assertions.assertEquals(model.getTag(),       "テストタグ");
		Assertions.assertEquals(model.getComment(),   "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
	}
	
	/**
	 * IDによる選択のテスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		Assertions.assertThrows(RuntimeException.class,
				() ->  this.service.select(new BlogId(2)));
	}
	
	/**
	 * タグによる選択の準備
	 */
	public void InitSelect_byTag() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_main "
				+ "WHERE tag = ?";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_TITLE_NAME,     "テストタイトル");
		map.put(WebConsts.SQL_TAG_NAME,       "テストタグ");
		map.put(WebConsts.SQL_COMMENT_NAME,   "テストコメント");
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql, "テスト"))
			.thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, "バグ"))
			.thenReturn(mapList2);
		
		setService();
	}
	
	/**
	 * タグによる選択のテスト(正常系)
	 */
	@Test
	public void Select_byTagTest() {
		InitSelect_byTag();
		
		List<BlogMainModel> list = this.service.select_byTag("テスト");
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTitle(), "テストタイトル");
		Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		list.clear();
	}
	
	/**
	 * タグによる選択のテスト(異常系)
	 */
	@Test
	public void Select_byTagTest_Error() {
		InitSelect_byTag();
		
		Assertions.assertThrows(RuntimeException.class,
				() -> { this.service.select_byTag("バグ"); });
	}
	
	/**
	 * インクリメントテストの準備
	 */
	public void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT thanksCnt "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.update("UPDATE blog_main SET "
				+ "thanksCnt = ? "
				+ "WHERE id = ?", 
				2, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		setService();
	}
	
	/**
	 * インクリメントテスト(正常系)
	 */
	@Test
	public void IncrementTest() {
		InitThanksIncrement();
		
		int ret = this.service.thanksIncrement(new BlogId(1));
		Assertions.assertEquals(ret, 2);
		
		
	}
	
	/**
	 * インクリメントテスト(正常系)
	 */
	@Test
	public void IncrementTest_Error() {
		InitThanksIncrement();
		
		assertThrows(RuntimeException.class, () ->
			this.service.thanksIncrement(new BlogId(2)));
	}
	
	/**
	 * サービスのインスタンス化
	 */
	public void setService() {
		BlogMainDao dao = new BlogMainDaoSql(this.jdbcTemp);
		this.service = new BlogMainServiceUse(dao);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.service = null;
		this.jdbcTemp = null;
	}
}
