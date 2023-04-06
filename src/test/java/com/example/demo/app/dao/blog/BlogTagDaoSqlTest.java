package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグDaoクラステスト
 * @author nanai
 *
 */
class BlogTagDaoSqlTest {

	/** DB名 */
	private final String DB_NAME = "blog_tag";

	/** パラム名 */
	private final String PARAM_ID			= "id";
	private final String PARAM_TAG			= "tag";

	/** テスト対象 */
	private BlogTagDaoSql dao = null;
	/** jdbcクラス */
	@Mock
	private JdbcTemplate jdbcTemp = null;
	/** ログクラス  */
	@Mock
	private IntroAppLogWriter logWriter = null;

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
	@BeforeEach
	public void init() {
		this.setMock();
		this.setDao();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 追加テストの準備
	 */
	private void initInsert() {
		String sql = WebConsts.SQL_INSERT + " " + DB_NAME
				+ "(" + PARAM_TAG +  ") "
				+ WebConsts.SQL_VALUES + "(?)";
		when(this.jdbcTemp.update(
				sql,
				TestConsts.TEST_TAG_NAME)).thenThrow(RuntimeException.class);
	}

	/**
	 * 追加テスト(異常系)
	 */
	@Test
	public void insertTest_Error() {
		initInsert();
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(), 
				new TagWord(TestConsts.TEST_TAG_NAME));
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
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_TAG + " = ? "
				+ WebConsts.SQL_WHERE + " "+ PARAM_ID + " = ?";

		// 異常系
		when(this.jdbcTemp.update(
			sql, 
			TestConsts.TEST_TAG_NAME,
			2
			)).thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void updateTest_Error() {
		initUpdate();
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(2),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);
		int ret = this.dao.update(model);
		assertEquals(WebConsts.ERROR_NUMBER, ret);
	}

	/**
	 * 更新テスト(異常系)(引数エラー)
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
		String sql =  WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		// 異常系
		when(this.jdbcTemp.update(
				sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();
		int ret = this.dao.delete(new BlogTagId(2));
		assertEquals(WebConsts.ERROR_NUMBER, ret);
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
	 * 全選択テストの準備(空)
	 */
	private void initGetAll_Empty() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_ID  + ", "
				+ PARAM_TAG + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME;
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
	}

	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void getAllTest_Empty() {
		initGetAll_Empty();
		List<BlogTagModel> list = this.dao.getAll();
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * IDによるデータ取得テストの準備
	 */
	private void initSelect_byId() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_ID  + ", "
				+ PARAM_TAG + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
	}

	/**
	 * IDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void selectTest_Error() {
		initSelect_byId();
		BlogTagModel model = this.dao.select(new BlogTagId(2));
		assertNull(model);
	}

	/**
	 * IDによるデータ取得のテスト(異常系)(引数エラー)
	 */
	@Test
	public void selectTest_ArgumentsError() {
		assertThrows(RuntimeException.class, () -> this.dao.select(null));
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * makeModelテスト
	 */
	@Test
	public void makeModelTest() {
		Method method = null;
		try {
			method = this.dao.getClass().getDeclaredMethod("makeModel", Map.class);
			method.setAccessible(true);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_ID, 1);
			map.put(PARAM_TAG, TestConsts.TEST_TAG_NAME);

			BlogTagModel result = (BlogTagModel)method.invoke(this.dao, map);
			assertNotNull(result);
			assertEquals(1, result.getId());
			assertEquals(TestConsts.TEST_TAG_NAME, result.getTag());
		} catch (NoSuchMethodException | 
				SecurityException | 
				IllegalAccessException| 
				IllegalArgumentException | 
				InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		dao = null;
		jdbcTemp = null;
		logWriter = null;
	}
}
