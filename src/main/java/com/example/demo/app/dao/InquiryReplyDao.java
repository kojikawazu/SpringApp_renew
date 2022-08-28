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
	
	/**
	 * 追加
	 * @param model
	 */
	void insertReplyInquiry(InquiryReplyModel model);
	
	/**
	 * 更新
	 * @param
	 * @return 0以下 失敗 それ以外 成功
	 */
	int updateReplyInquiry(InquiryReplyModel model);
	
	/**
	 * 削除(問い合わせ返信ID)
	 * @param id
	 * @return 0以下 失敗 それ以外 成功
	 */
	int deleteReplyInquiry(InquiryReplyId id);
	
	/**
	 * 削除(問い合わせID)
	 * @param inquiryId
	 * @return 0以下 失敗 それ以外 成功
	 */
	int deleteReply_byInquiry(InquiryId inquiryId);
	
	/**
	 * 全選択
	 * @return 問い合わせ返信モデルリスト
	 */
	List<InquiryReplyModel> getAll();
	
	/**
	 * 問い合わせIDによる選択
	 * @param 問い合わせID
	 * @return 問い合わせ返信モデルリスト
	 */
	List<InquiryReplyModel> select_byInquiryId(InquiryId inquiryId);

	/**
	 * IDによる選択
	 * @param 問い合わせ返信ID
	 * @return 問い合わせ返信モデル
	 */
	InquiryReplyModel select(InquiryReplyId id);
}
