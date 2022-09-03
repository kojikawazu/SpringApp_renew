package com.example.demo.common.common;

/**
 * App定数クラス
 * @author nanai
 *
 */
public class AppConsts {
	
	// ---------------------------------------------------------------------------------
	/** request mapping */
	
	/** request mapping[/home] */
	public static final String REQUEST_MAPPING_HOME           = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_HOME;
	
	/** request mapping[/reply] */
	public static final String REQUEST_MAPPING_REPLY          = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_REPLY;
	
	/** request mapping[/delete] */
	public static final String REQUEST_MAPPING_DELETE         = WebConsts.URL_KEY_ROOT + WebConsts.URL_KEY_DELETE;
	
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
	
	// ---------------------------------------------------------------------------------
	/** return URL */
	
	/** HomeページのURL */
	public static final String URL_HOME_INDEX            = WebConsts.URL_KEY_HOME + "/" + WebConsts.URL_KEY_INDEX;
	
	/** 自己紹介ページのURL */
	public static final String URL_INTRO_INDEX           = WebConsts.URL_KEY_INTRO + "/" + WebConsts.URL_KEY_INDEX;
	
	/** 問い合わせリストのURL */
	public static final String URL_INQUIRY_INDEX         = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_INDEX;
	
	/** 問い合わせフォームのURL */
	public static final String URL_INQUIRY_FORM          = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_FORM;
	
	/** 問い合わせ確認のURL */
	public static final String URL_INQUIRY_CONFIRM       = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_CONFIRM;
	
	/** 問い合わせ返信フォームのURL */
	public static final String URL_INQUIRY_REPLY_FORM    = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_REPLY_FORM;
	
	/** 問い合わせ返信確認のURL */
	public static final String URL_INQUIRY_REPLY_CONFIRM  = WebConsts.URL_KEY_INQUIRY + "/" + WebConsts.URL_KEY_REPLY_CONFIRM;
	
	/** リダイレクト[/inquiry] */
	public static final String REDIRECT_URL_INQUIRY_INDEX = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_INQUIRY;
	
	/** リダイレクト[/inquiry/form] */
	public static final String REDIRECT_URL_INQUIRY_FORM  = WebConsts.URL_REDIRECT + URL_INQUIRY_FORM;
	
	/** リダイレクト[/inquiry/reply] */
	public static final String REDIRECT_URL_INQUIRY_REPLY = WebConsts.URL_REDIRECT + WebConsts.URL_KEY_INQUIRY + REQUEST_MAPPING_REPLY;
	
	// ---------------------------------------------------------------------------------
	
	/** attribute key */
	
	/** attribute[inquiry] */
	public static final String ATTRIBUTE_INQUIRY      = "inquiry";
	
	public static final String ATTRIBUTE_INQUIRY_LIST = "inquiryList";
}
