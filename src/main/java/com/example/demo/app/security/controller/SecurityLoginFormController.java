package com.example.demo.app.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.common.AppConsts;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * セキュリティユーザーログインフォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SECURITY)
public class SecurityLoginFormController {

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter = IntroAppLogWriter.getInstance();

	/** -------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public SecurityLoginFormController() {
		
	}

	/** -------------------------------------------------------------------------------------------- */

	/**
	 * ログインフォーム受信
	 * @return 	{@link String}
	 * 			<br>{@value AppConsts#URL_SECURITY_INDEX}
	 */
	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form() {
		this.logWriter.start("");
		this.logWriter.successed("");

		return AppConsts.URL_SECURITY_INDEX;
	}
}
