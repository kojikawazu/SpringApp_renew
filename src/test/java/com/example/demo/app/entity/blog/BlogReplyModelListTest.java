package com.example.demo.app.entity.blog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ブロブ返信モデルリストテスト
 * @author nanai
 *
 */
class BlogReplyModelListTest {

	/** テスト対象 */
	BlogReplyModelList test = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		test = new BlogReplyModelList();
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		assertNotNull(test);
		assertNotNull(test.getList());
		assertEquals(0, test.getList().size());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		test.getList().clear();
		test = null;
	}
}
