package com.example.demo.app.servey;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SurveySatisFormTest {
	
	SurveySatisForm form = null;
	
	@BeforeEach
	void Setup() {
		form = new SurveySatisForm();
	}

	@Test
	void InitTest() {
		assertEquals(form.getId(), 0);
		assertEquals(form.getSatisfaction(), 0);
	}
	
	@Test
	void SetTest() {
		form.setId(2);
		form.setSatisfaction(3);
		
		assertEquals(form.getId(), 2);
		assertEquals(form.getSatisfaction(), 3);
	}
	
	@AfterEach
	void Release() {
		form = null;
	}

}
