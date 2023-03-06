package com.example.demo.json.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.list.WordList;

/**
 * 経験タイトルキーモデル(テスト)
 * @author nanai
 *
 */
class ExperienceTitleModelTest {

	/** テスト対象 */
	private ExperienceTitleModel test = null;

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		test = null;
	}

	/**
	 * コンストラクタテスト(初回)
	 */
	@Test
	void constractorTest() {
		this.test = new ExperienceTitleModel();

		assertNotNull(this.test);
		assertNotNull(this.test.getExperienceTitleKeyList());
		assertNotNull(this.test.getExperienceTitleBodyList());
		assertNotNull(this.test.getExperienceTitleKeyList().getList());
		assertNotNull(this.test.getExperienceTitleBodyList().getList());

		assertEquals(0, this.test.getExperienceTitleKeyList().getList().size());
		assertEquals(0, this.test.getExperienceTitleBodyList().getList().size());
	}

	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorObjTest() {
		this.test = new ExperienceTitleModel(
				new WordList(),
				new WordList()
				);

		assertNotNull(this.test);
		assertNotNull(this.test.getExperienceTitleKeyList());
		assertNotNull(this.test.getExperienceTitleBodyList());
		assertNotNull(this.test.getExperienceTitleKeyList().getList());
		assertNotNull(this.test.getExperienceTitleBodyList().getList());

		assertEquals(0, this.test.getExperienceTitleKeyList().getList().size());
		assertEquals(0, this.test.getExperienceTitleBodyList().getList().size());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorNullTest() {
		this.test = new ExperienceTitleModel(
				null,
				null
				);

		assertNotNull(this.test);
		assertNotNull(this.test.getExperienceTitleKeyList());
		assertNotNull(this.test.getExperienceTitleBodyList());
		assertNotNull(this.test.getExperienceTitleKeyList().getList());
		assertNotNull(this.test.getExperienceTitleBodyList().getList());

		assertEquals(0, this.test.getExperienceTitleKeyList().getList().size());
		assertEquals(0, this.test.getExperienceTitleBodyList().getList().size());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorModelTest() {
		ExperienceTitleModel model = new ExperienceTitleModel();
		model.getExperienceTitleKeyList().getList().add("a");
		model.getExperienceTitleBodyList().getList().add("b");

		this.test = new ExperienceTitleModel(
				null,
				null
				);
		this.test.setExperienceTitleModel(model);

		assertNotNull(this.test);
		assertNotNull(this.test.getExperienceTitleKeyList());
		assertNotNull(this.test.getExperienceTitleBodyList());
		assertNotNull(this.test.getExperienceTitleKeyList().getList());
		assertNotNull(this.test.getExperienceTitleBodyList().getList());

		assertEquals(1, this.test.getExperienceTitleKeyList().getList().size());
		assertEquals(1, this.test.getExperienceTitleBodyList().getList().size());
		assertEquals("a", this.test.getExperienceTitleKeyList().getList().get(0));
		assertEquals("b", this.test.getExperienceTitleBodyList().getList().get(0));
	}
	
	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		this.test.getExperienceTitleKeyList().getList().clear();
		this.test.getExperienceTitleBodyList().getList().clear();
	}
}
