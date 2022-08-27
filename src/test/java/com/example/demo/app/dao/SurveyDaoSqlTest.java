package com.example.demo.app.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.entity.SurveyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.SurveyId;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NameWord;

/**
 * 調査Daoテスト
 * @author nanai
 *
 */
class SurveyDaoSqlTest {

	private SurveyDao dao = null;

	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "INSERT INTO survey("
				+ "name, age, profession, ismen, satisfaction, comment, created) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				10,
				1,
				1,
				5,
				"テストコメント",
				dateTime1
				)).thenReturn(1);
		
		this.dao = new SurveyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		SurveyModel model = new SurveyModel(
				new NameWord("テストネーム"),
				new NormalNumber(10),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(5),
				new NameWord("テストコメント"),
				dateTime1
				);
		
		this.dao.insertSurvey(model);
	}
	
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
				"テストネーム",
				10,
				1,
				1,
				5,
				"テストコメント",
				1
				)).thenReturn(1);
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				10,
				1,
				1,
				5,
				"テストコメント",
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.dao = new SurveyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 更新テスト
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		int ret = 0;
		SurveyModel model = new SurveyModel(
				new SurveyId(1),
				new NameWord("テストネーム"),
				new NormalNumber(10),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(5),
				new NameWord("テストコメント"),
				dateTime1
			);
		
		ret = this.dao.updateSurvey(model);
		Assertions.assertEquals(ret, 1);
	}
	
	/**
	 * 全選択の初期化
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("age", 10);
		map.put("profession", 2);
		map.put("ismen", 1);
		map.put("satisfaction", 1);
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		this.dao = new SurveyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<SurveyModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getAge(), 10);
		Assertions.assertEquals(list.get(0).getProfession(), 2);
		Assertions.assertEquals(list.get(0).getMen_or_female(), 1);
		Assertions.assertEquals(list.get(0).getSatisfaction(), 1);
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
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
