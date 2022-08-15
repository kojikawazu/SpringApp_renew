package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.InquiryReplyDao;
import com.example.demo.app.entity.InquiryReplyModel;
import com.example.demo.app.exception.WebMvcConfig;

@Service
public class InquiryReplyServiceUse implements InquiryReplyService {
	
	private final InquiryReplyDao dao;
	
	@Autowired
	public InquiryReplyServiceUse(InquiryReplyDao dao) {
		// TODO コンストラクタ
		this.dao = dao;
	}

	@Override
	public void save(InquiryReplyModel model) {
		// TODO 保存
		dao.insertReplyInquiry(model);
	}

	@Override
	public void update(InquiryReplyModel model) {
		// TODO 更新
		if(dao.updateReplyInquiry(model) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public void delete(int id) {
		// TODO 削除
		if(dao.deleteReplyInquiry(id) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}
	
	@Override
	public void delete_byInquiry(int inquiryId) {
		// TODO 問合せIDによる削除
		dao.deleteReply_byInquiry(inquiryId);
	}

	@Override
	public List<InquiryReplyModel> getAll() {
		// TODO 全て取得
		return dao.getAll();
	}
	
	@Override
	public List<InquiryReplyModel> select_byInquiryId(int id) {
		// TODO 問合せIDによる返信リストの作成
		return dao.select_byInquiryId(id);
	}

	@Override
	public InquiryReplyModel select(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.select(id);
	}

}
