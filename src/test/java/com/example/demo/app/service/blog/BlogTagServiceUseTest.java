package com.example.demo.app.service.blog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
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
import com.example.demo.app.dao.blog.BlogTagDaoSql;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグサービスのテスト
 * @author nanai
 *
 */
class BlogTagServiceUseTest {

	/** DB名 */
	private final String DB_NAME = "blog_tag";

	/** パラム名 */
	private final String PARAM_ID			= "id";
	private final String PARAM_TAG			= "tag";

	/** SQL文(追加) */
	private final String SQL_INSERT =  WebConsts.SQL_INSERT + " " + DB_NAME
			+ "(" + PARAM_TAG +  ") "
			+ WebConsts.SQL_VALUES + "(?)";
	/** SQL文(全選択) */
	private final String SQL_GET_ALL = WebConsts.SQL_SELECT + " "
			+ PARAM_ID  + ", "
			+ PARAM_TAG + " "
			+ WebConsts.SQL_FROM + " " + DB_NAME;
	
	/** テスト対象 */
	BlogTagServiceUse service = null;
	@Mock
	JdbcTemplate jdbcTemp = null;
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
	 * サービスの設定
	 */
	private void setService() {
		BlogTagDaoSql dao = new BlogTagDaoSql(this.jdbcTemp);
		this.service      = new BlogTagServiceUse(dao);

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
	public void initSave() {
		when(this.jdbcTemp.update(
				SQL_INSERT,
				TestConsts.TEST_TAG_NAME)).thenReturn(TestConsts.RESULT_NUMBER_OK);
	}

	/**
	 * 追加テスト(正常系)
	 */
	@Test
	public void saveTest() {
		initSave();

		BlogTagModel model = new BlogTagModel(
				new BlogTagId(0),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);

		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				TestConsts.TEST_TAG_NAME);
	}

	/**
	 * 追加テストの準備(異常系)
	 */
	public void initSave_Error() {
		when(this.jdbcTemp.update(
				SQL_INSERT,
				TestConsts.TEST_TAG_NAME)).thenThrow(RuntimeException.class);
	}

	/**
	 * 追加テスト(異常系)
	 */
	@Test
	public void saveTest_Error() {
		initSave_Error();

		BlogTagModel model = new BlogTagModel(
				new BlogTagId(0),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);

		this.service.save(model);
		verify(this.logWriter, times(1)).error(null);
	}

	/**
	 * 追加テスト(異常系)(引数エラー)
	 */
	@Test
	public void saveTest_argumentsError() {
		this.service.save(null);
		verify(this.logWriter, times(1)).error(anyString());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 更新テストの準備
	 */
	public void initUpdate() {
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_TAG + " = ? "
				+ WebConsts.SQL_WHERE + " "+ PARAM_ID + " = ?";

		// 正常系
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_TAG_NAME,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		// 異常系
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_TAG_NAME,
				2
				)).thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void updateTest() {
		initUpdate();

		assertDoesNotThrow(
			() -> this.service.update(
				new BlogTagModel(
					new BlogTagId(1),
					new TagWord(TestConsts.TEST_TAG_NAME)
				)
			));
	}

	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void updateTest_Error() {
		initUpdate();

		assertThrows(
			RuntimeException.class, 
			() -> this.service.update(
				new BlogTagModel(
					new BlogTagId(2),
					new TagWord(TestConsts.TEST_TAG_NAME)
				)
			));
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
	public void initDelete() {
		String sql =  WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		// 正常系
		when(this.jdbcTemp.update(sql, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		// 異常系
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
	}

	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void deleteTest() {
		initDelete();
		assertDoesNotThrow(() -> this.service.delete(new BlogTagId(1)));
	}

	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void deleteTest_Error() {
		initDelete();
		assertThrows(RuntimeException.class, () -> this.service.delete(new BlogTagId(2)));
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
	 * 全選択テストの準備
	 */
	public void initGetAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		map.put(WebConsts.SQL_ID_NAME,  1);
		map.put(WebConsts.SQL_TAG_NAME, TestConsts.TEST_TAG_NAME);
		mapList.add(map);

		when(this.jdbcTemp.queryForList(SQL_GET_ALL)).thenReturn(mapList);
	}

	/**
	 * 全選択テスト
	 */
	@Test
	public void getAllTest() {
		initGetAll();
		List<BlogTagModel> list = this.service.getAll();

		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getId());
		assertEquals(TestConsts.TEST_TAG_NAME, list.get(0).getTag());
		list.clear();
	}

	/**
	 * 全選択テストの準備(空)
	 */
	public void initGetAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		when(this.jdbcTemp.queryForList(SQL_GET_ALL)).thenReturn(mapList);
	}

	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void getAllTest_Empty() {
		initGetAll_Empty();
		List<BlogTagModel> list = this.service.getAll();

		assertNotNull(list);
		assertEquals(0, list.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * IDによるデータ取得テストの準備
	 */
	public void initSelect() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_ID  + ", "
				+ PARAM_TAG + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConsts.SQL_ID_NAME,  1);
		map.put(WebConsts.SQL_TAG_NAME, TestConsts.TEST_TAG_NAME);

		when(this.jdbcTemp.queryForMap(sql, 1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
	}

	/**
	 * 選択テスト(正常系)
	 */
	@Test
	public void selectTest() {
		initSelect();
		BlogTagModel model = assertDoesNotThrow(() -> this.service.select(new BlogTagId(1)));

		assertNotNull(model);
		assertEquals(1, model.getId());
		assertEquals(TestConsts.TEST_TAG_NAME, model.getTag());
	}

	/**
	 * 選択テスト(異常系)
	 */
	@Test
	public void selectTest_Error() {
		initSelect();
		assertThrows(RuntimeException.class, () -> this.service.select(new BlogTagId(2)));
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
	 * 後処理
	 */
	@AfterEach
	public void release() {
		this.jdbcTemp  = null;
		this.service   = null;
		this.logWriter = null;
	}
}
