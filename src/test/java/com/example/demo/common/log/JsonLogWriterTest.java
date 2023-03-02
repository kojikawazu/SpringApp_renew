package com.example.demo.common.log;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

/**
 * Json用ログ出力クラステスト
 * @author nanai
 *
 */
class JsonLogWriterTest {

	/** テストターゲット */
	JsonLogWriter test = JsonLogWriter.getInstance();

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
			Field jsonLogName = test.getClass().getDeclaredField("jsonLogName");
			jsonLogName.setAccessible(true);
			String expected = String.valueOf(jsonLogName.get(test));

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
			assertEquals(Level.WARNING, level);
		} catch (SecurityException | 
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
