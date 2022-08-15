package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SurveyModelTest {
	
	SurveyModel model = null;
	
	@BeforeEach
	public void Init() {
		model = new SurveyModel();
	}

	@Test
	public void InitTest0() {
		// TODO コンストラクタテスト
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
	
	@Test
	public void InitTest1() {
		// TODO コンストラクタテスト
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


}
