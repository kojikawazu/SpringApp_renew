package com.example.demo.app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogTagModelTest {
	
	BlogTagModel model = null;
	
	@BeforeEach
	public void Init() {
		model = new BlogTagModel();
	}

	@Test
	public void InitTest0() {
		// TODO コンストラクタのテスト
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getTag(), "");
	}
	
	@Test
	public void InitTest1() {
		// TODO コンストラクタのテスト
		
		model.setId(1);
		model.setTag("テストタグ");
		
		Assertions.assertEquals(model.getId(), 1);
		Assertions.assertEquals(model.getTag(), "テストタグ");
	}

}
