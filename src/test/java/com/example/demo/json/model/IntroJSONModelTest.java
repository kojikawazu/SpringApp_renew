package com.example.demo.json.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.list.WordList;
import com.example.demo.common.word.NormalWord;
import com.example.demo.json.list.ExperienceList;

/**
 * JSONモデルテスト
 * @author nanai
 *
 */
class IntroJSONModelTest {

	/** 対象 */
	private IntroJSONModel test = null;

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		test = null;
	}

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		test = new IntroJSONModel();

		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getAfterList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getWord());

		assertEquals(test.getNameList().getList().size(), 0);
		assertEquals(test.getTitleList().getList().size(), 0);
		assertEquals(test.getExperienceList().getList().size(), 0);
		assertEquals(test.getAfterList().getList().size(), 0);
		assertEquals(test.getSkillLanguageList().getList().size(), 0);
		assertEquals(test.getSkillLibraryList().getList().size(), 0);
		assertEquals(test.getSkillFrameworkList().getList().size(), 0);
		assertEquals(test.getSkillOSList().getList().size(), 0);
		assertEquals(test.getSkillToolList().getList().size(), 0);
		assertEquals(test.getSkillOtherList().getList().size(), 0);
		assertEquals(test.getHobbyList().getList().size(), 0);

		assertEquals(test.getIntro().getWord(), "");
		assertEquals(test.getUrl().getWord(), "");
		assertEquals(test.getWord().getWord(), "");
	}

	/**
	 * コンストラクタテスト(null)
	 */
	@Test
	void constractorNullTest() {
		test = new IntroJSONModel(
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null);

		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getAfterList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getWord());

		assertEquals(test.getNameList().getList().size(), 0);
		assertEquals(test.getTitleList().getList().size(), 0);
		assertEquals(test.getExperienceList().getList().size(), 0);
		assertEquals(test.getAfterList().getList().size(), 0);
		assertEquals(test.getSkillLanguageList().getList().size(), 0);
		assertEquals(test.getSkillLibraryList().getList().size(), 0);
		assertEquals(test.getSkillFrameworkList().getList().size(), 0);
		assertEquals(test.getSkillOSList().getList().size(), 0);
		assertEquals(test.getSkillToolList().getList().size(), 0);
		assertEquals(test.getSkillOtherList().getList().size(), 0);
		assertEquals(test.getHobbyList().getList().size(), 0);

		assertEquals(test.getIntro().getWord(), "");
		assertEquals(test.getUrl().getWord(), "");
		assertEquals(test.getWord().getWord(), "");
	}
	
	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorObjTest() {
		test = new IntroJSONModel(
				new WordList(),
				new WordList(),
				new NormalWord(),
				new ExperienceList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new NormalWord(),
				new WordList(),
				new NormalWord());

		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getAfterList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getWord());

		assertEquals(test.getNameList().getList().size(), 0);
		assertEquals(test.getTitleList().getList().size(), 0);
		assertEquals(test.getExperienceList().getList().size(), 0);
		assertEquals(test.getAfterList().getList().size(), 0);
		assertEquals(test.getSkillLanguageList().getList().size(), 0);
		assertEquals(test.getSkillLibraryList().getList().size(), 0);
		assertEquals(test.getSkillFrameworkList().getList().size(), 0);
		assertEquals(test.getSkillOSList().getList().size(), 0);
		assertEquals(test.getSkillToolList().getList().size(), 0);
		assertEquals(test.getSkillOtherList().getList().size(), 0);
		assertEquals(test.getHobbyList().getList().size(), 0);

		assertEquals(test.getIntro().getWord(), "");
		assertEquals(test.getUrl().getWord(), "");
		assertEquals(test.getWord().getWord(), "");
	}

	/**
	 * コンストラクタテスト(マイオブジェクトnull)
	 */
	@Test
	void constractorMyNullTest() {
		test = new IntroJSONModel(
				null);

		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getAfterList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getWord());

		assertEquals(test.getNameList().getList().size(), 0);
		assertEquals(test.getTitleList().getList().size(), 0);
		assertEquals(test.getExperienceList().getList().size(), 0);
		assertEquals(test.getAfterList().getList().size(), 0);
		assertEquals(test.getSkillLanguageList().getList().size(), 0);
		assertEquals(test.getSkillLibraryList().getList().size(), 0);
		assertEquals(test.getSkillFrameworkList().getList().size(), 0);
		assertEquals(test.getSkillOSList().getList().size(), 0);
		assertEquals(test.getSkillToolList().getList().size(), 0);
		assertEquals(test.getSkillOtherList().getList().size(), 0);
		assertEquals(test.getHobbyList().getList().size(), 0);

		assertEquals(test.getIntro().getWord(), "");
		assertEquals(test.getUrl().getWord(), "");
		assertEquals(test.getWord().getWord(), "");
	}

	/**
	 * コンストラクタテスト(マイオブジェクト)
	 */
	@Test
	void constractorMyObjTest() {
		IntroJSONModel model = new IntroJSONModel(
				new WordList(),
				new WordList(),
				new NormalWord(),
				new ExperienceList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new WordList(),
				new NormalWord(),
				new WordList(),
				new NormalWord());

		test = new IntroJSONModel(
				model);

		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getAfterList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getWord());

		assertEquals(test.getNameList().getList().size(), 0);
		assertEquals(test.getTitleList().getList().size(), 0);
		assertEquals(test.getExperienceList().getList().size(), 0);
		assertEquals(test.getAfterList().getList().size(), 0);
		assertEquals(test.getSkillLanguageList().getList().size(), 0);
		assertEquals(test.getSkillLibraryList().getList().size(), 0);
		assertEquals(test.getSkillFrameworkList().getList().size(), 0);
		assertEquals(test.getSkillOSList().getList().size(), 0);
		assertEquals(test.getSkillToolList().getList().size(), 0);
		assertEquals(test.getSkillOtherList().getList().size(), 0);
		assertEquals(test.getHobbyList().getList().size(), 0);

		assertEquals(test.getIntro().getWord(), "");
		assertEquals(test.getUrl().getWord(), "");
		assertEquals(test.getWord().getWord(), "");
	}

	@AfterEach
	void release() {
		test.getNameList().getList().clear();
		test.getTitleList().getList().clear();
		test.getExperienceList().getList().clear();
		test.getAfterList().getList().clear();
		test.getSkillLanguageList().getList().clear();
		test.getSkillLibraryList().getList().clear();
		test.getSkillFrameworkList().getList().clear();
		test.getSkillOSList().getList().clear();
		test.getSkillToolList().getList().clear();
		test.getSkillOtherList().getList().clear();
		test.getHobbyList().getList().clear();
	}
}
