package com.example.demo.app.dao;

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

import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.id.InquiryReplyId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信Daoクラスのテスト
 * @author nanai
 *
 */
class InquiryReplyDaoSqlTest {
	
	private InquiryReplyDao dao = null;

	LocalDateTime dateTime1 = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	@Mock
	JdbcTemplate jdbcTemp = null;
	
	/**
	 * 追加テストの準備
	 */
	public void InitInsert() {
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		String sql = "INSERT INTO inquiry_reply("
				+ "inquiry_id, name, email, comment, created) "
				+ "VALUES(?,?,?,?,?)";
		
		when(this.jdbcTemp.update(
				sql, 
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				dateTime1
				)).thenReturn(1);
		
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
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
				dateTime1
				);
		
		this.dao.insertReplyInquiry(model);
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
				)).thenReturn(1);
		
		when(this.jdbcTemp.update(
				sql, 
				1,
				"テストネーム",
				"テストメールアドレス",
				"テストコメント",
				2
				)).thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 更新テスト
	 */
	@Test
	public void UpdateTest() {
		InitUpdate();
		int ret = 0;
		
		// 正常系
		InquiryReplyModel model = new InquiryReplyModel(
				new InquiryReplyId(1),
				new InquiryId(1),
				new NameWord("テストネーム"),
				new EmailWord("テストメールアドレス"),
				new CommentWord("テストコメント"),
				dateTime1
				);
		
		ret = dao.updateReplyInquiry(model);
		Assertions.assertEquals(ret, 1);
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
			.thenReturn(1);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 削除テスト
	 */
	@Test
	public void DeleteTest() {
		InitDelete();
		int ret = 0;
		
		ret = this.dao.deleteReplyInquiry(new InquiryReplyId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteReplyInquiry(new InquiryReplyId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
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
			.thenReturn(1);
		when(this.jdbcTemp.update(sql, 2))
			.thenReturn(WebConsts.ERROR_DB_STATUS);
		
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * 削除テスト(問い合わせID)
	 */
	@Test
	public void Delete_byInquiryId_Test() {
		InitDelete_byInquiryId();
		int ret = 0;
		
		ret = this.dao.deleteReply_byInquiry(new InquiryId(1));
		Assertions.assertEquals(ret, 1);
		
		ret = this.dao.deleteReply_byInquiry(new InquiryId(2));
		Assertions.assertEquals(ret, WebConsts.ERROR_DB_STATUS);
	}

	/**
	 * 全選択テストの準備
	 */
	public void InitSelectAll() {
		Map<String, Object> map           = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		map.put("id", 1);
		map.put("inquiry_id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(any())).thenReturn(mapList);
		
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
	}
	
	
	/**
	 * 全選択テスト
	 */
	@Test
	public void SelectAllTest() {
		InitSelectAll();
		
		List<InquiryReplyModel> list = this.dao.getAll();
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getInquiry_id(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(), "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
	}
	
	/**
	 * IDによる選択テストの準備
	 */
	public void InitSelect_byId() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT * FROM inquiry_reply "
				+ "WHERE id = ?";
		
		map.put("id", 1);
		map.put("inquiry_id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForMap(sql, 1)).thenReturn(map);
		when(this.jdbcTemp.queryForMap(sql, 2)).thenReturn(null);
		
		this.dao = new InquiryReplyDaoSql(this.jdbcTemp);
	}
	
	/**
	 * IDによる選択テスト
	 */
	@Test
	public void Select_byIdTest() {
		InitSelect_byId();
		
		InquiryReplyModel model = this.dao.select(new InquiryReplyId(1));
		
		Assertions.assertNotNull(model);
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getInquiry_id(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime1.toString());
		
		model = this.dao.select(new InquiryReplyId(2));
		Assertions.assertNull(model);
	}
	
	/**
	 * 問い合わせIDによる選択テストの準備
	 */
	public void InitSelect_byInquiryId() {
		Map<String, Object> map            = new HashMap<String, Object>();
		List<Map<String, Object>> mapList  = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * FROM inquiry_reply "
				+ "WHERE inquiry_id = ?";
		
		map.put("id", 1);
		map.put("inquiry_id", 1);
		map.put("name", "テストネーム");
		map.put("email", "テストメールアドレス");
		map.put("comment", "テストコメント");
		map.put("created", Timestamp.valueOf(dateTime1));
		mapList.add(map);
		
		// Mock化
		this.jdbcTemp = mock(JdbcTemplate.class);
		when(this.jdbcTemp.queryForList(sql, 1)).thenReturn(mapList);
		when(this.jdbcTemp.queryForList(sql, 2)).thenReturn(mapList2);
		
		this.dao = new InquiryReplyDaoSql(jdbcTemp);
	}
	
	/**
	 * 問い合わせIDによる選択テスト
	 */
	@Test
	public void Select_byInquiryId_Test() {
		InitSelect_byInquiryId();
		
		List<InquiryReplyModel> list = this.dao.select_byInquiryId(new InquiryId(1));
		
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.get(0).getId(), 1);
		Assertions.assertEquals(list.get(0).getInquiry_id(), 1);
		Assertions.assertEquals(list.get(0).getName(), "テストネーム");
		Assertions.assertEquals(list.get(0).getEmail(), "テストメールアドレス");
		Assertions.assertEquals(list.get(0).getComment(), "テストコメント");
		Assertions.assertEquals(list.get(0).getCreated().toString(), dateTime1.toString());
		list.clear();
		
		list = this.dao.select_byInquiryId(new InquiryId(2));
		Assertions.assertEquals(list.size(), 0);
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
