package com.example.demo.app.servey;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SurveyChangeFormTest {
	
	SurveyChangeForm form = null;
	
	@BeforeEach
	void Setup() {
		form = new SurveyChangeForm();
	}

	@Test
	void InitTest() {
		assertNull(form.getAgeName());
		assertNull(form.getName());
		assertNull(form.getProfName());
		assertNull(form.getSxName());
		
		assertEquals(form.getAllNum(), 0);
		assertEquals(form.getAnswerCnt(), 0);
		assertEquals(form.getId(), 0);
		assertEquals(form.getSatis1(), 0);
		assertEquals(form.getSatis2(), 0);
		assertEquals(form.getSatis3(), 0);
		assertEquals(form.getSatis4(), 0);
		assertEquals(form.getSatis5(), 0);
	}
	
	@Test
	void SetTest() {
		
		form.setAgeName("10");
		form.setName("太郎");
		form.setProfName("会社員");
		form.setSxName("男");
		
		form.setAllNum(1);
		form.setAnswerCnt(1);
		form.setId(100);
		form.setSatis1(3);
		form.setSatis2(3);
		form.setSatis3(3);
		form.setSatis4(3);
		form.setSatis5(3);
		
		
		assertEquals(form.getAgeName(), "10");
		assertEquals(form.getName(), "太郎");
		assertEquals(form.getProfName(), "会社員");
		assertEquals(form.getSxName(), "男");
		
		assertEquals(form.getAllNum(), 1);
		assertEquals(form.getAnswerCnt(), 1);
		assertEquals(form.getId(), 100);
		assertEquals(form.getSatis1(), 3);
		assertEquals(form.getSatis2(), 3);
		assertEquals(form.getSatis3(), 3);
		assertEquals(form.getSatis4(), 3);
		assertEquals(form.getSatis5(), 3);
	}
	
	@AfterEach
	void Release() {
		form = null;
	}

}
