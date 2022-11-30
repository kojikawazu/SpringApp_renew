package com.example.demo.app.service.blog;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.dao.blog.BlogTagDao;
import com.example.demo.app.dao.blog.BlogTagDaoSql;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグサービスのテスト
 * @author nanai
 *
 */
class BlogTagServiceUseTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO blog_tag(tag) "
											+ "VALUES(?)";
	
	/** テスト対象 */
	BlogTagService service = null;
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT,
				TestConsts.TEST_TAG_NAME)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		setService();
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
		
		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				TestConsts.TEST_TAG_NAME);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
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
		
		setService();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
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
	public void UpdateTest_Error() {
		InitUpdate();
		
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
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM blog_tag "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_NUMBER);
		
		setService();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();

		assertDoesNotThrow(
			() -> this.service.delete(new BlogTagId(1)));
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		assertThrows(RuntimeException.class, 
			() -> this.service.delete(new BlogTagId(2)));
	}
	
	/**
	 * 全選択テストの準備
	 */
	public void InitSelectAll() {
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
		
		setService();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<BlogTagModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(),         1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTag(), TestConsts.TEST_TAG_NAME);
		list.clear();
	}
	
	/**
	 * 全選択テストの準備(空)
	 */
	public void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM blog_tag";
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		assertThrows(RuntimeException.class, 
			() -> this.service.getAll());
	}
	
	/**
	 * IDによるデータ取得テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT * "
				+ "FROM blog_tag "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,  1);
		map.put(WebConsts.SQL_TAG_NAME, TestConsts.TEST_TAG_NAME);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(sql, 
				1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 
				2)).thenReturn(null);
		
		setService();
	}
	
	/**
	 * 選択テスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		BlogTagModel model = this.service.select(new BlogTagId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),  1);
		Assertions.assertEquals(model.getTag(), TestConsts.TEST_TAG_NAME);
	}
	
	/**
	 * 選択テスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select(new BlogTagId(2)));
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		BlogTagDao dao = new BlogTagDaoSql(this.jdbcTemp);
		this.service   = new BlogTagServiceUse(dao);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp = null;
		this.service  = null;
	}

}
