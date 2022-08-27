package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.InquiryModel;
import com.example.demo.common.id.InquiryId;

/**
 * 問い合わせサービスインターフェース
 * @author nanai
 *
 */
public interface InquiryService {
	
	void save(InquiryModel model);
	
	void update(InquiryModel model);
	
	void delete(InquiryId id);
	
	List<InquiryModel> getAll();
	
	InquiryModel select(InquiryId id);
	
}
