package com.example.demo.json.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.list.WordList;
import com.example.demo.common.word.NormalWord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json共通関数群クラス(テスト)
 * @author nanai
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsonCommonFunctionsTest {

	/** intro.jsonファイルのフルパス */
	private static final String FILE_FULL_PATH = "src/main/resources/static/json/intro.json";

	/** headerセクション名 */
	private static final String TEST_HEADER_SECTION = "header";

	/** nameセクション名 */
	private static final String TEST_NAME_SECTION = "name";

	/** introセクション名 */
	private static final String TEST_INTRO_SECTION = "intro";

	/** workPointセクション名 */
	private static final String TEST_WORKPOINT_SECTION = "workPoint";

	/** nowのキーワード名 */
	private static final String TEST_NOW_KEYWORD = "now";

	/** 現在のLocalDateTime型 */
	private LocalDateTime standardDateTime	= LocalDateTime.now();

	/** intro.json読み込みオブジェクト */
	private JsonNode      rootJsonNode		= null;

	@BeforeAll
	void init() {
		ObjectMapper mapper   = new ObjectMapper();
		try {
			rootJsonNode = mapper.readTree(Paths.get(FILE_FULL_PATH).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	void initEach() {
		
	}

	// [test] changeLocalDateTime(String timeString)
	// ------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 引数テスト(ブランク)
	 */
	@Test
	void changeLocalDateTimeBlankTest() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime("");

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(null)
	 */
	@Test
	void changeLocalDateTimeNullTest() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime(null);

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(文字列)
	 */
	@Test
	void changeLocalDateTimeError1Test() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime("a");

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(フォーマット異常)
	 */
	@Test
	void changeLocalDateTimeError2Test() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime("202010");

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(フォーマット異常)
	 */
	@Test
	void changeLocalDateTimeError3Test() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime("2020/0");

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(フォーマット異常)
	 */
	@Test
	void changeLocalDateTimeError4Test() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime("2020/00");

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(フォーマット異常)
	 */
	@Test
	void changeLocalDateTimeError5Test() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime("202a/10");

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(now)
	 */
	@Test
	void changeLocalDateTimeNowTest() {
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime(TEST_NOW_KEYWORD);

		assertNotNull(test);
		assertEquals(standardDateTime.getYear(), test.getYear());
		assertEquals(standardDateTime.getMonthValue(), test.getMonthValue());
		assertEquals(standardDateTime.getDayOfMonth(), test.getDayOfMonth());
	}

	/**
	 * 引数テスト(正常系テスト)
	 */
	@Test
	void changeLocalDateTimeSuccessedTest() {
		String testData = TestConsts.TEST_TIME_01.getYear() + "/" + TestConsts.TEST_TIME_01.getMonth().getValue();
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime(testData);

		assertNotNull(test);
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.toString());
	}

	/**
	 * 引数テスト(正常系テスト2)
	 */
	@Test
	void changeLocalDateTimeSuccessed2Test() {
		String testData = TestConsts.TEST_TIME_01.getYear() + "/" + "0" + TestConsts.TEST_TIME_01.getMonth().getValue();
		LocalDateTime test = JsonCommonFunctions.changeLocalDateTime(testData);

		assertNotNull(test);
		assertEquals(TestConsts.TEST_TIME_01.toString(), test.toString());
	}

	// [test] readData(JsonNode rootJsonNode, String keyword)
	// ------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 引数テスト(null)
	 */
	@Test
	void readDataNullTest() {
		NormalWord test = JsonCommonFunctions.readData(null, null);

		assertNotNull(test);
		assertEquals("", test.getWord());
	}

	/**
	 * 引数テスト(null2)
	 */
	@Test
	void readDataNull2Test() {
		NormalWord test = JsonCommonFunctions.readData(rootJsonNode, null);

		assertNotNull(test);
		assertEquals("", test.getWord());
	}

	/**
	 * 引数テスト(例外発生)
	 */
	@Test
	void readDataThrowTest() {
		NormalWord test = JsonCommonFunctions.readData(rootJsonNode, "intr");

		assertNotNull(test);
		assertEquals("", test.getWord());
	}

	/**
	 * 引数テスト(正常系テスト)
	 */
	@Test
	void readDataTest() {
		NormalWord test = JsonCommonFunctions.readData(rootJsonNode, TEST_INTRO_SECTION);

		assertNotNull(test);
		assertNotEquals("", test.getWord());
	}

	// [test] readDataLongString(JsonNode rootJsonNode, String keyword)
	// ------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 引数テスト(null)
	 */
	@Test
	void readDataLongStringNullTest() {
		NormalWord test = JsonCommonFunctions.readDataLongString(null, null);

		assertNotNull(test);
		assertEquals("", test.getWord());
	}

	/**
	 * 引数テスト(null2)
	 */
	@Test
	void readDataLongStringNull2Test() {
		NormalWord test = JsonCommonFunctions.readDataLongString(rootJsonNode, null);

		assertNotNull(test);
		assertEquals("", test.getWord());
	}

	/**
	 * 引数テスト(例外発生)
	 */
	@Test
	void readDataLongStringThrowTest() {
		NormalWord test = JsonCommonFunctions.readDataLongString(rootJsonNode, "workPoin");

		assertNotNull(test);
		assertEquals("", test.getWord());
	}

	/**
	 * 引数テスト(正常系テスト)
	 */
	@Test
	void readDataLongStringTest() {
		JsonNode node = rootJsonNode.get("experience");
		JsonNode node2 = node.get(0);
		NormalWord test = JsonCommonFunctions.readDataLongString(node2, TEST_WORKPOINT_SECTION);

		assertNotNull(test);
		assertNotEquals("", test.getWord());
	}

	// [test] readData_returnList(JsonNode rootJsonNode, String keyword)
	// ------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 引数テスト(null)
	 */
	@Test
	void readData_returnListNullTest() {
		WordList testList = JsonCommonFunctions.readData_returnList(null, null);

		assertNotNull(testList);
		assertEquals(0, testList.getList().size());
	}

	/**
	 * 引数テスト(null2)
	 */
	@Test
	void readData_returnListNull2Test() {
		WordList testList = JsonCommonFunctions.readData_returnList(rootJsonNode, null);

		assertNotNull(testList);
		assertEquals(0, testList.getList().size());
	}

	/**
	 * 引数テスト(例外発生)
	 */
	@Test
	void readData_returnListThrowTest() {
		WordList testList = JsonCommonFunctions.readData_returnList(rootJsonNode, "nam");

		assertNotNull(testList);
		assertEquals(0, testList.getList().size());
	}

	/**
	 * 引数テスト(正常系テスト)
	 */
	@Test
	void readData_returnListTest() {
		WordList testList = JsonCommonFunctions.readData_returnList(rootJsonNode, TEST_NAME_SECTION);

		assertNotNull(testList);
		assertEquals(2, testList.getList().size());

		testList.getList().clear();
	}

	// [test] readData_returnList(JsonNode rootJsonNode, WordList headerList, int headerIndex)
	// ------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 引数テスト(null)
	 */
	@Test
	void readData_returnList2NullTest() {
		WordList testList = JsonCommonFunctions.readData_returnList(null, null, 0);

		assertNotNull(testList);
		assertEquals(0, testList.getList().size());
	}

	/**
	 * 引数テスト(null2)
	 */
	@Test
	void readData_returnList2Null2Test() {
		WordList testList = JsonCommonFunctions.readData_returnList(rootJsonNode, null, 0);

		assertNotNull(testList);
		assertEquals(0, testList.getList().size());
	}

	/**
	 * 引数テスト(例外発生)
	 */
	@Test
	void readData_returnList2ThrowTest() {
		WordList 		headerList			= JsonCommonFunctions.readData_returnList(rootJsonNode, TEST_HEADER_SECTION);
		WordList 		testList			= JsonCommonFunctions.readData_returnList(rootJsonNode, headerList, 99);

		assertNotNull(testList);
		assertEquals(0, testList.getList().size());
	}

	/**
	 * 引数テスト(正常系テスト)
	 */
	@Test
	void readData_returnList2Test() {
		WordList 		headerList			= JsonCommonFunctions.readData_returnList(rootJsonNode, TEST_HEADER_SECTION);
		WordList 		testList			= JsonCommonFunctions.readData_returnList(rootJsonNode, headerList, 0);

		assertNotNull(testList);
		assertNotEquals(0, testList.getList().size());

		headerList.getList().clear();
		testList.getList().clear();
	}
}
