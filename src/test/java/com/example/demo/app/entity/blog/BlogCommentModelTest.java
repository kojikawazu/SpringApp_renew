package com.example.demo.app.entity.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.blog.BlogCommentId;
import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログコメントモデルテスト
 * @author nanai
 *
 */
class BlogCommentModelTest {

	/** テスト対象 */
	BlogCommentModel model = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new BlogCommentModel();
		model.setCreated(TestConsts.TEST_TIME_01);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void constractorTest() {
		assertEquals(0,  model.getId());
		assertEquals("", model.getName());
		assertEquals(0,  model.getThanksCnt());
		assertEquals("", model.getComment());
		assertEquals(0,  model.getBlogid());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	public void constractorTest_6() {
		model = new BlogCommentModel(
				new BlogCommentId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_02);

		assertEquals(1, 									model.getId());
		assertEquals(TestConsts.TEST_NAME_NAME, 			model.getName());
		assertEquals(1, 									model.getThanksCnt());
		assertEquals(TestConsts.TEST_COMMENT_NAME,			model.getComment());
		assertEquals(1, 									model.getBlogid());
		assertEquals(TestConsts.TEST_TIME_02.toString(),	model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	public void constractorTest_Null6() {
		model = new BlogCommentModel(
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		assertNotNull(model.getId());
		assertNotNull(model.getName());
		assertNotNull(model.getThanksCnt());
		assertNotNull(model.getComment());
		assertNotNull(model.getBlogid());
		assertNotNull(model.getCreated());
		assertEquals(0,  model.getId());
		assertEquals("", model.getName());
		assertEquals(0,  model.getThanksCnt());
		assertEquals("", model.getComment());
		assertEquals(0,  model.getBlogid());
	}

	/**
	 * コンストラクタテスト(モデル渡し)
	 */
	@Test
	public void constractorTest_Model() {
		BlogCommentModel test = new BlogCommentModel(
				new BlogCommentId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_02
				);
		model = new BlogCommentModel(test);

		assertEquals(1, 									model.getId());
		assertEquals(TestConsts.TEST_NAME_NAME, 			model.getName());
		assertEquals(1, 									model.getThanksCnt());
		assertEquals(TestConsts.TEST_COMMENT_NAME,			model.getComment());
		assertEquals(1, 									model.getBlogid());
		assertEquals(TestConsts.TEST_TIME_02.toString(),	model.getCreated().toString());
	}
}
