package com.example.demo.app.init.controller;

import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * 初期化コントローラークラス(シングルトン)
 * @author nanai
 *
 */
public class InitController {

	/** 起動処理ステータス(起動処理なし) */
	private static final boolean STATUS_STARTUP_NONE = false;
	/** 起動処理ステータス(起動処理済) */
	private static final boolean STATUS_STARTUP_DONE = true;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** Singletonインスタンス */
	private static final InitController initController = new InitController();

	/** ------------------------------------------------------------------------------------------- */

	/** 起動処理実行済? true: 実行済 false: 実行なし */
	private boolean isExecStartup;

	/**
	 * ログインサービス
	 * {@link LoginServiceUse}
	 */
	private LoginServiceUse loginServiceUse;

	/** ------------------------------------------------------------------------------------------- */

	/**
	 * (singleton)コンストラクタ
	 */
	private InitController() {
		this.loginServiceUse 	= null;
		this.logWriter			= IntroAppLogWriter.getInstance();
		this.isExecStartup 		= STATUS_STARTUP_NONE;
	}

	/** ------------------------------------------------------------------------------------------- */

	/**
	 * 初期化
	 */
	public void Init() {
		if (this.isExecStartup)		return ;
		if (this.loginServiceUse == null)	return ;

		logWriter.start("");

		logWriter.info("ログインデータの全削除: 開始");
		try {
			this.loginServiceUse.deleteAll();
		} catch (Exception ex) {
			// 全削除は処理なしでOK
		}
		logWriter.info("ログインデータの全削除: 終了");

		this.isExecStartup = STATUS_STARTUP_DONE;
		logWriter.successed("");
	}

	/** ------------------------------------------------------------------------------------------- */

	/**
	 * (singleton)インスタンス取得
	 * @return {@link InitController}
	 */
	public static InitController getInstance() {
		return initController;
	}

	/**
	 * setter
	 * @param loginServiceUse {@link LoginServiceUse}
	 */
	public void setSecurityUserServiceUse(LoginServiceUse loginServiceUse) {
		if (loginServiceUse == null)	return ;
		this.loginServiceUse = loginServiceUse;
	}
}
