package com.example.demo.app.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.dao.survey.SurveyDao;
import com.example.demo.app.dao.survey.SurveyDaoSql;
import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.survey.SurveyId;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * 調査Daoテスト
 * @author nanai
 *
 */
class SurveyDaoSqlTest {
	
	private static final String SQL_INSERT = "INSERT INTO survey("
			+ "name, age, profession, ismen, satisfaction, comment, created) "
			+ "VALUES(?,?,?,?,?,?,?)";
	
	/** daoクラス */
	private SurveyDao dao = null;
	
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
				TestConsts.TEST_NAME_NAME,
				10,
				1,
				1,
				5,
				TestConsts.TEST_COMMENT_NAME,
				TestConsts.TEST_TIME_01
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		this.setDao();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		SurveyModel model = new SurveyModel(
				new NameWord(TestConsts.TEST_NAME_NAME),
				new NormalNumber(10),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(5),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		this.dao.insertSurvey(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				TestConsts.TEST_NAME_NAME,
				10,
				1,
				1,
				5,
				TestConsts.TEST_COMMENT_NAME,
				TestConsts.TEST_TIME_01);
	}
	
	/**
	 * 更新テストの準備
	 */
	private void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE survey SET "
				+ "name = ?, age = ?, profession = ?, ismen = ?, satisfaction = ?, comment = ? "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_NAME_NAME,
				10,
				1,
				1,
				5,
				TestConsts.TEST_COMMENT_NAME,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_NAME_NAME,
				10,
				1,
				1,
				5,
				TestConsts.TEST_COMMENT_NAME,
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		SurveyModel model = new SurveyModel(
				new SurveyId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new NormalNumber(10),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(5),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
			);
		
		int ret = this.dao.updateSurvey(model);
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTest_Error() {
		InitUpdate();
		
		SurveyModel model = new SurveyModel(
				new SurveyId(2),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new NormalNumber(10),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(5),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
			);
		
		int ret = this.dao.updateSurvey(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	/**
	 * 全選択の初期化
	 */
	private void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM survey";
		
		map.put(WebConsts.SQL_ID_NAME,           1);
		map.put(WebConsts.SQL_NAME_NAME,         TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_AGE_NAME,         10);
		map.put(WebConsts.SQL_PROFESSION_NAME,   1);
		map.put(WebConsts.SQL_ISMEN_NAME,        1);
		map.put(WebConsts.SQL_SATISFACTION_NAME, 1);
		map.put(WebConsts.SQL_COMMENT_NAME,      TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
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
		
		List<SurveyModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(),                   1);
		Assertions.assertEquals(list.get(0).getId(),           1);
		Assertions.assertEquals(list.get(0).getName(),         TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(0).getAge(),          10);
		Assertions.assertEquals(list.get(0).getProfession(),    1);
		Assertions.assertEquals(list.get(0).getMen_or_female(), 1);
		Assertions.assertEquals(list.get(0).getSatisfaction(),  1);
		Assertions.assertEquals(list.get(0).getComment(),       TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
			TestConsts.TEST_TIME_01.toString());
		list.clear();
	}
	
	/**
	 * 全選択の初期化(空)
	 */
	private void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM survey";
		
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
		
		List<SurveyModel> list = this.dao.getAll();
		assertNotNull(list);
		assertEquals(list.size(), 0);
	}
	
	/**
	 * Dao設定
	 */
	private void setDao() {
		this.dao = new SurveyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.dao = null;
		this.jdbcTemp = null;
	}

}
