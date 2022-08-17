package com.example.demo.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.id.InquiryId;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせクラステスト
 * @author nanai
 *
 */
class InquiryModelTest {
	
	InquiryModel model= null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model= new InquiryModel(
				new InquiryId(0),
				new NameWord(""),
				new NameWord(""),
				new NameWord(""),
				LocalDateTime.of(2000, 01, 01, 00, 00, 00),
				new ArrayList<InquiryReplyModel>()
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getEmail(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * 値設定テスト
	 */
	@Test
	public void InitTest1() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setName("テストネーム");
		model.setEmail("テストメールアドレス");
		model.setComment("テストコメント");
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	public void InitNull() {
		model= new InquiryModel(
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getName());
		Assertions.assertNotNull(model.getEmail());
		Assertions.assertNotNull(model.getComment());
		Assertions.assertNotNull(model.getCreated());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void InitModel() {
		LocalDateTime now = LocalDateTime.now();
		InquiryModel test= new InquiryModel(
				new InquiryId(1),
				new NameWord("テスト"),
				new NameWord("テストメール"),
				new NameWord("テストコメント"),
				now,
				new ArrayList<InquiryReplyModel>()
				);
		
		model = new InquiryModel(test);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getEmail(), "テストメール");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest2() {
		LocalDateTime now = LocalDateTime.now();
		model= new InquiryModel(
				new InquiryId(1),
				new NameWord("テスト"),
				new NameWord("テストメール"),
				new NameWord("テストコメント"),
				now
				);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getEmail(), "テストメール");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest3() {
		LocalDateTime now = LocalDateTime.now();
		model= new InquiryModel(
				new NameWord("テスト"),
				new NameWord("テストメール"),
				new NameWord("テストコメント"),
				now
				);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getEmail(), "テストメール");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
		Assertions.assertNotNull(model.getReplyList());
	}

}
