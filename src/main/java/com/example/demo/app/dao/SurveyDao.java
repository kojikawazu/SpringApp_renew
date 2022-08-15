package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.SurveyModel;

public interface SurveyDao {

	void insertSurvey(SurveyModel model);
	
	int updateSurvey(SurveyModel model);
	
	List<SurveyModel> getAll();
	
	int countSatisfactionAll(int satis);
	
	int countSatisfactionAge(int satis, int age);
	
	int countSatisfactionSx(int satis, int ismen);
	
	int countSatisfactionProf(int satis, int prof);
	
	int countSatisfactionSxProf(int satis, int ismen,  int prof);
	
	int countSatisfactionProfAge(int satis, int prof, int age);
	
	int countSatisfactionSxAge(int satis, int ismen, int age);
	
	int countSatisfactionSxProfAge(int satis, int ismen,  int prof, int age);
}
