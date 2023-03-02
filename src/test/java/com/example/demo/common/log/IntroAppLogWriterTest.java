package com.example.demo.common.log;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

/**
 * IntroApp用ファイル出力クラス(テスト)
 * @author nanai
 *
 */
class IntroAppLogWriterTest {

	/** テストターゲット */
	IntroAppLogWriter test = IntroAppLogWriter.getInstance();

	/**
	 * 初期化テスト
	 */
	@Test
	void initTest() {
		assertNotNull(test);
		assertEquals(true, test.isSetting());
	}

	/**
	 * ログ名テスト
	 */
	@Test
	void logNameTest() {
		try {
			Field introAppLogName = test.getClass().getDeclaredField("introAppLogName");
			introAppLogName.setAccessible(true);
			String expected = String.valueOf(introAppLogName.get(test));

			Field currentLogName = test.getClass().getSuperclass().getDeclaredField("logName");
			currentLogName.setAccessible(true);
			String resultString = String.valueOf(currentLogName.get(test));
			assertEquals(expected, resultString);
		} catch (SecurityException | 
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ログレベルテスト
	 */
	@Test
	void logLevelTest() {
		try {
			Field fld = test.getClass().getSuperclass().getDeclaredField("logLevel");
			fld.setAccessible(true);
			Level level = (Level)(fld.get(test));
			assertEquals(Level.ALL, level);
		} catch (SecurityException | 
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
