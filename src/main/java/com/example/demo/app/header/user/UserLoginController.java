package com.example.demo.app.header.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * ユーザーログインコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping("/user")
public class UserLoginController extends SuperUserController {
	
	Logger logger = null;
	
	/**
	 * コンストラクタ
	 * @param userService
	 */
	@Autowired
	public UserLoginController(
			UserServiceUse		userService,
			SessionLoginUser	sessionLoginUser) {
		super(userService, sessionLoginUser);
		this.logger = LoggerFactory.getLogger(HeaderController.class);
	}
	
	/**
	 * ログイン受信
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String loginAction(
			@Validated UserLoginForm 	userLoginForm,
			BindingResult          		result,
			Model						model) {
		logger.info("[debug]: serverside login OK");
		logger.info("[debug]: -------------------------------------------------------------");
		logger.info("[debug]: name..."             + userLoginForm.getName());
		logger.info("[debug]: password..."         + userLoginForm.getPassword());
		logger.info("[debug]: result..."           + result.hasErrors());
		logger.info("[debug]: session.userId..."   + this.sessionLoginUser.getUserId());
		logger.info("[debug]: session.userName..." + this.sessionLoginUser.getUserName());
		logger.info("[debug]: model..."            + model);
		
		if (result.hasErrors()) {
			// エラー
			return "NG";
		}
		
		this.sessionLoginUser.setUserId(1);
		this.sessionLoginUser.setUserName(userLoginForm.getName());
		
		logger.info("[debug]: session.userId..."   + this.sessionLoginUser.getUserId());
		logger.info("[debug]: session.userName..." + this.sessionLoginUser.getUserName());
		logger.info("[debug]: -------------------------------------------------------------");
		
		model.addAttribute("loginId", 1);
		
		return "OK";
	}

}
