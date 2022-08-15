package com.example.demo.app.blog.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogFormTest {
	
	BlogForm form = null;
	
	@BeforeEach
	public void SetUp() {
		form = new BlogForm();
	}
	

	@Test
	void InitTest() {
		assertNull(form.getBlogTitle());
		assertNull(form.getTag());
		assertNull(form.getBlogContents());
		assertEquals(form.getThanksCnt(), 0);
	}
	
	@Test
	void SetTest() {
		form.setBlogTitle("テストタイトル");
		form.setBlogContents("テストコンテンツ");
		form.setTag("テストタグ");
		form.setThanksCnt(2);
		
		
		assertEquals(form.getBlogTitle(), "テストタイトル");
		assertEquals(form.getTag(), "テストタグ");
		assertEquals(form.getBlogContents(), "テストコンテンツ");
		assertEquals(form.getThanksCnt(), 2);
	}
	
	@AfterEach
	public void Release() {
		form = null;
	}

}
