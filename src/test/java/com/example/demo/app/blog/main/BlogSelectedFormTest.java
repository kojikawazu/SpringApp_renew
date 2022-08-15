package com.example.demo.app.blog.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogSelectedFormTest {
	
	BlogSelectedForm form = null;
	
	@BeforeEach
	public void SetUp() {
		form = new BlogSelectedForm();
	}

	@Test
	void InitTest() {
		assertEquals(form.getSelectIdx(), 0);
	}
	
	@Test
	void SetTest() {
		form.setSelectIdx(2);
		assertEquals(form.getSelectIdx(), 2);
	}
	
	@AfterEach
	public void Release() {
		form = null;
	}

}
