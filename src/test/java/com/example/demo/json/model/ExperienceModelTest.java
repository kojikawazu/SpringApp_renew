package com.example.demo.json.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.common.list.WordList;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NormalWord;

/**
 * 経験モデルテスト
 * @author nanai
 *
 */
class ExperienceModelTest {

	/** 対象 */
	private ExperienceModel test = null;

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
		test = new ExperienceModel();

		assertNotNull(test.getExperience());
		assertNotNull(test.getStartTime());
		assertNotNull(test.getEndTime());
		assertNotNull(test.getMemberSum());
		assertNotNull(test.getPosition());
		assertNotNull(test.getOverview());
		assertNotNull(test.getPersonChargeList());
		assertNotNull(test.getWorkPoint());
		assertNotNull(test.getTechList());

		assertEquals("", test.getExperience().getWord());
		assertEquals(0, test.getMemberSum().getNumber());
		assertEquals("", test.getPosition().getWord());
		assertEquals("", test.getOverview().getWord());
		assertEquals(0, test.getPersonChargeList().getList().size());
		assertEquals("", test.getWorkPoint().getWord());
		assertEquals(0, test.getTechList().getList().size());
	}

	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorObjTest() {
		test = new ExperienceModel(
				new NormalWord(),
				LocalDateTime.now(),
				LocalDateTime.now(),
				new NormalNumber(),
				new NormalWord(),
				new NormalWord(),
				new WordList(),
				new NormalWord(),
				new WordList());

		assertNotNull(test.getExperience());
		assertNotNull(test.getStartTime());
		assertNotNull(test.getEndTime());
		assertNotNull(test.getMemberSum());
		assertNotNull(test.getPosition());
		assertNotNull(test.getOverview());
		assertNotNull(test.getPersonChargeList());
		assertNotNull(test.getWorkPoint());
		assertNotNull(test.getTechList());

		assertEquals("", test.getExperience().getWord());
		assertEquals(0, test.getMemberSum().getNumber());
		assertEquals("", test.getPosition().getWord());
		assertEquals("", test.getOverview().getWord());
		assertEquals(0, test.getPersonChargeList().getList().size());
		assertEquals("", test.getWorkPoint().getWord());
		assertEquals(0, test.getTechList().getList().size());
	}
	
	@Test
	void constractorNullTest() {
		test = new ExperienceModel(
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null);

		assertNotNull(test.getExperience());
		assertNotNull(test.getStartTime());
		assertNotNull(test.getEndTime());
		assertNotNull(test.getMemberSum());
		assertNotNull(test.getPosition());
		assertNotNull(test.getOverview());
		assertNotNull(test.getPersonChargeList());
		assertNotNull(test.getWorkPoint());
		assertNotNull(test.getTechList());

		assertEquals("", test.getExperience().getWord());
		assertEquals(0, test.getMemberSum().getNumber());
		assertEquals("", test.getPosition().getWord());
		assertEquals("", test.getOverview().getWord());
		assertEquals(0, test.getPersonChargeList().getList().size());
		assertEquals("", test.getWorkPoint().getWord());
		assertEquals(0, test.getTechList().getList().size());
	}

	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		test.getPersonChargeList().getList().clear();
		test.getTechList().getList().clear();
	}
}
