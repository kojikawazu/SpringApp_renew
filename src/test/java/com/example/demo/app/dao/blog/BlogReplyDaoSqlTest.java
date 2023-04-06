package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;
/**
 * ブログ返信Daoクラステスト
 * @author nanai
 *
 */
class BlogReplyDaoSqlTest {

	/** DB名 */
	private final String DB_NAME = "blog_reply";

	/** パラム */
	private final String PARAM_REPLY_ID				= "id";
	private final String PARAM_REPLY_BLOG_ID		= "blog_id";
	private final String PARAM_REPLY_NAME			= "name";
	private final String PARAM_REPLY_COMMENT		= "comment";
	private final String PARAM_REPLY_THANTKSCNT		= "thankscnt";
	private final String PARAM_REPLY_CREATED		= "created";

	/** SQL文(全て選択) */
	private final String SQL_SELECT_ALL = WebConsts.SQL_SELECT + " "
			+ PARAM_REPLY_ID 			+ ", "
			+ PARAM_REPLY_BLOG_ID 		+ ", "
			+ PARAM_REPLY_NAME 			+ ", "
			+ PARAM_REPLY_COMMENT 		+ ", "
			+ PARAM_REPLY_THANTKSCNT 	+ ", "
			+ PARAM_REPLY_CREATED 		+ " "
			+ WebConsts.SQL_FROM + " " + DB_NAME;

	/** SQL文(ブログIDによる選択) */
	private final String SQL_SELECT_BY_BLOG_ID = WebConsts.SQL_SELECT + " "
			+ PARAM_REPLY_ID 			+ ", "
			+ PARAM_REPLY_BLOG_ID 		+ ", "
			+ PARAM_REPLY_NAME 			+ ", "
			+ PARAM_REPLY_COMMENT 		+ ", "
			+ PARAM_REPLY_THANTKSCNT 	+ ", "
			+ PARAM_REPLY_CREATED 		+ " "
			+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
			+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_BLOG_ID + " = ?";

	/** daoクラス */
	BlogReplyDaoSql dao = null;

	/** Mock    */
	@Mock
	JdbcTemplate jdbcTemp = null;
	@Mock
	IntroAppLogWriter logWriter = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * Mockの設定
	 */
	private void setMock() {
		this.jdbcTemp 	= mock(JdbcTemplate.class);
		this.logWriter 	= mock(IntroAppLogWriter.class);
	}

