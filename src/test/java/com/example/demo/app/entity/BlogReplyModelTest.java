package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信モデルのテスト
 * @author nanai
 *
 */
class BlogReplyModelTest {
	
	BlogReplyModel model= null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model= new BlogReplyModel(
				new BlogReplyId(0),
				new BlogId(0),
				new NameWord(""),
				new NameWord(""),
				new ThanksCntNumber(0),
				LocalDateTime.of(2000, 01, 01, 00, 00, 00)
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getBlogId(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * 値設定後のテスト
	 */
	@Test
	public void InitTest1() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setBlogId(1);
		model.setName("テストネーム");
		model.setComment("テストコメント");
		model.setThanksCnt(1);
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getBlogId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * コンストラクタテスト(null渡し)
	 */
	@Test
	public void InitNull() {
		model= new BlogReplyModel(
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getBlogId());
		Assertions.assertNotNull(model.getName());
		Assertions.assertNotNull(model.getComment());
		Assertions.assertNotNull(model.getThanksCnt());
		Assertions.assertNotNull(model.getCreated().toString());
	}
	
	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void InitModel() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
		BlogReplyModel test= new BlogReplyModel(
				new BlogReplyId(1),
				new BlogId(1),
				new NameWord("テストネーム"),
				new NameWord("テストコメント"),
				new ThanksCntNumber(1),
				dateTime
				);
		
		model = new BlogReplyModel(test);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getBlogId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	

}
