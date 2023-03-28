package com.example.demo.common.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * T型リストオブジェクトクラステスト
 * @author nanai
 *
 */
class ListObjectTest {

	/** テスト対象 */
	ListObject<String> test = null;

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		test = new ListObject<String>();
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

	/**
	 * コンストラクタテスト(初期化後オブジェクト追加)
	 */
	@Test
	void constractorTest_addObj() {
		test.getList().add("a");

		assertNotNull(test);
		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals("a", test.getList().get(0));
	}

	/**
	 * コンストラクタテスト(リスト)
	 */
	@Test
	void constractorTest_List() {
		List<String> list = new ArrayList<String>();
		list.add("b");
		test = new ListObject<String>(list);

		assertNotNull(test);
		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals("b", test.getList().get(0));
	}

	/**
	 * コンストラクタテスト(オブジェクト)
	 */
	@Test
	void constractorTest_Obj() {
		ListObject<String> obj 	= new ListObject<String>();
		List<String> list 		= new ArrayList<String>();
		list.add("c");
		obj.setList(list);
		test = new ListObject<String>(obj);

		assertNotNull(test);
		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals("c", test.getList().get(0));
	}

	/**
	 * setterテスト(リスト)
	 */
	@Test
	void setterTest_List() {
		List<String> list = new ArrayList<String>();
		list.add("d");
		test.setList(list);

		assertNotNull(test);
		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals("d", test.getList().get(0));
	}

	/**
	 * setterテスト(オブジェクト)
	 */
	@Test
	void setterTest_Obj() {
		ListObject<String> obj 	= new ListObject<String>();
		List<String> list 		= new ArrayList<String>();
		list.add("e");
		obj.setList(list);
		test.setList(obj);

		assertNotNull(test);
		assertNotNull(test.getList());
		assertEquals(1, test.getList().size());
		assertEquals("e", test.getList().get(0));
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
