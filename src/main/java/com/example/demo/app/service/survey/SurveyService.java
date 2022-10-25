package com.example.demo.app.service.survey;

import java.util.List;

import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.survey.form.SurveySatisForm;

/**
 * 調査サービスインターフェース
 * @author nanai
 *
 */
public interface SurveyService {
	
	/**
	 * 保存
	 * @param model {@link SurveyModel}
	 */
	void save(SurveyModel model);
	
	/**
	 * 更新
	 * @param model {@link SurveyModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	void update(SurveyModel model);
	
	/**
	 * 全選択
	 * @return 調査モデルリスト  {@link List}({@link SurveyModel})
	 */
	List<SurveyModel> getAll();
	
	/**
	 * 全ての評価の数を取得
	 * @return 評価フォームリスト {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionAll();
	
	/**
	 * 年齢別評価の数を取得
	 * @param  age 年齢
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionAge(int age);
	
	/**
	 * 性別による評価の数を取得
	 * @param  ismen 男 or 女
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionSx(int ismen);
	
	/**
	 * 職業別評価の数を取得
	 * @param  prof 職業
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionProf(int prof);
	
	/**
	 * 性別 & 職業別の評価の数を取得
	 * @param ismen 男 or 女
	 * @param prof 職業
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionSxProf(int ismen,  int prof);
	
	/**
	 * 職業 & 年齢別の評価の数を取得
	 * @param  prof 職業
	 * @param  age  年齢
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionProfAge(int prof, int age);
	
	/**
	 * 性別 & 年齢別の評価の数を取得
	 * @param  ismen 男 or 女
	 * @param  age 年齢
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionSxAge(int ismen, int age);
	
	/**
	 * 性別 & 年齢 & 職業別の評価の数を取得
	 * @param  ismen 男 or 女
	 * @param  prof  職業
	 * @param  age   年齢
	 * @return 評価フォーム {@link List}({@link SurveySatisForm})
	 */
	List<SurveySatisForm> countSatisfactionSxProfAge(int ismen,  int prof, int age);

}
