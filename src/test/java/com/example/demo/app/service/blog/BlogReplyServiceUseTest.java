package com.example.demo.app.service.blog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.dao.blog.BlogReplyDaoSql;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信サービステスト
 * @author nanai
 *
 */
class BlogReplyServiceUseTest {

	/** DB名 */
	private final String DB_NAME = "blog_reply";

	/** パラム */
	private final String PARAM_REPLY_ID				= "id";
	private final String PARAM_REPLY_BLOG_ID		= "blog_id";
	private final String PARAM_REPLY_NAME			= "name";
	private final String PARAM_REPLY_COMMENT		= "comment";
	private final String PARAM_REPLY_THANTKSCNT		= "thankscnt";
	private final String PARAM_REPLY_CREATED		= "created";

	/** SQL文(追加) */
	private final String SQL_INSERT =  WebConsts.SQL_INSERT + " " + DB_NAME
			+ "("
			+ PARAM_REPLY_BLOG_ID 		+ ", "
			+ PARAM_REPLY_NAME 			+ ", "
			+ PARAM_REPLY_COMMENT 		+ ", "
			+ PARAM_REPLY_THANTKSCNT 	+ ", "
			+ PARAM_REPLY_CREATED
			+ ") "
			+ "VALUES(?,?,?,?,?)";

	/** SQL文(全て選択) */
	private final String SQL_SELECT_ALL = WebConsts.SQL_SELECT + " "
			+ PARAM_REPLY_ID 			+ ", "
			+ PARAM_REPLY_BLOG_ID 		+ ", "
			+ PARAM_REPLY_NAME 			+ ", "
			+ PARAM_REPLY_COMMENT 		+ ", "
			+ PARAM_REPLY_THANTKSCNT 	+ ", "
			+ PARAM_REPLY_CREATED 		+ " "
			+ WebConsts.SQL_FROM + " " + DB_NAME;

	/** テスト対象 */
	BlogReplyDaoSql        	dao     = null;
	BlogReplyServiceUse 	service = null;

	/** モック */
	@Mock
	JdbcTemplate jdbcTemp = null;
	@Mock
	IntroAppLogWriter logWriter = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * モックの設定
	 */
	private void setDao() {
		this.jdbcTemp 		= mock(JdbcTemplate.class);
		this.logWriter		= mock(IntroAppLogWriter.class);
	}

	/**
	 * サービスの設定
	 */
	private void setService() {
		this.dao 			= new BlogReplyDaoSql(this.jdbcTemp);
		this.service		= new BlogReplyServiceUse(dao);

		Field fld, fldDao;
		try {
			fld    = this.service.getClass().getDeclaredField("logWriter");
			fldDao = dao.getClass().getDeclaredField("logWriter");
			fld.setAccessible(true);
			fldDao.setAccessible(true);
			fld.set(this.service, this.logWriter);
			fldDao.set(dao, this.logWriter);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 初期化
	 */
	@BeforeEach
	public void init() {
		this.setDao();
		this.setService();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テストの準備
	 */
	private void initSave() {
		// 正常系
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		// 異常系
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME + "NG",
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01
				)).thenThrow(RuntimeException.class);
	}

	/**
	 * 追加テスト(正常系)
	 */
	@Test
	public void saveTest() {
		initSave();

		BlogReplyModel model = new BlogReplyModel(
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01 
				);

		assertDoesNotThrow(() -> this.service.save(model));
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				1,
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01);
	}

	/**
	 * 追加テスト(異常系)
	 */
	@Test
	public void saveTest_Error() {
		initSave();

		BlogReplyModel model = new BlogReplyModel(
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME + "NG"),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01);

