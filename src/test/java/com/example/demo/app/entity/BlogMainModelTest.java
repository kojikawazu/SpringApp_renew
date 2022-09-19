package com.example.demo.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.BlogId;
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
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new BlogMainModel(
				new BlogId(0),
				new TittleWord(""),
				new TagWord(""),
				new CommentWord(""),
				new ThanksCntNumber(0),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02,
				new ArrayList<BlogReplyModel>()
				);
	}

	/**
	 * コンストラクタのテスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getTitle(), "");
		Assertions.assertEquals(model.getTag(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * 値設定後のテスト
	 */
	@Test
	public void InitTest1() {
		
		model.setId(1);
		model.setTitle(TestConsts.TEST_TITLE_NAME);
		model.setTag(TestConsts.TEST_TAG_NAME);
		model.setComment(TestConsts.TEST_COMMENT_NAME);
		model.setThanksCnt(1);
		model.setCreated(TestConsts.TEST_TIME_01);
		model.setUpdated(TestConsts.TEST_TIME_02);
		
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(model.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
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
		BlogMainModel test = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02,
				new ArrayList<BlogReplyModel>()
				);
		
		model = new BlogMainModel(test);
		
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(model.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest2() {
		model = new BlogMainModel(
				new BlogId(1),
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		Assertions.assertEquals(model.getId(),        1);
		Assertions.assertEquals(model.getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(model.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest3() {
		model = new BlogMainModel(
				new TittleWord(TestConsts.TEST_TITLE_NAME),
				new TagWord(TestConsts.TEST_TAG_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				new ThanksCntNumber(1),
				TestConsts.TEST_TIME_01,
				TestConsts.TEST_TIME_02
				);
		
		Assertions.assertEquals(model.getId(),        0);
		Assertions.assertEquals(model.getTitle(),     TestConsts.TEST_TITLE_NAME);
		Assertions.assertEquals(model.getTag(),       TestConsts.TEST_TAG_NAME);
		Assertions.assertEquals(model.getComment(),   TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertEquals(model.getUpdated().toString(), TestConsts.TEST_TIME_02.toString());
		Assertions.assertNotNull(model.getReplyList());
	}

}
