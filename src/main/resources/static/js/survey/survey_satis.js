
$(function(){

	// TODO クリックイベント
	// ボタンの状態
	// 選択中
	// btn-warning
	// 未選択(選択可能)
	// btn-primary
	// 未選択(選択不可))
	// btn-secondary	

	/* 満足度合計ボタン */
	$("#main-satis-button").click(function(){
		//console.log("ok");
		// TODO クリックイベント
		if($(this).hasClass('btn-primary')){
			// 自身のボタン
			$(this).removeClass('btn-primary');
			$(this).addClass('btn-warning');
		
			$otherButton = $("#main-questsum-button");
			$otherButton.removeClass('btn-warning');
			$otherButton.addClass('btn-primary');
		}
	});
	
	/* 投稿者数合計ボタン */
	$("#main-questsum-button").click(function(){
		if($(this).hasClass('btn-primary')){
			// 自身のボタン
			$(this).removeClass('btn-primary');
			$(this).addClass('btn-warning');
		
			$otherButton = $("#main-satis-button");
			$otherButton.removeClass('btn-warning');
			$otherButton.addClass('btn-primary');
		}
	});
	
	/* 全てボタン */
	$("#main-all-button").click(function(){
		buttonClear_sx();
		buttonClear_prof();
		buttonClear_age();
		
		if($(this).hasClass('btn-primary')){
			// 自身のボタン
			focusButton($(this));
		}
		changeChart($('.main-button .btn-warning'), $('.all-button .btn-warning'));
	});
	
	/* 性別別ボタン */
	$('.sx-button .btn').click(function(){
		clearButton($('.all-button .btn'));
		if($(this).hasClass('btn-primary')){
			// 他のサブボタンをクリア
			clearButton($('.sx-button .btn'));
			// 自身のボタン
			focusButton($(this));
		}
		changeChart($('.main-button .btn-warning'), $('.sx-button .btn-warning'));
	});
	
	/* 職業別ボタン */
	$('.prof-button .btn').click(function(){
		clearButton($('.all-button .btn'));
		if($(this).hasClass('btn-primary')){
			clearButton($('.prof-button .btn'));
			// 自身のボタン
			focusButton($(this));
		}
		changeChart($('.main-button .btn-warning'), $('.prof-button .btn-warning'));
	});
	
	/* 年齢別ボタン */
	$(".age-button .btn").click(function(){
		clearButton($('.all-button .btn'));
		if($(this).hasClass('btn-primary')){
			clearButton($('.age-button .btn'));
			// 自身のボタン
			focusButton($(this));
		}
		changeChart($('.main-button .btn-warning'), $('.age-button .btn-warning'));
	});
	
	
	
	function changeChart($main_button, $sub_button){
		if( $main_button.attr('id') == 'main-satis-button' ){
			let $sub = $sub_button.attr('id'); 
			
			//全てのボタン
			let sx = ($('.sx-button .btn-warning').length > 0 ?
						$('.sx-button .btn-warning').attr("id") : "");
			let prof = ($('.prof-button .btn-warning').length > 0 ?
						$('.prof-button .btn-warning').attr("id") : "");
			let age = ($('.age-button .btn-warning').length > 0 ?
						$('.age-button .btn-warning').attr("id") : "");
			
			let inputData = {
					"id": 1,
					"name": $sub,
					"allNum": $('.all-button .btn-warning').length,
					"sxName": sx,
					"profName": prof,
					"ageName": age,
					"satis5": 0,
					"satis4": 0,
					"satis3": 0,
					"satis2": 0,
					"satis1": 0
				};
			createChart(inputData);
		}else{
			
		}
	}
	
	function createChart(inputData){
		$.ajax({
			url: '/survey/satis_reply',
			type: 'POST', 
			contentType: 'application/json',
			data: JSON.stringify(inputData),
			success: function(data){	
				let ctx = document.getElementById("myBarChart");
				let barChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: ['5', '4', '3', '2', '1'],
						datasets:  [
							{
								label: '評価累計',
								data: [
								 	data["satis5"],
								 	data["satis4"], 
								 	data["satis3"],
								 	data["satis2"],
								 	data["satis1"]
								 ],
								backgroundColor: "rgba(219,39,91,0.5)"
							}
						]
					},
					options: {
						title: {
							display: true,
							text: '評価点(アンケート投稿数' + data['answerCnt'] + '人)',
							position: 'top',
							fontSize: 20,
						},
						legend:{
							display: false,
							position: 'bottom',
						},
						scales: {
							yAxes: [{
					          ticks: {
					            suggestedMax: 10,
					            suggestedMin: 0,
					            stepSize: 1,
					            callback: function(value, index, values){
					              return  value
					            }
					          } // ticks
					        }] //yAxes
						}, // scales
					} // options
				}); // charts
			} // success
		}); // ajax
	}
	
	function buttonClear_sx(){
		$button = $(".sx-button .btn-warning");
		clearButton($button);
	}
	
	function buttonClear_prof(){
		$button = $(".prof-button .btn-warning");
		clearButton($button);
	}	
	
	function buttonClear_age(){
		$button = $(".age-button .btn-warning");
		clearButton($button);
	}
	
	function clearButton($button){
		$button.removeClass("btn-warning");
		$button.addClass("btn-primary");
	}
	
	function focusButton($button){
		$button.removeClass("btn-primary");
		$button.addClass("btn-warning");
	}
	
	// --------------------------------------------------------------------------
	// load
	
	
	let inputData = {
				"id": 1,
				"name": "main-all-button",
				"allNum": 1,
				"sxName": "",
				"profName": "",
				"ageName": "",
				"satis5": 0,
				"satis4": 0,
				"satis3": 0,
				"satis2": 0,
				"satis1": 0
			};
	createChart(inputData);

});