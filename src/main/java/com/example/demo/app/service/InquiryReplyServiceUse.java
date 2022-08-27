package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.InquiryReplyDao;
import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;
import com.example.demo.common.id.InquiryReplyId;

/**
 * 問い合わせ返信サービスクラス
 * @author nanai
 *
 */
@Service
public class InquiryReplyServiceUse implements InquiryReplyService {
	
	/** daoクラス */
	private final InquiryReplyDao dao;
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Autowired
	public InquiryReplyServiceUse(InquiryReplyDao dao) {
		this.dao = dao;
	}

	/**
	 * 保存
	 * @param model
	 */
	@Override
	public void save(InquiryReplyModel model) {
		this.dao.insertReplyInquiry(model);
	}

	/**
	 * 更新
	 * @param model
	 */
	@Override
	public void update(InquiryReplyModel model) {
		if(this.dao.updateReplyInquiry(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	/**
	 * 削除(問い合わせ返信ID)
	 * @param id
	 */
	@Override
	public void delete(InquiryReplyId id) {
		if(this.dao.deleteReplyInquiry(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}
	
	/**
	 * 削除(問い合わせID)
	 * @param inquiryId
	 */
	@Override
	public void delete_byInquiry(InquiryId inquiryId) {
		if(this.dao.deleteReply_byInquiry(inquiryId) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	/**
	 * 全選択
	 * @return 問い合わせ返信モデルリスト
	 */
	@Override
	public List<InquiryReplyModel> getAll() {
		List<InquiryReplyModel> list = this.dao.getAll();
		
		if(list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
	}
	
	/**
	 * 選択(問い合わせID)
	 * @param  id
	 * @return 問い合わせ返信モデルリスト
	 */
	@Override
	public List<InquiryReplyModel> select_byInquiryId(InquiryId id) {
		List<InquiryReplyModel> list = this.dao.select_byInquiryId(id);
		
		if(list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
	}

	/**
	 * 選択(問い合わせ返信ID)
	 * @param id
	 * @return 問い合わせ返信モデル
	 */
	@Override
	public InquiryReplyModel select(InquiryReplyId id) {
		InquiryReplyModel model = this.dao.select(id);
		
		if(model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

}
