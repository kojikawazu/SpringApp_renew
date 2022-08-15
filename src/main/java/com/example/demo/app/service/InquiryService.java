package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.InquiryModel;

public interface InquiryService {
	
	void save(InquiryModel model);
	
	void update(InquiryModel model);
	
	void delete(int id);
	
	List<InquiryModel> getAll();
	
	InquiryModel select(int id);
	
}
