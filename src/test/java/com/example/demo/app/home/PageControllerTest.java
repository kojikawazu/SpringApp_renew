package com.example.demo.app.home;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ページコントローラーテスト
 * @author nanai
 *
 */
class PageControllerTest {

	/** テスト対象 */
	private PageController pageController = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		pageController = new PageController();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタテスト
	 */
	@Test
	void constractorTest() {
		assertNotNull(pageController.getCurrentPageIdx());
		assertNotNull(pageController.getListSize());
		assertNotNull(pageController.getPage1Size());
		assertNotNull(pageController.getPageName());
		assertNotNull(pageController.getPageNumberList());
		assertNotNull(pageController.getPageSize());

		assertEquals(0,  pageController.getCurrentPageIdx());
		assertEquals(0,  pageController.getListSize());
		assertEquals(0,  pageController.getPage1Size());
		assertEquals("", pageController.getPageName());
		assertEquals(0,  pageController.getPageNumberList().size());
		assertEquals(0,  pageController.getPageSize());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setPaging()テスト(正常系1)
	 */
	@Test
	void setPagingTest() {
		List<Integer> inputList  = new ArrayList<Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		int currentIdx = 1;
		int page1Idx   = 5;

		for (int idx = 0; idx < 20; idx++) {
			inputList.add(idx);
		}
		resultList = pageController.setPaging(inputList, currentIdx, page1Idx);

		assertNotNull(resultList);
		assertEquals(5, resultList.size());
		for (int idx = 0; idx < 5; idx++) {
			assertEquals(idx, resultList.get(idx));
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setPaging()テスト(正常系2)
	 */
	@Test
	void setPagingTest2() {
		List<Integer> inputList  = new ArrayList<Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		int currentIdx = 2;
		int page1Idx   = 5;

		for (int idx = 0; idx < 20; idx++) {
			inputList.add(idx);
		}
		resultList = pageController.setPaging(inputList, currentIdx, page1Idx);

		assertNotNull(resultList);
		assertEquals(5, resultList.size());
		for (int idx = 0; idx < 5; idx++) {
			assertEquals((idx+5), resultList.get(idx));
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setPaging()テスト(異常系)
	 */
	@Test
	void setPagingTest_Error() {
		List<Integer> resultList = new ArrayList<Integer>();
		int currentIdx = 1;
		int page1Idx   = 5;

		resultList = pageController.setPaging(null, currentIdx, page1Idx);

		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setPaging()テスト(異常系2)
	 */
	@Test
	void setPagingTest_Error2() {
		List<Integer> inputList  = new ArrayList<Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		int currentIdx = 1;
		int page1Idx   = 5;

		resultList = pageController.setPaging(inputList, currentIdx, page1Idx);

		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setPaging()テスト(異常系3)
	 */
	@Test
	void setPagingTest_Error3() {
		List<Integer> inputList  = new ArrayList<Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		int currentIdx = 0;
		int page1Idx   = 5;

		for (int idx = 0; idx < 20; idx++) {
			inputList.add(idx);
		}
		resultList = pageController.setPaging(inputList, currentIdx, page1Idx);

		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setPaging()テスト(異常系4)
	 */
	@Test
	void setPagingTest_Error4() {
		List<Integer> inputList  = new ArrayList<Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		int currentIdx = 5;
		int page1Idx   = 5;

		for (int idx = 0; idx < 20; idx++) {
			inputList.add(idx);
		}
		resultList = pageController.setPaging(inputList, currentIdx, page1Idx);

		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	void release() {
		pageController.getPageNumberList().clear();
		pageController = null;
	}
}
