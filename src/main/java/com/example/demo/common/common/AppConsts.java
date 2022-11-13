package com.example.demo.common.common;

/**
 * App定数クラス
 * @author nanai
 *
 */
public class AppConsts {
	
	// ---------------------------------------------------------------------------------
	/** request mapping */
	
	/** request mapping[/security] */
	public static final String REQUEST_MAPPING_SECURITY       = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_SECURITY;
	
	/** request mapping[/user] */
	public static final String REQUEST_MAPPING_USER           = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_USER;
	
	/** request mapping[/userinterval] */
	public static final String REQUEST_MAPPING_USER_INTERVAL  = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_USER_INTERVAL;
	
	/** request mapping[/home] */
	public static final String REQUEST_MAPPING_HOME           = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_HOME;
	
	/** request mapping[/reply] */
	public static final String REQUEST_MAPPING_REPLY          = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_REPLY;
	
	/** request mapping[/delete] */
	public static final String REQUEST_MAPPING_DELETE         = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_DELETE;
	
	/** request mapping[/selected] */
	public static final String REQUEST_MAPPING_SELECTED       = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_SELECTED;
	
	/** request mapping[/thanks] */
	public static final String REQUEST_MAPPING_THANKS         = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_THANKS;
	
	/** request mapping[/reply_thanks] */
	public static final String REQUEST_MAPPING_REPLY_THANKS   = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_REPLY_THANKS;
	
	/** request mapping[/form] */
	public static final String REQUEST_MAPPING_FORM           = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_FORM;
	
	/** request mapping[/confirm] */
	public static final String REQUEST_MAPPING_CONFIRM        = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_CONFIRM;
	
	/** request mapping[/complete] */
	public static final String REQUEST_MAPPING_COMPLETE       = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_COMPLETE;
	
	/** request mapping[/reply_confirm] */
	public static final String REQUEST_MAPPING_REPLY_CONFIRM  = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_REPLY_CONFIRM;
	
	/** request mapping[/reply_complete] */
	public static final String REQUEST_MAPPING_REPLY_COMPLETE = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_REPLY_COMPLETE;

	/** request mapping[/intro] */
	public static final String REQUEST_MAPPING_INTRO          = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_INTRO;
	
	/** request mapping[/inquiry] */
	public static final String REQUEST_MAPPING_INQUIRY        = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_INQUIRY;
	
	/** request mapping[/blog] */
	public static final String REQUEST_MAPPING_BLOG           = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_BLOG;
	
	/** request mapping[/edit] */
	public static final String REQUEST_MAPPING_EDIT           = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_EDIT;
	
	/** request mapping[/survey] */
	public static final String REQUEST_MAPPING_SURVEY        = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_SURVEY;
	
	/** request mapping[/satis] */
	public static final String REQUEST_MAPPING_SATIS         = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_SATIS;
	
	/** request mapping[/satis_reply] */
	public static final String REQUEST_MAPPING_SATIS_REPLY   = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_REPLY_SATIS;
	
	/** request mapping[/login] */
	public static final String REQUEST_MAPPING_LOGIN         = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_LOGIN;
	
	/** request mapping[/logout] */
	public static final String REQUEST_MAPPING_LOGOUT         = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_LOGOUT;
	
	/** request mapping[/security/form] */
	public static final String REQUEST_MAPPING_SECURITY_FORM  = REQUEST_MAPPING_SECURITY + REQUEST_MAPPING_FORM;
	
	/** request mapping[/security/logout] */
	public static final String REQUEST_MAPPING_SECURITY_LOGOUT = REQUEST_MAPPING_SECURITY + REQUEST_MAPPING_LOGOUT;
	
	// ---------------------------------------------------------------------------------
	/** return URL */
	
	/** SecurityのURL */
	public static final String URL_SECURITY_INDEX           = WebConsts.URL_KEY_SECURITY + "/" + WebConsts.URL_KEY_INDEX;
	
	/** HomeページのURL */
	public static final String URL_HOME_INDEX               = WebConsts.URL_KEY_HOME + "/" + WebConsts.URL_KEY_INDEX;
	
	/** 自己紹介ページのURL */
	public static final String URL_INTRO_INDEX              = WebConsts.URL_KEY_INTRO + "/" + WebConsts.URL_KEY_INDEX;
	
	/** 問い合わせリストのURL */
	public static final String URL_INQUIRY_INDEX            = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_INDEX;
	
	/** 問い合わせフォームのURL */
	public static final String URL_INQUIRY_FORM             = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_FORM;
	
	/** 問い合わせ確認のURL */
	public static final String URL_INQUIRY_CONFIRM          = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_CONFIRM;
	
