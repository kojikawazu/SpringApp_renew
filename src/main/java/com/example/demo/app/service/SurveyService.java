package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.SurveyModel;
import com.example.demo.app.servey.SurveySatisForm;

public interface SurveyService {
	
	void save(SurveyModel model);
	
	void update(SurveyModel model);
	
	List<SurveyModel> getAll();
	
	List<SurveySatisForm> countSatisfactionAll();
	
	List<SurveySatisForm> countSatisfactionAge(int age);
	
	List<SurveySatisForm> countSatisfactionSx(int ismen);
	
	List<SurveySatisForm> countSatisfactionProf(int prof);
	
	List<SurveySatisForm> countSatisfactionSxProf(int ismen,  int prof);
	
	List<SurveySatisForm> countSatisfactionProfAge(int prof, int age);
	
	List<SurveySatisForm> countSatisfactionSxAge(int ismen, int age);
	
	List<SurveySatisForm> countSatisfactionSxProfAge(int ismen,  int prof, int age);

}
