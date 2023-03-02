package com.example.demo.common.log;

/**
 * IntroApp用ファイル出力クラス
 * @author nanai
 *
 */
public class IntroAppLogWriter extends LogWriter {

	/** シングルトン */
	private static final IntroAppLogWriter introAppLogWriter = new IntroAppLogWriter();

	/** IntroApp用ログファイル名 */
	private final String introAppLogName = "IntroApp";

	/** シングルトンコンストラクタ */
	private IntroAppLogWriter() {
		this.setLogName(introAppLogName);
		this.init();
	}

	/**
	 * シングルトン用インスタンス取得
	 * @return {@link IntroAppLogWriter}
	 */
	public static IntroAppLogWriter getInstance() {
		return introAppLogWriter;
	}
}
