package com.example.demo.app.entity.blog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.id.blog.BlogReplyId;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
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
				new CommentWord(""),
				new ThanksCntNumber(0),
				TestConsts.TEST_TIME_01
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(),        0);
		Assertions.assertEquals(model.getBlogId(),    0);
		Assertions.assertEquals(model.getName(),      "");
		Assertions.assertEquals(model.getComment(),   "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
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
		BlogReplyModel test= new BlogReplyModel(
				new BlogReplyId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01
				);
		
		model = new BlogReplyModel(test);
		
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getBlogId(),    1);
		Assertions.assertEquals(model.getName(),      TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
	}
	
}
