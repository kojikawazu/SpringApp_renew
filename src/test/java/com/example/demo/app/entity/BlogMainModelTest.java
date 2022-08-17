package com.example.demo.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログメインモデルテスト
 * @author nanai
 *
 */
class BlogMainModelTest {
	
	BlogMainModel model = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new BlogMainModel(
				new BlogId(0),
				new NameWord(""),
				new NameWord(""),
				new NameWord(""),
				new ThanksCntNumber(0),
				LocalDateTime.of(2000, 01, 01, 00, 00, 00),
				LocalDateTime.of(2000, 01, 01, 00, 00, 00),
				new ArrayList<BlogReplyModel>()
				);
	}

	/**
	 * コンストラクタのテスト
	 */
	@Test
	public void InitTest0() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getTitle(), "");
		Assertions.assertEquals(model.getTag(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertEquals(model.getUpdated().toString(), dateTime.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * 値設定後のテスト
	 */
	@Test
	public void InitTest1() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setTitle("テストタイトル");
		model.setTag("テストタグ");
		model.setComment("テストコメント");
		model.setThanksCnt(1);
		model.setCreated(dateTime);
		model.setUpdated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTitle(), "テストタイトル");
		Assertions.assertEquals(model.getTag(), "テストタグ");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertEquals(model.getUpdated().toString(), dateTime.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト(null渡し)
	 */
	@Test
	public void InitNull() {
		model = new BlogMainModel(
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getTitle());
		Assertions.assertNotNull(model.getTag());
		Assertions.assertNotNull(model.getComment());
		Assertions.assertNotNull(model.getThanksCnt());
		Assertions.assertNotNull(model.getCreated());
		Assertions.assertNotNull(model.getUpdated());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト(モデル渡し)
	 */
	@Test
	public void InitModel() {
		LocalDateTime now = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
		BlogMainModel test = new BlogMainModel(
				new BlogId(1),
				new NameWord("テスト"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				LocalDateTime.of(2000, 01, 02, 00, 00, 00),
				LocalDateTime.of(2000, 01, 02, 00, 00, 00),
				new ArrayList<BlogReplyModel>()
				);
		
		model = new BlogMainModel(test);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTitle(), "テスト");
		Assertions.assertEquals(model.getTag(), "テストタグ");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
		Assertions.assertEquals(model.getUpdated().toString(), now.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest2() {
		LocalDateTime now = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
		model = new BlogMainModel(
				new BlogId(1),
				new NameWord("テスト"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				LocalDateTime.of(2000, 01, 02, 00, 00, 00),
				LocalDateTime.of(2000, 01, 02, 00, 00, 00)
				);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTitle(), "テスト");
		Assertions.assertEquals(model.getTag(), "テストタグ");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
		Assertions.assertEquals(model.getUpdated().toString(), now.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest3() {
		LocalDateTime now = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
		model = new BlogMainModel(
				new NameWord("テスト"),
				new NameWord("テストタグ"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				LocalDateTime.of(2000, 01, 02, 00, 00, 00),
				LocalDateTime.of(2000, 01, 02, 00, 00, 00)
				);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getTitle(), "テスト");
		Assertions.assertEquals(model.getTag(), "テストタグ");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
		Assertions.assertEquals(model.getUpdated().toString(), now.toString());
		Assertions.assertNotNull(model.getReplyList());
	}

}
