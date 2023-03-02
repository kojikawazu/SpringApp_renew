package com.example.demo.common.log;

/**
 * Json用ログ出力クラス
 * @author nanai
 *
 */
public class JsonLogWriter extends LogWriter {

	/** シングルトン */
	private static final JsonLogWriter jsonLogWriter = new JsonLogWriter();

	/** Json用ログファイル名 */
	private final String jsonLogName = "JsonReader";

	/** シングルトンコンストラクタ */
	private JsonLogWriter() {
		this.setLogName(jsonLogName);
		this.setLogLevelWarning();
		this.init();
	}

	/**
	 * シングルトン用インスタンス取得
	 * @return {@link JsonLogWriter}
	 */
	public static JsonLogWriter getInstance() {
		return jsonLogWriter;
	}
}
