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

		assertEquals(test.getExperience().getWord(), "");
		assertEquals(test.getMemberSum().getNumber(), 0);
		assertEquals(test.getPosition().getWord(), "");
		assertEquals(test.getOverview().getWord(), "");
		assertEquals(test.getPersonChargeList().getList().size(), 0);
		assertEquals(test.getWorkPoint().getWord(), "");
		assertEquals(test.getTechList().getList().size(), 0);
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

		assertEquals(test.getExperience().getWord(), "");
		assertEquals(test.getMemberSum().getNumber(), 0);
		assertEquals(test.getPosition().getWord(), "");
		assertEquals(test.getOverview().getWord(), "");
		assertEquals(test.getPersonChargeList().getList().size(), 0);
		assertEquals(test.getWorkPoint().getWord(), "");
		assertEquals(test.getTechList().getList().size(), 0);
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

		assertEquals(test.getExperience().getWord(), "");
		assertEquals(test.getMemberSum().getNumber(), 0);
		assertEquals(test.getPosition().getWord(), "");
		assertEquals(test.getOverview().getWord(), "");
		assertEquals(test.getPersonChargeList().getList().size(), 0);
		assertEquals(test.getWorkPoint().getWord(), "");
		assertEquals(test.getTechList().getList().size(), 0);
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
