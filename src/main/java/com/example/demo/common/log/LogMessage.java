package com.example.demo.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ログメッセージ
 * @author nanai
 *
 */
@Component
public class LogMessage {

	/**
	 * ロガークラス
	 * {@link Logger}
	 */
	private final Logger logger;

	/** ------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public LogMessage() {
		StackTraceElement ste	= Thread.currentThread().getStackTrace()[2];
		this.logger				= LoggerFactory.getLogger(ste.getClassName());
	}

	/** ------------------------------------------------------------------- */

	/**
	 * 開始出力
	 */
	public void start() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String 		cls		= ste.getClassName();
		String[]	clsList	= cls.split("\\.");
		String		clsName = clsList[clsList.length-1];
		String 		mtd		= ste.getMethodName();

		this.logger.info(clsName + "#" + mtd + "() : " + "start...");
	}

	/**
	 * 終了出力
	 */
	public void end() {
		StackTraceElement ste 	= Thread.currentThread().getStackTrace()[2];
		String 		cls			= ste.getClassName();
		String[]	clsList		= cls.split("\\.");
		String		clsName 	= clsList[clsList.length-1];
		String 		mtd			= ste.getMethodName();

		this.logger.info(clsName + "#" + mtd + "() : " + "done.");
	}

	/**
	 * 情報出力
	 * @param message
	 */
	public void info(String message) {
		StackTraceElement ste 	= Thread.currentThread().getStackTrace()[2];
		String 		cls			= ste.getClassName();
		String[]	clsList		= cls.split("\\.");
		String		clsName 	= clsList[clsList.length-1];
		String 		mtd			= ste.getMethodName();

		this.logger.info(clsName + "#" + mtd + "() : " + message);
	}

	/**
	 * エラー出力
	 * @param message
	 */
	public void error(String message) {
		StackTraceElement ste 	= Thread.currentThread().getStackTrace()[2];
		String 		cls			= ste.getClassName();
		String[]	clsList		= cls.split("\\.");
		String		clsName 	= clsList[clsList.length-1];
		String 		mtd			= ste.getMethodName();

		this.logger.error(clsName + "#" + mtd + "() : " + message);
	}

	/**
	 * warning出力
	 * @param message
	 */
	public void warning(String message) {
		StackTraceElement ste 	= Thread.currentThread().getStackTrace()[2];
		String 		cls			= ste.getClassName();
		String[]	clsList		= cls.split("\\.");
		String		clsName 	= clsList[clsList.length-1];
		String 		mtd			= ste.getMethodName();

		this.logger.warn(clsName + "#" + mtd + "() : " + message);
	}

	/**
	 * debug出力
	 * @param message
	 */
	public void debug(String message) {
		StackTraceElement ste 	= Thread.currentThread().getStackTrace()[2];
		String 		cls			= ste.getClassName();
		String[]	clsList		= cls.split("\\.");
		String		clsName 	= clsList[clsList.length-1];
		String 		mtd			= ste.getMethodName();

		this.logger.debug(clsName + "#" + mtd + "() : " + message);
	}
}
