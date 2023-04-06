package com.example.demo.app.entity.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * ブログ返信モデルのテスト
 * @author nanai
 *
 */
class BlogReplyModelTest {

	/** テスト対象 */
	BlogReplyModel model= null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model= new BlogReplyModel();
		model.setCreated(TestConsts.TEST_TIME_01);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void constractorTest() {
		assertEquals(0,  model.getId());
		assertEquals(0,  model.getBlogId());
		assertEquals("", model.getName());
		assertEquals("", model.getComment());
		assertEquals(0,  model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(6つ - null渡し)
	 */
	@Test
	public void constractorTest_Null6() {
		model= new BlogReplyModel(
				null,
				null,
				null,
				null,
				null,
				null
				);

		assertNotNull(model.getId());
		assertNotNull(model.getBlogId());
		assertNotNull(model.getName());
		assertNotNull(model.getComment());
		assertNotNull(model.getThanksCnt());
		assertNotNull(model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(6つ)
	 */
	@Test
	public void constractorTest_6() {
		model= new BlogReplyModel(
				new BlogReplyId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01
				);

		assertEquals(1, model.getId());
		assertEquals(1, model.getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,    model.getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME, model.getComment());
		assertEquals(1, model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(5つ - null渡し)
	 */
	@Test
	public void constractorTest_Null5() {
		model= new BlogReplyModel(
				null,
				null,
				null,
				null,
				null
				);

		assertNotNull(model.getId());
		assertNotNull(model.getBlogId());
		assertNotNull(model.getName());
		assertNotNull(model.getComment());
		assertNotNull(model.getThanksCnt());
		assertNotNull(model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(5つ)
	 */
	@Test
	public void constractorTest_5() {
		model= new BlogReplyModel(
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01
				);

		assertEquals(0, model.getId());
		assertEquals(1, model.getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,    model.getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME, model.getComment());
		assertEquals(1, model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(model渡し)(Null)
	 */
	@Test
	public void constractorTest_ModelNull() {
		model = new BlogReplyModel(null);

		assertEquals(0,  model.getId());
		assertEquals(0,  model.getBlogId());
		assertEquals("", model.getName());
		assertEquals("", model.getComment());
		assertEquals(0,  model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
	}

	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void constractorTest_Model() {
		BlogReplyModel test= new BlogReplyModel(
				new BlogReplyId(1),
				new BlogId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01
				);
		model = new BlogReplyModel(test);

		assertEquals(1, model.getId());
		assertEquals(1, model.getBlogId());
		assertEquals(TestConsts.TEST_NAME_NAME,    model.getName());
		assertEquals(TestConsts.TEST_COMMENT_NAME, model.getComment());
		assertEquals(1, model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
	}
}
