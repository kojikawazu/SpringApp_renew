package com.example.demo.app.service.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.inquiry.InquiryId;
import com.example.demo.app.common.id.inquiry.InquiryReplyId;
import com.example.demo.app.dao.inquiry.InquiryReplyDao;
import com.example.demo.app.entity.inquiry.InquiryReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;

/**
 * 問い合わせ返信サービスクラス
 * @author nanai
 *
 */
@Service
public class InquiryReplyServiceUse implements InquiryReplyService {
	
	/** 
	 * daoクラス
	 * {@link InquiryReplyDao} 
	 */
	private final InquiryReplyDao dao;
	
	/**
	 * コンストラクタ
	 * @param dao {@link InquiryReplyDao} 
	 */
	@Autowired
	public InquiryReplyServiceUse(InquiryReplyDao dao) {
		this.dao = dao;
	}

	/**
	 * 保存
	 * @param model {@link InquiryReplyModel}
	 */
	@Override
	public void save(InquiryReplyModel model) {
		this.dao.insertReplyInquiry(model);
	}

	/**
	 * 更新
	 * @param  model {@link InquiryReplyModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(InquiryReplyModel model) {
		if (this.dao.updateReplyInquiry(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 削除(問い合わせ返信ID)
	 * @param  id {@link InquiryReplyId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(InquiryReplyId id) {
		if (this.dao.deleteReplyInquiry(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}
	
	/**
	 * 削除(問い合わせID)
	 * @param  inquiryId {@link InquiryId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete_byInquiry(InquiryId inquiryId) {
		if (this.dao.deleteReply_byInquiry(inquiryId) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全選択
	 * @return 問い合わせ返信モデルリスト {@link List}({@link InquiryReplyModel})
	 */
	@Override
	public List<InquiryReplyModel> getAll() {
		return this.dao.getAll();
	}
	
	/**
	 * 選択(問い合わせID)
	 * @param  id {@link InquiryId}
	 * @return 問い合わせ返信モデルリスト {@link List}({@link InquiryReplyModel})
	 */
	@Override
	public List<InquiryReplyModel> select_byInquiryId(InquiryId id) {
		return this.dao.select_byInquiryId(id);
	}

	/**
	 * 選択(問い合わせ返信ID)
	 * @param  id {@link InquiryReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return 問い合わせ返信モデル {@link InquiryReplyModel}
	 */
	@Override
	public InquiryReplyModel select(InquiryReplyId id) {
		InquiryReplyModel model = this.dao.select(id);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

}
