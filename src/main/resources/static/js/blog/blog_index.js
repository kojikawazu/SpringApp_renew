// ---------------------------------------------------------------------------
// 【ブログ.js】
// ---------------------------------------------------------------------------

/**
 * 削除確認
 */
function checkDelete(){
	return confirm("削除してもよろしいですか？");
}

/**
 * リンククリック処理(前のページ番号へ)
 */
function OnLinkClick_Back() {
	let pageIdx = parseInt(pagingBlogForm.pagingIdx.value, 10);

	// 前のページ番号に設定
	pageIdx--;
	if (pageIdx <= 0)	pageIdx = 1;
	pagingBlogForm.pagingIdx.value = pageIdx;
	//console.log(pagingBlogForm.pagingIdx.value);

	// フォーム送信
	pagingBlogForm.submit();
	return false;
}

/**
 * リンククリック処理(ページ指定)
 */
function OnLinkClick_Paging(pageIdx) {
	let     changePageIdx          = parseInt(pageIdx, 10);
	pagingBlogForm.pagingIdx.value = changePageIdx;
	//console.log(pagingBlogForm.pagingIdx.value);

	// フォーム送信
	pagingBlogForm.submit();
	return false;
}

/**
 * リンククリック処理(次のページ番号へ)
 */
function OnLinkClick_Next() {
	let     pageIdx    = parseInt(pagingBlogForm.pagingIdx.value, 10);
	const   pageSizeId = document.getElementById("pageSize");
	let     pageSize   = parseInt(pageSizeId.textContent, 10);

	// 次のページ番号に設定
	pageIdx++;
	if (pageIdx > pageSize)	pageIdx = pageSize;
	pagingBlogForm.pagingIdx.value = pageIdx;
	//console.log(pagingBlogForm.pagingIdx.value);

	// フォーム送信
	pagingBlogForm.submit();
	return false;
}

/**
 * Function(非同期通信設定)
 */
$(function(){
	// ブログのいいね処理
	let btnLayoutClass = ".bloglayout";
	let thanksBtnName   = "#thanksBtn";
	let len = $(btnLayoutClass).length;
	let blogList = [];
	
	// ブログレイアウトリストの取得
	for(let idx=0; idx<len; idx++){
		blogList.push($(btnLayoutClass)[idx].id);
	}
	
	// ブログ1つ1つに設定していく
	for(let idx=0; idx<len; idx++){
		// ※複数のformの非同期処理対策を追加。
		$(thanksBtnName + blogList[idx]).attr({
			passive: false
		});
		
		$(thanksBtnName + blogList[idx]).submit(function(event){
			// いいねボタンを押下するといいね数加算する。
			event.preventDefault();
			console.log(event);
			let $form = $(this);
			
			// 非同期通信
			$.ajax({
				url:  $form.attr('action'),
				type: $form.attr('method'),
				data: $form.serialize(),
				success: function(data){
					// [成功]
					// いいね数が返ってくるので、いいねボタンに反映する
					if(data==null){}
					else{
						$form.children('input')[2].value = data + "　いいね";
					}
					event.preventDefault();
				},
				error: function(xhr, textStatus, errorThrown) {
					// [失敗]
					var res = {}
					try {
						res = $.parseJSON(xhr.responseText);
						console.log(res);
						console.log(textStatus);
						console.log(errorThrown);
					} catch (e) {}
				}
			});
		});
	}
	
	// ブログ返信のいいね処理
	// 複数のformの非同期処理対策を追加。
	$(".replycnt_tag form").attr({
		passive: false
	});
	$(".replycnt_tag form").submit(function(event){
		// いいねボタンを押下するといいね数加算する。
		event.preventDefault();
		let $form = $(this);
		
		// 非同期通信
		$.ajax({
			url:  $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function(data){
				// [成功]
				// いいね数が返ってくるので、いいねボタンに反映する
				if(data==null){}
				else{
					$form.children('input')[2].value = data + "　いいね";	
				}
				event.preventDefault();
			},
			error: function(xhr, textStatus, errorThrown) {
				// [失敗]
				var res = {}
				try {
					res = $.parseJSON(xhr.responseText);
					console.log(res);
					console.log(textStatus);
					console.log(errorThrown);
				} catch (e) {}
			}
		});
	});
	
});

