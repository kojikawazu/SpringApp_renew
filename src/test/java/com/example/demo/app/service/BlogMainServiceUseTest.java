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

import com.example.demo.app.dao.BlogMainDao;
import com.example.demo.app.dao.BlogMainDaoSql;
import com.example.demo.app.entity.BlogMainModel;
import com.example.demo.app.exception.InquiryNotFoundException;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログメインサービスクラス
 * @author nanai
 *
 */
class BlogMainServiceUseTest {
	
	/** テスト対象 */
	BlogMainService service = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	LocalDateTime dateTime2 = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストタイトル"),
				eq("テストタグ"),
				eq("テストコメント"),
				eq(1),
				eq(dateTime2)
				)).thenReturn(1);
		
		setService();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		// 準備
		InitInsert();
		
		// テスト
		BlogMainModel model = new BlogMainModel(
				new NameWord(""),
				new NameWord(""),
				new NameWord(""),
				new ThanksCntNumber(0),
				LocalDateTime.now(),
				LocalDateTime.now()
				);
		service.save(model);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(
				any(), 
				eq("テストタイトル"),
				eq("テストタグ"),
				eq("テストコメント"),
				eq(1),
				eq(dateTime2),
				eq(1)
				)).thenReturn(1);
		
		setService();
	}
	
	/**
	 * 更新テスト
	 */
	@Test
	public void UpdateTest() {
		// 準備
		InitUpdate();
		
		// テスト
		BlogMainModel model = new BlogMainModel(
				new BlogId(1),
				new NameWord("テストタイトル"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime1,
				dateTime2
				);
		
		// 例外テスト1
		//assertThrows(RuntimeException.class, () -> service.update(model));
		
		// 例外テスト2
		assertDoesNotThrow(() -> service.update(model));
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.update(any(), eq(1))).thenReturn(1);
		when(jdbcTemp.update(any(), eq(2))).thenReturn(0);
		
		setService();
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		// 準備
		InitDelete();
		
		// テスト
		assertDoesNotThrow(() -> service.delete(new BlogId(1)));
		assertThrows(RuntimeException.class, () -> service.delete(new BlogId(2)));
	}
	
	/**
	 * 全て選択の準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("title", "テストタイトル");
		map.put("tag", "テストタグ");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		map.put("updated", Timestamp.valueOf(dateTime2));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全て選択テスト
	 */
	@Test
	public void SelectAllTest() {
		// 準備
		InitSelectAll();
		
		// テスト
		List<BlogMainModel> list = service.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getTitle(), "テストタイトル");
		Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		Assertions.assertEquals(list.get(0).getUpdated().toString(), dateTime2.toString());
		list.clear();
	}
	
	/**
	 * IDによるデータ選択の準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("title", "テストタイトル");
		map.put("tag", "テストタグ");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		map.put("updated", Timestamp.valueOf(dateTime2));
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		setService();
	}
	
	/**
	 * IDによる選択のテスト
	 */
	@Test
	public void Select_byIdTest() {
		// 準備
		InitSelect_byId();
		BlogMainModel model = null;
		
		try {
			model = service.select(new BlogId(1));
			
			Assertions.assertNotNull(model);
			Assertions.assertEquals(model.getId(), 1);
			Assertions.assertEquals(model.getTitle(), "テストタイトル");
			Assertions.assertEquals(model.getTag(), "テストタグ");
			Assertions.assertEquals(model.getComment(), "テストコメント");
			Assertions.assertEquals(model.getThanksCnt(), 1);
			Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
			Assertions.assertEquals(model.getUpdated().toString(), dateTime2.toString());
		} catch(InquiryNotFoundException ex) {
			Assertions.assertThrows(InquiryNotFoundException.class,
					() -> { service.select(new BlogId(1)); });
		}
		
		try {
			model = service.select(new BlogId(2));
			Assertions.assertNull(model);
		} catch(InquiryNotFoundException ex) {
			Assertions.assertThrows(InquiryNotFoundException.class,
					() -> { service.select(new BlogId(2)); });
		}
	}
	
	/**
	 * タグによる選択の準備
	 */
	public void InitSelect_byTag() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("title", "テストタイトル");
		map.put("tag", "テストタグ");
		map.put("comment", "テストコメント");
		map.put("thanksCnt", 1);
		map.put("created", Timestamp.valueOf(dateTime1));
		map.put("updated", Timestamp.valueOf(dateTime2));
		mapList.add(map);
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForList(any(), eq("テスト"))).thenReturn(mapList);
		when(jdbcTemp.queryForList(any(), eq("バグ"))).thenReturn(mapList2);
		
		setService();
	}
	
	/**
	 * タグによる選択のテスト
	 */
	@Test
	public void Select_byTagTest() {
		// 準備
		InitSelect_byTag();
		
		// テスト
		List<BlogMainModel> list = null;
		try {
			list = service.select_byTag("テスト");
			
			Assertions.assertEquals(list.size(), 1);
			Assertions.assertEquals(list.get(0).getId(), 1);
			Assertions.assertEquals(list.get(0).getTitle(), "テストタイトル");
			Assertions.assertEquals(list.get(0).getTag(), "テストタグ");
			Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
			Assertions.assertEquals(list.get(0).getThanksCnt(), 1);
			Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
			Assertions.assertEquals(list.get(0).getUpdated().toString(), dateTime2.toString());
			list.clear();
		} catch(InquiryNotFoundException ex) {
			Assertions.assertThrows(InquiryNotFoundException.class,
					() -> { service.select_byTag("テスト"); });
		}
		
		try {
			list = service.select_byTag("バグ");
			Assertions.assertEquals(list.size(), 0);
		} catch(InquiryNotFoundException ex) {
			Assertions.assertThrows(InquiryNotFoundException.class,
					() -> { service.select_byTag("バグ"); });
		}
	}
	
	/**
	 * インクリメントテストの準備
	 */
	public void InitThanksIncrement() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thanksCnt", 1);
		
		
		// Mock化
		jdbcTemp = mock(JdbcTemplate.class);
		when(jdbcTemp.queryForMap(any(), eq(1))).thenReturn(map);
		when(jdbcTemp.update(any(), eq(2), eq(1))).thenReturn(1);
		
		when(jdbcTemp.queryForMap(any(), eq(2))).thenReturn(null);
		
		setService();
	}
	
	/**
	 * インクリメントテスト
	 */
	@Test
	public void IncrementTest() {
		// 準備
		InitThanksIncrement();
		
		// テスト
		int ret = service.thanksIncrement(new BlogId(1));
		Assertions.assertEquals(ret, 2);
		
		assertThrows(RuntimeException.class, () ->
			this.service.thanksIncrement(new BlogId(2)));
	}
	
	/**
	 * サービスのインスタンス化
	 */
	public void setService() {
		BlogMainDao dao = new BlogMainDaoSql(jdbcTemp);
		service = new BlogMainServiceUse(dao);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		service = null;
		jdbcTemp = null;
	}
}
