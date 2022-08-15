package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.InquiryModel;

public interface InquiryDao {
	
	void insertInquiry(InquiryModel model);
	
	int updateInquiry(InquiryModel model);
	
	int deleteInquiry(int id);
	
	List<InquiryModel> getAll();
	
	InquiryModel select(int id);
	
}
