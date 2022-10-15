package com.example.demo.app.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.user.UserDaoSql;
import com.example.demo.app.entity.user.UserModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.user.UserId;

/**
 * ユーザーサービスクラス
 * @author nanai
 *
 */
@Component
@Service
public class UserServiceUse implements SuperService<UserModel, UserId> {
	
	/** Daoクラス */
	@Autowired
	private final UserDaoSql dao;
	
	/**
	 * コンストラクタ
	 * @param dao
	 */
	public UserServiceUse(UserDaoSql dao) {
		this.dao = dao;
	}

	/**
	 * 保存
	 * @param model
	 */
	@Override
	public void save(UserModel model) {
		this.dao.insert(model);
	}

	/**
	 * 更新
	 * @param model
	 */
	@Override
	public void update(UserModel model) {
		if(this.dao.update(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 削除
	 * @param id
	 */
	@Override
	public void delete(UserId id) {
		if(this.dao.delete(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全て選択
	 * @return ユーザーモデルリスト
	 */
	@Override
	public List<UserModel> getAll() {
		return this.dao.getAll();
	}

	/**
	 * IDによる選択
	 * @param  id
	 * @return ユーザーモデルクラス
	 */
	@Override
	public UserModel select(UserId id) {
		UserModel model = this.dao.select(id);
		
		if(model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

}
