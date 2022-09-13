package com.example.demo.app.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.service.SurveyService;
import com.example.demo.app.survey.form.SurveyChangeForm;
import com.example.demo.app.survey.form.SurveySatisForm;
import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * 調査返信コントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_SURVEY)
public class SatisReplyController extends SuperSurveyController  {

	/** 男性ボタン */
	private static final String BUTTON_MEN        = "men-button";

	/** 男性番号 */
	private static final int    NUMBER_MEN        = 1;
	
	/** 女性ボタン */
	private static final String BUTTON_WOMEN      = "famale-button";
	
	/** 女性番号 */
	private static final int    NUMBER_WOMEN      = 2;
	
	/** 学生ボタン */
	private static final String BUTTON_STUDENT    = "student-button";
	
	/** 学生番号 */
	private static final int    NUMBER_STUDENT    = 1;
	
	/** 会社員ボタン */
	private static final String BUTTON_EMPLO      = "emplo-button";
	
	/** 会社員番号 */
	private static final int    NUMBER_EMPLO      = 2;
	
	/** 芸能人ボタン */
	private static final String BUTTON_ART        = "art-button";
	
	/** 芸能人番号 */
	private static final int    NUMBER_ART        = 3;
	
	/** 主婦ボタン */
	private static final String BUTTON_WIFE       = "wife-button";
	
	/** 主婦番号 */
	private static final int    NUMBER_WIFE       = 4;
	
	/** 年齢ボタン */
	private static final String BUTTON_MAIN_AGE   = "main-age-button";
	
	/**
	 * コンストラクタ
	 * @param service
	 */
	@Autowired
	public SatisReplyController(
			SurveyService service) {
		super(service);
	}
	
	/**
	 * 調査返信受信
	 * (非同期通信)
	 * @param surveyChangeForm
	 * @return
	 */
	@RequestMapping(AppConsts.REQUEST_MAPPING_SATIS_REPLY)
	@ResponseBody
	public SurveyChangeForm satis_reply(
			@RequestBody SurveyChangeForm surveyChangeForm) {
		List<SurveySatisForm> list = null;
		if(surveyChangeForm.getId() == 1) {
			// 満足度集計
			if(surveyChangeForm.getAllNum() == 1) {
				// 全体満足度集計
				list = this.surveyService.countSatisfactionAll();
			}else {
				String sxName   = surveyChangeForm.getSxName();
				String profName = surveyChangeForm.getProfName();
				String ageName  = surveyChangeForm.getAgeName();
				int outsxName   = 0;
				int outprofName = 0;
				int outageName  = 0;
				
				if(!sxName.isEmpty()) {
					//性別集計
					switch(sxName) {
						// 男性別集計
						case BUTTON_MEN:		outsxName = NUMBER_MEN;				break;
						// 女性別集計
						case BUTTON_WOMEN:		outsxName = NUMBER_WOMEN;			break;
						// その他
						default:				outsxName = WebConsts.ZERO_NUMBER;	break;
					}
				}
				if(!profName.isEmpty()) {
					//職業別集計
					switch(profName) {
						//学生別集計
						case BUTTON_STUDENT:	outprofName = NUMBER_STUDENT;			break;
						//会社員別集計
						case BUTTON_EMPLO:		outprofName = NUMBER_EMPLO;				break;
						//芸能人別集計
						case BUTTON_ART:		outprofName = NUMBER_ART;				break;
						//主婦別集計
						case BUTTON_WIFE:		outprofName = NUMBER_WIFE;				break;
						// その他
						default:				outprofName = WebConsts.ZERO_NUMBER;	break;
					}
				}
				if(!ageName.isEmpty()) {
					// 年齢別集計
					if(!ageName.equals(BUTTON_MAIN_AGE)) {
						String age = ageName.substring(4,6);
						outageName = Integer.parseInt(age);
					}else {
						outageName = 0;
					}
				}
				
				if(outsxName != WebConsts.ZERO_NUMBER && 
						outprofName != WebConsts.ZERO_NUMBER && 
						outageName  != WebConsts.ZERO_NUMBER) {
					// 全て該当
					list = surveyService.countSatisfactionSxProfAge(outsxName, outprofName, outageName);
				}
				else if(outsxName != WebConsts.ZERO_NUMBER && outprofName != WebConsts.ZERO_NUMBER) {
					// 性別＆職業別
					list = surveyService.countSatisfactionSxProf(outsxName, outprofName);
				}else if(outsxName != WebConsts.ZERO_NUMBER && outageName != WebConsts.ZERO_NUMBER) {
					// 性別＆年齢別
					list = surveyService.countSatisfactionSxAge(outsxName, outageName);
				}else if(outprofName != WebConsts.ZERO_NUMBER && outageName != WebConsts.ZERO_NUMBER) {
					// 職業＆年齢別
					list = surveyService.countSatisfactionProfAge(outprofName, outageName);
				}else if(outsxName != WebConsts.ZERO_NUMBER){
					// 性別別
					list = surveyService.countSatisfactionSx(outsxName);
				}else if(outprofName != WebConsts.ZERO_NUMBER) {
					// 職業別
					list = surveyService.countSatisfactionProf(outprofName);
				}else if(outageName != WebConsts.ZERO_NUMBER) {
					// 年齢別
					list = surveyService.countSatisfactionAge(outageName); 
				}else {
					// 全て
					list = this.surveyService.countSatisfactionAll();
				}
			}
		}
		
		if( list != null ) {
			// 設定
			int inputIdx = 0;
			surveyChangeForm.setSatis5(list.get(inputIdx).getSatisfaction());
			surveyChangeForm.setSatis4(list.get(inputIdx + 1).getSatisfaction());
			surveyChangeForm.setSatis3(list.get(inputIdx + 2).getSatisfaction());
			surveyChangeForm.setSatis2(list.get(inputIdx + 3).getSatisfaction());
			surveyChangeForm.setSatis1(list.get(inputIdx + 4).getSatisfaction());
			
			surveyChangeForm.setAnswerCnt(
					list.get(inputIdx).getSatisfaction() + 
					list.get(inputIdx + 1).getSatisfaction() + 
					list.get(inputIdx + 2).getSatisfaction() + 
					list.get(inputIdx + 3).getSatisfaction() + 
					list.get(inputIdx + 4).getSatisfaction() );
		}
		return surveyChangeForm;
	}
	
}
