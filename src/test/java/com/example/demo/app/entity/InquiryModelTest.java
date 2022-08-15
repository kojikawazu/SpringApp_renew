package com.example.demo.app.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InquiryModelTest {
	
	InquiryModel model= null;
	
	@BeforeEach
	public void Init() {
		model= new InquiryModel();
	}

	@Test
	public void InitTest0() {
		// TODO コンストラクタテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getName(), "");
		Assertions.assertEquals(model.getEmail(), "");
		Assertions.assertEquals(model.getComment(), "");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertNull(model.getReplyList());
	}
	
	@Test
	public void InitTest1() {
		// TODO コンストラクタテスト
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		
		model.setId(1);
		model.setName("テストネーム");
		model.setEmail("テストメールアドレス");
		model.setComment("テストコメント");
		model.setCreated(dateTime);
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getName(), "テストネーム");
		Assertions.assertEquals(model.getEmail(), "テストメールアドレス");
		Assertions.assertEquals(model.getComment(), "テストコメント");
		Assertions.assertEquals(model.getCreated().toString(), dateTime.toString());
		Assertions.assertNull(model.getReplyList());
		
	}

}
