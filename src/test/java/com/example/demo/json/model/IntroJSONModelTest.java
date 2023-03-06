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

		assertNotNull(test.getAttributeKeyModel());
		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceTitleModel());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getFuturePlanList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getOneLast());

		assertEquals(0, test.getAttributeKeyModel().getTitleKeyList().getList().size());
		assertEquals(0, test.getAttributeKeyModel().getBodyKeyList().getList().size());
		assertEquals(0, test.getNameList().getList().size());
		assertEquals(0, test.getTitleList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleKeyList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleBodyList().getList().size());
		assertEquals(0, test.getExperienceList().getList().size());
		assertEquals(0, test.getFuturePlanList().getList().size());
		assertEquals(0, test.getSkillLanguageList().getList().size());
		assertEquals(0, test.getSkillLibraryList().getList().size());
		assertEquals(0, test.getSkillFrameworkList().getList().size());
		assertEquals(0, test.getSkillOSList().getList().size());
		assertEquals(0, test.getSkillToolList().getList().size());
		assertEquals(0, test.getSkillOtherList().getList().size());
		assertEquals(0, test.getHobbyList().getList().size());

		assertEquals("", test.getIntro().getWord());
		assertEquals("", test.getUrl().getWord());
		assertEquals("", test.getOneLast().getWord());
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
				null,
				null,
				null);

		assertNotNull(test.getAttributeKeyModel());
		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceTitleModel());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getFuturePlanList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getOneLast());

		assertEquals(0, test.getAttributeKeyModel().getTitleKeyList().getList().size());
		assertEquals(0, test.getAttributeKeyModel().getBodyKeyList().getList().size());
		assertEquals(0, test.getNameList().getList().size());
		assertEquals(0, test.getTitleList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleKeyList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleBodyList().getList().size());
		assertEquals(0, test.getExperienceList().getList().size());
		assertEquals(0, test.getFuturePlanList().getList().size());
		assertEquals(0, test.getSkillLanguageList().getList().size());
		assertEquals(0, test.getSkillLibraryList().getList().size());
		assertEquals(0, test.getSkillFrameworkList().getList().size());
		assertEquals(0, test.getSkillOSList().getList().size());
		assertEquals(0, test.getSkillToolList().getList().size());
		assertEquals(0, test.getSkillOtherList().getList().size());
		assertEquals(0, test.getHobbyList().getList().size());

		assertEquals("", test.getIntro().getWord());
		assertEquals("", test.getUrl().getWord());
		assertEquals("", test.getOneLast().getWord());
	}
	
	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorObjTest() {
		test = new IntroJSONModel(
				new AttributeKeyModel(),
				new WordList(),
				new WordList(),
				new NormalWord(),
				new ExperienceTitleModel(),
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

		assertNotNull(test.getAttributeKeyModel());
		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceTitleModel());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getFuturePlanList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getOneLast());

		assertEquals(0, test.getAttributeKeyModel().getTitleKeyList().getList().size());
		assertEquals(0, test.getAttributeKeyModel().getBodyKeyList().getList().size());
		assertEquals(0, test.getNameList().getList().size());
		assertEquals(0, test.getTitleList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleKeyList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleBodyList().getList().size());
		assertEquals(0, test.getExperienceList().getList().size());
		assertEquals(0, test.getFuturePlanList().getList().size());
		assertEquals(0, test.getSkillLanguageList().getList().size());
		assertEquals(0, test.getSkillLibraryList().getList().size());
		assertEquals(0, test.getSkillFrameworkList().getList().size());
		assertEquals(0, test.getSkillOSList().getList().size());
		assertEquals(0, test.getSkillToolList().getList().size());
		assertEquals(0, test.getSkillOtherList().getList().size());
		assertEquals(0, test.getHobbyList().getList().size());

		assertEquals("", test.getIntro().getWord());
		assertEquals("", test.getUrl().getWord());
		assertEquals("", test.getOneLast().getWord());
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
		assertNotNull(test.getExperienceTitleModel());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getFuturePlanList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getOneLast());

		assertEquals(0, test.getNameList().getList().size());
		assertEquals(0, test.getTitleList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleKeyList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleBodyList().getList().size());
		assertEquals(0, test.getExperienceList().getList().size());
		assertEquals(0, test.getFuturePlanList().getList().size());
		assertEquals(0, test.getSkillLanguageList().getList().size());
		assertEquals(0, test.getSkillLibraryList().getList().size());
		assertEquals(0, test.getSkillFrameworkList().getList().size());
		assertEquals(0, test.getSkillOSList().getList().size());
		assertEquals(0, test.getSkillToolList().getList().size());
		assertEquals(0, test.getSkillOtherList().getList().size());
		assertEquals(0, test.getHobbyList().getList().size());

		assertEquals("", test.getIntro().getWord());
		assertEquals("", test.getUrl().getWord());
		assertEquals("", test.getOneLast().getWord());
	}

	/**
	 * コンストラクタテスト(マイオブジェクト)
	 */
	@Test
	void constractorMyObjTest() {
		IntroJSONModel model = new IntroJSONModel(
				new AttributeKeyModel(),
				new WordList(),
				new WordList(),
				new NormalWord(),
				new ExperienceTitleModel(),
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
		assertNotNull(test.getNameList());
		assertNotNull(test.getTitleList());
		assertNotNull(test.getIntro());
		assertNotNull(test.getExperienceTitleModel());
		assertNotNull(test.getExperienceList());
		assertNotNull(test.getFuturePlanList());
		assertNotNull(test.getSkillLanguageList());
		assertNotNull(test.getSkillLibraryList());
		assertNotNull(test.getSkillFrameworkList());
		assertNotNull(test.getSkillOSList());
		assertNotNull(test.getSkillToolList());
		assertNotNull(test.getSkillOtherList());
		assertNotNull(test.getUrl());
		assertNotNull(test.getHobbyList());
		assertNotNull(test.getOneLast());

		assertEquals(0, test.getNameList().getList().size());
		assertEquals(0, test.getTitleList().getList().size());
		assertEquals(0, test.getNameList().getList().size());
		assertEquals(0, test.getTitleList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleKeyList().getList().size());
		assertEquals(0, test.getExperienceTitleModel().getExperienceTitleBodyList().getList().size());
		assertEquals(0, test.getExperienceList().getList().size());
		assertEquals(0, test.getFuturePlanList().getList().size());
		assertEquals(0, test.getSkillLanguageList().getList().size());
		assertEquals(0, test.getSkillLibraryList().getList().size());
		assertEquals(0, test.getSkillFrameworkList().getList().size());
		assertEquals(0, test.getSkillOSList().getList().size());
		assertEquals(0, test.getSkillToolList().getList().size());
		assertEquals(0, test.getSkillOtherList().getList().size());
		assertEquals(0, test.getHobbyList().getList().size());

		assertEquals("", test.getIntro().getWord());
		assertEquals("", test.getUrl().getWord());
		assertEquals("", test.getOneLast().getWord());
	}

	@AfterEach
	void release() {
		test.getAttributeKeyModel().getTitleKeyList().getList().clear();
		test.getAttributeKeyModel().getBodyKeyList().getList().clear();
		test.getNameList().getList().clear();
		test.getTitleList().getList().clear();
		test.getExperienceTitleModel().getExperienceTitleKeyList().getList().clear();
		test.getExperienceTitleModel().getExperienceTitleBodyList().getList().clear();
		test.getExperienceList().getList().clear();
		test.getFuturePlanList().getList().clear();
		test.getSkillLanguageList().getList().clear();
		test.getSkillLibraryList().getList().clear();
		test.getSkillFrameworkList().getList().clear();
		test.getSkillOSList().getList().clear();
		test.getSkillToolList().getList().clear();
		test.getSkillOtherList().getList().clear();
		test.getHobbyList().getList().clear();
	}
}
