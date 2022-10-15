package com.example.demo.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

import com.example.demo.app.dao.inquiry.InquiryReplyDao;
import com.example.demo.app.dao.inquiry.InquiryReplyDaoSql;
import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.app.service.inquiry.InquiryReplyService;
import com.example.demo.app.service.inquiry.InquiryReplyServiceUse;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.inquiry.InquiryId;
import com.example.demo.common.id.inquiry.InquiryReplyId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信サービスのテスト
 * @author nanai
 *
 */
class InquiryReplyServiceUseTest {
	
	private static final String SQL_INSERT = "INSERT INTO inquiry_reply("
			+ "inquiry_id, name, email, comment, created) "
			+ "VALUES(?,?,?,?,?)";

	/** テスト対象 */
	InquiryReplyService service = null;
	
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
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
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
		
		InquiryReplyModel model = new InquiryReplyModel(
				new InquiryId(1),
				new NameWord("テストネーム"),
				new EmailWord("テストメールアドレス"),
				new CommentWord("テストコメント"),
				TestConsts.TEST_TIME_01
				);
		
		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT,
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				TestConsts.TEST_TIME_01);
	}
	
	/**
	 * 更新テストの準備
	 */
	public void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE inquiry_reply SET "
				+ "inquiry_id = ?, name = ?, email = ?, comment = ? "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.update(
				sql, 
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
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
		
		// 正常系
		assertDoesNotThrow(
			() -> this.service.update(
				new InquiryReplyModel(
					new InquiryReplyId(1),
					new InquiryId(1),
					new NameWord("テストネーム"),
					new EmailWord("テストメールアドレス"),
					new CommentWord("テストコメント"),
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
		
		// 異常系
		assertThrows(
			RuntimeException.class,
			() -> this.service.update(
				new InquiryReplyModel(
					new InquiryReplyId(2),
					new InquiryId(1),
					new NameWord("テストネーム"),
					new EmailWord("テストメールアドレス"),
					new CommentWord("テストコメント"),
					TestConsts.TEST_TIME_01
				)
			));
	}
	
	/**
	 * 削除テストの準備
	 */
	public void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry_reply "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		assertDoesNotThrow(() -> 
			this.service.delete(new InquiryReplyId(1)));
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		assertThrows(RuntimeException.class, 
			() -> this.service.delete(new InquiryReplyId(2)));
	}
	
	/**
	 * 削除テストの準備(問い合わせID)
	 */
	public void InitDelete_byInquiryId() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry_reply "
				+ "WHERE inquiry_id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		setService();
	}
	
	/**
	 * 削除テスト(問い合わせID)(正常系)
	 */
	@Test
	public void Delete_byInquiryId_Test() {
		InitDelete_byInquiryId();
		
		// 正常系
		assertDoesNotThrow(() -> 
			this.service.delete_byInquiry(new InquiryId(1)));
	}
	
	/**
	 * 削除テスト(問い合わせID)(異常系)
	 */
	@Test
	public void Delete_byInquiryId_Test_Error() {
		InitDelete_byInquiryId();
		
		// 異常系
		assertThrows(RuntimeException.class, 
				() -> this.service.delete_byInquiry(new InquiryId(2)));
	}
	
	/**
	 * 全選択の準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry_reply";
		
		map.put(WebConsts.SQL_ID_NAME,         1);
		map.put(WebConsts.SQL_INQUIRY_ID_NAME, 1);
		map.put(WebConsts.SQL_NAME_NAME,       "テストネーム");
		map.put(WebConsts.SQL_EMAIL_NAME,      "テストメールアドレス");
		map.put(WebConsts.SQL_COMMENT_NAME,    "テストコメント");
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<InquiryReplyModel> list = this.service.getAll();
		
		Assertions.assertEquals(list.size(),                 1);
		Assertions.assertEquals(list.get(0).getId(),         1);
		Assertions.assertEquals(list.get(0).getInquiry_id(), 1);
		Assertions.assertEquals(list.get(0).getName(),       "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(),      "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(),    "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		list.clear();
	}
	
	/**
	 * 全選択の準備(空)
	 */
	public void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry_reply";
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		List<InquiryReplyModel> list = this.service.getAll();
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * IDによるデータ取得テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry_reply "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,         1);
		map.put(WebConsts.SQL_INQUIRY_ID_NAME, 1);
		map.put(WebConsts.SQL_NAME_NAME,       "テストネーム");
		map.put(WebConsts.SQL_EMAIL_NAME,      "テストメールアドレス");
		map.put(WebConsts.SQL_COMMENT_NAME,    "テストコメント");
		map.put(WebConsts.SQL_CREATED_NAME, 
			Timestamp.valueOf(TestConsts.TEST_TIME_01));
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		setService();
	}
	
	/**
	 * IDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryReplyModel model = this.service.select(new InquiryReplyId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getInquiry_id(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), 
			TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * IDによる選択テスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		assertThrows(RuntimeException.class, 
			() -> this.service.select(new InquiryReplyId(2)));
	}
	
	/**
	 * 問い合わせIDによる選択テストの準備
	 */
	public void InitSelect_byInquiryId() {
		Map<String, Object> map            = new HashMap<String, Object>();
		List<Map<String, Object>> mapList  = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry_reply "
				+ "WHERE inquiry_id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,         1);
		map.put(WebConsts.SQL_INQUIRY_ID_NAME, 1);
		map.put(WebConsts.SQL_NAME_NAME,       "テストネーム");
		map.put(WebConsts.SQL_EMAIL_NAME,      "テストメールアドレス");
		map.put(WebConsts.SQL_COMMENT_NAME,    "テストコメント");
		map.put(WebConsts.SQL_CREATED_NAME, 
			Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);
		
		when(this.jdbcTemp.queryForList(sql, 1))
			.thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, 2))
			.thenReturn(mapList2);
		
		setService();
	}
	
	/**
	 * 問い合わせIDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byInquiryId_Test() {
		InitSelect_byInquiryId();
		
		List<InquiryReplyModel> list = this.service.select_byInquiryId(new InquiryId(1));
		
		Assertions.assertEquals(list.size(),                 1);
		Assertions.assertEquals(list.get(0).getId(),         1);
		Assertions.assertEquals(list.get(0).getInquiry_id(), 1);
		Assertions.assertEquals(list.get(0).getName(),       "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(),      "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(),    "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
			TestConsts.TEST_TIME_01.toString());
		list.clear();
	}
	
	/**
	 * 問い合わせIDによる選択テスト(異常系)
	 */
	@Test
	public void Select_byInquiryId_Test_Error() {
		InitSelect_byInquiryId();
		
		List<InquiryReplyModel> list = this.service.select_byInquiryId(new InquiryId(2));
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * サービスの設定
	 */
	public void setService() {
		InquiryReplyDao dao = new InquiryReplyDaoSql(this.jdbcTemp);
		this.service        = new InquiryReplyServiceUse(dao);
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
