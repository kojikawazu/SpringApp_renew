package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.common.id.InquiryId;

/**
 * 問い合わせDaoインターフェース
 * @author nanai
 *
 */
public interface InquiryDao {
	
	void insertInquiry(InquiryModel model);
	
	int updateInquiry(InquiryModel model);
	
	int deleteInquiry(InquiryId id);
	
	List<InquiryModel> getAll();
	
	InquiryModel select(InquiryId id);
	
}
