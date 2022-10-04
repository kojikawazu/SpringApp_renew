/**
 * [共通部Jquery]
 */
 
 /** main関数 */
$(function () {
	
	/** 初回実行 */
	/** --------------------------------------------------- */
	/** 上に戻るボタンの設定 */
	setBackToTopButton();
});

/** 上に戻るボタンの設定 */
function setBackToTopButton(){
	/** pagetopクラスに設定する */
	var pagetop = $('.pagetop');
	/** 最初は非表示 */
	pagetop.hide();
	
	/** スクロール設定 */
	$(window).scroll(function () {
		if ($(this).scrollTop() > 100) {
			/** フェードイン */
			pagetop.fadeIn();
		} else {
			/** フェードアウト */
			pagetop.fadeOut();
 		}
  	});
  	
  	/** 上に戻るボタンクリック時の設定 */
	pagetop.click(function () {
 		$('body, html').animate({ scrollTop: 0 }, 500);
		return false;
	});
}