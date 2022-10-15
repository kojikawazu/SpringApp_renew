package com.example.demo.app.header.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.header.HeaderController;
import com.example.demo.app.header.form.UserLoginForm;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;

/**
 * ユーザーログアウトコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping("/user")
public class UserLogoutController extends SuperUserController {

	Logger logger = null;
	
	/**
	 * コンストラクタ
	 * @param userService
	 * @param sessionLoginUser
	 */
	public UserLogoutController(
			UserServiceUse userService, 
			SessionLoginUser sessionLoginUser) {
		super(userService, sessionLoginUser);
		this.logger = LoggerFactory.getLogger(HeaderController.class);
	}
	
	/**
	 * ログイン受信
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String loginAction(
			Model						model) {
		logger.info("[debug]: serverside logout OK");
		logger.info("[debug]: -------------------------------------------------------------");
		logger.info("[debug]: session.userId..."   + this.sessionLoginUser.getUserId());
		logger.info("[debug]: session.userName..." + this.sessionLoginUser.getUserName());
		logger.info("[debug]: model..."            + model);
		
		this.sessionLoginUser.setUserId(0);
		this.sessionLoginUser.setUserName("");
		
		logger.info("[debug]: session.userId..."   + this.sessionLoginUser.getUserId());
		logger.info("[debug]: session.userName..." + this.sessionLoginUser.getUserName());
		logger.info("[debug]: -------------------------------------------------------------");
		
		model.addAttribute("loginId", 0);
		
		return "OK";
	}

}
