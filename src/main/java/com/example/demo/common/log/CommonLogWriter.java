package com.example.demo.common.log;

/**
 * Common用ログ出力クラス
 * <br>
 * extends {@link LogWriter}
 * @author nanai
 *
 */
public class CommonLogWriter extends LogWriter {

	/** シングルトン */
	private static final CommonLogWriter commonLogWriter = new CommonLogWriter();

	/** Common用ログファイル名 */
	private final String commonLogName = "common";

	/** シングルトンコンストラクタ */
	private CommonLogWriter() {
		this.setLogName(commonLogName);
		this.setLogLevelWarning();
		this.init();
	}

	/**
	 * シングルトン用インスタンス取得
	 * @return {@link CommonLogWriter}
	 */
	public static CommonLogWriter getInstance() {
		return commonLogWriter;
	}
}
