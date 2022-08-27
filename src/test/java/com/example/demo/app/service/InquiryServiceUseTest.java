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

import com.example.demo.app.dao.InquiryDao;
import com.example.demo.app.dao.InquiryDaoSql;
import com.example.demo.app.entity.InquiryModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogTagId;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせサービスクラスのテスト
 * @author nanai
 *
 */
class InquiryServiceUseTest {

	/** テスト対象 */
	InquiryService service = null;
	
	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "INSERT INTO inquiry("
				+ "name, email, comment, created) "
				+ "VALUES(?,?,?,?)";
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				dateTime1
				)).thenReturn(1);
		
		setService();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		InquiryModel model = new InquiryModel(
				new NameWord("テストネーム"),
				new NameWord("テストメールアドレス"),
				new NameWord("テストコメント"),
				dateTime1
				);
		
		this.service.save(model);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE inquiry SET "
				+ "name = ?, email = ?, comment = ? "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				1
				)).thenReturn(1);
		
		when(this.jdbcTemp.update(
				sql, 
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * 更新テスト
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		// 正常系
		assertDoesNotThrow(
				() -> this.service.update(
						new InquiryModel(
							new InquiryId(1),
							new NameWord("テストネーム"),
							new NameWord("テストメールアドレス"),
							new NameWord("テストコメント"),
							dateTime1
						)
				));
		
		// 異常系
		assertThrows(
				RuntimeException.class,
				() -> this.service.update(
						new InquiryModel(
							new InquiryId(2),
							new NameWord("テストネーム"),
							new NameWord("テストメールアドレス"),
							new NameWord("テストコメント"),
							dateTime1
						)
				));
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(1);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		assertDoesNotThrow(() -> 
			this.service.delete(new InquiryId(1)));
		assertThrows(RuntimeException.class, () -> 
			this.service.delete(new InquiryId(2)));
	}
	
	/**
	 * 全て選択の準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<InquiryModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(), "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
	}
	
	/**
	 * IDによるデータ取得テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT * "
				+ "FROM inquiry "
				+ "WHERE id = ?";
		
		map.put("id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(sql, 1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
		
		setService();
	}
	
	/**
	 * IDによるデータ取得テスト
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryModel model = this.service.select(new InquiryId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select(new InquiryId(2)));
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		InquiryDao dao = new InquiryDaoSql(this.jdbcTemp);
		this.service = new InquiryServiceUse(dao);
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.jdbcTemp = null;
		this.service = null;
	}

}
