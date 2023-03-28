package com.example.demo.common.log;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

/**
 * Common用ログ出力クラステスト
 * @author nanai
 *
 */
class CommonLogWriterTest {

	/** テストターゲット */
	CommonLogWriter test = CommonLogWriter.getInstance();

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 初期化テスト
	 */
	@Test
	void initTest() {
		assertNotNull(test);
		assertEquals(true, test.isSetting());
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * ログ名テスト
	 */
	@Test
	void logNameTest() {
		try {
			Field commonLogName = test.getClass().getDeclaredField("commonLogName");
			commonLogName.setAccessible(true);
			String expected = String.valueOf(commonLogName.get(test));

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