	/** 問い合わせ返信フォームのURL */
	public static final String URL_INQUIRY_REPLY_FORM       = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_REPLY_FORM;
	
	/** 問い合わせ返信確認のURL */
	public static final String URL_INQUIRY_REPLY_CONFIRM    = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_REPLY_CONFIRM;
	
	/** ブログリストのURL */
	public static final String URL_BLOG_MAIN_INDEX           = WebConsts.URL_KEY_BLOG + "/" + WebConsts.URL_KEY_INDEX;
	
	/** ブログ投稿フォームのURL */
	public static final String URL_BLOG_MAIN_FORM           = WebConsts.URL_KEY_BLOG + "/" + WebConsts.URL_KEY_FORM;
	
	/** ブログ投稿確認のURL */
	public static final String URL_BLOG_MAIN_CONFIRM        = WebConsts.URL_KEY_BLOG + "/" + WebConsts.URL_KEY_CONFIRM;
	
	/** ブログ返信フォームのURL */
	public static final String URL_BLOG_REPLY_FORM          = WebConsts.URL_KEY_BLOG + "/" + WebConsts.URL_KEY_REPLY_FORM;
	
	/** ブログ返信確認のURL */
	public static final String URL_BLOG_REPLY_CONFIRM       = WebConsts.URL_KEY_BLOG + "/" + WebConsts.URL_KEY_REPLY_CONFIRM;

	/** 調査一覧のURL */
	public static final String URL_SURVEY_INDEX             = WebConsts.URL_KEY_SURVEY + "/" + WebConsts.URL_KEY_INDEX;

	/** 調査フォームのURL */
	public static final String URL_SURVEY_FORM              = WebConsts.URL_KEY_SURVEY + "/" + WebConsts.URL_KEY_FORM;
	
	/** 調査投稿確認のURL */
	public static final String URL_SURVEY_CONFIRM           = WebConsts.URL_KEY_SURVEY + "/" + WebConsts.URL_KEY_CONFIRM;
	
	/** 調査投稿確認のURL */
	public static final String URL_SURVEY_SATISTICS         = WebConsts.URL_KEY_SURVEY + "/" + WebConsts.URL_KEY_SATISTICS;
	
	/** リダイレクト[/inquiry] */
	public static final String REDIRECT_URL_INQUIRY_INDEX   = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_INQUIRY;
	
	/** リダイレクト[/inquiry/form] */
	public static final String REDIRECT_URL_INQUIRY_FORM    = WebConsts.URL_REDIRECT + URL_INQUIRY_FORM;
	
	/** リダイレクト[/inquiry/reply] */
	public static final String REDIRECT_URL_INQUIRY_REPLY   = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_INQUIRY + REQUEST_MAPPING_REPLY;
	
	/** リダイレクト[/blog] */
	public static final String REDIRECT_URL_BLOG_INDEX      = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_BLOG;
	
	/** リダイレクト[/blog/form] */
	public static final String REDIRECT_URL_BLOG_FORM       = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_BLOG + REQUEST_MAPPING_FORM;
	
	/** リダイレクト[/blog/edit] */
	public static final String REDIRECT_URL_BLOG_EDIT       = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_BLOG + REQUEST_MAPPING_EDIT;
	
	/** リダイレクト[/blog/reply] */
	public static final String REDIRECT_URL_BLOG_REPLY      = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_BLOG + REQUEST_MAPPING_REPLY;
	
	/** リダイレクト[/survey/form] */
	public static final String REDIRECT_URL_SURVEY_FORM     = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_SURVEY + REQUEST_MAPPING_FORM;
	
	
	// ---------------------------------------------------------------------------------
	
	/** attribute key */
	
	/** attribute[inquiry] */
	public static final String ATTRIBUTE_INQUIRY        = "inquiry";
	
	/** attribute[inquiryList] */
	public static final String ATTRIBUTE_INQUIRY_LIST   = "inquiryList";
	
	/** attribute[blogmainList] */
	public static final String ATTRIBUTE_BLOG_MAIN_LIST = "blogmainList";
	
	/** attribute[tagList] */
	public static final String ATTRIBUTE_TAG_LIST       = "tagList";
	
	/** attribute[blogForm] */
	public static final String ATTRIBUTE_BLOG_FORM      = "blogForm";
	
	/** attribute[blogMain] */
	public static final String ATTRIBUTE_BLOG_MAIN      = "blogMain";
	
	/** attribute[satisList] */
	public static final String ATTRIBUTE_SATIS_LIST      = "satisList";
	
	/** attribute[surveyList] */
	public static final String ATTRIBUTE_SURVEY_LIST      = "surveyList";
}
