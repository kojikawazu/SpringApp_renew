package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.id.InquiryReplyId;

/**
 * 問い合わせ返信Daoインターフェース
 * @author nanai
 *
 */
public interface InquiryReplyDao {
	
	void insertReplyInquiry(InquiryReplyModel model);
	
	int updateReplyInquiry(InquiryReplyModel model);
	
	int deleteReplyInquiry(InquiryReplyId id);
	
	int deleteReply_byInquiry(InquiryId inquiryId);
	
	List<InquiryReplyModel> getAll();
	
	List<InquiryReplyModel> select_byInquiryId(InquiryId inquiryId);

	InquiryReplyModel select(InquiryReplyId id);
}
