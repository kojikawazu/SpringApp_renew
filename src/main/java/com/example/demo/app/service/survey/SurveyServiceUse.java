package com.example.demo.app.service.survey;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.survey.SurveyDao;
import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.survey.form.SurveySatisForm;
import com.example.demo.common.common.WebConsts;

/**
 * 調査サービスクラス
 * @author nanai
 *
 */
@Service
public class SurveyServiceUse implements SurveyService {
	
	/** 評価最大数 */
	private static final int MAX_SATIS = 5;

	/** daoクラス */
	private final SurveyDao dao;
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Autowired
	public SurveyServiceUse(SurveyDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 保存
	 * @param model
	 */
	@Override
	public void save(SurveyModel model) {
		this.dao.insertSurvey(model);
	}

	/**
	 * 更新
	 * @param model
	 */
	@Override
	public void update(SurveyModel model) {
		if(this.dao.updateSurvey(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 全選択
	 * @return 調査モデルリスト
	 */
	@Override
	public List<SurveyModel> getAll() {
		return this.dao.getAll();
	}

	/**
	 * 全ての評価の数を取得
	 * @return 評価フォームリスト
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionAll() {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionAll(idx));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 年齢別評価の数を取得
	 * @param  age 年齢
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionAge(int age) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionAge(idx, age));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 性別による評価の数を取得
	 * @param  ismen 男 or 女
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionSx(int ismen) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSx(idx, ismen));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 職業別評価の数を取得
	 * @param  prof 職業
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionProf(int prof) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionProf(idx, prof));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 性別 & 職業別の評価の数を取得
	 * @param ismen 男 or 女
	 * @param prof 職業
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionSxProf(int ismen, int prof) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSxProf(idx, ismen, prof));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 職業 & 年齢別の評価の数を取得
	 * @param  prof 職業
	 * @param  age  年齢
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionProfAge(int prof, int age) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionProfAge(idx, prof, age));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 性別 & 年齢別の評価の数を取得
	 * @param  ismen 男 or 女
	 * @param  age 年齢
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionSxAge(int ismen, int age) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSxAge(idx, ismen, age));
			list.add(form);
		}
		
		return list;
	}

	/**
	 * 性別 & 年齢 & 職業別の評価の数を取得
	 * @param  ismen 男 or 女
	 * @param  prof  職業
	 * @param  age   年齢
	 * @return 評価フォーム
	 */
	@Override
	public List<SurveySatisForm> countSatisfactionSxProfAge(int ismen, int prof, int age) {
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		
		for(int idx=MAX_SATIS; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSxProfAge(idx, ismen, prof, age));
			list.add(form);
		}
		
		return list;
	}
}
