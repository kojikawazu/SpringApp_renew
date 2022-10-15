package com.example.demo.app.dao.survey;

import java.util.List;

import com.example.demo.app.entity.survey.SurveyModel;

/**
 * 調査インターフェース
 * @author nanai
 *
 */
public interface SurveyDao {

	/**
	 * 追加
	 * @param model
	 */
	void insertSurvey(SurveyModel model);
	
	/**
	 * 更新
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	int updateSurvey(SurveyModel model);
	
	/**
	 * 全選択
	 * @return 調査モデルリスト
	 */
	List<SurveyModel> getAll();
	
	/**
	 * 全ての評価の数を取得
	 * @param  satis 評価
	 * @return 評価の数
	 */
	int countSatisfactionAll(int satis);
	
	/**
	 * 年齢による評価の数を取得
	 * @param  satis 評価
	 * @param  age   年齢
	 * @return 評価の数
	 */
	int countSatisfactionAge(int satis, int age);
	
	/**
	 * 性別による評価の数を取得
	 * @param  satis 評価
	 * @param  ismen 男 or 女
	 * @return 評価の数
	 */
	int countSatisfactionSx(int satis, int ismen);
	
	/**
	 * 職業による評価の数を取得
	 * @param  satis 評価
	 * @param  prof  職業
	 * @return 評価の数
	 */
	int countSatisfactionProf(int satis, int prof);
	
	/**
	 * 性別 & 職業の評価の数を取得
	 * @param  satis 評価
	 * @param  ismen 男 or 女
	 * @param  prof  職業
	 * @return 評価の数
	 */
	int countSatisfactionSxProf(int satis, int ismen,  int prof);
	
	/**
	 * 職業 & 年齢別の評価の数
	 * @param  satis 評価
	 * @param  prof  職業
	 * @param  age   年齢
	 * @return 評価の数
	 */
	int countSatisfactionProfAge(int satis, int prof, int age);
	
	/**
	 * 性別 & 年齢別の評価の数を取得
	 * @param  satis 評価
	 * @param  ismen 男 or 女
	 * @param  age   年齢
	 * @return 評価の数
	 */
	int countSatisfactionSxAge(int satis, int ismen, int age);
	
	/**
	 * 性別 & 年齢 & 職業別の評価の数
	 * @paran satis 評価
	 * @param ismen 男 or 女
	 * @param prof  職業
	 * @param age   年齢
	 * @return 評価の数
	 */
	int countSatisfactionSxProfAge(int satis, int ismen,  int prof, int age);
}
