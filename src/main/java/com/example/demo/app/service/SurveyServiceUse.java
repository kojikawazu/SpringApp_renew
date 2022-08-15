package com.example.demo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.SurveyDao;
import com.example.demo.app.entity.SurveyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.servey.SurveySatisForm;

@Service
public class SurveyServiceUse implements SurveyService {

	private final SurveyDao dao;
	
	@Autowired
	public SurveyServiceUse(SurveyDao dao) {
		// TODO コンストラクタ
		this.dao = dao;
	}
	
	@Override
	public void save(SurveyModel model) {
		// TODO 保存処理
		this.dao.insertSurvey(model);
	}

	@Override
	public void update(SurveyModel model) {
		// TODO 更新処理
		if(this.dao.updateSurvey(model) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public List<SurveyModel> getAll() {
		// TODO 全て取得
		return this.dao.getAll();
	}

	@Override
	public List<SurveySatisForm> countSatisfactionAll() {
		// TODO 全ての評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionAll(idx));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionAge(int age) {
		// TODO 年齢による評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionAge(idx, age));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionSx(int ismen) {
		// TODO 性別による評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSx(idx, ismen));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionProf(int prof) {
		// TODO 職業による評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionProf(idx, prof));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionSxProf(int ismen, int prof) {
		// TODO 性別＆職業の評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSxProf(idx, ismen, prof));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionProfAge(int prof, int age) {
		// TODO 職業&年齢別の評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionProfAge(idx, prof, age));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionSxAge(int ismen, int age) {
		// TODO 性別＆年齢別の評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSxAge(idx, ismen, age));
			list.add(form);
		}
		return list;
	}

	@Override
	public List<SurveySatisForm> countSatisfactionSxProfAge(int ismen, int prof, int age) {
		// TODO 性別＆年齢＆職業別の評価の数を取得
		List<SurveySatisForm> list = new ArrayList<SurveySatisForm>();
		for(int idx=5; idx>0; idx--){
			SurveySatisForm form = new SurveySatisForm();
			form.setId(idx);
			form.setSatisfaction(this.dao.countSatisfactionSxProfAge(idx, ismen, prof, age));
			list.add(form);
		}
		return list;
	}
}
