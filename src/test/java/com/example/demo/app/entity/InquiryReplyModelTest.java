package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.id.InquiryId;
import com.example.demo.common.id.InquiryReplyId;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信モデルクラス
 * @author nanai
 *
 */
class InquiryReplyModelTest {
	
	InquiryReplyModel model= null;
	
	LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model= new InquiryReplyModel(
				new InquiryReplyId(0),
				new InquiryId(0),
				new NameWord(""),
				new EmailWord(""),
				new CommentWord(""),
				dateTime);
	}

	/**
	 * 初期化テスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getInquiry_id(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getEmail(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * 値設定テスト
	 */
	@Test
	public void InitTest1() {
		model.setId(1);
		model.setInquiry_id(1);
		model.setName("テストネーム");
		model.setEmail("テストメールアドレス");
		model.setComment("テストコメント");
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getInquiry_id(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * コンストラクタテスト(null渡し)
	 */
	@Test
	public void InitNull() {
		model = new InquiryReplyModel(null);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getInquiry_id(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getEmail(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertNotNull(model.getCreated());
	}
	
	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void InitModel() {
		InquiryReplyModel test = new InquiryReplyModel(
				new InquiryReplyId(2),
				new InquiryId(2),
				new NameWord("テストネーム"),
				new EmailWord("テストアドレス"),
				new CommentWord("テストコメント"),
				dateTime);
		
		model = new InquiryReplyModel(test);
		
		Assertions.assertEquals(model.getId(), 2);
		Assertions.assertEquals(model.getInquiry_id(), 2);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * コンストラクタテスト2
	 */
	@Test
	public void InitTest2() {
		model = new InquiryReplyModel(
				new InquiryId(2),
				new NameWord("テストネーム"),
				new EmailWord("テストアドレス"),
				new CommentWord("テストコメント"),
				dateTime);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getInquiry_id(), 2);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
}
