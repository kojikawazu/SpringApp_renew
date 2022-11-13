package com.example.demo.common.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Web定数クラス
 * @author nanai
 *
 */
public class WebConsts {
	
	/** エラー番号 */
	public static final int    			ERROR_NUMBER    = -1;
	
	/** データなし */
	public static final int   	 		ERROR_DB_STATUS = 0;
	
	/** 0の文字列 */
	public static final String			ZERO_STRING     = "0";
	
	/** 0の数字 */
	public static final int    			ZERO_NUMBER     = 0;
	
	/** 時間がだいぶ経っている */
	public static final boolean 		TIME_RUNNING_OUT		= false;
	
	/** 時間内 */
	public static final boolean 		TIME_WITHIN				= true;
	
	
	// ---------------------------------------------------------------------------------
	/** Ajax response */
	
	/** response[OK] */
	public static final String			RESPONSE_OK			= "OK";
	
	/** response[NG] */
	public static final String			RESPONSE_NG			= "NG";
	
	/** response[none] */
	public static final String			RESPONSE_NONE		= "none";
	
	/** response[init] */
	public static final String			RESPONSE_INIT		= "init";
	
	/** response[delete] */
	public static final String			RESPONSE_DELETE		= "delete";
	
	/** response[reload] */
	public static final String			RESPONSE_RELOAD		= "reload";
	
	// ---------------------------------------------------------------------------------
	
	/** セッション & Cookie */
	
	/** Cookie(0) */
	public static final String			COOKIE_ZERO			= "wfssM4JI4nk=";
	
	/** Cookie(none) */
	public static final String			COOKIE_NONE			= "340Zwm6z8hs=";
	
	/** Cookie(loginId) */
	public static final String			COOKIE_LOGIN_ID		= "loginId";
	
	/** Cookie(userId) */
	public static final String			COOKIE_USER_ID		= "userId";
	
	/** Cookie(userName) */
	public static final String			COOKIE_USER_NAME	= "userName";
	
	/** Cookieのタイムアウト(1日) */
	public static final int				COOKIE_TIMEOUT		= 24 * 60 * 60;
	
	/** ログインのタイムアウト(時間単位)({@value #LOGIN_TIMEOUT_HOUR}時間) */
	public static final int				LOGIN_TIMEOUT_HOUR	= 1;
	
	/** Cookieキーリスト*/
	public static final List<String>	COOKIE_KEY_LIST	= new ArrayList<String>(Arrays.asList(
			COOKIE_LOGIN_ID,
			COOKIE_USER_ID,
			COOKIE_USER_NAME));
	
	// ---------------------------------------------------------------------------------
	/** SQL */
	
	/** SQL[INSERT INTO] */
	public static final String SQL_INSERT					= "INSERT INTO";
	
	/** SQL[VALUES] */
	public static final String SQL_VALUES					= "VALUES";
	
	/** SQL[UPDATE] */
	public static final String SQL_UPDATE					= "UPDATE";
	
	/** SQL[SET] */
	public static final String SQL_SET						= "SET";
	
	/** SQL[DELETE FROM] */
	public static final String SQL_DELETE					= "DELETE FROM";
	
	/** SQL[SELECT] */
	public static final String SQL_SELECT					= "SELECT";
	
	/** SQL[FROM] */
	public static final String SQL_FROM						= "FROM";
	
	/** SQL[WHERE] */
	public static final String SQL_WHERE					= "WHERE";
	
	/** SQL[JOIN] */
	public static final String SQL_JOIN						= "JOIN";
	
	/** SQL[ON] */
	public static final String SQL_ON						= "ON";
	
	/** SQL[AS] */
	public static final String SQL_AS						= "AS";
	
	/** SQL[?] */
	public static final String SQL_QUESTION					= "?";
	
	// ---------------------------------------------------------------------------------
	
	/** SQL[id] */
	public static final String SQL_ID_NAME					= "id";
	
	/** SQL[user_id] */
	public static final String SQL_USER_ID_NAME				= "user_id";
	
	/** SQL[blog_id] */
	public static final String SQL_BLOG_ID_NAME				= "blog_id";
	
	/** SQL[inquiry_id] */
	public static final String SQL_INQUIRY_ID_NAME			= "inquiry_id";
	
	/** SQL[kind_id] */
	public static final String SQL_KIND_ID_NAME				= "kind_id";
	
	/** SQL[session_id] */
	public static final String SQL_SESSION_ID_NAME			= "session_id";
	
	/** SQL[title] */
	public static final String SQL_TITLE_NAME				= "title";
	
	/** SQL[name] */
	public static final String SQL_NAME_NAME				= "name";
	
	/** SQL[email] */
	public static final String SQL_EMAIL_NAME				= "email";
	
	/** SQL[tag] */
	public static final String SQL_TAG_NAME					= "tag";
	
	/** SQL[comment] */
	public static final String SQL_COMMENT_NAME				= "comment";
	
	/** SQL[password] */
	public static final String SQL_PASSWORD_NAME			= "password";
	
	/** SQL[thanksCnt] */
	public static final String SQL_THANKSCNT_NAME			= "thanksCnt";
	
	/** SQL[age] */
	public static final String SQL_AGE_NAME					= "age";
	
	/** SQL[profession] */
	public static final String SQL_PROFESSION_NAME			= "profession";
	
	/** SQL[ismen] */
	public static final String SQL_ISMEN_NAME				= "ismen";
	
	/** SQL[satisfaction] */
	public static final String SQL_SATISFACTION_NAME		= "satisfaction";
	
