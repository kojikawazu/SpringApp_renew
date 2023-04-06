package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.exception.SQLNoInsertException;
import com.example.demo.common.log.IntroAppLogWriter;
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

	/** DB名 */
	private final String DB_NAME = "blog_main";

	/** DB名[blog_reply] */
	private final String DB_BLOG_REPLY_NAME = "blog_reply";

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

	private final String PARAM_REPLY_ID				= "id";
	private final String PARAM_REPLY_BLOG_ID		= "blog_id";
	private final String PARAM_REPLY_NAME			= "name";
	private final String PARAM_REPLY_COMMENT		= "comment";
	private final String PARAM_REPLY_THANTKSCNT		= "thanksCnt";
	private final String PARAM_REPLY_CREATED		= "created";

	private final String PARAM_AS_REPLY_ID			= "reply_id";
	private final String PARAM_AS_REPLY_NAME		= "reply_name";
	private final String PARAM_AS_REPLY_COMMENT		= "reply_comment";
	private final String PARAM_AS_REPLY_THANKSCNT	= "reply_thanksCnt";
	private final String PARAM_AS_REPLY_CREATED		= "reply_created";

	/** SQL文(追加) */
	private final String SQL_INSERT =  WebConsts.SQL_INSERT + " " + DB_NAME
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

	/** SQL文(全選択)(ブログ返信リストつき) */
	private final String SQL_SELECT_ALL_PLUS = WebConsts.SQL_SELECT + " "
			+ DB_NAME + "." + PARAM_ID + ","
			+ DB_NAME + "." + PARAM_TITLE + ","
			+ DB_NAME + "." + PARAM_TAG + ","
			+ DB_NAME + "." + PARAM_COMMENT + ","
			+ DB_NAME + "." + PARAM_THANKSCNT + ","
			+ DB_NAME + "." + PARAM_CREATED + ","
			+ DB_NAME + "." + PARAM_UPDATED + ","
			+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_ID         + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_ID + ","
			+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_NAME       + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_NAME + ","
			+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_COMMENT    + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_COMMENT + ","
			+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_THANTKSCNT + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_THANKSCNT + ","
			+ DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_CREATED    + " " + WebConsts.SQL_AS + " " + PARAM_AS_REPLY_CREATED + " "
			+ WebConsts.SQL_FROM + " " + DB_NAME + " "
			+ WebConsts.SQL_LEFT_OUTER_JOIN + " " + DB_BLOG_REPLY_NAME + " " + WebConsts.SQL_ON + " "
			+ DB_NAME + "." + PARAM_ID + " = " + DB_BLOG_REPLY_NAME + "." + PARAM_REPLY_BLOG_ID + " "
			+ WebConsts.SQL_ORDER_BY + " " + PARAM_ID + " ";

	private final String        TEST_REPLY_NAME     = "リプライネーム";
	private final String        TEST_REPLY_COMMENT  = "リプライコメント";

	/** daoクラス  */
	private BlogMainDaoSql dao = null;
	/** jdbcクラス */
	@Mock
	JdbcTemplate jdbcTemp = null;
	/** ログクラス  */
	@Mock
	IntroAppLogWriter logWriter = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * Mockの設定
	 */
	private void setMock() {
		this.jdbcTemp  = mock(JdbcTemplate.class);
		this.logWriter = mock(IntroAppLogWriter.class);
	}

	/**
	 * daoクラスの設定
	 */
	private void setDao() {
		this.dao = new BlogMainDaoSql(this.jdbcTemp);

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
	public void Init() {
		this.setMock();
		this.setDao();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テストの準備(異常系)
	 */
	private void initInsert_Error() {
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				TestConsts.TEST_TITLE_NAME,
				TestConsts.TEST_TAG_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1,
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				)).thenThrow(SQLNoInsertException.class);
	}

	/**
	 * 追加テスト(異常系)
	 */
	@Test
	public void insertTest_Error() {
		initInsert_Error();

		BlogMainModel model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		assertThrows(RuntimeException.class, () -> this.dao.insert(model));
	}

	/**
	 * 追加テスト(異常系)(引数エラー)
	 */
	@Test
	public void insertTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.insert(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 更新テストの準備
	 */
	private void initUpdate() {
		// SQL文(更新)
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_TITLE 		+ " = ?, "
				+ PARAM_TAG 		+ " = ?, "
				+ PARAM_COMMENT 	+ " = ?, "
				+ PARAM_THANKSCNT 	+ " = ?, "
				+ PARAM_UPDATED 	+ " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

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
	 * 更新テスト（異常系)
	 */
	@Test
	public void updateTest_Error() {
		initUpdate();

		BlogMainModel 	model = new BlogMainModel(
				new BlogId(2),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		int ret = this.dao.update(model);
		assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 更新テスト（異常系)(引数エラー)
	 */
	@Test
	public void updateTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.update(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 削除テストの準備
	 */
	private void initDelete() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();

		int ret = this.dao.delete(new BlogId(2));
		assertEquals(ret, WebConsts.ERROR_DB_STATUS);
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
	 * 全て選択テストの準備(空リスト)
	 */
	private void initSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		when(this.jdbcTemp.queryForList(SQL_SELECT_ALL))
			.thenReturn(mapList);
	}

	/**
	 * 全て選択テスト(空リスト)
	 */
	@Test
	public void selectAllTest_Empty() {
		initSelectAll_Empty();

		List<BlogMainModel> list = this.dao.getAll();

		assertNotNull(list);
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全て選択テストの準備(Plus)
	 */
	private void initSelectAll_PLus(boolean isDesc) {
		String sql = SQL_SELECT_ALL_PLUS;
		sql += (isDesc ? WebConsts.SQL_DESC : WebConsts.SQL_ASC);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		int cnter=1;
		for (int idx=1; idx<=2; idx++) {
			Map<String, Object> map01 = new HashMap<String, Object>();
			map01.put(PARAM_ID,               cnter);
			map01.put(PARAM_TITLE,            TestConsts.TEST_TITLE_NAME   + cnter);
			map01.put(PARAM_TAG,              TestConsts.TEST_TAG_NAME     + cnter);
			map01.put(PARAM_COMMENT,          TestConsts.TEST_COMMENT_NAME + cnter);
			map01.put(PARAM_THANKSCNT,        cnter);
			map01.put(PARAM_CREATED,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map01.put(PARAM_UPDATED,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
			map01.put(PARAM_AS_REPLY_ID,         idx);
			map01.put(PARAM_AS_REPLY_NAME,       TEST_REPLY_NAME    + idx);
			map01.put(PARAM_AS_REPLY_COMMENT,    TEST_REPLY_COMMENT + idx);
			map01.put(PARAM_AS_REPLY_THANKSCNT,  1);
			map01.put(PARAM_AS_REPLY_CREATED,    Timestamp.valueOf(TestConsts.TEST_TIME_01));
			mapList.add(map01);
		}

		cnter++;
		Map<String, Object> map02 = new HashMap<String, Object>();
		map02.put(PARAM_ID,               2);
		map02.put(PARAM_TITLE,            TestConsts.TEST_TITLE_NAME + cnter);
		map02.put(PARAM_TAG,              TestConsts.TEST_TAG_NAME   + cnter);
		map02.put(PARAM_COMMENT,          TestConsts.TEST_COMMENT_NAME + cnter);
		map02.put(PARAM_THANKSCNT,        1);
		map02.put(PARAM_CREATED,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map02.put(PARAM_UPDATED,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
		mapList.add(map02);

		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
	}

	/**
	 * 全て選択のテスト(Plus)
	 */
	@Test
	public void selectAllPlus_Test() {
		boolean isDesc = false;
		initSelectAll_PLus(isDesc);

		List<BlogMainModel> list = this.dao.getAll_Plus(isDesc);

		assertEquals(2, list.size());
		BlogMainModel model1                 = list.get(0);
		List<BlogReplyModel> replyModel1     = model1.getReplyList();
		BlogMainModel model2                 = list.get(1);
		List<BlogReplyModel> replyModel2     = model2.getReplyList();

		// model1
		int modelCnt = 1;
		assertEquals(modelCnt, model1.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME   + modelCnt, model1.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME     + modelCnt, model1.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME + modelCnt, model1.getComment());
		assertEquals(1, model1.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), 
				model1.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model1.getUpdated().toString());

		// replyModel
		assertEquals(2, replyModel1.size());
		int modelReplyCnt = 1;
		for (int idx=0; idx<2; idx++) {
			assertEquals(idx+1, 								replyModel1.get(idx).getId());
			assertEquals(modelCnt, 								replyModel1.get(idx).getBlogId());
			assertEquals(TEST_REPLY_NAME    + modelReplyCnt, 	replyModel1.get(idx).getName());
			assertEquals(TEST_REPLY_COMMENT + modelReplyCnt, 	replyModel1.get(idx).getComment());
			assertEquals(1, 									replyModel1.get(idx).getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					replyModel1.get(idx).getCreated().toString());
			modelReplyCnt++;
		}

		// model2
		modelCnt++;
		assertEquals(modelCnt, model2.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME   + modelCnt, model2.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME     + modelCnt, model2.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME + modelCnt, model2.getComment());
		assertEquals(1, model2.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),
				model2.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),
				model2.getUpdated().toString());
		assertEquals(0, replyModel2.size());

		list.clear();
		replyModel1.clear();
		replyModel2.clear();
	}

	/**
	 * 全て選択テストの準備(Plus)(空)
	 */
	private void initSelectAll_PLus_Empty(boolean isDesc) {
		String sql = SQL_SELECT_ALL_PLUS;
		sql += (isDesc ? WebConsts.SQL_DESC : WebConsts.SQL_ASC);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
	}

	/**
	 * 全て選択のテスト(Plus)(空)
	 */
	@Test
	public void selectAllPlus_Test_Empty() {
		boolean isDesc = false;
		initSelectAll_PLus_Empty(isDesc);

		List<BlogMainModel> list = this.dao.getAll_Plus(isDesc);

		assertNotNull(list);
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.dao = null;
		this.jdbcTemp = null;
	}
}
