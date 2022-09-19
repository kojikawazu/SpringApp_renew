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

import com.example.demo.app.dao.InquiryDao;
import com.example.demo.app.dao.InquiryDaoSql;
import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせサービスクラスのテスト
 * @author nanai
 *
 */
class InquiryServiceUseTest {
	
	private static final String SQL_INSERT = "INSERT INTO inquiry("
											+ "name, email, comment, created) "
											+ "VALUES(?,?,?,?)";

	/** テスト対象 */
	InquiryService service = null;
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 追加テストの準備
	 */
	private void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		
		when(this.jdbcTemp.update(
				SQL_INSERT, 
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
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
		
		InquiryModel model = new InquiryModel(
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		this.service.save(model);
		verify(this.jdbcTemp, times(1)).update(
				SQL_INSERT, 
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
				TestConsts.TEST_COMMENT_NAME,
				TestConsts.TEST_TIME_01);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 更新テストの準備
	 */
	private void InitUpdate() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "UPDATE inquiry SET "
				+ "name = ?, email = ?, comment = ? "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
				TestConsts.TEST_COMMENT_NAME,
				1
				)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		
		when(this.jdbcTemp.update(
				sql, 
				TestConsts.TEST_NAME_NAME,
				TestConsts.TEST_EMAIL_NAME,
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
			() -> this.service.update(
				new InquiryModel(
					new InquiryId(1),
					new NameWord(TestConsts.TEST_NAME_NAME),
					new EmailWord(TestConsts.TEST_EMAIL_NAME),
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
		
		assertThrows(
			RuntimeException.class,
			() -> this.service.update(
				new InquiryModel(
					new InquiryId(2),
					new NameWord(TestConsts.TEST_NAME_NAME),
					new EmailWord(TestConsts.TEST_EMAIL_NAME),
					new CommentWord(TestConsts.TEST_COMMENT_NAME),
					TestConsts.TEST_TIME_01
				)
			));
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 削除テストの準備
	 */
	private void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry "
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
			this.service.delete(new InquiryId(1)));
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.delete(new InquiryId(2)));
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全て選択の準備
	 */
	private void InitSelectAll(boolean isDesc) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry "
					+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConsts.SQL_ID_NAME,      1);
		map.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put(WebConsts.SQL_ID_NAME,      2);
		map2.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME + "2");
		map2.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME + "2");
		map2.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME + "2");
		map2.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map2);
		
		if(isDesc) {
			Map<String, Object> mapTemp = mapList.get(0);
			mapList.set(0, mapList.get(1));
			mapList.set(1, mapTemp);
		}
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト(昇順)
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll(false);
		
		List<InquiryModel> list = this.service.getAll(false);
		
		Assertions.assertEquals(list.size(),             2);
		
		Assertions.assertEquals(list.get(0).getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(0).getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(list.get(0).getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		
		Assertions.assertEquals(list.get(1).getName(),    TestConsts.TEST_NAME_NAME    + "2");
		Assertions.assertEquals(list.get(1).getEmail(),   TestConsts.TEST_EMAIL_NAME   + "2");
		Assertions.assertEquals(list.get(1).getComment(), TestConsts.TEST_COMMENT_NAME + "2");
		Assertions.assertEquals(list.get(1).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		
		list.clear();
	}
	
	/**
	 * 全選択テスト(降順)
	 */
	@Test
	public void SelectAllTest_Desc() {
		InitSelectAll(true);
		
		List<InquiryModel> list = this.service.getAll(true);
		
		Assertions.assertEquals(list.size(),             2);
		
		Assertions.assertEquals(list.get(0).getName(),    TestConsts.TEST_NAME_NAME    + "2");
		Assertions.assertEquals(list.get(0).getEmail(),   TestConsts.TEST_EMAIL_NAME   + "2");
		Assertions.assertEquals(list.get(0).getComment(), TestConsts.TEST_COMMENT_NAME + "2");
		Assertions.assertEquals(list.get(0).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		
		Assertions.assertEquals(list.get(1).getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(list.get(1).getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(list.get(1).getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(list.get(1).getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全て選択の準備(空)
	 */
	private void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry "
					+ "order by id asc";
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		setService();
	}
	
	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		List<InquiryModel> list = this.service.getAll(false);
		
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全て選択(問い合わせ返信モデルリストつき)の準備
	 */
	private void InitSelectAllPlus(boolean isDesc) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT inquiry.*,"
				+ "inquiry_reply.id AS reply_id,"
				+ "inquiry_reply.name AS reply_name,"
				+ "inquiry_reply.email AS reply_email,"
				+ "inquiry_reply.comment AS reply_comment,"
				+ "inquiry_reply.created AS reply_created "
				+ "FROM inquiry "
				+ "LEFT OUTER JOIN inquiry_reply ON "
				+ "inquiry.id = inquiry_reply.inquiry_id "
				+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		
		Map<String, Object> map01 = new HashMap<String, Object>();
		map01.put(WebConsts.SQL_ID_NAME,      1);
		map01.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME);
		map01.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME);
		map01.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME);
		map01.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map01.put(WebConsts.SQL_REPLY_ID_NAME,      1);
		map01.put(WebConsts.SQL_REPLY_NAME_NAME,    "リプライネーム");
		map01.put(WebConsts.SQL_REPLY_EMAIL_NAME,   "リプライメール");
		map01.put(WebConsts.SQL_REPLY_COMMENT_NAME, "リプライコメント");
		map01.put(WebConsts.SQL_REPLY_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map01);
		
		Map<String, Object> map11 = new HashMap<String, Object>();
		map11.put(WebConsts.SQL_ID_NAME,      1);
		map11.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME);
		map11.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME);
		map11.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME);
		map11.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map11.put(WebConsts.SQL_REPLY_ID_NAME,      2);
		map11.put(WebConsts.SQL_REPLY_NAME_NAME,    "リプライネーム2");
		map11.put(WebConsts.SQL_REPLY_EMAIL_NAME,   "リプライメール2");
		map11.put(WebConsts.SQL_REPLY_COMMENT_NAME, "リプライコメント2");
		map11.put(WebConsts.SQL_REPLY_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map11);
		
		Map<String, Object> map21 = new HashMap<String, Object>();
		map21.put(WebConsts.SQL_ID_NAME,      2);
		map21.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME    + "2");
		map21.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME   + "2");
		map21.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME + "2");
		map21.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map21);
		
		if(isDesc) {
			Map<String, Object> mapTemp = mapList.get(0);
			mapList.set(0, mapList.get(1));
			mapList.set(1, mapTemp);
		}
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		setService();
	}
	
	/**
	 * 全選択(問い合わせ返信モデルリストつき)テスト(昇順)
	 */
	@Test
	public void SelectAllPlus_Test() {
		InitSelectAllPlus(false);
		
		List<InquiryModel> list = this.service.getAllPlus(false);
		
		Assertions.assertEquals(list.size(),              2);
		InquiryModel model1                 = list.get(0);
		List<InquiryReplyModel> replyModel1 = model1.getReplyList();
		InquiryModel model2                 = list.get(1);
		List<InquiryReplyModel> replyModel2 = model2.getReplyList();
		
		// model1
		Assertions.assertEquals(model1.getId(),      1);
		Assertions.assertEquals(model1.getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model1.getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model1.getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model1.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(replyModel1.size(),  2);
		
		// replyModel1
		Assertions.assertEquals(replyModel1.get(0).getId(),          1);
		Assertions.assertEquals(replyModel1.get(0).getInquiry_id(),  1);
		Assertions.assertEquals(replyModel1.get(0).getName(),        "リプライネーム");
		Assertions.assertEquals(replyModel1.get(0).getEmail(),       "リプライメール");
		Assertions.assertEquals(replyModel1.get(0).getComment(),     "リプライコメント");
		Assertions.assertEquals(replyModel1.get(0).getCreated().toString(),
				TestConsts.TEST_TIME_01.toString());
		
		// replyModel2
		Assertions.assertEquals(replyModel1.get(1).getId(),          2);
		Assertions.assertEquals(replyModel1.get(1).getInquiry_id(),  1);
		Assertions.assertEquals(replyModel1.get(1).getName(),        "リプライネーム2");
		Assertions.assertEquals(replyModel1.get(1).getEmail(),       "リプライメール2");
		Assertions.assertEquals(replyModel1.get(1).getComment(),     "リプライコメント2");
		Assertions.assertEquals(replyModel1.get(1).getCreated().toString(),
				TestConsts.TEST_TIME_01.toString());
		
		// model1
		Assertions.assertEquals(model2.getId(),      2);
		Assertions.assertEquals(model2.getName(),    TestConsts.TEST_NAME_NAME    + "2");
		Assertions.assertEquals(model2.getEmail(),   TestConsts.TEST_EMAIL_NAME   + "2");
		Assertions.assertEquals(model2.getComment(), TestConsts.TEST_COMMENT_NAME + "2");
		Assertions.assertEquals(model2.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(replyModel2.size(),  0);
		
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * IDによるデータ取得テストの準備
	 */
	private void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * "
				+ "FROM inquiry "
				+ "WHERE id = ?";
		
		map.put(WebConsts.SQL_ID_NAME,      1);
		map.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME);
		map.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME);
		map.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME);
		map.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		
		when(this.jdbcTemp.queryForMap(sql, 1))
			.thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2))
			.thenReturn(null);
		
		setService();
	}
	
	/**
	 * IDによるデータ取得テスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryModel model = this.service.select(new InquiryId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),      1);
		Assertions.assertEquals(model.getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * IDによるデータ取得テスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		assertThrows(RuntimeException.class, () -> 
			this.service.select(new InquiryId(2)));
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * サービスの設定
	 */
	private void setService() {
		InquiryDao dao = new InquiryDaoSql(this.jdbcTemp);
		this.service   = new InquiryServiceUse(dao);
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
