
function checkDelete(){
	// TODO 削除確認
	return confirm("削除してもよろしいですか？");
}

// FUNC 非同期通信
$(function(){
	// TODO ブログのいいね処理
	let len = $(".bloglayout").length;
	let layoutIdx = $(".bloglayout")[0].id;
	for(let idx=layoutIdx; idx<layoutIdx+len+1; idx++){
		// ※複数のformの非同期処理対策を追加。
		$("#thanksBtn" + idx).attr({
			passive: false
		});
		
		$("#thanksBtn" + idx).submit(function(event){
			// TODO いいねボタンを押下するといいね数加算する。
			event.preventDefault();
			console.log(event);
			let $form = $(this);
			$.ajax({
				url:  $form.attr('action'),
				type: $form.attr('method'),
				data: $form.serialize(),
				success: function(data){
					if(data==null){}
					else{
						$form.children('input')[2].value = data + "　いいね";	
					}
					event.preventDefault();
				},
				error: function(xhr, textStatus, errorThrown) {
					console.log("デバッグエラー");
					var res = {}
					try {
						res = $.parseJSON(xhr.responseText);
						console.log(res);
					} catch (e) {}
				}
			});
		});
	}
	
	// TODO ブログ返信のいいね処理
	// 複数のformの非同期処理対策を追加。
	$(".replycnt_tag form").attr({
		passive: false
	});
	$(".replycnt_tag form").submit(function(event){
		// TODO いいねボタンを押下するといいね数加算する。
		event.preventDefault();			
		let $form = $(this);
		$.ajax({
			url:  $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function(data){
				if(data==null){}
				else{
					$form.children('input')[2].value = data + "　いいね";	
				}
				event.preventDefault();
			},
			error: function(xhr, textStatus, errorThrown) {
				console.log("デバッグエラー");
				var res = {}
				try {
					res = $.parseJSON(xhr.responseText);
					console.log(res);
				} catch (e) {}
			}
		});
	});
	
});

