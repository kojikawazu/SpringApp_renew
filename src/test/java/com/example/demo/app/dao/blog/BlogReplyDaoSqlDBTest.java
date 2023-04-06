package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信Daoクラステスト(DB)
 * @author nanai
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogReplyDaoSqlDBTest {

	private static final int ENUM_INSERT 			= 1;
	private static final int ENUM_SELECT_ALL		= 2;
	private static final int ENUM_SELECT_BY_BLOG_ID = 3;
	private static final int ENUM_SELECT_BY_ID 		= 4;
	private static final int ENUM_THANKS_INCREMENT 	= 5;
	private static final int ENUM_DELETE 			= 6;
	private static final int ENUM_UPDATE_BY_BLOG_ID	= 7;

	/** 選択ID保存用 */
	private BlogReplyId id = null;
	private BlogId blogId  = null;

	/** daoクラス  */
	@Autowired
	BlogReplyDaoSql dao;
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
		blogId = new BlogId(99);
		BlogReplyModel model = new BlogReplyModel(
				blogId,
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01);
		assertDoesNotThrow(() -> this.dao.insert(model));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT_ALL)
	public void getAllTest() {
		List<BlogReplyModel> result = this.dao.getAll();

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログIDによる選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT_BY_BLOG_ID)
	public void select_blogIdTest() {
		List<BlogReplyModel> result = this.dao.select_byBlogId(blogId);

		assertNotNull(result);
		assertEquals(1,										result.size());
		assertEquals(99,									result.get(0).getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,				result.get(0).getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME,			result.get(0).getComment());
		assertEquals(1,										result.get(0).getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),	result.get(0).getCreated().toString());
		id = new BlogReplyId(result.get(0).getId());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログ返信IDによる選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT_BY_ID)
	public void selectTest() {
		BlogReplyModel result = this.dao.select(id);

		assertNotNull(result);
		assertEquals(99,									result.getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,				result.getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME,			result.getComment());
		assertEquals(1,										result.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(),	result.getCreated().toString());
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

	// --------------------------------------------------------------------------------------------------

	/**
	 * ブログIDによる削除テスト(正常系)
	 */
	@Test
	@Order(ENUM_UPDATE_BY_BLOG_ID)
	public void deleteReply_byBlogTest() {
		blogId = new BlogId(99);
		BlogReplyModel model = new BlogReplyModel(
				blogId,
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01);
		this.dao.insert(model);

		int result = assertDoesNotThrow(() -> this.dao.delete_byBlogId(blogId));
		assertEquals(1, result);
	}

	// --------------------------------------------------------------------------------------------------
}
