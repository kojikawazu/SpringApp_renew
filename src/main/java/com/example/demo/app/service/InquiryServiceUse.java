package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.InquiryDao;
import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.InquiryId;

/**
 * 問い合わせサービスクラス
 * @author nanai
 *
 */
@Service
public class InquiryServiceUse implements InquiryService {
	
	/** Daoクラス */
	private final InquiryDao dao;

	/**
	 * コンストラクタ
	 * @param dao
	 */
	@Autowired
	public InquiryServiceUse(InquiryDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 保存
	 * @param model
	 */
	@Override
	public void save(InquiryModel model) {
		this.dao.insertInquiry(model);
	}

	/**
	 * 更新
	 * @param model
	 */
	@Override
	public void update(InquiryModel model) {
		if(this.dao.updateInquiry(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}
	
	/**
	 * 削除
	 * @param id
	 */
	@Override
	public void delete(InquiryId id) {
		if(this.dao.deleteInquiry(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	/**
	 * 全選択
	 * @return 問い合わせモデルリスト
	 */
	@Override
	public List<InquiryModel> getAll() {
		List<InquiryModel> list = this.dao.getAll();
		
		if(list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
	}
	
	/**
	 * 選択
	 * @param  id
	 * @return 問い合わせモデル
	 */
	@Override
	public InquiryModel select(InquiryId id) {
		InquiryModel model = this.dao.select(id);
		
		if(model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

}
