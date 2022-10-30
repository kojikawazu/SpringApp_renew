/**
 * [共通部Jquery]
 */
 
let RESPONSE_NONE				= "none";
let RESPONSE_OK					= "OK";
let RESPONSE_INIT				= "init";
let RESPONSE_DELETE				= "delete";
let RESPONSE_RELOAD				= "reload";

let YES_LOGIN					= "#yesLogin";
let YES_LOGIN_ID				= "#yesLoginId";
let YES_LOGIN_CHILD_ID			= "#yesLoginIdChild";
let NO_LOGIN					= "#noLogin";
let NO_LOGIN_ID					= "#noLoginId";
let NO_LOGIN_CHILD_ID			= "#noLoginIdChild";
let LOGOUT_USER_NAME_ID			= "#logoutUserName";

let LOGIN_USER_NAME_VALUE_INIT	= "";
let LOGIN_SHOW_VALUE			= "yes";

 /** main関数 */
$(function () {
	
	/** 初回実行 */
	/** --------------------------------------------------- */
	/** 上に戻るボタンの設定 */
	setBackToTopButton();
	
	/** ユーザーログイン/ログアウトの設定 */
	setUserLoginForm();
	
	/** ユーザーログアウトの設定 */
	setUserLogoutForm();
	
	/** インターバルの設定 */
	setLoginUserInterval();
});

/** ------------------------------------------------------- */

/** 上に戻るボタンの設定 */
function setBackToTopButton() {
	/** pagetopクラスに設定する */
	var pagetop = $('.pagetop');
	/** 最初は非表示 */
	pagetop.hide();
	
	/** スクロール設定 */
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			/** フェードイン */
			pagetop.fadeIn();
		} else {
			/** フェードアウト */
			pagetop.fadeOut();
 		}
  	});
  	
  	/** 上に戻るボタンクリック時の設定 */
	pagetop.click(function() {
 		$('body, html').animate({ scrollTop: 0 }, 500);
		return false;
	});
}

/** ------------------------------------------------------- */

/** ユーザーログインの設定 */
function setUserLoginForm() {
	
	let noLoginId		= NO_LOGIN_ID;
	let noLoginIdChild	= NO_LOGIN_CHILD_ID;
	let noLoginForm		= NO_LOGIN;
	
	
	/** ログインフォーム設定 */
	let loginId_text = $(noLoginId).find(noLoginIdChild).text();
	if (loginId_text == LOGIN_SHOW_VALUE) {
		$(noLoginId).show();
	} else {
		$(noLoginId).hide();
	}
	
	// ※formの非同期処理対策を追加。
	$(noLoginForm).attr({
		passive: false
	});
	
	/** ログインなしのログインボタン設定 */
	$(noLoginForm).submit(function(event) {
		/** ログインボタンを押下するとここが実行される */ 
		event.preventDefault();
		let $form = $(this);
		
		/** 非同期通信 */
		$.ajax({
			url:  $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function(data) {
				/** [成功] */
				console.log("frontend login OK");
				console.log(data);
				
				if (data.message == RESPONSE_OK) {
					let changeNoLoginId     = NO_LOGIN_ID;
					let changeYesLoginId    = YES_LOGIN_ID;
					let logoutUserName		= LOGOUT_USER_NAME_ID;
					
					/** ユーザー名設定 */
					$(logoutUserName).val(data.userName);
					
					/** ログイン/ログアウト表示切替 */
					$(changeNoLoginId).hide();
					$(changeYesLoginId).show();
					
					/** ページリロード */
					location.reload();
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				// [失敗]
				console.log("frontend login NG");
			}
		});
	});
}

/** ユーザーログアウトの設定 */
function setUserLogoutForm() {
	
	let yesLoginId		= YES_LOGIN_ID;
	let yesLoginIdChild	= YES_LOGIN_CHILD_ID;
	let yesLoginForm	= YES_LOGIN;
	
	/** ログアウトフォームの設定 */
	let loginId_text = $(yesLoginId).find(yesLoginIdChild).text();
	if (loginId_text == LOGIN_SHOW_VALUE) {
		$(yesLoginId).show();
	} else {
		$(yesLoginId).hide();
	}
	
	// ※formの非同期処理対策を追加。
	$(yesLoginForm).attr({
		passive: false
	});
	
	/** ログアウトボタン設定 */
	$(yesLoginForm).submit(function(event) {
		// ログアウトボタンを押下するとここが実行される
		event.preventDefault();
		let $form = $(this);
		
		$.ajax({
			url:  $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function(data) {
				// [成功]
				console.log("frontend logout OK");
				console.log(data);
				
				if (data == RESPONSE_OK || data == RESPONSE_INIT) {
					let changeNoLoginId		= NO_LOGIN_ID;
					let changeYesLoginId	= YES_LOGIN_ID;
					let logoutUserName		= LOGOUT_USER_NAME_ID;
				
					/** ユーザー名初期化 */
					$(logoutUserName).val(LOGIN_USER_NAME_VALUE_INIT);
				
					/** ログイン/ログアウト表示切替 */
					$(changeNoLoginId).show();
					$(changeYesLoginId).hide();
					
					/** ページリロード */
					location.reload();
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				// [失敗]
				console.log("frontend logout NG");
			}
		});
	});
}

/** インターバル着火時間[1h] */
let LOGIN_USER_INTERVAL_TIME = 60 * 60 * 1000;
/** インターバルの設定 */
function setLoginUserInterval(){
	setInterval(function(){
		
		$.ajax({
			url:  "/user/userinterval",
			success: function(data) {
				// [成功]
				console.log("frontend interval OK");
				console.log(data);
				
				if (data == RESPONSE_DELETE) {
					let changeNoLoginId		= NO_LOGIN_ID;
					let changeYesLoginId	= YES_LOGIN_ID;
					let logoutUserName		= LOGOUT_USER_NAME_ID;
					
					/** ユーザー名初期化 */
					$(logoutUserName).val(LOGIN_USER_NAME_VALUE_INIT);
				
					/** ログイン/ログアウト表示切替 */
					$(changeNoLoginId).show();
					$(changeYesLoginId).hide();
					
					/** ページリロード */
					location.reload();
				} else if(data == RESPONSE_RELOAD) {
					/** ページリロード */
					location.reload();
				}
				
			},
			error: function(xhr, textStatus, errorThrown) {
				// [失敗]
				console.log("frontend interval NG");
			}
		});
		
	}, LOGIN_USER_INTERVAL_TIME);
}