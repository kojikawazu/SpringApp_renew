package com.example.demo.app.header.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;

/**
 * スーパーユーザーコントローラー
 * @author nanai
 *
 */
public class SuperUserController {
	
	/** サービス */
	protected final UserServiceUse	userService;
	
	/** セッション */
	protected final SessionLoginUser	sessionLoginUser;
	
	/**
	 * コンストラクタ
	 * @param userService
	 */
	@Autowired
	public SuperUserController(
			UserServiceUse		userService,
			SessionLoginUser	sessionLoginUser) {
		this.userService 		= userService;
		this.sessionLoginUser	= sessionLoginUser;
	}

}
