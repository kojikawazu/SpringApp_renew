package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogReplyModelTest {
	
	BlogReplyModel model= null;
	
	@BeforeEach
	public void Init() {
		model= new BlogReplyModel();
	}

	@Test
	public void InitTest0() {
		// TODO コンストラクタのテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getCommentid(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getThanksCnt(), 0);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}
	
	@Test
	public void InitTest1() {
		// TODO コンストラクタのテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setCommentid(1);
		model.setName("テストネーム");
		model.setComment("テストコメント");
		model.setThanksCnt(1);
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getCommentid(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getThanksCnt(), 1);
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
	}

}
