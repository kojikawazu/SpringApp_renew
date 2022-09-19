package com.example.demo.app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.id.BlogTagId;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグモデルテスト
 * @author nanai
 *
 */
class BlogTagModelTest {
	
	BlogTagModel model = null;
	
	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		model = new BlogTagModel(
				new BlogTagId(0),
				new TagWord("")
				);
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	public void InitTest0() {
		
		Assertions.assertEquals(model.getId(), 0);
		Assertions.assertEquals(model.getTag(), "");
	}
	
	/**
	 * 値設定後テスト
	 */
	@Test
	public void InitTest1() {
		
		model.setId(1);
		model.setTag(TestConsts.TEST_TAG_NAME);
		
		Assertions.assertEquals(model.getId(),  1);
		Assertions.assertEquals(model.getTag(), TestConsts.TEST_TAG_NAME);
	}
	
	/**
	 * null渡しテスト
	 */
	@Test
	public void InitNull() {
		model = new BlogTagModel(
				null,
				null
				);
		
		Assertions.assertEquals(model.getId(),  0);
		Assertions.assertEquals(model.getTag(), "");
	}
	
	/**
	 * model渡しテスト
	 */
	@Test
	public void InitModel() {
		
		BlogTagModel test = new BlogTagModel(
				new BlogTagId(1),
				new TagWord(TestConsts.TEST_TAG_NAME)
				);
		
		model = new BlogTagModel(test);
		
		Assertions.assertEquals(model.getId(),  1);
		Assertions.assertEquals(model.getTag(), TestConsts.TEST_TAG_NAME);
	}
	
	/**
	 *  コンストラクタテスト2
	 */
	@Test
	public void InitTest2() {
		
		BlogTagModel test = new BlogTagModel(
				new TagWord("テストタグ")
				);
		
		model = new BlogTagModel(test);
		
		Assertions.assertEquals(model.getId(),  0);
		Assertions.assertEquals(model.getTag(), TestConsts.TEST_TAG_NAME);
	}

}
