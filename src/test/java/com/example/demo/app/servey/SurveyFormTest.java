package com.example.demo.app.servey;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SurveyFormTest {
	
	SurveyForm form = null;
	
	@BeforeEach
	void SetUp() {
		form = new SurveyForm();
	}

	@Test
	void InitTest() {
		assertNull(form.getComment());
		assertNull(form.getName());
		
		assertEquals(form.getAge(), 0);
		assertEquals(form.getMen_or_female(), 0);
		assertEquals(form.getProfession(), 0);
		assertEquals(form.getSatisfaction(), 0);
	}
	
	@Test
	void SetTest(){
		form.setComment("テストコメント");
		form.setName("テストネーム");
		
		form.setAge(10);
		form.setMen_or_female(1);
		form.setProfession(1);
		form.setSatisfaction(1);
		
		assertEquals(form.getComment(), "テストコメント");
		assertEquals(form.getName(), "テストネーム");
		
		assertEquals(form.getAge(), 10);
		assertEquals(form.getMen_or_female(), 1);
		assertEquals(form.getProfession(), 1);
		assertEquals(form.getSatisfaction(), 1);
	}
	
	
	@AfterEach
	void Release() {
		form = null;
	}

}
