package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.InquiryDao;
import com.example.demo.app.entity.InquiryModel;
import com.example.demo.app.exception.WebMvcConfig;

@Service
public class InquiryServiceUse implements InquiryService {
	
	private final InquiryDao dao;

	@Autowired
	public InquiryServiceUse(InquiryDao dao) {
		// TODO コンストラクタ
		this.dao = dao;
	}
	
	
	@Override
	public void save(InquiryModel model) {
		// TODO 保存
		dao.insertInquiry(model);
	}

	@Override
	public void update(InquiryModel model) {
		// TODO 更新
		if(dao.updateInquiry(model) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}

	@Override
	public List<InquiryModel> getAll() {
		// TODO 全て取得
		return dao.getAll();
	}


	@Override
	public void delete(int id) {
		// TODO 削除
		if(dao.deleteInquiry(id) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}


	@Override
	public InquiryModel select(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.select(id);
	}

}
