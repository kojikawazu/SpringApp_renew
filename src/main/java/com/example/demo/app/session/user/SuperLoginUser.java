package com.example.demo.app.session.user;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.UserId;

/**
 * スーパーログインユーザークラス
 * @author nanai
 *
 */
public class SuperLoginUser {

	/** ログインID初期化値 */
	private static final int LOGIN_ID_INIT		= 0;
	
	/** ユーザーID初期化値 */
	private static final int USER_ID_INIT		= 0;
	
	/** ユーザー名初期化文字列 */
	private static final String USER_NAME_INIT	= "";
	
	/** ---------------------------------------------------------------------------------------------------- */
	
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
		return (this.loginId.getId() > LOGIN_ID_INIT);
	}
	
	/**
	 * ユーザーIDは初期化状態か?
	 * @return true 初期化状態じゃない false 初期化状態
	 */
	public boolean isUserId() {
		if (this.userId == null) {
			return false;
		}
		return (this.userId.getId() > USER_ID_INIT);
	}
	
	/**
	 * ユーザ名は初期化状態か?
	 * @return true 初期化状態じゃない false 初期化状態
	 */
	public boolean isUserName() {
		if (this.userName == null) {
			return false;
		}
		return (!this.userName.equals(USER_NAME_INIT));
	}
	
	/** ----------------------------------------------------- */
	
	/**
	 * 比較したいログインIDと一致している？
	 * @param targetLoginId
	 * @return true 一致している false 一致しない or ログインIDがnull
	 */
	public boolean equalsLoginId(int targetLoginId) {
		return (this.loginId != null &&
				this.loginId.getId() == targetLoginId);
	}
	
	/**
	 * 比較したいユーザーIDと一致している？
	 * @param targetUserId
	 * @return true 一致している false 一致しない or ユーザーIDがnull
	 */
	public boolean equalsUserId(int targetUserId) {
		return (this.userId != null &&
				this.userId.getId() == targetUserId);
	}
	
	/**
	 * 比較したいユーザー名と一致している？
	 * @param targetUserName
	 * @return true 一致している false 一致しない or ユーザー名がnull
	 */
	public boolean equalsUserName(String targetUserName) {
		return (this.userName != null &&
				this.userName.equals(targetUserName));
	}
	
	/** ----------------------------------------------------- */
	
	/**
	 * ログインIDの初期化
	 */
	public void initLoginId() {
		this.setLoginId(LOGIN_ID_INIT);
	}
	
	/**
	 * ユーザIDの初期化
	 */
	public void initUserId() {
		this.setUserId(USER_ID_INIT);
	}
	
	/**
	 * ユーザ名の初期化
	 */
	public void initUserName() {
		this.setUserName(USER_NAME_INIT);
	}
	
	/** ----------------------------------------------------- */
	/** setter/getter */
	
	public void setLoginId(int loginId) {
		this.loginId = new LoginId(loginId);
	}
	
	public void setLoginId(String loginId) {
		this.loginId = new LoginId(Integer.valueOf(loginId));
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
	
	public void setUserId(String userId) {
		this.userId = new UserId(Integer.valueOf(userId));
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
	/** 初期化定数のgetter */
	
	public static int getLoginIdInit() {
		return LOGIN_ID_INIT;
	}
	
	public static int getUserIdInit() {
		return USER_ID_INIT;
	}
	
	public static String getUserNameInit() {
		return USER_NAME_INIT;
	}
	
	/** ----------------------------------------------------- */
}
