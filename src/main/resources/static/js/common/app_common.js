/**
 * [共通部Jquery]
 */

 /** main関数 */
$(function () {
	
	/** 初回実行 */
	/** --------------------------------------------------- */
	/** 上に戻るボタンの設定 */
	setBackToTopButton();
	
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

/** インターバル着火時間[1h] */
let LOGIN_USER_INTERVAL_TIME = 60 * 60 * 1000;
/** インターバルの設定 */
function setLoginUserInterval(){
	setInterval(function(){
		
		// ユーザーログイン状態チェック[非同期実行]
		$.ajax({
			url:  "/security/userinterval",
			success: function(data) {
				// [成功]
				if (data == "delete") {
					// タイムアウト発生。自動ログアウト実行
					let yesLogin = $('#yesLogin');
					yesLogin.submit();
				}
			},
			error: function() {
				// [失敗]
				console.log("frontend interval NG");
			}
		});
		
	}, LOGIN_USER_INTERVAL_TIME);
}
