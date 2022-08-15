package com.example.demo.app.blog.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogMainIdTest {
	
	BlogMainId form = null;
	
	@BeforeEach
	public void Setup() {
		form = new BlogMainId();
	}

	@Test
	public void InitTest() {
		assertEquals(form.getId(), 0);
		assertEquals(form.getThanksCnt(), 0);	
	}
	
	@Test
	public void SetTest() {
		form.setId(3);
		form.setThanksCnt(2);
		
		assertEquals(form.getId(), 3);
		assertEquals(form.getThanksCnt(), 2);	
	}
	
	@AfterEach
	public void Realease() {
		form = null;
	}

}
