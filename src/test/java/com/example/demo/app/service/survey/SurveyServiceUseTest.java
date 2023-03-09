package com.example.demo.app.service.survey;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.app.common.id.survey.SurveyId;
import com.example.demo.app.dao.survey.SurveyDao;
import com.example.demo.app.dao.survey.SurveyDaoSql;
import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * 調査サービスのテスト
 * @author nanai
 *
 */
class SurveyServiceUseTest {
	
	private static final String SQL_INSERT = "INSERT INTO survey("
			+ "name, age, profession, ismen, satisfaction, comment, created) "
			+ "VALUES(?,?,?,?,?,?,?)";

	/** テスト対象 */
	SurveyService service = null;
	
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
				TestConsts.TEST_NAME_NAME,
				10,
				1,
				1,
				5,
				TestConsts.TEST_COMMENT_NAME,
				TestConsts.TEST_TIME_01
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		setService();
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
		
		this.service.save(model);
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
	
	/** ------------------------------------------------------------------------------------------------------------- */
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
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
				"テストネーム",
				10,
				1,
				1,
				5,
				TestConsts.TEST_COMMENT_NAME,
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();

		assertDoesNotThrow(
			() -> this.service.update(new SurveyModel(
					new SurveyId(1),
					new NameWord(TestConsts.TEST_NAME_NAME),
					new NormalNumber(10),
					new NormalNumber(1),
					new NormalNumber(1),
					new NormalNumber(5),
					new CommentWord(TestConsts.TEST_COMMENT_NAME),
					TestConsts.TEST_TIME_01
					)
			));
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTest_Error() {
		InitUpdate();

		assertThrows(RuntimeException.class,
			() -> this.service.update(new SurveyModel(
					new SurveyId(2),
					new NameWord(TestConsts.TEST_NAME_NAME),
					new NormalNumber(10),
					new NormalNumber(1),
					new NormalNumber(1),
					new NormalNumber(5),
					new CommentWord(TestConsts.TEST_COMMENT_NAME),
					TestConsts.TEST_TIME_01
					)
			));
	}
	
	/**
	 * 全選択の初期化
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM survey";
		
		map.put(WebConsts.SQL_ID_NAME,           1);
		map.put(WebConsts.SQL_NAME_NAME,         TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_AGE_NAME,         10);
		map.put(WebConsts.SQL_PROFESSION_NAME,   2);
		map.put(WebConsts.SQL_ISMEN_NAME,        1);
		map.put(WebConsts.SQL_SATISFACTION_NAME, 1);
		map.put(WebConsts.SQL_COMMENT_NAME,      TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
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
		
		List<SurveyModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(),                    1);
		Assertions.assertEquals(list.get(0).getId(),            1);
		Assertions.assertEquals(list.get(0).getName(),          TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(0).getAge(),          10);
		Assertions.assertEquals(list.get(0).getProfession(),    2);
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
	public void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM survey";
				
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
		
		List<SurveyModel> list = this.service.getAll();
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		SurveyDao dao = new SurveyDaoSql(this.jdbcTemp);
		this.service  = new SurveyServiceUse(dao);
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
