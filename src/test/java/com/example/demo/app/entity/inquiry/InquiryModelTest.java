package com.example.demo.app.entity.inquiry;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせクラステスト
 * @author nanai
 *
 */
class InquiryModelTest {
	
	InquiryModel model= null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model= new InquiryModel(
				new InquiryId(0),
				new NameWord(""),
				new EmailWord(""),
				new CommentWord(""),
				TestConsts.TEST_TIME_01,
				new ArrayList<InquiryReplyModel>()
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(),      0);
		Assertions.assertEquals(model.getName(),    "");
		Assertions.assertEquals(model.getEmail(),   "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	public void InitNull() {
		model= new InquiryModel(
				null,
				null,
				null,
				null,
				null,
				null
				);
		
		Assertions.assertNotNull(model.getId());
		Assertions.assertNotNull(model.getName());
		Assertions.assertNotNull(model.getEmail());
		Assertions.assertNotNull(model.getComment());
		Assertions.assertNotNull(model.getCreated());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void InitModel() {
		InquiryModel test= new InquiryModel(
				new InquiryId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01,
				new ArrayList<InquiryReplyModel>()
				);
		
		model = new InquiryModel(test);
		
		Assertions.assertEquals(model.getId(),      1);
		Assertions.assertEquals(model.getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest2() {
		model= new InquiryModel(
				new InquiryId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		Assertions.assertEquals(model.getId(),      1);
		Assertions.assertEquals(model.getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertNotNull(model.getReplyList());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest3() {
		model= new InquiryModel(
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_01
				);
		
		Assertions.assertEquals(model.getId(),      0);
		Assertions.assertEquals(model.getName(),    TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(),   TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
		Assertions.assertNotNull(model.getReplyList());
	}

}
