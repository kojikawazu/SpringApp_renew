package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.id.InquiryReplyId;

/**
 * 問い合わせ返信サービスインターフェース
 * @author nanai
 *
 */
public interface InquiryReplyService {
	
	void save(InquiryReplyModel model);
	
	void update(InquiryReplyModel model);
	
	void delete(InquiryReplyId id);
	
	void delete_byInquiry(InquiryId inquiryId);
	
	List<InquiryReplyModel> getAll();
	
	List<InquiryReplyModel> select_byInquiryId(InquiryId id);
	
	InquiryReplyModel select(InquiryReplyId id);
}