	/** SQL[created] */
	public static final String SQL_CREATED_NAME				= "created";
	
	/** SQL[updated] */
	public static final String SQL_UPDATED_NAME				= "updated";
	
	/** SQL[reply_id] */
	public static final String SQL_REPLY_ID_NAME			= "reply_id";
	
	/** SQL[reply_name] */
	public static final String SQL_REPLY_NAME_NAME			= "reply_name";
	
	/** SQL[reply_email] */
	public static final String SQL_REPLY_EMAIL_NAME			= "reply_email";
	
	/** SQL[reply_comment] */
	public static final String SQL_REPLY_COMMENT_NAME		= "reply_comment";

	/** SQL[reply_thankscnt] */
	public static final String SQL_REPLY_THANKS_CNT_NAME	= "reply_thankscnt";
	
	/** SQL[reply_created] */
	public static final String SQL_REPLY_CREATED_NAME		= "reply_created";
	
	/** SQL[satis_count] */
	public static final String SQL_SATIS_COUNT				= "satis_count";
	
	/** SQL[role_name] */
	public static final String SQL_ROLE_NAME				= "role_name";
	
	/** SQL[role_id] */
	public static final String SQL_ROLE_ID					= "role_id";

	// ---------------------------------------------------------------------------------
	/** URL key */
	
	public static final String URL_REDIRECT           = "redirect:/";
	
	/** URL key[root] */
	public static final String URL_KEY_ROOT           = "/";
	
	/** URL key[security] */
	public static final String URL_KEY_SECURITY       = "security";
	
	/** URL key[home] */
	public static final String URL_KEY_INDEX          = "index";
	
	/** URL key[user] */
	public static final String URL_KEY_USER           = "user";
	
	/** URL key[userinterval] */
	public static final String URL_KEY_USER_INTERVAL  = "userinterval";
	
	/** URL key[home] */
	public static final String URL_KEY_HOME           = "home";
	
	/** URL key[intro] */
	public static final String URL_KEY_INTRO          = "intro";
	
	/** URL key[inquiry] */
	public static final String URL_KEY_INQUIRY        = "inquiry";
	
	/** URL key[blog] */
	public static final String URL_KEY_BLOG           = "blog";
	
	/** URL key[survey] */
	public static final String URL_KEY_SURVEY         = "survey";
	
	/** URL key[satis] */
	public static final String URL_KEY_SATIS          = "satis";
	
	/** URL key[satistics] */
	public static final String URL_KEY_SATISTICS      = "satistics";
	
	/** URL key[reply] */
	public static final String URL_KEY_REPLY          = "reply";
	
	/** URL key[selected] */
	public static final String URL_KEY_SELECTED       = "selected";
	
	/** URL key[thanks] */
	public static final String URL_KEY_THANKS         = "thanks";
	
	/** URL key[delete] */
	public static final String URL_KEY_DELETE         = "delete";
	
	/** URL key[form] */
	public static final String URL_KEY_FORM           = "form";
	
	/** URL key[edit] */
	public static final String URL_KEY_EDIT           = "edit";
	
	/** URL key[confirm] */
	public static final String URL_KEY_CONFIRM        = "confirm";
	
	/** URL key[complete] */
	public static final String URL_KEY_COMPLETE       = "complete";
	
	/** URL key[login] */
	public static final String URL_KEY_LOGIN          = "login";
	
	/** URL key[logout] */
	public static final String URL_KEY_LOGOUT         = "logout";
	
	/** URL key[reply_form] */
	public static final String URL_KEY_REPLY_FORM     = URL_KEY_REPLY + "_" + URL_KEY_FORM;
	
	/** URL key[reply_confirm] */
	public static final String URL_KEY_REPLY_CONFIRM  = URL_KEY_REPLY + "_" + URL_KEY_CONFIRM;
	
	/** URL key[reply_complete] */
	public static final String URL_KEY_REPLY_COMPLETE = URL_KEY_REPLY + "_" + URL_KEY_COMPLETE;
	
	/** URL key[reply_thanks] */
	public static final String URL_KEY_REPLY_THANKS   = URL_KEY_REPLY + "_" + URL_KEY_THANKS;
	
	/** URL key[satis_reply] */
	public static final String URL_KEY_REPLY_SATIS    = URL_KEY_SATIS + "_" + URL_KEY_REPLY;
	
	// ---------------------------------------------------------------------------------
	/** attribute key */
	
	/** attribute[model] */
	public static final String ATTRIBUTE_MODEL         = "model";
	
	/** attribute[error] */
	public static final String ATTRIBUTE_ERROR         = "error";
	
	/** attribute[title] */
	public static final String ATTRIBUTE_TITLE         = "title";
	
	/** attribute[cont] */
	public static final String ATTRIBUTE_CONT          = "cont";
	
	/** attribute[complete] */
	public static final String ATTRIBUTE_COMPLETE      = "complete";
	
	/** attribute[ID] */
	public static final String ATTRIBUTE_ID            = "id";
	
	/** attribute[pageidx] */
	public static final String ATTRIBUTE_PAGE_IDX      = "pageidx";
	
	/** attribute[paging] */
	public static final String ATTRIBUTE_PAGING        = "paging";
	
	/** attribute[editor] */
	public static final String ATTRIBUTE_EDITOR        = "editor";
	
	/** attribute[selectIdx] */
	public static final String ATTRIBUTE_SELECT_IDX    = "selectIdx";
	
	// ---------------------------------------------------------------------------------
	
	/** バリデーションエラー(入力なし) */
	public static final String ERROR_MESSAGE_BLANK     = "を入力してください。";

}
