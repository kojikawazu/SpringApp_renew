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
	
	/**
	 * 追加
	 * @param model
	 */
	void insertInquiry(InquiryModel model);
	
	/**
	 * 更新
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	int updateInquiry(InquiryModel model);
	
	/**
	 * 削除
	 * @param id
	 * @return 0以下 失敗 それ以外 成功
	 */
	int deleteInquiry(InquiryId id);
	
	/**
	 * 全選択
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト
	 */
	List<InquiryModel> getAll(boolean isDesc);
	
	/**
	 * 全て選択(問い合わせ返信モデルリストつき)
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト
	 */
	List<InquiryModel> getAll_Plus(boolean isDesc);
	
	/**
	 * 選択(ID)
	 * @param id
	 * @return 問い合わせモデル
	 */
	InquiryModel select(InquiryId id);
	
}
