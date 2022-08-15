package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.InquiryReplyModel;

public interface InquiryReplyDao {
	
	void insertReplyInquiry(InquiryReplyModel model);
	
	int updateReplyInquiry(InquiryReplyModel model);
	
	int deleteReplyInquiry(int id);
	
	int deleteReply_byInquiry(int inquiryId);
	
	List<InquiryReplyModel> getAll();
	
	List<InquiryReplyModel> select_byInquiryId(int inquiryId);

	InquiryReplyModel select(int id);
}
