package com.example.demo.app.dao;

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

import com.example.demo.app.dao.inquiry.InquiryDao;
import com.example.demo.app.dao.inquiry.InquiryDaoSql;
import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.inquiry.InquiryId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせDaoクラスのテスト
 * @author nanai
 *
 */
class InquiryDaoSqlTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO inquiry("
											+ "name, email, comment, created) "
											+ "VALUES(?,?,?,?)";
	
	private final String        TEST_REPLY_NAME     = "リプライネーム";
	private final String        TEST_REPLY_EMAIL    = "リプライメールアドレス";
	private final String        TEST_REPLY_COMMENT  = "リプライコメント";
	
	/** daoクラス */
	private InquiryDao dao = null;
	
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
		
		this.setDao();
	}
	
	/**
	 * 追加テスト
	 */
	@Test
	public void InsertTest() {
		InitInsert();
		
		InquiryModel model= new InquiryModel(
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		this.dao.insertInquiry(model);
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
		
		this.setDao();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		InquiryModel model = new InquiryModel(
				new InquiryId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		int ret = this.dao.updateInquiry(model);
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTestError() {
		InitUpdate();
		
		InquiryModel model = new InquiryModel(
				new InquiryId(2),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		int ret = this.dao.updateInquiry(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
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
		
		when(this.jdbcTemp.update(sql, 
				1)).thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 
				2)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteInquiry(new InquiryId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		int ret = this.dao.deleteInquiry(new InquiryId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	// --------------------------------------------------------------------------------------------------

	/**
	 * 全選択の準備
	 */
	private void InitSelectAll(boolean isDesc) {
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry "
					+ "order by id ";
		sql += (isDesc ? "desc" : "asc");
		
		for(int idx=1; idx<=2; idx++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(WebConsts.SQL_ID_NAME,      idx);
			map.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME    + idx);
			map.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME   + idx);
			map.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME + idx);
			map.put(WebConsts.SQL_CREATED_NAME, 
					Timestamp.valueOf(TestConsts.TEST_TIME_01));
			mapList.add(map);
		}
		
		if(isDesc) {
			Map<String, Object> mapTemp = mapList.get(0);
			mapList.set(0, mapList.get(1));
			mapList.set(1, mapTemp);
		}
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		this.setDao();
	}
	
	/**
	 * 全選択テスト(昇順)
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll(false);
		
		List<InquiryModel> list = this.dao.getAll(false);
		
		Assertions.assertEquals(list.size(),              2);
		for(int idx=1; idx<=2; idx++) {
			Assertions.assertEquals(list.get(idx-1).getName(),    TestConsts.TEST_NAME_NAME    + idx);
			Assertions.assertEquals(list.get(idx-1).getEmail(),   TestConsts.TEST_EMAIL_NAME   + idx);
			Assertions.assertEquals(list.get(idx-1).getComment(), TestConsts.TEST_COMMENT_NAME + idx);
			Assertions.assertEquals(list.get(idx-1).getCreated().toString(), 
					TestConsts.TEST_TIME_01.toString());
		}
		list.clear();
	}
	
	/**
	 * 全選択テスト(降順)
	 */
	@Test
	public void SelectAllTest_Desc() {
		InitSelectAll(true);
		
		List<InquiryModel> list = this.dao.getAll(true);
		
		Assertions.assertEquals(list.size(),              2);
		for(int idx=1; idx<=2; idx++) {
			Assertions.assertEquals(list.get(idx-1).getName(),    TestConsts.TEST_NAME_NAME    + (3-idx));
			Assertions.assertEquals(list.get(idx-1).getEmail(),   TestConsts.TEST_EMAIL_NAME   + (3-idx));
			Assertions.assertEquals(list.get(idx-1).getComment(), TestConsts.TEST_COMMENT_NAME + (3-idx));
			Assertions.assertEquals(list.get(idx-1).getCreated().toString(), 
					TestConsts.TEST_TIME_01.toString());
		}
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全選択の準備(空)
	 */
	private void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry";
		
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
		
		List<InquiryModel> list = this.dao.getAll(false);
		
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * 全選択(問い合わせ返信モデルリストつき)の準備
	 * @param isDesc
	 */
	private void InitSelectAll_Plus(boolean isDesc) {
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
		
		int modelCnt      = 1;
		int modelReplyCnt = 1;
		
		Map<String, Object> map01 = new HashMap<String, Object>();
		map01.put(WebConsts.SQL_ID_NAME,      modelCnt);
		map01.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME    + modelCnt);
		map01.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME   + modelCnt);
		map01.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME + modelCnt);
		map01.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map01.put(WebConsts.SQL_REPLY_ID_NAME,      modelReplyCnt);
		map01.put(WebConsts.SQL_REPLY_NAME_NAME,    TEST_REPLY_NAME    + modelReplyCnt);
		map01.put(WebConsts.SQL_REPLY_EMAIL_NAME,   TEST_REPLY_EMAIL   + modelReplyCnt);
		map01.put(WebConsts.SQL_REPLY_COMMENT_NAME, TEST_REPLY_COMMENT + modelReplyCnt);
		map01.put(WebConsts.SQL_REPLY_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map01);
		
		Map<String, Object> map11 = new HashMap<String, Object>();
		modelReplyCnt++;
		map11.put(WebConsts.SQL_ID_NAME,      modelCnt);
		map11.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME    + modelCnt);
		map11.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME   + modelCnt);
		map11.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME + modelCnt);
		map11.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		map11.put(WebConsts.SQL_REPLY_ID_NAME,      2);
		map11.put(WebConsts.SQL_REPLY_NAME_NAME,    TEST_REPLY_NAME    + modelReplyCnt);
		map11.put(WebConsts.SQL_REPLY_EMAIL_NAME,   TEST_REPLY_EMAIL   + modelReplyCnt);
		map11.put(WebConsts.SQL_REPLY_COMMENT_NAME, TEST_REPLY_COMMENT + modelReplyCnt);
		map11.put(WebConsts.SQL_REPLY_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map11);
		
		Map<String, Object> map21 = new HashMap<String, Object>();
		modelCnt++;
		map21.put(WebConsts.SQL_ID_NAME,      modelCnt);
		map21.put(WebConsts.SQL_NAME_NAME,    TestConsts.TEST_NAME_NAME    + modelCnt);
		map21.put(WebConsts.SQL_EMAIL_NAME,   TestConsts.TEST_EMAIL_NAME   + modelCnt);
		map21.put(WebConsts.SQL_COMMENT_NAME, TestConsts.TEST_COMMENT_NAME + modelCnt);
		map21.put(WebConsts.SQL_CREATED_NAME, 
				Timestamp.valueOf(TestConsts.TEST_TIME_01));
		mapList.add(map21);
		
		if(isDesc) {
			Map<String, Object> mapTemp = mapList.get(0);
			mapList.set(0, mapList.get(1));
			mapList.set(1, mapTemp);
		}
		
		when(this.jdbcTemp.queryForList(sql))
			.thenReturn(mapList);
		this.setDao();
	}
	
	/**
	 * 全選択(問い合わせ返信モデルリストつき)テスト(昇順)
	 */
	@Test
	public void SelectAll_Plus_Test() {
		InitSelectAll_Plus(false);
		
		List<InquiryModel> list = this.dao.getAll_Plus(false);
		int modelCnt      = 1;
		int modelReplyCnt = 1;
		
		Assertions.assertEquals(list.size(), 2);
		InquiryModel model1                 = list.get(0);
		List<InquiryReplyModel> replyModel1 = model1.getReplyList();
		InquiryModel model2                 = list.get(1);
		List<InquiryReplyModel> replyModel2 = model2.getReplyList();
		
		// model1
		Assertions.assertEquals(model1.getId(),      modelCnt);
		Assertions.assertEquals(model1.getName(),    TestConsts.TEST_NAME_NAME    + modelCnt);
		Assertions.assertEquals(model1.getEmail(),   TestConsts.TEST_EMAIL_NAME   + modelCnt);
		Assertions.assertEquals(model1.getComment(), TestConsts.TEST_COMMENT_NAME + modelCnt);
		Assertions.assertEquals(model1.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(replyModel1.size(),  2);
		
		// replyModel1
		Assertions.assertEquals(replyModel1.get(0).getId(),          modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(0).getInquiry_id(),  modelCnt);
		Assertions.assertEquals(replyModel1.get(0).getName(),        TEST_REPLY_NAME    + modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(0).getEmail(),       TEST_REPLY_EMAIL   + modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(0).getComment(),     TEST_REPLY_COMMENT + modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(0).getCreated().toString(),
				TestConsts.TEST_TIME_01.toString());
		
		// replyModel2
		modelReplyCnt++;
		Assertions.assertEquals(replyModel1.get(1).getId(),          modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(1).getInquiry_id(),  modelCnt);
		Assertions.assertEquals(replyModel1.get(1).getName(),        TEST_REPLY_NAME    + modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(1).getEmail(),       TEST_REPLY_EMAIL   + modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(1).getComment(),     TEST_REPLY_COMMENT + modelReplyCnt);
		Assertions.assertEquals(replyModel1.get(1).getCreated().toString(),
				TestConsts.TEST_TIME_01.toString());
		
		// model1
		modelCnt++;
		Assertions.assertEquals(model2.getId(),      2);
		Assertions.assertEquals(model2.getName(),    TestConsts.TEST_NAME_NAME    + modelCnt);
		Assertions.assertEquals(model2.getEmail(),   TestConsts.TEST_EMAIL_NAME   + modelCnt);
		Assertions.assertEquals(model2.getComment(), TestConsts.TEST_COMMENT_NAME + modelCnt);
		Assertions.assertEquals(model2.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(replyModel2.size(),  0);
		
		list.clear();
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * IDによる選択テストの準備
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
		
		this.setDao();
	}
	
	/**
	 * IDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryModel model = this.dao.select(new InquiryId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),      1);
		Assertions.assertEquals(model.getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * IDによる選択テスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		InquiryModel model = this.dao.select(new InquiryId(2));
		Assertions.assertNull(model);
	}
	
	// --------------------------------------------------------------------------------------------------
	
	/**
	 * dao設定
	 */
	private void setDao() {
		this.dao = new InquiryDaoSql(this.jdbcTemp);
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
