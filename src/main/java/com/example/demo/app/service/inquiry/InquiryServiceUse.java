package com.example.demo.app.service.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.dao.inquiry.InquiryDao;
import com.example.demo.app.entity.inquiry.InquiryModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;

/**
 * 問い合わせサービスクラス
 * @author nanai
 *
 */
@Service
public class InquiryServiceUse implements InquiryService {
	
	/** 
	 * Daoクラス
	 * {@link InquiryDao}
	 */
	private final InquiryDao dao;

	/**
	 * コンストラクタ
	 * @param dao {@link InquiryDao}
	 */
	@Autowired
	public InquiryServiceUse(InquiryDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 保存
	 * @param model {@link InquiryModel}
	 */
	@Override
	public void save(InquiryModel model) {
		this.dao.insertInquiry(model);
	}

	/**
	 * 更新
	 * @param  model {@link InquiryModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(InquiryModel model) {
		if (this.dao.updateInquiry(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}
	
	/**
	 * 削除
	 * @param  id {@link InquiryId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(InquiryId id) {
		if (this.dao.deleteInquiry(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全選択
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト {@link List}({@link InquiryModel})
	 */
	@Override
	public List<InquiryModel> getAll(boolean isDesc) {
		return this.dao.getAll(isDesc);
	}
	
	/**
	 * 全選択(問い合わせ返信モデルリストつき)
	 * @param isDesc false 昇順 true 降順
	 * @return 問い合わせモデルリスト {@link List}({@link InquiryModel})
	 */
	@Override
	public List<InquiryModel> getAllPlus(boolean isDesc) {
		return this.dao.getAll_Plus(isDesc);
	}
	
	/**
	 * 選択
	 * @param  id {@link InquiryId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return 問い合わせモデル {@link InquiryModel}
	 */
	@Override
	public InquiryModel select(InquiryId id) {
		InquiryModel model = this.dao.select(id);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

}
