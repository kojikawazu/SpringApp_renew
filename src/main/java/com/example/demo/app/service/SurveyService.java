package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.SurveyModel;
import com.example.demo.app.survey.form.SurveySatisForm;

/**
 * 調査サービスインターフェース
 * @author nanai
 *
 */
public interface SurveyService {
	
	/**
	 * 保存
	 * @param model
	 */
	void save(SurveyModel model);
	
	/**
	 * 更新
	 * @param model
	 */
	void update(SurveyModel model);
	
	/**
	 * 全選択
	 * @return 調査モデルリスト
	 */
	List<SurveyModel> getAll();
	
	/**
	 * 全ての評価の数を取得
	 * @return 評価フォームリスト
	 */
	List<SurveySatisForm> countSatisfactionAll();
	
	/**
	 * 年齢別評価の数を取得
	 * @param  age 年齢
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionAge(int age);
	
	/**
	 * 性別による評価の数を取得
	 * @param  ismen 男 or 女
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionSx(int ismen);
	
	/**
	 * 職業別評価の数を取得
	 * @param  prof 職業
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionProf(int prof);
	
	/**
	 * 性別 & 職業別の評価の数を取得
	 * @param ismen 男 or 女
	 * @param prof 職業
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionSxProf(int ismen,  int prof);
	
	/**
	 * 職業 & 年齢別の評価の数を取得
	 * @param  prof 職業
	 * @param  age  年齢
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionProfAge(int prof, int age);
	
	/**
	 * 性別 & 年齢別の評価の数を取得
	 * @param  ismen 男 or 女
	 * @param  age 年齢
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionSxAge(int ismen, int age);
	
	/**
	 * 性別 & 年齢 & 職業別の評価の数を取得
	 * @param  ismen 男 or 女
	 * @param  prof  職業
	 * @param  age   年齢
	 * @return 評価フォーム
	 */
	List<SurveySatisForm> countSatisfactionSxProfAge(int ismen,  int prof, int age);

}
