package com.example.demo.app.dao.inquiry;

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

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.common.id.inquiry.InquiryReplyId;
import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信Daoクラスのテスト
 * @author nanai
 *
 */
class InquiryReplyDaoSqlTest {
	
	/** SQL文(追加) */
	private static final String SQL_INSERT = "INSERT INTO inquiry_reply("
			+ "inquiry_id, name, email, comment, created) "
			+ "VALUES(?,?,?,?,?)";
	
	/** daoクラス */
	private InquiryReplyDao dao = null;
	
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
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
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
		
		InquiryReplyModel model = new InquiryReplyModel(
				new InquiryId(1),
				new NameWord("テストネーム"),
				new EmailWord("テストメールアドレス"),
				new CommentWord("テストコメント"),
				TestConsts.TEST_TIME_01
				);
		
		this.dao.insertReplyInquiry(model);
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
	private void InitUpdate() {
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
		
		this.setDao();
	}
	
	/**
	 * 更新テスト(正常系)
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		
		InquiryReplyModel model = new InquiryReplyModel(
				new InquiryReplyId(1),
				new InquiryId(1),
				new NameWord("テストネーム"),
				new EmailWord("テストメールアドレス"),
				new CommentWord("テストコメント"),
				TestConsts.TEST_TIME_01
				);
		
		int ret = this.dao.updateReplyInquiry(model);
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 更新テスト(異常系)
	 */
	@Test
	public void UpdateTest_Error() {
		InitUpdate();
		
		InquiryReplyModel model = new InquiryReplyModel(
				new InquiryReplyId(2),
				new InquiryId(1),
				new NameWord("テストネーム"),
				new EmailWord("テストメールアドレス"),
				new CommentWord("テストコメント"),
				TestConsts.TEST_TIME_01
				);
		
		int ret = this.dao.updateReplyInquiry(model);
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	/**
	 * 削除テストの準備
	 */
	private void InitDelete() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry_reply "
				+ "WHERE id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(正常系)
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		
		int ret = this.dao.deleteReplyInquiry(new InquiryReplyId(1));
		Assertions.assertEquals(ret, 1);
	}
	
	/**
	 * 削除テスト(異常系)
	 */
	@Test
	public void DeleteTest_Error() {
		InitDelete();
		
		int ret = this.dao.deleteReplyInquiry(new InquiryReplyId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}
	
	/**
	 * 削除テストの準備(問い合わせID)
	 */
	private void InitDelete_byInquiryId() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "DELETE FROM inquiry_reply "
				+ "WHERE inquiry_id = ?";
		
		when(this.jdbcTemp.update(sql, 1))
			.thenReturn(TestConsts.RESULT_NUMBER_OK);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.setDao();
	}
	
	/**
	 * 削除テスト(問い合わせID)(正常系)
	 */
	@Test
	public void Delete_byInquiryId_Test() {
		InitDelete_byInquiryId();
		
		int ret = this.dao.deleteReply_byInquiry(new InquiryId(1));
		Assertions.assertEquals(ret, TestConsts.RESULT_NUMBER_OK);
	}
	
	/**
	 * 削除テスト(問い合わせID)(異常系)
	 */
	@Test
	public void Delete_byInquiryId_Test_Error() {
		InitDelete_byInquiryId();
		
		int ret = this.dao.deleteReply_byInquiry(new InquiryId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 全選択テストの準備
	 */
	private void InitSelectAll() {
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
		
		this.setDao();
	}
	
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<InquiryReplyModel> list = this.dao.getAll();
		
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
	 * 全選択テストの準備(空)
	 */
	private void InitSelectAll_Empty() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "SELECT * FROM inquiry_reply";
		
		when(this.jdbcTemp.queryForList(sql)).thenReturn(mapList);
		
		this.setDao();
	}
	
	/**
	 * 全選択テスト(空)
	 */
	@Test
	public void SelectAllTest_Empty() {
		InitSelectAll_Empty();
		
		List<InquiryReplyModel> list = this.dao.getAll();
		
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * IDによる選択テストの準備
	 */
	private void InitSelect_byId() {
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
		
		this.setDao();
	}
	
	/**
	 * IDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryReplyModel model = this.dao.select(new InquiryReplyId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(),         1);
		Assertions.assertEquals(model.getInquiry_id(), 1);
		Assertions.assertEquals(model.getName(),       "テストネーム");
		Assertions.assertEquals(model.getEmail(),      "テストメールアドレス");
		Assertions.assertEquals(model.getComment(),    "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), 
				TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * IDによる選択テスト(異常系)
	 */
	@Test
	public void Select_byIdTest_Error() {
		InitSelect_byId();
		
		InquiryReplyModel model = this.dao.select(new InquiryReplyId(2));
			Assertions.assertNull(model);
	}
	
	/**
	 * 問い合わせIDによる選択テストの準備
	 */
	private void InitSelect_byInquiryId() {
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
		
		this.setDao();
	}
	
	/**
	 * 問い合わせIDによる選択テスト(正常系)
	 */
	@Test
	public void Select_byInquiryId_Test() {
		InitSelect_byInquiryId();
		
		List<InquiryReplyModel> list = this.dao.select_byInquiryId(new InquiryId(1));
		
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
		
		List<InquiryReplyModel> list = this.dao.select_byInquiryId(new InquiryId(2));
		Assertions.assertEquals(list.size(), 0);
	}
	
	/**
	 * dao設定
	 */
	private void setDao() {
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
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
