package com.example.demo.app.entity.inquiry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.common.id.inquiry.InquiryReplyId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;

/**
 * 問い合わせ返信モデルクラス
 * @author nanai
 *
 */
class InquiryReplyModelTest {
	
	InquiryReplyModel model= null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model= new InquiryReplyModel(
				new InquiryReplyId(0),
				new InquiryId(0),
				new NameWord(""),
				new EmailWord(""),
				new CommentWord(""),
				TestConsts.TEST_TIME_01);
	}

	/**
	 * 初期化テスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getInquiry_id(), 	0);
		Assertions.assertEquals(model.getName(), 		"");
		Assertions.assertEquals(model.getEmail(), 		"");
		Assertions.assertEquals(model.getComment(), 	"");
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * コンストラクタテスト(null渡し)
	 */
	@Test
	public void InitNull() {
		model = new InquiryReplyModel(null);
		
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getInquiry_id(), 	0);
		Assertions.assertEquals(model.getName(), 		"");
		Assertions.assertEquals(model.getEmail(), 		"");
		Assertions.assertEquals(model.getComment(), 	"");
		Assertions.assertNotNull(model.getCreated());
	}
	
	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void InitModel() {
		InquiryReplyModel test = new InquiryReplyModel(
				new InquiryReplyId(2),
				new InquiryId(2),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_02);
		
		model = new InquiryReplyModel(test);
		
		Assertions.assertEquals(model.getId(), 			2);
		Assertions.assertEquals(model.getInquiry_id(), 	2);
		Assertions.assertEquals(model.getName(), 		TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(), 		TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), 	TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_02.toString());
	}
	
	/**
	 * コンストラクタテスト2
	 */
	@Test
	public void InitTest2() {
		model = new InquiryReplyModel(
				new InquiryId(2),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new EmailWord(TestConsts.TEST_EMAIL_NAME),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_02);
		
		Assertions.assertEquals(model.getId(), 			0);
		Assertions.assertEquals(model.getInquiry_id(), 	2);
		Assertions.assertEquals(model.getName(), 		TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getEmail(), 		TestConsts.TEST_EMAIL_NAME);
		Assertions.assertEquals(model.getComment(), 	TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_02.toString());
	}
}
