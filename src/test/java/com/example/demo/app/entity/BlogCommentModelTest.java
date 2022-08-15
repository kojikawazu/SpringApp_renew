package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogCommentModelTest {
	
	BlogCommentModel model = null;
	
	@BeforeEach
	public void Init() {
		model = new BlogCommentModel();
	}
	
	@Test
	public void InitTest0() {
		// TODO 初期コンストラクタのテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getBlogid(), 0);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}

	@Test
	public void InitTest1() {
		// TODO 値設定後のテスト
		LocalDateTime dateTime = LocalDateTime.now();
		
		model.setId(1);
		model.setName("テスト");
		model.setThanksCnt(0);
		model.setComment("テストコメント");
		model.setBlogid(1);
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テスト");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getBlogid(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
}
