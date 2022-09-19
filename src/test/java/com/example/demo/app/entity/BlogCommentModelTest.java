package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.id.BlogCommentId;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログコメントモデルテスト
 * @author nanai
 *
 */
class BlogCommentModelTest {
	
	BlogCommentModel model = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new BlogCommentModel(
				new BlogCommentId(0),
				new BlogId(0),
				new NameWord(""),
				new CommentWord(""),
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
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getBlogid(), 0);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}

	/**
	 * 値設定後のテスト
	 */
	@Test
	public void InitTest1() {
		LocalDateTime dateTime = LocalDateTime.now();
		
		model.setId(1);
		model.setName("テスト");
		model.setThanksCnt(0);
		model.setComment("テストコメント");
		model.setBlogid(1);
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getBlogid(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	public void InitNull() {
		model = new BlogCommentModel(
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getName());
		Assertions.assertNotNull(model.getThanksCnt());
		Assertions.assertNotNull(model.getComment());
		Assertions.assertNotNull(model.getBlogid());
		Assertions.assertNotNull(model.getCreated());
	}
	
	/**
	 * コンストラクタテスト(モデル渡し)
	 */
	@Test
	public void InitModel() {
		LocalDateTime now = LocalDateTime.of(2000, 01, 02, 00, 00, 00);
		BlogCommentModel test = new BlogCommentModel(
				new BlogCommentId(1),
				new BlogId(1),
				new NameWord("テスト"),
				new CommentWord("コメントテスト"),
				new ThanksCntNumber(1),
				now
				);
		
		model = new BlogCommentModel(test);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getComment(), "コメントテスト");
		Assertions.assertEquals(model.getBlogid(), 1);
		Assertions.assertEquals(model.getCreated().toString(), now.toString());
	}
}
