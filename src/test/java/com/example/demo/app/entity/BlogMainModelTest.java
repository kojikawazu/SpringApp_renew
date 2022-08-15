package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogMainModelTest {
	
	BlogMainModel model = null;
	
	@BeforeEach
	public void Init() {
		model = new BlogMainModel();
	}

	@Test
	public void InitTest0() {
		// TODO 初期コンストラクタのテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getTitle(), "");
		Assertions.assertEquals(model.getTag(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertEquals(model.getUpdated().toString(), dateTime.toString());
		Assertions.assertNull(model.getReplyList());
	}
	
	@Test
	public void InitTest1() {
		// TODO 初期コンストラクタのテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setTitle("テストタイトル");
		model.setTag("テストタグ");
		model.setComment("テストコメント");
		model.setThanksCnt(1);
		model.setCreated(dateTime);
		model.setUpdated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTitle(), "テストタイトル");
		Assertions.assertEquals(model.getTag(), "テストタグ");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertEquals(model.getUpdated().toString(), dateTime.toString());
		Assertions.assertNull(model.getReplyList());
	}

}
