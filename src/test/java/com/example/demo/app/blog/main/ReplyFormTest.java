package com.example.demo.app.blog.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReplyFormTest {
	
	ReplyForm form = null;
	
	@BeforeEach
	void Setup() {
		form = new ReplyForm();
	}

	@Test
	void InitTest() {
		assertNull(form.getName());
		assertNull(form.getComment());
	}
	
	@Test
	void SetTest() {
		form.setName("テストネーム");
		form.setComment("テストコメント");
		
		assertEquals(form.getName(), "テストネーム");
		assertEquals(form.getComment(), "テストコメント");
	}
	
	@AfterEach
	void Release() {
		form = null;
	}

}
