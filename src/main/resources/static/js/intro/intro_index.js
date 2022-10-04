/**
 * [自己紹介Jquery]
 */
 
 /** main関数 */
$(function () {
	
	/** 初回実行 */
	/** --------------------------------------------------- */
	/** 初回にフェード処理を実行 */
	fadeAnimation();
	
	/** 全体設定 */
	/** --------------------------------------------------- */
	/** windows画面スクロール時にフェード処理実行 */
	$(window).scroll(function () {
		fadeAnimation();
	});
	
	/** 経験フェーズ */
	/** --------------------------------------------------- */
	/** 経験の詳細 - 初期化(非表示にする) */
	$(".exper-detail-after").css("display", "none");
	
	/** 経験の詳細 - 表示、非表示アニメーション */
	$(".exper-after").on("click", function () {
		$(this).find(".exper-detail-after").slideToggle(500);
	});
});

/** フェードインアニメーション関数 */
function fadeAnimation(){
	const windowHeight = $(window).height();
	const scroll       = $(window).scrollTop();

	$('.fadeclass').each(function () {
		const targetPosition = $(this).offset().top;
		if (scroll > targetPosition - windowHeight + 100) {
			$(this).addClass("fadeinclass");
		}
	});
}
