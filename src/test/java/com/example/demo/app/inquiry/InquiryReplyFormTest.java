package com.example.demo.app.inquiry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.app.inquiry.form.InquiryReplyForm;

class InquiryReplyFormTest {
	
	InquiryReplyForm form = null;
	
	@BeforeEach
	void Setup() {
		form = new InquiryReplyForm();
	}

	@Test
	void InitTest() {
		assertNull(form.getName());
		assertNull(form.getEmail());
		assertNull(form.getComment());
	}
	
	@Test
	void SetTest() {
		form.setName("テストネーム");
		form.setEmail("テストメールアドレス");
		form.setComment("テストコメント");
		
		assertEquals(form.getName(), "テストネーム");
		assertEquals(form.getEmail(), "テストメールアドレス");
		assertEquals(form.getComment(), "テストコメント");
	}
	
	
	@AfterEach
	void Release() {
		form = null;
	}

}
