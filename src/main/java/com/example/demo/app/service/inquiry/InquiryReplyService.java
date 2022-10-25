package com.example.demo.app.service.inquiry;

import java.util.List;

import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.id.inquiry.InquiryId;
import com.example.demo.common.id.inquiry.InquiryReplyId;

/**
 * 問い合わせ返信サービスインターフェース
 * @author nanai
 *
 */
public interface InquiryReplyService {
	
	/**
	 * 保存
	 * @param model {@link InquiryReplyModel}
	 */
	void save(InquiryReplyModel model);
	
	/**
	 * 更新
	 * @param  model {@link InquiryReplyModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	void update(InquiryReplyModel model);
	
	/**
	 * 削除(問い合わせ返信ID)
	 * @param  id {@link InquiryReplyId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	void delete(InquiryReplyId id);
	
	/**
	 * 削除(問い合わせID)
	 * @param inquiryId {@link InquiryId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	void delete_byInquiry(InquiryId inquiryId);
	
	/**
	 * 全選択
	 * @return 問い合わせ返信モデルリスト {@link List}({@link InquiryReplyModel})
	 */
	List<InquiryReplyModel> getAll();
	
	/**
	 * 選択(問い合わせID)
	 * @param  id {@link InquiryId}
	 * @return 問い合わせ返信モデルリスト {@link List}({@link InquiryReplyModel})
	 */
	List<InquiryReplyModel> select_byInquiryId(InquiryId id);
	
	/**
	 * 選択(問い合わせ返信ID)
	 * @param  id {@link InquiryReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return 問い合わせ返信モデル {@link InquiryReplyModel}
	 */
	InquiryReplyModel select(InquiryReplyId id);
}
