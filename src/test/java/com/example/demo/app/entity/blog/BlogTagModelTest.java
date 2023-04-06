package com.example.demo.app.entity.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグモデルテスト
 * @author nanai
 *
 */
class BlogTagModelTest {

	/** テスト対象 */
	BlogTagModel test = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		test = new BlogTagModel();
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void constractorTest() {
		assertEquals(0,  test.getId());
		assertEquals("", test.getTag());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト(2渡し)
	 */
	@Test
	public void constractorTest_2() {
		test = new BlogTagModel(
				new BlogTagId(1),
				new TagWord(TestConsts.TEST_TAG_NAME));

		assertEquals(1, 						test.getId());
		assertEquals(TestConsts.TEST_TAG_NAME, 	test.getTag());
	}

	/**
	 * コンストラクタテスト(2渡し - null)
	 */
	@Test
	public void constractorTest_Null2() {
		test = new BlogTagModel(
				null,
				null);

		assertEquals(0,  test.getId());
		assertEquals("", test.getTag());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 *  コンストラクタテスト(1渡し)
	 */
	@Test
	public void constractorTest_1() {
		test = new BlogTagModel(
				new TagWord(TestConsts.TEST_TAG_NAME));

		assertEquals(test.getId(),  0);
		assertEquals(test.getTag(), TestConsts.TEST_TAG_NAME);
	}

	/**
	 *  コンストラクタテスト(1渡し)
	 */
	@Test
	public void constractorTest_Null1() {
		test = new BlogTagModel((TagWord)null);

		assertEquals(test.getId(),  0);
		assertEquals(test.getTag(), "");
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト(model)
	 */
	@Test
	public void constractorTest_Model() {
		BlogTagModel model = new BlogTagModel(
				new BlogTagId(1),
				new TagWord(TestConsts.TEST_TAG_NAME));
		test = new BlogTagModel(model);

		assertEquals(1, 						test.getId());
		assertEquals(TestConsts.TEST_TAG_NAME, 	test.getTag());
	}

	/**
	 * コンストラクタテスト(model - Null)
	 */
	@Test
	public void constractorTest_ModelNull() {
		test = new BlogTagModel((BlogTagModel)null);

		assertEquals(test.getId(),  0);
		assertEquals(test.getTag(), "");
	}
}
