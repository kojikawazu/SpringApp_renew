package com.example.demo.app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.entity.blog.BlogCommentModel;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.blog.BlogCommentId;
import com.example.demo.common.id.blog.BlogId;
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
				TestConsts.TEST_TIME_01
				);
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getName(), 		"");
		Assertions.assertEquals(model.getThanksCnt(), 	0);
		Assertions.assertEquals(model.getComment(), 	"");
		Assertions.assertEquals(model.getBlogid(), 		0);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
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
		BlogCommentModel test = new BlogCommentModel(
				new BlogCommentId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_02
				);
		
		model = new BlogCommentModel(test);
		
		Assertions.assertEquals(model.getId(), 			1);
		Assertions.assertEquals(model.getName(), 		TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 	1);
		Assertions.assertEquals(model.getComment(), 	TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getBlogid(), 		1);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_02.toString());
	}
}
