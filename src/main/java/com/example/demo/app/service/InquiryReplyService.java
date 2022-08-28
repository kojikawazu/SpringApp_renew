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
	
	/**
	 * 保存
	 * @param model
	 */
	void save(InquiryReplyModel model);
	
	/**
	 * 更新
	 * @param model
	 */
	void update(InquiryReplyModel model);
	
	/**
	 * 削除(問い合わせ返信ID)
	 * @param id
	 */
	void delete(InquiryReplyId id);
	
	/**
	 * 削除(問い合わせID)
	 * @param inquiryId
	 */
	void delete_byInquiry(InquiryId inquiryId);
	
	/**
	 * 全選択
	 * @return 問い合わせ返信モデルリスト
	 */
	List<InquiryReplyModel> getAll();
	
	/**
	 * 選択(問い合わせID)
	 * @param  id
	 * @return 問い合わせ返信モデルリスト
	 */
	List<InquiryReplyModel> select_byInquiryId(InquiryId id);
	
	/**
	 * 選択(問い合わせ返信ID)
	 * @param id
	 * @return 問い合わせ返信モデル
	 */
	InquiryReplyModel select(InquiryReplyId id);
}
