package com.example.demo.app.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.app.dao.SurveyDao;
import com.example.demo.app.dao.SurveyDaoSql;
import com.example.demo.app.entity.SurveyModel;
import com.example.demo.common.id.SurveyId;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NameWord;

class SurveyServiceUseTest {

	// テスト対象
	SurveyService service = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	public void InitSelectAll() {
		// TODO 全て選択初期化
		Map<String, Object> map = new HashMap<String, Object>();
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
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		setService();
	}
	
	
	@Test
	public void SelectAllTest() {
		// TODO 全選択テスト
		InitSelectAll();
		
		List<SurveyModel> list = service.getAll();
		
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
	
	public void InitInsert() {
		// TODO 追加テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストネーム"),
				eq(10),
				eq(1),
				eq(1),
				eq(5),
				eq("テストコメント"),
				eq(dateTime1)
				)).thenReturn(1);
		
		setService();
	}
	
	@Test
	public void InsertTest() {
		// TODO 更新テスト
		InitInsert();
		
		SurveyModel model = new SurveyModel(
				new SurveyId(0),
				new NameWord(""),
				new NormalNumber(0),
				new NormalNumber(0),
				new NormalNumber(0),
				new NormalNumber(0),
				new NameWord(""),
				LocalDateTime.now()
				);
		service.save(model);
	}
	
	public void InitUpdate() {
		// TODO 更新テストの初期化
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストネーム"),
				eq(10),
				eq(1),
				eq(1),
				eq(5),
				eq("テストコメント"),
				eq(1)
				)).thenReturn(1);
		
		setService();
	}
	
	@Test
	public void UpdateTest() {
		// TODO 更新テスト
		InitUpdate();
		
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
		
		// 例外テスト1
		//assertThrows(RuntimeException.class, () -> service.update(model));
		
		// 例外テスト2
		assertDoesNotThrow(() -> service.update(model));
	}
	
	
	
	public void setService() {
		// TODO サービスのインスタンス化
		SurveyDao dao = new SurveyDaoSql(jdbcTemp);
		service = new SurveyServiceUse(dao);
	}
	
	@AfterEach
	public void Release() {
		jdbcTemp = null;
		service = null;
	}

}