		assertDoesNotThrow(() -> this.service.save(model));
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 追加テスト(異常系)(引数エラー)
	 */
	@Test
	public void saveTest_ArgumentsError() {
		this.service.save(null);
		verify(this.logWriter, times(1)).error(anyString());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
					+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";

		when(this.jdbcTemp.update(sql, 1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2)).thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void deleteTest() {
		initDelete();
		assertDoesNotThrow(() -> this.service.delete(new BlogReplyId(1)));
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();
		assertThrows(RuntimeException.class, () -> this.service.delete(new BlogReplyId(2)));
	}

	/**
	 * 削除テスト(異常系)(引数エラー)
	 */
	@Test
	public void deleteTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.service.delete(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログIDによる削除テストの準備
	 */
	public void initDelete_byBlogId() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_BLOG_ID + " = ?";

		when(this.jdbcTemp.update(sql, 1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2)).thenReturn(WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * ブログIDによる削除テスト(正常系)
	 */
	@Test
	public void delete_byBlogIdTest() {
		initDelete_byBlogId();
		assertDoesNotThrow(() -> this.service.delete_byBlogid(new BlogId(1)));
	}

	/**
	 * ブログIDによる削除テスト(異常系)
	 */
	@Test
	public void delete_byBlogIdTest_Error() {
		initDelete_byBlogId();
		assertThrows(RuntimeException.class, () -> this.service.delete_byBlogid(new BlogId(2)));
	}

	/**
	 * ブログIDによる削除テスト(異常系)(引数エラー)
	 */
	@Test
	public void delete_byBlogIdTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.service.delete_byBlogid(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全て選択テストの準備
	 */
	public void initGetAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		map.put(PARAM_REPLY_ID,			1);
		map.put(PARAM_REPLY_BLOG_ID,	1);
		map.put(PARAM_REPLY_NAME,		TestConsts.TEST_NAME_NAME);
		map.put(PARAM_REPLY_COMMENT,	TestConsts.TEST_COMMENT_NAME);
		map.put(PARAM_REPLY_THANTKSCNT,	1);
		map.put(PARAM_REPLY_CREATED,	Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenReturn(mapList);
	}

	/**
	 * 全選択テスト
	 */
	@Test
	public void getAllTest() {
		initGetAll();

		List<BlogReplyModel> list = this.service.getAll();

		assertEquals(1,									list.size());
		assertEquals(1,									list.get(0).getId());
		assertEquals(1, 								list.get(0).getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,			list.get(0).getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME,		list.get(0).getComment());
		assertEquals(1,									list.get(0).getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				list.get(0).getCreated().toString());
		list.clear();
	}

	/**
	 * 全て選択テストの準備(空)
	 */
	public void initGetAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL)).thenReturn(mapList);
	}

	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void getAllTest_Empty() {
		initGetAll_Empty();
		List<BlogReplyModel> list = this.service.getAll();
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 選択の準備
	 */
	public void initSelect() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_REPLY_ID 			+ ", "
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED 		+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_ID + " = ?";
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(PARAM_REPLY_ID,         1);
		map.put(PARAM_REPLY_BLOG_ID,    1);
		map.put(PARAM_REPLY_NAME,       TestConsts.TEST_NAME_NAME);
		map.put(PARAM_REPLY_COMMENT,    TestConsts.TEST_COMMENT_NAME);
		map.put(PARAM_REPLY_THANTKSCNT, 1);
		map.put(PARAM_REPLY_CREATED, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));

		when(this.jdbcTemp.queryForMap(sql, 1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
	}

	/**
	 * 選択テスト(正常系)
	 */
	@Test
	public void selectTest() {
		initSelect();

		BlogReplyModel model = assertDoesNotThrow(() -> this.service.select(new BlogReplyId(1)));

		assertNotNull(model);
		assertEquals(1,								model.getId());
		assertEquals(1, 							model.getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,		model.getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME,	model.getComment());
		assertEquals(1,								model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model.getCreated().toString());
	}

	/**
	 * 選択テスト(異常系)
	 */
	@Test
	public void selectTest_Error() {
		initSelect();
		assertThrows(RuntimeException.class, () -> this.service.select(new BlogReplyId(2)));
	}

	/**
	 * 選択テスト(異常系)(引数エラー)
	 */
	@Test
	public void selectTest_ArgumentsError() {
		initSelect();
		assertThrows(RuntimeException.class, () -> this.service.select(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログIDによる選択テストの準備
	 */
	public void initSelect_byBlogId() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_REPLY_ID 			+ ", "
				+ PARAM_REPLY_BLOG_ID 		+ ", "
				+ PARAM_REPLY_NAME 			+ ", "
				+ PARAM_REPLY_COMMENT 		+ ", "
				+ PARAM_REPLY_THANTKSCNT 	+ ", "
				+ PARAM_REPLY_CREATED 		+ " "
				+ WebConsts.SQL_FROM  + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_REPLY_BLOG_ID + " = ?";
		Map<String, Object> map            = new HashMap<String, Object>();
		List<Map<String, Object>> mapList  = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();

		map.put(PARAM_REPLY_ID,         1);
		map.put(PARAM_REPLY_BLOG_ID,    1);
		map.put(PARAM_REPLY_NAME,       TestConsts.TEST_NAME_NAME);
		map.put(PARAM_REPLY_COMMENT,    TestConsts.TEST_COMMENT_NAME);
		map.put(PARAM_REPLY_THANTKSCNT, 1);
		map.put(PARAM_REPLY_CREATED, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);

		when(this.jdbcTemp.queryForList(sql, 1)).thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, 2)).thenReturn(mapList2);
	}

	/**
	 * ブログIDによる選択テスト(正常系)
	 */
	@Test
	public void select_byBlogIdTest() {
		initSelect_byBlogId();

		List<BlogReplyModel> list = this.service.select_byBlogId(new BlogId(1));

		assertEquals(1, 							list.size());
		assertEquals(1, 							list.get(0).getId());
		assertEquals(1, 							list.get(0).getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME, 	list.get(0).getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	list.get(0).getComment());
		assertEquals(1, 							list.get(0).getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
						list.get(0).getCreated().toString());
		list.clear();
	}

	/**
	 *  ブログIDによる選択テスト(異常系)
	 */
	@Test
	public void select_byBlogIdTest_Error() {
		initSelect_byBlogId();
		List<BlogReplyModel> list = assertDoesNotThrow(() -> this.service.select_byBlogId(new BlogId(2)));
		assertEquals(0, list.size());
	}

	/**
	 *  ブログIDによる選択テスト(異常系)(引数エラー)
	 */
	@Test
	public void select_byBlogIdTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.service.select_byBlogId(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * いいね数加算の準備
	 */
	public void initThanksIncrement() {
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

		// 正常系
		when(this.jdbcTemp.queryForMap(sql, 1)).thenReturn(map);
		when(this.jdbcTemp.update(sql_update, 2, 1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		// 異常系
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
	}

	/**
	 * いいね数加算(正常系)
	 */
	@Test
	public void thanksIncrementTest() {
		initThanksIncrement();
		int ret = assertDoesNotThrow(() -> this.service.thanksIncrement(new BlogReplyId(1)));
		assertEquals(ret, 2);
	}

	/**
	 * いいね数加算(異常系)
	 */
	@Test
	public void thanksIncrementTest_Error() {
		initThanksIncrement();
		assertThrows(RuntimeException.class, () -> this.service.thanksIncrement(new BlogReplyId(2)));
	}

	/**
	 * いいね数加算(異常系)(引数エラー)
	 */
	@Test
	public void thanksIncrementTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.service.thanksIncrement(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp 	= null;
		this.service  	= null;
		this.dao		= null;
		this.logWriter	= null;
	}
}
