package com.example.demo.app.service.blog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.dao.blog.BlogMainDaoSql;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.SQLNoInsertException;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログメインサービスクラステスト
 * @author nanai
 *
 */
class BlogMainServiceUseTest {

	/** DB名 */
	private final String DB_NAME = "blog_main";

	/**
	 * パラム名
	 */
	private final String PARAM_ID 			= "id";
	private final String PARAM_TITLE 		= "title";
	private final String PARAM_TAG			= "tag";
	private final String PARAM_COMMENT		= "comment";
	private final String PARAM_THANKSCNT	= "thanksCnt";
	private final String PARAM_CREATED		= "created";
	private final String PARAM_UPDATED		= "updated";

	/** SQL文(追加) */
	private final String SQL_INSERT = WebConsts.SQL_INSERT + " " + DB_NAME
			+ "("
			+ PARAM_TITLE 		+ ", "
			+ PARAM_TAG 		+ ", "
			+ PARAM_COMMENT 	+ ", "
			+ PARAM_THANKSCNT 	+ ", "
			+ PARAM_CREATED 	+ ", "
			+ PARAM_UPDATED
			+ ") "
			+ "VALUES(?,?,?,?,?,?)";

	/** SQL文(全選択) */
	private final String SQL_SELECT_ALL = WebConsts.SQL_SELECT + " "
		+ PARAM_ID + ", "
		+ PARAM_TITLE + ", "
		+ PARAM_TAG + ", "
		+ PARAM_COMMENT + ", "
		+ PARAM_THANKSCNT + ", "
		+ PARAM_CREATED + ", "
		+ PARAM_UPDATED + " " 
		+ WebConsts.SQL_FROM + " " + DB_NAME;	

