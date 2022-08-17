package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.id.SurveyId;
import com.example.demo.common.number.NormalNumber;
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
				new NameWord(""),
				LocalDateTime.of(2000, 01, 01, 00, 00, 00)
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getAge(), 0);
		Assertions.assertEquals(model.getProfession(), 0);
		Assertions.assertEquals(model.getMen_or_female(), 0);
		Assertions.assertEquals(model.getSatisfaction(), 0);
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * 値設定後テスト
	 */
	@Test
	public void InitTest1() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setName("テストネーム");
		model.setAge(10);
		model.setProfession(2);
		model.setMen_or_female(1);
		model.setSatisfaction(1);
		model.setComment("テストコメント");
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getAge(), 10);
		Assertions.assertEquals(model.getProfession(), 2);
		Assertions.assertEquals(model.getMen_or_female(), 1);
		Assertions.assertEquals(model.getSatisfaction(), 1);
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
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
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		SurveyModel test = new SurveyModel(
				new SurveyId(1),
				new NameWord("テスト"),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new NameWord("コメント"),
				dateTime
				);
		
		this.model = new SurveyModel(test);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getAge(), 1);
		Assertions.assertEquals(model.getProfession(), 1);
		Assertions.assertEquals(model.getMen_or_female(), 1);
		Assertions.assertEquals(model.getSatisfaction(), 1);
		Assertions.assertEquals(model.getComment(), "コメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest2() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model = new SurveyModel(
				new NameWord("テスト"),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new NormalNumber(1),
				new NameWord("コメント"),
				dateTime
				);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getAge(), 1);
		Assertions.assertEquals(model.getProfession(), 1);
		Assertions.assertEquals(model.getMen_or_female(), 1);
		Assertions.assertEquals(model.getSatisfaction(), 1);
		Assertions.assertEquals(model.getComment(), "コメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
}
