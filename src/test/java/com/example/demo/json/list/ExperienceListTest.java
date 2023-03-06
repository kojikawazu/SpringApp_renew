package com.example.demo.json.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.word.NormalWord;
import com.example.demo.json.model.ExperienceModel;

/**
 * 経験リストクラス(テスト)
 * @author nanai
 *
 */
class ExperienceListTest {

	/** テスト文字列 */
	private static final String TEST_WORD = "経験テスト";

	/** 対象 */
	private ExperienceList test = null;

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
		test = new ExperienceList();

		assertNotNull(test.getList());
		assertEquals(0, test.getList().size());
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorNullTest() {
		test = new ExperienceList(null);

		assertNotNull(test.getList());
		assertEquals(0, test.getList().size());
	}

	/**
	 * コンストラクタテスト(引数設定)(準備)
	 */
	private void preConstractorObjectTest() {
		List<ExperienceModel> list = new ArrayList<ExperienceModel>();
		ExperienceModel model      = new ExperienceModel();
		model.setExperience(new NormalWord(TEST_WORD));
		list.add(model);
		test = new ExperienceList(list);
	}

	/**
	 * コンストラクタテスト(引数設定)
	 */
	@Test
	void constractorObjectTest() {
		preConstractorObjectTest();

		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals(TEST_WORD, test.getList().get(0).getExperience().getWord());
	}

	/**
	 * setterテスト(準備)
	 */
	private void preSetterTest() {
		test = new ExperienceList(null);
	}

	/**
	 * setterテスト
	 */
	@Test
	void setterTest() {
		List<ExperienceModel> list = new ArrayList<ExperienceModel>();
		ExperienceModel model      = new ExperienceModel();
		model.setExperience(new NormalWord(TEST_WORD));
		list.add(model);

		preSetterTest();
		this.test.setList(list);

		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals(TEST_WORD, test.getList().get(0).getExperience().getWord());
	}

	/**
	 * 後処理
	 */
	@AfterEach
	public void release() {
		this.test.getList().clear();
	}
}
