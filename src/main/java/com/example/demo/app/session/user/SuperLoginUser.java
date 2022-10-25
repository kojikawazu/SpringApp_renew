package com.example.demo.app.session.user;

import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.id.user.UserId;

/**
 * スーパーログインユーザークラス
 * @author nanai
 *
 */
public class SuperLoginUser {

	/** ログインID初期化値 */
	private static final int loginIdInit		= 0;
	
	/** ユーザーID初期化値 */
	private static final int userIdInit			= 0;
	
	/** ユーザー名初期化文字列 */
	private static final String userNameInit	= "";
	
	/** 
	 * ログインID
	 * {@link LoginId}
	 */
	private LoginId	loginId 	= null;
	
	/** 
	 * ユーザーID
	 * {@link UserId}
	 */
	private UserId	userId 		= null;
	
	/** ユーザー名 */
	private String	userName 	= "";
	
	/** ---------------------------------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 */
	public SuperLoginUser() {
		this.initLoginId();
		this.initUserId();
		this.initUserName();
	}
	
	/** ---------------------------------------------------------------------------------------------------- */
	
	/**
	 * ログインIDは初期化状態か？
	 * @return true 初期化状態じゃない false 初期化状態
	 */
	public boolean isLoginId() {
		if (this.loginId == null) {
			return false;
		}
		return (this.loginId.getId() > loginIdInit);
	}
	
	/**
	 * ユーザーIDは初期化状態か?
	 * @return true 初期化状態じゃない false 初期化状態
	 */
	public boolean isUserId() {
		if (this.userId == null) {
			return false;
		}
		return (this.userId.getId() > userIdInit);
	}
	
	/**
	 * ユーザ名は初期化状態か?
	 * @return true 初期化状態じゃない false 初期化状態
	 */
	public boolean isUserName() {
		if (this.userName == null) {
			return false;
		}
		return (!this.userName.equals(userNameInit));
	}
	
	/** ----------------------------------------------------- */
	
	/**
	 * ログインIDの初期化
	 */
	public void initLoginId() {
		this.setLoginId(loginIdInit);
	}
	
	/**
	 * ユーザIDの初期化
	 */
	public void initUserId() {
		this.setUserId(userIdInit);
	}
	
	/**
	 * ユーザ名の初期化
	 */
	public void initUserName() {
		this.setUserName(userNameInit);
	}
	
	/** ----------------------------------------------------- */
	
	public void setLoginId(int loginId) {
		this.loginId = new LoginId(loginId);
	}
	
	public int getLoginId() {
		if (this.loginId == null) {
			this.initLoginId();
		}
		return this.loginId.getId();
	}
	
	public void setUserId(int userId) {
		this.userId = new UserId(userId);
	}
	
	public int getUserId() {
		if (this.userId == null) {
			this.initUserId();
		}
		return this.userId.getId();
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	/** ----------------------------------------------------- */
	
	public static int getLoginIdInit() {
		return loginIdInit;
	}
	
	public static int getUserIdInit() {
		return userIdInit;
	}
	
	public static String getUserNameInit() {
		return userNameInit;
	}
	
	/** ----------------------------------------------------- */
}
