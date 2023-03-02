package com.example.demo.common.log;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * ログ出力用クラス(テスト)
 * @author nanai
 *
 */
class LogWriterTest {

	/** テスト対象 */
	private LogWriter test = null;

	/** モック対象 */
	@Mock
	private Logger logger = null;

	/**
	 * 初期化
	 */
	@BeforeEach
	void init() {
		logger = mock(Logger.class);
		test = new LogWriter();
	}

	/**
	 * Mockの準備
	 */
	private void preMock() {
		// testにlogger(Mock)割り当て
		try {
			Field fld = test.getClass().getDeclaredField("logger");
			fld.setAccessible(true);
			fld.set(test, logger);
		} catch (NoSuchFieldException | 
				SecurityException |
				IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初期化テスト
	 */
	@Test
	void initTest() {
		test.init();
		assertEquals(true, test.isSetting());
	}

	/**
	 * 初期化なしテスト
	 */
	@Test
	void noInitTest() {
		assertEquals(false, test.isSetting());
	}

	/**
	 * ログ名設定テスト(protected)
	 */
	@Test
	void setLogNameTest() {
		try {
			// setLogNameの実行
			Method method = test.getClass().getDeclaredMethod("setLogName", String.class);
			method.setAccessible(true);
			method.invoke(test, "test");

			// logNameのテスト
			Field fld = test.getClass().getDeclaredField("logName");
			fld.setAccessible(true);
			String resultString = String.valueOf(fld.get(test));
			assertEquals("test", resultString);
		} catch (NoSuchMethodException | 
				SecurityException | 
				InvocationTargetException |
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ログレベル設定テスト(protected)
	 */
	@Test
	void setLogLevelTest() {
		preMock();
		try {
			// setLogLevelの実行
			Method method = test.getClass().getDeclaredMethod("setLogLevel", Level.class);
			method.setAccessible(true);
			method.invoke(test, Level.INFO);

			// LogLevelのテスト
			Field fld = test.getClass().getDeclaredField("logLevel");
			fld.setAccessible(true);
			Level level = (Level)(fld.get(test));
			assertEquals(Level.INFO, level);
		} catch (NoSuchMethodException | 
				SecurityException | 
				InvocationTargetException |
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ログレベル設定テスト(warning)
	 */
	@Test
	void setLogLevelWarningTest() {
		preMock();
		try {
			test.setLogLevelWarning();

			// LogLevelのテスト
			Field fld = test.getClass().getDeclaredField("logLevel");
			fld.setAccessible(true);
			Level level = (Level)(fld.get(test));
			assertEquals(Level.WARNING, level);
		} catch (SecurityException | 
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ログレベル設定テスト(default)
	 */
	@Test
	void setLogLevelAllTest() {
		preMock();
		try {
			test.setLogLevelWarning();
			test.setLogLevelAll();

			// LogLevelのテスト
			Field fld = test.getClass().getDeclaredField("logLevel");
			fld.setAccessible(true);
			Level level = (Level)(fld.get(test));
			assertEquals(Level.ALL, level);
		} catch (SecurityException | 
				IllegalAccessException |
				NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 開始ログテスト
	 */
	@Test
	void startLogTest() {
		preMock();
		doNothing().when(logger).info(anyString());

		test.start("start");
		verify(logger, times(1)).info(anyString());
	}

	/**
	 * 成功ログテスト
	 */
	@Test
	void successedLogTest() {
		preMock();
		doNothing().when(logger).info(anyString());

		test.successed("OK");
		verify(logger, times(1)).info(anyString());
	}

	/**
	 * 通常ログテスト
	 */
	@Test
	void infoLogTest() {
		preMock();
		doNothing().when(logger).info(anyString());

		test.info("info");
		verify(logger, times(1)).info(anyString());
	}

	/**
	 * warningログテスト
	 */
	@Test
	void warningLogTest() {
		preMock();
		doNothing().when(logger).warning(anyString());

		test.warning("warning");
		verify(logger, times(1)).warning(anyString());
	}

	/**
	 * errorログテスト
	 */
	@Test
	void errorLogTest() {
		preMock();
		doNothing().when(logger).severe(anyString());

		test.error("error");
		verify(logger, times(1)).severe(anyString());
	}
}
