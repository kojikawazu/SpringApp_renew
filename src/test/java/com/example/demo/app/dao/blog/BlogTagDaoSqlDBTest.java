package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグDaoクラステスト(DB)
 * @author nanai
 *
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogTagDaoSqlDBTest {

	/** テスト順番 */
	private static final int ENUM_INSERT 			= 1;
	private static final int ENUM_INSERT_RETURN_ID	= 2;
	private static final int ENUM_GET_ALL		 	= 3;
	private static final int ENUM_SELECT 			= 4;
	private static final int ENUM_IS_SELECT_BY_TAG	= 5;
	private static final int ENUM_UPDATE 			= 6;
	private static final int ENUM_DELETE 			= 7;

	/** id */
	private BlogTagId id = null;

	/** daoクラス */
	private BlogTagDaoSql dao = null;
	/** jdbcクラス */
	@Autowired
	private JdbcTemplate jdbcTemp = null;
	/** ログクラス  */
	@Mock
	private IntroAppLogWriter logWriter = null;

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
		this.dao = new BlogTagDaoSql(this.jdbcTemp);

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
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(1),
				new TagWord(TestConsts.TEST_TAG_NAME));
		assertDoesNotThrow(() -> this.dao.insert(model));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テスト(return id)(正常系)
	 */
	@Test
	@Order(ENUM_INSERT_RETURN_ID)
	public void insert_returnIdTest() {
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(1),
				new TagWord(TestConsts.TEST_TAG_NAME));
		int resultId;
		resultId = assertDoesNotThrow(() -> this.dao.insert_returnId(model));
		assertNotEquals(0, resultId);
		
		id = new BlogTagId(resultId);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 全選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_GET_ALL)
	public void getAllTest() {
		List<BlogTagModel> resultList = this.dao.getAll();
		assertNotNull(resultList);
		assertNotEquals(0, resultList);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * idによる選択テスト(正常系)
	 */
	@Test
	@Order(ENUM_SELECT)
	public void selectTest() {
		BlogTagModel result = this.dao.select(id);
		assertNotNull(result);
		assertNotEquals(0, result.getId());
		assertEquals(TestConsts.TEST_TAG_NAME, result.getTag());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * タグが存在するかチェックテスト(正常系)
	 */
	@Test
	@Order(ENUM_IS_SELECT_BY_TAG)
	public void isSelect_byTagTest() {
		boolean result = this.dao.isSelect_byTag(TestConsts.TEST_TAG_NAME);
		assertEquals(true, result);
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 更新テスト(正常系)
	 */
	@Test
	@Order(ENUM_UPDATE)
	public void updateTest() {
		String updateTagString = TestConsts.TEST_TAG_NAME + "update";
		BlogTagModel model = new BlogTagModel(
				id,
				new TagWord(updateTagString));
		int result = this.dao.update(model);
		BlogTagModel resultModel = this.dao.select(id);

		assertNotEquals(0, result);
		assertEquals(updateTagString, resultModel.getTag());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 削除テスト(正常系)
	 */
	@Test
	@Order(ENUM_DELETE)
	public void deleteTest() {
		for (int i=0; i<2; i++) {
			int result = assertDoesNotThrow(() -> this.dao.delete(id));
			assertEquals(1, result);
			id.setId(id.getId() - (i+1));
		}
	}
}
