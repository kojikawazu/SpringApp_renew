package com.example.demo.app.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.common.common.AppConsts;

/**
 * セキュリティユーザーログインフォームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SECURITY)
public class SecurityLoginFormController {
	
	/** -------------------------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param securityService
	 */
	public SecurityLoginFormController(
			SecurityUserServiceUse securityService) {
		
	}
	
	/** -------------------------------------------------------------------------------------------- */

	@GetMapping(AppConsts.REQUEST_MAPPING_FORM)
	public String form() {
		
		return AppConsts.URL_SECURITY_INDEX;
	}
	
}
