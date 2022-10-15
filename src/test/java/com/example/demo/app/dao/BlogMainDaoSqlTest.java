package com.example.demo.app.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
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

import com.example.demo.app.dao.blog.BlogMainDao;
import com.example.demo.app.dao.blog.BlogMainDaoSql;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログメインDaoクラステスト
 * @author nanai
 *
 */
class BlogMainDaoSqlTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO blog_main("
			+ "title, tag, comment, thanksCnt, created, updated) "
			+ "VALUES(?,?,?,?,?,?)";
	
	private final String        TEST_REPLY_NAME     = "リプライネーム";
	private final String        TEST_REPLY_COMMENT  = "リプライコメント";
	
	/** daoクラス */
	private BlogMainDao dao = null;
	
	/** jdbcクラス */
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		
		
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 追加テストの準備
	 */
	private void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setDao();
	}
	
	/**
	 * 追加テスト(正常系)
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		BlogMainModel model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		this.dao.insertBlog(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}
	
	// --------------------------------------------------------------------------------------------------
	
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
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_02,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		// 異常系
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_02,
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 更新テスト（正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		BlogMainModel model = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		int ret = this.dao.updateBlog(model);
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 更新テスト（異常系)
	 */
	@Test
	public void UpdateTestError() {
		InitUpdate();
		
		BlogMainModel 	model = new BlogMainModel(
				new BlogId(2),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		// 異常系テスト
		int ret = dao.updateBlog(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	// --------------------------------------------------------------------------------------------------
	
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
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteBlog(new BlogId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTestError() {
		InitDelete();
		
		int ret = this.dao.deleteBlog(new BlogId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全て選択テストの準備
	 */
	private void InitSelectAll() {
		Map<String, Object>       map     = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_main";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_TITLE_NAME,     TestConsts.TEST_TITLE_NAME);
		map.put(WebConsts.SQL_TAG_NAME,       TestConsts.TEST_TAG_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME,   TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME,   Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME,   Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		this.setDao();
	}
	
	/**
	 * 全て選択のテスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogMainModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(),                1);
		Assertions.assertEquals(list.get(0).getId(),        1);
		Assertions.assertEquals(list.get(0).getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(list.get(0).getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(list.get(0).getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全て選択テストの準備(空リスト)
	 */
	private void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_main";
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
	
		this.setDao();
	}
	
	/**
	 * 全て選択テスト(空リスト)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		List<BlogMainModel> list = this.dao.getAll();
		
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全て選択テストの準備
	 */
	private void InitSelectAll_PLus(boolean isDesc) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT blog_main.*,"
				+ "blog_reply.id AS reply_id,"
				+ "blog_reply.name AS reply_name,"
				+ "blog_reply.comment AS reply_comment,"
				+ "blog_reply.thankscnt AS reply_thankscnt,"
				+ "blog_reply.created AS reply_created "
				+ "from blog_main "
				+ "LEFT OUTER JOIN blog_reply ON "
				+ "blog_main.id = blog_reply.blog_id "
				+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		
		int cnter=1;
		for(int idx=1; idx<=2; idx++) {
			Map<String, Object> map01 = new HashMap<String, Object>();
			map01.put(WebConsts.SQL_ID_NAME,               cnter);
			map01.put(WebConsts.SQL_TITLE_NAME,            TestConsts.TEST_TITLE_NAME   + cnter);
			map01.put(WebConsts.SQL_TAG_NAME,              TestConsts.TEST_TAG_NAME     + cnter);
			map01.put(WebConsts.SQL_COMMENT_NAME,          TestConsts.TEST_COMMENT_NAME + cnter);
			map01.put(WebConsts.SQL_THANKSCNT_NAME,        cnter);
			map01.put(WebConsts.SQL_CREATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map01.put(WebConsts.SQL_UPDATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
			map01.put(WebConsts.SQL_REPLY_ID_NAME,         idx);
			map01.put(WebConsts.SQL_REPLY_NAME_NAME,       TEST_REPLY_NAME    + idx);
			map01.put(WebConsts.SQL_REPLY_COMMENT_NAME,    TEST_REPLY_COMMENT + idx);
			map01.put(WebConsts.SQL_REPLY_THANKS_CNT_NAME, 1);
			map01.put(WebConsts.SQL_REPLY_CREATED_NAME,    Timestamp.valueOf(TestConsts.TEST_TIME_01));
			mapList.add(map01);
		}
		
		cnter++;
		Map<String, Object> map02 = new HashMap<String, Object>();
		map02.put(WebConsts.SQL_ID_NAME,               2);
		map02.put(WebConsts.SQL_TITLE_NAME,            TestConsts.TEST_TITLE_NAME + cnter);
		map02.put(WebConsts.SQL_TAG_NAME,              TestConsts.TEST_TAG_NAME   + cnter);
		map02.put(WebConsts.SQL_COMMENT_NAME,          TestConsts.TEST_COMMENT_NAME + cnter);
		map02.put(WebConsts.SQL_THANKSCNT_NAME,        1);
		map02.put(WebConsts.SQL_CREATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map02.put(WebConsts.SQL_UPDATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map02);
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		this.setDao();
	}
	
	/**
	 * 全て選択のテスト
	 */
	@Test
	public void SelectAllPlus_Test() {
		this.InitSelectAll_PLus(false);
		
		List<BlogMainModel> list = this.dao.getAll_Plus(false);

		Assertions.assertEquals(list.size(), 2);
		BlogMainModel model1                 = list.get(0);
		List<BlogReplyModel> replyModel1     = model1.getReplyList();
		BlogMainModel model2                 = list.get(1);
		List<BlogReplyModel> replyModel2     = model2.getReplyList();
		
		// model1
		int modelCnt = 1;
		Assertions.assertEquals(model1.getId(),        modelCnt);
		Assertions.assertEquals(model1.getTitle(),     TestConsts.TEST_TITLE_NAME   + modelCnt);
		Assertions.assertEquals(model1.getTag(),       TestConsts.TEST_TAG_NAME     + modelCnt);
		Assertions.assertEquals(model1.getComment(),   TestConsts.TEST_COMMENT_NAME + modelCnt);
		Assertions.assertEquals(model1.getThanksCnt(), 1);
		Assertions.assertEquals(model1.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model1.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		
		// replyModel
		Assertions.assertEquals(replyModel1.size(),  2);
		int modelReplyCnt = 1;
		for(int idx=0; idx<2; idx++) {
			Assertions.assertEquals(replyModel1.get(idx).getId(),          idx+1);
			Assertions.assertEquals(replyModel1.get(idx).getBlogId(),      modelCnt);
			Assertions.assertEquals(replyModel1.get(idx).getName(),        TEST_REPLY_NAME    + modelReplyCnt);
			Assertions.assertEquals(replyModel1.get(idx).getComment(),     TEST_REPLY_COMMENT + modelReplyCnt);
			Assertions.assertEquals(replyModel1.get(idx).getThanksCnt(),   1);
			Assertions.assertEquals(replyModel1.get(idx).getCreated().toString(),
					TestConsts.TEST_TIME_01.toString());
			modelReplyCnt++;
		}
		
		// model2
		modelCnt++;
		Assertions.assertEquals(model2.getId(),        modelCnt);
		Assertions.assertEquals(model2.getTitle(),     TestConsts.TEST_TITLE_NAME   + modelCnt);
		Assertions.assertEquals(model2.getTag(),       TestConsts.TEST_TAG_NAME     + modelCnt);
		Assertions.assertEquals(model2.getComment(),   TestConsts.TEST_COMMENT_NAME + modelCnt);
		Assertions.assertEquals(model2.getThanksCnt(), 1);
		Assertions.assertEquals(model2.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model2.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		Assertions.assertEquals(replyModel2.size(),  0);
		
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * IDによるデータ取得の準備
	 */
	private void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_TITLE_NAME,     TestConsts.TEST_TITLE_NAME);
		map.put(WebConsts.SQL_TAG_NAME,       TestConsts.TEST_TAG_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME,   TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_THANKSCNT_NAME, 1);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(WebConsts.SQL_UPDATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_02));
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		this.setDao();
	}
	
	/**
	 * IDによるデータ取得のテスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogMainModel model = this.dao.select(new BlogId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(model.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
	}
	
	/**
	 * IDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		BlogMainModel model = this.dao.select(new BlogId(2));
		Assertions.assertNull(model);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * タグ名によるデータ取得の準備
	 */
	private void InitSelect_byTag() {
		Map<String, Object> map            = new HashMap<String, Object>();
		List<Map<String, Object>> mapList  = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_main "
				+ "WHERE tag = ?";
		
		map.put(WebConsts.SQL_ID_NAME,        1);
		map.put(WebConsts.SQL_TITLE_NAME,     TestConsts.TEST_TITLE_NAME);
		map.put(WebConsts.SQL_TAG_NAME,       TestConsts.TEST_TAG_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME,   TestConsts.TEST_COMMENT_NAME);
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
		
		this.setDao();
	}
	
	/**
	 * タグ名による選択のテスト(正常系)
	 */
	@Test
	public void Select_byTagTest() {
		InitSelect_byTag();
		
		List<BlogMainModel> list = this.dao.select_byTag("テスト");
		
		Assertions.assertEquals(list.size(),                1);
		Assertions.assertEquals(list.get(0).getId(),        1);
		Assertions.assertEquals(list.get(0).getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(list.get(0).getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(list.get(0).getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		list.clear();
		
	}
	
	/**
	 * タグ名による選択のテスト(異常系)
	 */
	@Test
	public void Select_byTagTest_Error() {
		InitSelect_byTag();
		
		List<BlogMainModel> list = this.dao.select_byTag("バグ");
		Assertions.assertEquals(list.size(), 0);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * タグによる選択テストの準備
	 * (ブログ返信モデルリストつき)
	 */
	private void InitSelect_byTagPlus(boolean isDesc) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT blog_main.*,"
				+ "blog_reply.id AS reply_id,"
				+ "blog_reply.name AS reply_name,"
				+ "blog_reply.comment AS reply_comment,"
				+ "blog_reply.thankscnt AS reply_thankscnt,"
				+ "blog_reply.created AS reply_created "
				+ "from blog_main "
				+ "LEFT OUTER JOIN blog_reply ON "
				+ "blog_main.id = blog_reply.blog_id "
				+ "where blog_main.tag = ? "
				+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		
		int cnter=1;
		for(int idx=1; idx<=2; idx++) {
			Map<String, Object> map01 = new HashMap<String, Object>();
			map01.put(WebConsts.SQL_ID_NAME,               cnter);
			map01.put(WebConsts.SQL_TITLE_NAME,            TestConsts.TEST_TITLE_NAME   + cnter);
			map01.put(WebConsts.SQL_TAG_NAME,              TestConsts.TEST_TAG_NAME);
			map01.put(WebConsts.SQL_COMMENT_NAME,          TestConsts.TEST_COMMENT_NAME + cnter);
			map01.put(WebConsts.SQL_THANKSCNT_NAME,        cnter);
			map01.put(WebConsts.SQL_CREATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map01.put(WebConsts.SQL_UPDATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
			map01.put(WebConsts.SQL_REPLY_ID_NAME,         idx);
			map01.put(WebConsts.SQL_REPLY_NAME_NAME,       TEST_REPLY_NAME    + idx);
			map01.put(WebConsts.SQL_REPLY_COMMENT_NAME,    TEST_REPLY_COMMENT + idx);
			map01.put(WebConsts.SQL_REPLY_THANKS_CNT_NAME, 1);
			map01.put(WebConsts.SQL_REPLY_CREATED_NAME,    Timestamp.valueOf(TestConsts.TEST_TIME_01));
			mapList.add(map01);
		}
		
		cnter++;
		Map<String, Object> map02 = new HashMap<String, Object>();
		map02.put(WebConsts.SQL_ID_NAME,               2);
		map02.put(WebConsts.SQL_TITLE_NAME,            TestConsts.TEST_TITLE_NAME + cnter);
		map02.put(WebConsts.SQL_TAG_NAME,              TestConsts.TEST_TAG_NAME);
		map02.put(WebConsts.SQL_COMMENT_NAME,          TestConsts.TEST_COMMENT_NAME + cnter);
		map02.put(WebConsts.SQL_THANKSCNT_NAME,        1);
		map02.put(WebConsts.SQL_CREATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map02.put(WebConsts.SQL_UPDATED_NAME,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map02);
		
		when(this.jdbcTemp.queryForList(sql, TestConsts.TEST_TAG_NAME))
			.thenReturn(mapList);
		
		this.setDao();
	}
	
	/**
	 * タグによる選択テスト
	 * (ブログ返信モデルリストつき)
	 */
	@Test
	public void Select_byTagPlus_Test() {
		this.InitSelect_byTagPlus(false);
		
		List<BlogMainModel> list = this.dao.select_byTagPlus(TestConsts.TEST_TAG_NAME, false);

		Assertions.assertEquals(list.size(), 2);
		BlogMainModel model1                 = list.get(0);
		List<BlogReplyModel> replyModel1     = model1.getReplyList();
		BlogMainModel model2                 = list.get(1);
		List<BlogReplyModel> replyModel2     = model2.getReplyList();
		
		// model1
		int modelCnt = 1;
		Assertions.assertEquals(model1.getId(),        modelCnt);
		Assertions.assertEquals(model1.getTitle(),     TestConsts.TEST_TITLE_NAME   + modelCnt);
		Assertions.assertEquals(model1.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model1.getComment(),   TestConsts.TEST_COMMENT_NAME + modelCnt);
		Assertions.assertEquals(model1.getThanksCnt(), 1);
		Assertions.assertEquals(model1.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model1.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		
		// replyModel
		Assertions.assertEquals(replyModel1.size(),  2);
		int modelReplyCnt = 1;
		for(int idx=0; idx<2; idx++) {
			Assertions.assertEquals(replyModel1.get(idx).getId(),          idx+1);
			Assertions.assertEquals(replyModel1.get(idx).getBlogId(),      modelCnt);
			Assertions.assertEquals(replyModel1.get(idx).getName(),        TEST_REPLY_NAME    + modelReplyCnt);
			Assertions.assertEquals(replyModel1.get(idx).getComment(),     TEST_REPLY_COMMENT + modelReplyCnt);
			Assertions.assertEquals(replyModel1.get(idx).getThanksCnt(),   1);
			Assertions.assertEquals(replyModel1.get(idx).getCreated().toString(),
					TestConsts.TEST_TIME_01.toString());
			modelReplyCnt++;
		}
		
		// model2
		modelCnt++;
		Assertions.assertEquals(model2.getId(),        modelCnt);
		Assertions.assertEquals(model2.getTitle(),     TestConsts.TEST_TITLE_NAME   + modelCnt);
		Assertions.assertEquals(model2.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model2.getComment(),   TestConsts.TEST_COMMENT_NAME + modelCnt);
		Assertions.assertEquals(model2.getThanksCnt(), 1);
		Assertions.assertEquals(model2.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model2.getUpdated().toString(), 
				TestConsts.TEST_TIME_02.toString());
		Assertions.assertEquals(replyModel2.size(),  0);
		
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * インクリメントテストの準備
	 */
	private void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT thanksCnt "
				+ "FROM blog_main "
				+ "WHERE id = ?";
		
		map.put("thanksCnt", 1);
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.update(
				"UPDATE blog_main SET "
				+ "thanksCnt = ? "
				+ "WHERE id = ?", 
				2, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		this.setDao();
	}
	
	/**
	 * インクリメントテスト(正常系)
	 */
	@Test
	public void IncrementTest() {
		InitThanksIncrement();
		
		int ret = this.dao.thanksIncrement(new BlogId(1));
		Assertions.assertEquals(ret, 2);
	}
	
	/**
	 * インクリメントテスト(異常系)
	 */
	@Test
	public void IncrementTest_Error() {
		InitThanksIncrement();
		
		int ret = this.dao.thanksIncrement(new BlogId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * daoクラスの設定
	 */
	private void setDao() {
		this.dao = new BlogMainDaoSql(this.jdbcTemp);
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
