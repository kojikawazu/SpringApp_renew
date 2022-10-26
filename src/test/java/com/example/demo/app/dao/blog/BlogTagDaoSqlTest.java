package com.example.demo.app.dao.blog;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.blog.BlogTagId;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグDaoクラステスト
 * @author nanai
 *
 */
class BlogTagDaoSqlTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO blog_tag(tag) "
											+ "VALUES(?)";
	
	private BlogTagDao dao = null;
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	private void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT,
				TestConsts.TEST_TAG_NAME)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setDao();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(0),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);
		
		this.dao.insertTag(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT,
				TestConsts.TEST_TAG_NAME);
	}
	
	/**
	 * 更新テストの準備
	 */
	private void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE blog_tag SET "
				+ "tag = ? WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_TAG_NAME,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_TAG_NAME,
				2
				)).thenReturn(WebConsts.ERROR_NUMBER);
		
		this.setDao();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(1),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);
		
		int ret = this.dao.updateTag(model);
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTest_Error() {
		InitUpdate();
		
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(2),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);
		
		int ret = this.dao.updateTag(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}
	
	/**
	 * 削除テストの準備
	 */
	private void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_tag "
				+ "WHERE id = ?";
		
		when(jdbcTemp.update(
				sql,
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(jdbcTemp.update(
				sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteTag(new BlogTagId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		int ret = this.dao.deleteTag(new BlogTagId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_NUMBER);
	}

	/**
	 * 全選択テストの準備
	 */
	private void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_tag";
		
		map.put(WebConsts.SQL_ID_NAME,  1);
		map.put(WebConsts.SQL_TAG_NAME, TestConsts.TEST_TAG_NAME);
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		this.setDao();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogTagModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(),         1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTag(), TestConsts.TEST_TAG_NAME);
		list.clear();
	}
	
	/**
	 * 全選択テストの準備(空)
	 */
	private void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_tag";
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		this.setDao();
	}
	
	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		List<BlogTagModel> list = this.dao.getAll();
		
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * IDによるデータ取得テストの準備
	 */
	private void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM blog_tag "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,  1);
		map.put(WebConsts.SQL_TAG_NAME, TestConsts.TEST_TAG_NAME);
		
		when(this.jdbcTemp.queryForMap(sql, 
				1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 
				2)).thenReturn(null);
		
		this.setDao();
	}
	
	/**
	 * IDによるデータ取得のテスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogTagModel model = this.dao.select(new BlogTagId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTag(), TestConsts.TEST_TAG_NAME);
	}
	
	/**
	 * IDによるデータ取得のテスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		BlogTagModel model = this.dao.select(new BlogTagId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * Daoクラスの設定
	 */
	private void setDao() {
		this.dao = new BlogTagDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		dao = null;
		jdbcTemp = null;
	}

}