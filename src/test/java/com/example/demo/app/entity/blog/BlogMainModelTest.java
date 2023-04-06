package com.example.demo.app.entity.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログメインモデルテスト
 * @author nanai
 *
 */
class BlogMainModelTest {

	/** モデル */
	BlogMainModel model = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new BlogMainModel();
		model.setCreated(TestConsts.TEST_TIME_01);
		model.setUpdated(TestConsts.TEST_TIME_02);
	}
 
	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタのテスト
	 */
	@Test
	public void constractorTest() {
		assertEquals(0, 	model.getId());
		assertEquals("", 	model.getTitle());
		assertEquals("", 	model.getTag());
		assertEquals("", 	model.getComment());
		assertEquals(0, 	model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), model.getUpdated().toString());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(引数 - 8つ - Null)
	 */
	@Test
	public void constractorTest_8_Null() {
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

		assertEquals(0, 	model.getId());
		assertEquals("", 	model.getTitle());
		assertEquals("", 	model.getTag());
		assertEquals("", 	model.getComment());
		assertEquals(0, 	model.getThanksCnt());
		assertNotNull(model.getCreated());
		assertNotNull(model.getUpdated());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(引数 - 8つ)
	 */
	@Test
	public void constractorTest_8() {
		model = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02,
				new BlogReplyModelList()
				);

		assertEquals(1, 							model.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	model.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		model.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	model.getComment());
		assertEquals(1, 							model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), model.getUpdated().toString());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(引数 - 7つ - Null)
	 */
	@Test
	public void constractorTest_7_Null() {
		model = new BlogMainModel(
				null,
				null,
				null,
				null,
				null,
				null,
				null
				);

		assertEquals(0, 	model.getId());
		assertEquals("", 	model.getTitle());
		assertEquals("", 	model.getTag());
		assertEquals("", 	model.getComment());
		assertEquals(0, 	model.getThanksCnt());
		assertNotNull(model.getCreated());
		assertNotNull(model.getUpdated());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(引数 - 7つ)
	 */
	@Test
	public void constractorTest_7() {
		model = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		assertEquals(1,								model.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	model.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		model.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	model.getComment());
		assertEquals(1, 							model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), model.getUpdated().toString());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(引数 - 6つ - Null)
	 */
	@Test
	public void constractorTest_6_Null() {
		model = new BlogMainModel(
				null,
				null,
				null,
				null,
				null,
				null
				);

		assertEquals(0, 	model.getId());
		assertEquals("", 	model.getTitle());
		assertEquals("", 	model.getTag());
		assertEquals("", 	model.getComment());
		assertEquals(0, 	model.getThanksCnt());
		assertNotNull(model.getCreated());
		assertNotNull(model.getUpdated());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(引数 - 6つ)
	 */
	@Test
	public void constractorTest_6() {
		model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);

		assertEquals(0, 							model.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	model.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		model.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	model.getComment());
		assertEquals(1, 							model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), model.getUpdated().toString());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(モデル渡し)
	 */
	@Test
	public void constractorTest_Model_Null() {
		model = new BlogMainModel(null);

		assertEquals(0, 	model.getId());
		assertEquals("", 	model.getTitle());
		assertEquals("", 	model.getTag());
		assertEquals("", 	model.getComment());
		assertEquals(0, 	model.getThanksCnt());
		assertNotNull(model.getCreated());
		assertNotNull(model.getUpdated());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/**
	 * コンストラクタテスト(モデル渡し)
	 */
	@Test
	public void constractorTest_Model() {
		BlogMainModel test = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02,
				new BlogReplyModelList()
				);

		model = new BlogMainModel(test);

		assertEquals(1, 							model.getId());
		assertEquals(TestConsts.TEST_TITLE_NAME, 	model.getTitle());
		assertEquals(TestConsts.TEST_TAG_NAME, 		model.getTag());
		assertEquals(TestConsts.TEST_COMMENT_NAME, 	model.getComment());
		assertEquals(1, 							model.getThanksCnt());
		assertEquals(TestConsts.TEST_TIME_01.toString(), model.getCreated().toString());
		assertEquals(TestConsts.TEST_TIME_02.toString(), model.getUpdated().toString());
		assertNotNull(model.getReplyList());
		assertEquals(0, model.getReplyList().size());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		model.getReplyList().clear();
		model = null;
	}
}
