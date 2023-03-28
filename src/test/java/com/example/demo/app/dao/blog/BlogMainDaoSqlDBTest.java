package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログメインDaoクラステスト(DB)
 * @author nanai
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogMainDaoSqlDBTest {

	private static final int ENUM_INSERT 			= 1;
	private static final int ENUM_SELECT_ALL		= 2;
	private static final int ENUM_SELECT_BY_TAG 	= 3;
	private static final int ENUM_SELECT_BY_ID 		= 4;
	private static final int ENUM_UPDATE			= 5;
	private static final int ENUM_THANKS_INCREMENT 	= 6;
	private static final int ENUM_DELETE 			= 7;

	/** 選択ID保存用 */
	private BlogId id = null;
	
	/** daoクラス  */
	@Autowired
	BlogMainDaoSql dao;
	/** jdbcクラス */
	@Autowired
	JdbcTemplate jdbcTemp;
	/** ログクラス  */
	@Mock
	IntroAppLogWriter logWriter = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * Mockの設定
	 */
	private void setMock() {
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
	@BeforeAll
	public void Init() {
		this.setMock();
		this.setDao();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テスト(正常系)
	 */
	@Test
	@Order(ENUM_INSERT)
	public void insertTest() {
		BlogMainModel model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
		assertDoesNotThrow(() -> this.dao.insert(model));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT_ALL)
	public void getAllTest() {
		List<BlogMainModel> result = this.dao.getAll();

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * タグによる選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT_BY_TAG)
	public void select_byTagTest() {
		List<BlogMainModel> result = this.dao.select_byTag(TestConsts.TEST_TAG_NAME);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(TestConsts.TEST_TITLE_NAME,           result.get(0).getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME,             result.get(0).getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME,         result.get(0).getComment());
		assertEquals(1,                                    result.get(0).getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),   result.get(0).getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),   result.get(0).getUpdated().toString());
		id = new BlogId(result.get(0).getId());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * IDによる選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT_BY_ID)
	public void selectTest() {
		BlogMainModel result = this.dao.select(id);

		assertNotNull(result);
		assertEquals(TestConsts.TEST_TITLE_NAME,           result.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME,             result.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME,         result.getComment());
		assertEquals(1,                                    result.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),   result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),   result.getUpdated().toString());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 更新テスト(正常系)
	 */
	@Test
	@Order(ENUM_UPDATE)
	public void updateTest() {
		BlogMainModel model = new BlogMainModel(
				id,
				new TittleWord(TestConsts.TEST_TITLE_NAME + "update"),
				new TagWord(TestConsts.TEST_TAG_NAME + "update"),
				new CommentWord(TestConsts.TEST_COMMENT_NAME + "update"),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02);
		int ret = assertDoesNotThrow(() -> this.dao.update(model));
		BlogMainModel result = this.dao.select(id);

		assertEquals(1, ret);
		assertEquals(TestConsts.TEST_TITLE_NAME + "update",		result.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME + "update",		result.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME + "update",	result.getComment());
		assertEquals(1,											result.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),		result.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(),		result.getUpdated().toString());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * いいね数加算テスト(正常系)
	 */
	@Test
	@Order(ENUM_THANKS_INCREMENT)
	public void thanksIncrementTest() {
		int thanksCnt = assertDoesNotThrow(() -> this.dao.thanksIncrement(id));
		assertEquals(2, thanksCnt);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 削除テスト(正常系)
	 */
	@Test
	@Order(ENUM_DELETE)
	public void deleteTest() {
		int result = assertDoesNotThrow(() -> this.dao.delete(id));
		assertEquals(1, result);
	}
}
