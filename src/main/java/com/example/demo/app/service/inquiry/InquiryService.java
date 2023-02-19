package com.example.demo.app.service.inquiry;

import java.util.List;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.exception.WebMvcConfig;

/**
 * 問い合わせサービスインターフェース
 * @author nanai
 *
 */
public interface InquiryService {
	
	/**
	 * 保存
	 * @param model {@link InquiryModel}
	 */
	void save(InquiryModel model);
	
	/**
	 * 更新
	 * @param  model {@link InquiryModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	void update(InquiryModel model);
	
	/**
	 * 削除
	 * @param  id {@link InquiryId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	void delete(InquiryId id);
	
	/**
	 * 全選択
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト  {@link List}({@link InquiryModel})
	 */
	List<InquiryModel> getAll(boolean isDesc);
	
	/**
	 * 全選択(問い合わせ返信モデルリストつき)
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト {@link List}({@link InquiryModel})
	 */
	List<InquiryModel> getAllPlus(boolean isDesc);
	
	/**
	 * 選択
	 * @param  id {@link InquiryId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return 問い合わせモデル {@link InquiryModel}
	 */
	InquiryModel select(InquiryId id);
	
}
