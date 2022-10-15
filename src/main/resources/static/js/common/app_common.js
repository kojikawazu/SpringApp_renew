/**
 * [共通部Jquery]
 */
 
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
});

/** ------------------------------------------------------- */

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

/** ------------------------------------------------------- */

/** ユーザーログインの設定 */
function setUserLoginForm(){
	
	let noLoginId   = "#noLoginId"
	let noLoginForm = "#noLogin"
	
	/** ログインフォーム設定 */
	let loginId_text = $(noLoginId).find('div').text();
	if(loginId_text == "yes"){
		$(noLoginId).show();
	} else{
		$(noLoginId).hide();
	}
	
	// ※formの非同期処理対策を追加。
	$(noLoginForm).attr({
		passive: false
	});
	
	/** ログインなしのログインボタン設定 */
	$(noLoginForm).submit(function(event) {
		// ログインボタンを押下するとここが実行される
		event.preventDefault();
		console.log(event);
		let $form = $(this);
		
		$.ajax({
			url:  $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function(data){
				// [成功]
				console.log("[debug]frontend login OK");
				console.log(data);
				
				if(data == "OK"){
					let changeNoLoginId     = "#noLoginId"
					let changeYesLoginId    = "#yesLoginId"
				
					/** ログイン/ログアウト表示切替 */
					$(changeNoLoginId).hide();
					$(changeYesLoginId).show();
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				// [失敗]
				console.log("[debug]frontend login NG");
			
			}
		});
	});
}

/** ユーザーログアウトの設定 */
function setUserLogoutForm(){
	
	let yesLoginId    = "#yesLoginId"
	let yesLoginForm  = "#yesLogin"
	
	/** ログアウトフォームの設定 */
	let loginId_text = $(yesLoginId).find('div').text();
	if(loginId_text == "yes"){
		$(yesLoginId).show();
	} else{
		$(yesLoginId).hide();
	}
	
	// ※formの非同期処理対策を追加。
	$(yesLoginForm).attr({
		passive: false
	});
	
	/** ログインなしのログインボタン設定 */
	$(yesLoginForm).submit(function(event) {
		// ログインボタンを押下するとここが実行される
		event.preventDefault();
		console.log(event);
		let $form = $(this);
		
		$.ajax({
			url:  $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function(data){
				// [成功]
				console.log("[debug]frontend logout OK");
				console.log(data);
				
				let changeNoLoginId     = "#noLoginId"
				let changeYesLoginId    = "#yesLoginId"
				
				/** ログイン/ログアウト表示切替 */
				$(changeNoLoginId).show();
				$(changeYesLoginId).hide();
			},
			error: function(xhr, textStatus, errorThrown) {
				// [失敗]
				console.log("[debug]frontend logout NG");
			
			}
		});
	});
}