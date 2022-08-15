package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.InquiryReplyModel;

public interface InquiryReplyService {
	
	void save(InquiryReplyModel model);
	
	void update(InquiryReplyModel model);
	
	void delete(int id);
	
	void delete_byInquiry(int inquiryId);
	
	List<InquiryReplyModel> getAll();
	
	List<InquiryReplyModel> select_byInquiryId(int id);
	
	InquiryReplyModel select(int id);
}
