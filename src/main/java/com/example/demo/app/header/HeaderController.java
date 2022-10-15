package com.example.demo.app.header;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;

public class HeaderController {

	/** サービス */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ユーザーサービス 
	 * {@link UserServiceUse} 
	 */
	private final UserServiceUse	userService;
	
	/** セッション */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ログインセッション 
	 * {@link SessionLoginUser} 
	 */
	private SessionLoginUser		sessionLoginUser;
	
	Logger logger = null;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userService		{@link UserServiceUse}
	 * @param sessionLoginUser	{@link SessionLoginUser}
	 */
	public HeaderController(
			UserServiceUse		userService,
			SessionLoginUser	sessionLoginUser) {
		this.userService 		= userService;
		this.sessionLoginUser 	= sessionLoginUser;
		this.logger 			= LoggerFactory.getLogger(HeaderController.class);
	}
	
	/**
	 * ヘッダーの設定
	 * @param model {@link Model}
	 */
	public void setHeader(Model model) {
		int userId = sessionLoginUser.getUserId();
		
		logger.info("[debug]:" + userId);
		if (userId > 0) {
			model.addAttribute("noLoginId",  "");
			model.addAttribute("yesLoginId", "yes");
		} else {
			model.addAttribute("noLoginId",  "yes");
			model.addAttribute("yesLoginId", "");
		}
	}
	
}
