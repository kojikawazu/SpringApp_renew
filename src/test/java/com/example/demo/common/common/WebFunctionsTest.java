package com.example.demo.common.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Web用関数群テスト
 * @author nanai
 *
 */
class WebFunctionsTest {

	/**
	 * Nullチェックのテスト
	 */
	@Test
	void isNullTest() {
		String data = "";
		boolean result = false;

		result = WebFunctions.isNull(data);
		assertEquals(false, result);
	}

	/**
	 * Nullチェックのテスト(Null)
	 */
	@Test
	void isNullTest_Null() {
		boolean result = false;

		result = WebFunctions.isNull(null);
		assertEquals(true, result);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * checkDiffMinutesテスト(正常系)
	 */
	@Test
	void checkDiffMinutesTest() {
		LocalDateTime test = LocalDateTime.now().minusSeconds(30);
		int minites = 1;
		boolean result = false;

		result = WebFunctions.checkDiffMinutes(test, minites);
		assertEquals(true, result);
	}

	/**
	 * checkDiffMinutesテスト(異常系)
	 */
	@Test
	void checkDiffMinutesTest_false() {
		LocalDateTime test = LocalDateTime.now().minusMinutes(2);
		int minites = 1;
		boolean result = false;

		result = WebFunctions.checkDiffMinutes(test, minites);
		assertEquals(false, result);
	}

	/**
	 * checkDiffMinutesテスト(引数エラー)
	 */
	@Test
	void checkDiffMinutesTest_ArgumentsError() {
		boolean result = false;

		result = WebFunctions.checkDiffMinutes(null, 1);
		assertEquals(false, result);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * checkDiffHourテスト(正常系)
	 */
	@Test
	void checkDiffHourTest() {
		LocalDateTime test = LocalDateTime.now().minusMinutes(30);
		int hours = 1;
		boolean result = false;

		result = WebFunctions.checkDiffHour(test, hours);
		assertEquals(true, result);
	}

	/**
	 * checkDiffHourテスト(異常系)
	 */
	@Test
	void checkDiffHourTest_false() {
		LocalDateTime test = LocalDateTime.now().minusHours(2);
		int hours = 1;
		boolean result = false;

		result = WebFunctions.checkDiffHour(test, hours);
		assertEquals(false, result);
	}

	/**
	 * checkDiffHourテスト(異常系 - 1日)
	 */
	@Test
	void checkDiffHourTest_Dayfalse() {
		LocalDateTime test = LocalDateTime.now().minusDays(1);
		int hours = 1;
		boolean result = false;

		result = WebFunctions.checkDiffHour(test, hours);
		assertEquals(false, result);
	}

	/**
	 * checkDiffHourテスト(引数エラー)
	 */
	@Test
	void checkDiffHourTest_ArgumentsError() {
		boolean result = false;

		result = WebFunctions.checkDiffHour(null, 1);
		assertEquals(false, result);
	}

	/** ------------------------------------------------------------------------------------------------------------- */
}
