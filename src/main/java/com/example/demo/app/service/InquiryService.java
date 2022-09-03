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
	
	/**
	 * 保存
	 * @param model
	 */
	void save(InquiryModel model);
	
	/**
	 * 更新
	 * @param model
	 */
	void update(InquiryModel model);
	
	/**
	 * 削除
	 * @param id
	 */
	void delete(InquiryId id);
	
	/**
	 * 全選択
	 * @return 問い合わせモデルリスト
	 */
	List<InquiryModel> getAll(boolean isDesc);
	
	/**
	 * 全選択(問い合わせ返信モデルリストつき)
	 * @return 問い合わせモデルリスト
	 */
	List<InquiryModel> getAllPlus(boolean isDesc);
	
	/**
	 * 選択
	 * @param  id
	 * @return 問い合わせモデル
	 */
	InquiryModel select(InquiryId id);
	
}