	/** テスト対象 */
	BlogMainServiceUse service = null;
	@Mock
	JdbcTemplate jdbcTemp = null;
	@Mock
	IntroAppLogWriter logWriter = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * Mockの設定
	 */
	private void setMock() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		this.logWriter = mock(IntroAppLogWriter.class);
	}

	/**
	 * サービスのインスタンス化
	 */
	public void setService() {
		BlogMainDaoSql dao = new BlogMainDaoSql(this.jdbcTemp);
		this.service = new BlogMainServiceUse(dao);

		Field fld, fldDao;
		try {
			fld    = this.service.getClass().getDeclaredField("logWriter");
			fldDao = dao.getClass().getDeclaredField("logWriter");
			fld.setAccessible(true);
			fldDao.setAccessible(true);
			fld.set(this.service, this.logWriter);
			fldDao.set(dao, this.logWriter);
		} catch (IllegalArgumentException | 
				IllegalAccessException | 
				NoSuchFieldException | 
				SecurityException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 初期化
	 */
	@BeforeEach
	public void init() {
		setMock();
		setService();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テストの準備
	 */
	private void initInsert() {
		// 正常系
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		// 異常系
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				TestConsts.TEST_TITLE_NAME + "NG",
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenThrow(SQLNoInsertException.class);
		
	}

	/**
	 * 追加テスト(正常系)
	 */
	@Test
	public void insertTest() {
		initInsert();

		BlogMainModel model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
	}

	/**
	 * 追加テスト(異常系)
	 */
	@Test
	public void insertTest_Error() {
		initInsert();

		BlogMainModel model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME + "NG"),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		this.service.save(model);
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 追加テスト(異常系)(引数エラー)
	 */
	@Test
	public void insertTest_ArgumentsError() {
		this.service.save(null);
		verify(this.logWriter, times(1)).error(anyString());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 更新テストの準備
	 */
	private void initUpdate() {
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_TITLE 		+ " = ?, "
				+ PARAM_TAG 		+ " = ?, "
				+ PARAM_COMMENT 	+ " = ?, "
				+ PARAM_THANKSCNT 	+ " = ?, "
				+ PARAM_UPDATED 	+ " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

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
	}

	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void updateTest() {
		initUpdate();

		BlogMainModel model = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);

		assertDoesNotThrow(() -> this.service.update(model));
	}

	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void updateTest_Error() {
		initUpdate();

		BlogMainModel model = new BlogMainModel(
				new BlogId(2),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);

		assertThrows(RuntimeException.class, () -> this.service.update(model));
	}

	/**
	 * 更新テスト(異常系)(引数エラー)
	 */
	@Test
	public void updateTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.service.update(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void deleteTest() {
		initDelete();
		assertDoesNotThrow(() -> this.service.delete(new BlogId(1)));
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();
		assertThrows(RuntimeException.class, () -> this.service.delete(new BlogId(2)));
	}

	/**
	 * 削除テスト(異常系)(引数エラー)
	 */
	@Test
	public void deleteTest_ArgumentsError() {
		initDelete();
		assertThrows(RuntimeException.class, () -> this.service.delete(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全て選択の準備
	 */
	private void initSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		map.put(PARAM_ID,        1);
		map.put(PARAM_TITLE,     TestConsts.TEST_TITLE_NAME);
		map.put(PARAM_TAG,       TestConsts.TEST_TAG_NAME);
		map.put(PARAM_COMMENT,   TestConsts.TEST_COMMENT_NAME);
		map.put(PARAM_THANKSCNT, 1);
		map.put(PARAM_CREATED, Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenReturn(mapList);
	}

	/**
	 * 全て選択テスト
	 */
	@Test
	public void selectAllTest() {
		initSelectAll();
	
		List<BlogMainModel> list = this.service.getAll();
	
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	list.get(0).getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		list.get(0).getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	list.get(0).getComment());
		assertEquals(1, list.get(0).getThanksCnt(), 1);
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				list.get(0).getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				list.get(0).getUpdated().toString());
		list.clear();
	}

	/**
	 * 全て選択の準備(空)
	 */
	private void initSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenReturn(mapList);
	}

	/**
	 * 全て選択テスト(空)
	 */
	@Test
	public void selectAllTest_Empty() {
		initSelectAll_Empty();

		List<BlogMainModel> list =  this.service.getAll();
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * IDによるデータ選択の準備
	 */
	private void initSelect_byId() {
		String sql = WebConsts.SQL_SELECT + " "
				+ DB_NAME + "." + PARAM_ID + ","
				+ DB_NAME + "." + PARAM_TITLE + ","
				+ DB_NAME + "." + PARAM_TAG + ","
				+ DB_NAME + "." + PARAM_COMMENT + ","
				+ DB_NAME + "." + PARAM_THANKSCNT + ","
				+ DB_NAME + "." + PARAM_CREATED + ","
				+ DB_NAME + "." + PARAM_UPDATED + " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME  + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PARAM_ID,        1);
		map.put(PARAM_TITLE,     TestConsts.TEST_TITLE_NAME);
		map.put(PARAM_TAG,       TestConsts.TEST_TAG_NAME);
		map.put(PARAM_COMMENT,   TestConsts.TEST_COMMENT_NAME);
		map.put(PARAM_THANKSCNT, 1);
		map.put(PARAM_CREATED, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, 
				Timestamp.valueOf(TestConsts.TEST_TIME_02));

		// 正常系
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		// 異常系
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
	}

	/**
	 * IDによる選択のテスト(正常系)
	 */
	@Test
	public void select_byIdTest() {
		initSelect_byId();

		BlogMainModel model = this.service.select(new BlogId(1));

		assertNotNull(model);
		assertEquals(1, model.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	model.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		model.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	model.getComment());
		assertEquals(1, model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model.getUpdated().toString());
	}

	/**
	 * IDによる選択のテスト(異常系)
	 */
	@Test
	public void select_byIdTest_Error() {
		initSelect_byId();
		assertThrows(RuntimeException.class, () ->  this.service.select(new BlogId(2)));
	}

	/**
	 * IDによる選択のテスト(異常系)(引数エラー)
	 */
	@Test
	public void select_byIdTest_ArgumentsError() {
		initSelect_byId();
		assertThrows(RuntimeException.class, () ->  this.service.select(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * タグによる選択の準備
	 */
	public void initSelect_byTag() {
		String sql = WebConsts.SQL_SELECT + " "
				+ DB_NAME + "." + PARAM_ID + ","
				+ DB_NAME + "." + PARAM_TITLE + ","
				+ DB_NAME + "." + PARAM_TAG + ","
				+ DB_NAME + "." + PARAM_COMMENT + ","
				+ DB_NAME + "." + PARAM_THANKSCNT + ","
				+ DB_NAME + "." + PARAM_CREATED + ","
				+ DB_NAME + "." + PARAM_UPDATED + " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME   + " "
				+ WebConsts.SQL_WHERE + " "	+ PARAM_TAG + " = ?";

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		map.put(PARAM_ID,        1);
		map.put(PARAM_TITLE,     TestConsts.TEST_TITLE_NAME);
		map.put(PARAM_TAG,       TestConsts.TEST_TAG_NAME);
		map.put(PARAM_COMMENT,   TestConsts.TEST_COMMENT_NAME);
		map.put(PARAM_THANKSCNT, 1);
		map.put(PARAM_CREATED, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map.put(PARAM_UPDATED, 
				Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(sql, "テスト"))
			.thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, "バグ"))
			.thenReturn(mapList2);
	}

	/**
	 * タグによる選択のテスト(正常系)
	 */
	@Test
	public void select_byTagTest() {
		initSelect_byTag();

		List<BlogMainModel> list = this.service.select_byTag("テスト");

		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	list.get(0).getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		list.get(0).getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	list.get(0).getComment());
		assertEquals(1, list.get(0).getThanksCnt(), 1);
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				list.get(0).getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				list.get(0).getUpdated().toString());
		list.clear();
	}
	
	/**
	 * タグによる選択のテスト(異常系)
	 */
	@Test
	public void select_byTagTest_Error() {
		initSelect_byTag();

		List<BlogMainModel> list = this.service.select_byTag("バグ");
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	/**
	 * タグによる選択のテスト(異常系)(引数エラー)
	 */
	@Test
	public void select_byTagTest_ArgumentsError() {
		initSelect_byTag();

		List<BlogMainModel> list = this.service.select_byTag(null);
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * インクリメントテストの準備
	 */
	public void initThanksIncrement() {
		// select文
		String sql = WebConsts.SQL_SELECT + " " 
				+ PARAM_THANKSCNT + " "
				+ WebConsts.SQL_FROM + " "  + DB_NAME  + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";
		// update文
		String sql_update = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_THANKSCNT + " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PARAM_THANKSCNT, 1);

		// 正常系
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.update(sql_update, 
				2, 
				1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);

		// 異常系
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
	}

	/**
	 * インクリメントテスト(正常系)
	 */
	@Test
	public void incrementTest() {
		initThanksIncrement();
		
		int ret = this.service.thanksIncrement(new BlogId(1));
		assertEquals(2, ret);
	}

	/**
	 * インクリメントテスト(異常系)
	 */
	@Test
	public void incrementTest_Error() {
		initThanksIncrement();
		assertThrows(RuntimeException.class, () -> this.service.thanksIncrement(new BlogId(2)));
	}

	/**
	 * インクリメントテスト(異常系)(引数エラー)
	 */
	@Test
	public void incrementTest_ArgumentsError() {
		initThanksIncrement();
		assertThrows(RuntimeException.class, () -> this.service.thanksIncrement(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.service = null;
		this.jdbcTemp = null;
	}
}