	/**
	 * Dao設定
	 */
	private void setDao() {
		this.dao = new BlogReplyDaoSql(this.jdbcTemp);

		Field fld;
		try {
			fld    = this.dao.getClass().getDeclaredField("logWriter");
			fld.setAccessible(true);
			fld.set(this.dao, this.logWriter);
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
	void init() {
		setMock();
		setDao();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テストの準備(異常系)
	 */
	private void initInsert_Error() {
		String sql =  WebConsts.SQL_INSERT + " " + DB_NAME
				+ "("
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED
				+ ") "
				+ "VALUES(?,?,?,?,?)";

		when(this.jdbcTemp.update(
				sql, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01
				)).thenThrow(RuntimeException.class);
	}

	/**
	 * 追加のテスト(異常系)
	 */
	@Test
	public void insertTest_Error() {
		initInsert_Error();

		BlogReplyModel model = new BlogReplyModel(
				new BlogReplyId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01
				);

		assertThrows(RuntimeException.class, () -> this.dao.insert(model));
	}

	/**
	 * 追加のテスト(異常系)(引数エラー)
	 */
	@Test
	public void insertTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.insert(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";

		// 異常系
		when(this.jdbcTemp.update(sql,
				2))
			.thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();

		int ret = this.dao.delete(new BlogReplyId(2));
		assertEquals(ret, WebConsts.ERROR_NUMBER);
	}

	/**
	 * 削除テスト(異常系)(引数エラー)
	 */
	@Test
	public void deleteTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.delete(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログIDによる削除テストの準備
	 */
	private void initDelete_byBlogId() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_BLOG_ID + " = ?";

		// 異常系
		when(this.jdbcTemp.update(
				sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * ブログIDによる削除テスト(異常系)
	 */
	@Test
	public void delete_byBlogIdTest_Error() {
		initDelete_byBlogId();

		int ret = this.dao.delete_byBlogId(new BlogId(2));
		assertEquals(ret, WebConsts.ERROR_NUMBER);
	}

	/**
	 * ブログIDによる削除テスト(異常系)(引数エラー)
	 */
	@Test
	public void delete_byBlogIdTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.delete_byBlogId(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全て選択の準備(空)
	 */
	private void initGetAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenReturn(mapList);
	}

	/**
	 * 全て選択テスト(空)
	 */
	@Test
	public void getAllTest_Empty() {
		initGetAll_Empty();
		List<BlogReplyModel> list = this.dao.getAll();
		assertEquals(0, list.size());
	}

	/**
	 * 全て選択の準備(null)
	 */
	private void initGetAll_Null() {
		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenReturn(null);
	}

	/**
	 * 全て選択テスト(null)
	 */
	@Test
	public void getAllTest_Null() {
		initGetAll_Null();
		List<BlogReplyModel> list = this.dao.getAll();
		assertEquals(0, list.size());
	}

	/**
	 * 全て選択の準備(throw)
	 */
	private void initGetAll_Throws() {
		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenThrow(RuntimeException.class);
	}

	/**
	 * 全て選択テスト(null)
	 */
	@Test
	public void getAllTest_Throws() {
		initGetAll_Throws();
		List<BlogReplyModel> list = this.dao.getAll();
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログIDによる選択テストの準備(異常系)(空)
	 */
	private void initSelect_byBlogId_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(SQL_SELECT_BY_BLOG_ID, 2)).thenReturn(mapList);
	}

	/**
	 * ブログIDによる選択のテスト(異常系)(空)
	 */
	@Test
	public void select_byBlogIdTest_Empty() {
		initSelect_byBlogId_Empty();
		List<BlogReplyModel> list = this.dao.select_byBlogId(new BlogId(2));
		assertEquals(0, list.size());
	}

	/**
	 * ブログIDによる選択テストの準備(異常系)(null)
	 */
	private void initSelect_byBlogId_Null() {
		when(this.jdbcTemp.queryForList(SQL_SELECT_BY_BLOG_ID, 2)).thenReturn(null);
	}

	/**
	 * ブログIDによる選択のテスト(異常系)(空)
	 */
	@Test
	public void select_byBlogIdTest_Null() {
		initSelect_byBlogId_Null();
		List<BlogReplyModel> list = this.dao.select_byBlogId(new BlogId(2));
		assertEquals(0, list.size());
	}

	/**
	 * ブログIDによる選択のテスト(異常系)(引数エラー)
	 */
	@Test
	public void select_byBlogIdTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.select_byBlogId(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 選択テストの準備(異常系)(null)
	 */
	private void initSelect_Error() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_REPLY_ID 			+ ", "
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED 		+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";
		when(this.jdbcTemp.queryForMap(
				sql, 2)).thenReturn(null);
	}

	/**
	 * 選択テスト(異常系)
	 */
	@Test
	public void selectTest_Error() {
		initSelect_Error();
		BlogReplyModel model = this.dao.select(new BlogReplyId(2));
		assertNull(model);
	}

	/**
	 * 選択テスト(異常系)(引数エラー)
	 */
	@Test
	public void selectTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.select(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * インクリメントテストの準備
	 */
	private void initThanksIncrement() {
		// select文
		String sql = WebConsts.SQL_SELECT 	+ " "
				+ PARAM_REPLY_THANTKSCNT 	+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";
		// update文
		String sql_update = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_REPLY_THANTKSCNT + " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PARAM_REPLY_THANTKSCNT, 1);

		// select(正常)、update(異常)
		when(this.jdbcTemp.queryForMap(
				sql, 1)).thenReturn(map);
		when(this.jdbcTemp.update(
				sql_update, 2, 1)).thenReturn(WebConsts.ERROR_DB_STATUS);
		// select(異常)
		when(this.jdbcTemp.queryForMap(
				sql, 2)).thenReturn(null);
	}

	/**
	 * インクリメントテスト(異常系)(更新異常)
	 */
	@Test
	public void thanksIncrementTest_UpdateError() {
		initThanksIncrement();
		int ret = this.dao.thanksIncrement(new BlogReplyId(1));
		assertEquals(WebConsts.ERROR_NUMBER, ret);
	}

	/**
	 * インクリメントテスト(異常系)(選択異常)
	 */
	@Test
	public void thanksIncrementTest_SelectError() {
		initThanksIncrement();
		int ret = this.dao.thanksIncrement(new BlogReplyId(2));
		assertEquals(WebConsts.ERROR_NUMBER, ret);
	}

	/**
	 * インクリメントテスト(異常系)(引数エラー)
	 */
	@Test
	public void thanksIncrementTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.thanksIncrement(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * makeModelテストの準備
	 */
	private Method initMakeModel() {
		Method method = null;
		try {
			method = this.dao.getClass().getDeclaredMethod("makeModel", Map.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}

	/**
	 * makeModelテスト(正常系)
	 */
	@Test
	public void makeModelTest() {
		Method method = initMakeModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_REPLY_ID, 		1);
			map.put(PARAM_REPLY_BLOG_ID, 	1);
			map.put(PARAM_REPLY_NAME, 		TestConsts.TEST_NAME_NAME);
			map.put(PARAM_REPLY_COMMENT, 	TestConsts.TEST_COMMENT_NAME);
			map.put(PARAM_REPLY_THANTKSCNT, 1);
			map.put(PARAM_REPLY_CREATED, 	Timestamp.valueOf(TestConsts.TEST_TIME_01));

			BlogReplyModel result = (BlogReplyModel)method.invoke(this.dao, map);
			assertNotNull(result);
			assertEquals(1,										result.getId());
			assertEquals(1,										result.getBlogId());
			assertEquals(TestConsts.TEST_NAME_NAME,				result.getName());
			assertEquals(TestConsts.TEST_COMMENT_NAME,			result.getComment());
			assertEquals(1,										result.getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(),	result.getCreated().toString());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeModelテスト(異常系)
	 */
	@Test
	public void makeModelTest_Error() {
		Method method = initMakeModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			BlogReplyModel result = (BlogReplyModel)method.invoke(this.dao, map);
			assertNull(result);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeModelテスト(異常系)(引数エラー)
	 */
	@Test
	public void makeModelTest_ArgumentsError() {
		Method method = initMakeModel();

		try {
			BlogReplyModel result = (BlogReplyModel)method.invoke(this.dao, (Map<String, Object>)null);
			assertNull(result);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp 	= null;
		this.dao 		= null;
		this.logWriter	= null;
	}
}
