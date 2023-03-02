package com.example.demo.common.log;

/**
 * スーパーファイルログ出力クラス
 * @author nanai
 *
 */
public interface SuperLogWriter {

	/**
	 * 初期化
	 */
	void init();

	/**
	 * 設定済か確認
	 * @return true 準備OK false 準備NG
	 */
	boolean isSetting();

	/**
	 * 開始ログ
	 * @param log {@link String}
	 */
	void start(String log);

	/**
	 * 成功ログ
	 * @param log {@link String}
	 */
	void successed(String log);

	/**
	 * 情報ログ
	 * @param log {@link String}
	 */
	void info(String log);

	/**
	 * warningログ
	 * @param log {@link String}
	 */
	void warning(String log);

	/**
	 * エラーログ
	 * @param log {@link String}
	 */
	void error(String log);
}
