package com.example.demo.app.entity.survey;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.common.id.survey.SurveyId;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * 調査モデルテスト
 * @author nanai
 *
 */
class SurveyModelTest {
	
	SurveyModel model = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new SurveyModel(
				new SurveyId(0),
				new NameWord(""),
				new NormalNumber(0),
				new NormalNumber(0),
				new NormalNumber(0),
				new NormalNumber(0),
				new CommentWord(""),
				TestConsts.TEST_TIME_01
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		Assertions.assertEquals(model.getId(), 				0);
		Assertions.assertEquals(model.getName(), 			"");
		Assertions.assertEquals(model.getAge(), 			0);
		Assertions.assertEquals(model.getProfession(), 		0);
		Assertions.assertEquals(model.getMen_or_female(), 	0);
		Assertions.assertEquals(model.getSatisfaction(), 	0);
		Assertions.assertEquals(model.getComment(), 		"");
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_01.toString());
	}
	
	/**
	 * コンストラクタテスト(null渡し)
	 */
	@Test
	public void InitNull() {
		model = new SurveyModel(
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
		Assertions.assertNotNull(model.getName());
		Assertions.assertNotNull(model.getAge());
		Assertions.assertNotNull(model.getProfession());
		Assertions.assertNotNull(model.getMen_or_female());
		Assertions.assertNotNull(model.getSatisfaction());
		Assertions.assertNotNull(model.getComment());
		Assertions.assertNotNull(model.getCreated());
	}
	
	/**
	 * コンストラクタテスト(model渡し)
	 */
	@Test
	public void InitModel() {
		SurveyModel test = new SurveyModel(
				new SurveyId(1),
				new NameWord(TestConsts.TEST_NAME_NAME),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				TestConsts.TEST_TIME_02
				);
		
		this.model = new SurveyModel(test);
		
		Assertions.assertEquals(model.getId(), 				1);
		Assertions.assertEquals(model.getName(), 			TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getAge(), 			1);
		Assertions.assertEquals(model.getProfession(), 		1);
		Assertions.assertEquals(model.getMen_or_female(), 	1);
		Assertions.assertEquals(model.getSatisfaction(), 	1);
		Assertions.assertEquals(model.getComment(), 		TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), TestConsts.TEST_TIME_02.toString());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest2() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model = new SurveyModel(
				new NameWord(TestConsts.TEST_NAME_NAME),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new CommentWord(TestConsts.TEST_COMMENT_NAME),
				dateTime
				);
		
		Assertions.assertEquals(model.getId(), 				0);
		Assertions.assertEquals(model.getName(), 			TestConsts.TEST_NAME_NAME);
		Assertions.assertEquals(model.getAge(), 			1);
		Assertions.assertEquals(model.getProfession(), 		1);
		Assertions.assertEquals(model.getMen_or_female(), 	1);
		Assertions.assertEquals(model.getSatisfaction(), 	1);
		Assertions.assertEquals(model.getComment(), 		TestConsts.TEST_COMMENT_NAME);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
}
