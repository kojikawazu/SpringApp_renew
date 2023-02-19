package com.example.demo.app.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.user.LoginDaoSql;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;

/**
 * ログインサービスクラス
 * @author nanai
 *
 */
@Component
@Service
public class LoginServiceUse implements SuperService<LoginModel, LoginId>, LoginService {

	/**
	 * daoクラス
	 * {@link LoginDaoSql}
	 */
	@Autowired
	private final LoginDaoSql dao;
	
	/**
	 * コンストラクタ
	 * @param dao {@link LoginDaoSql}
	 */
	public LoginServiceUse(LoginDaoSql dao) {
		this.dao = dao;
	}
	
	/**
	 * 保存
	 * @param model {@link LoginModel}
	 */
	@Override
	public void save(LoginModel model) {
		this.dao.insert(model);
	}
	
	/**
	 * 追加
	 * @param  model {@link LoginModel}
	 * @return {@link LoginId}
	 */
	@Override
	public LoginId save_returnId(LoginModel model) {
		return this.dao.insert_returnId(model);
	}

	/**
	 * 更新
	 * @param  model {@link LoginModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(LoginModel model) {
		if (this.dao.update(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}
	
	/**
	 * ログイン情報の更新日付更新
	 * @param loginId ログインID
	 */
	@Override
	public void updateTime(LoginId loginId) {
		if (this.dao.updateTime(loginId) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 削除
	 * @param  id {@link LoginId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(LoginId id) {
		if (this.dao.delete(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}
	
	/**
	 * 削除
	 * @param  userId {@link UserId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(UserId userId) {
		if (this.dao.delete_byUserId(userId) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全て選択
	 * @return list {@link List}({@link LoginModel})
	 */
	@Override
	public List<LoginModel> getAll() {
		return this.dao.getAll();
	}
	
	

	/**
	 * IDによる選択
	 * @param  id {@link LoginId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link LoginModel}
	 */
	@Override
	public LoginModel select(LoginId id) {
		LoginModel model = this.dao.select(id);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}
	
	/**
	 * 選択
	 * @param  userId {@link UserId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return {@link LoginModel}
	 */
	@Override
	public LoginModel select(UserId userId) {
		LoginModel model = this.dao.select(userId);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

	/**
	 * IDは存在する？
	 * @param id ターゲットID
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byId(int targetID) {
		return this.dao.isSelect_byId(targetID);
	}

	/**
	 * IDは存在する？
	 * @param  targetID {@link UserId}
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byUserId(UserId targetID) {
		return this.dao.isSelect_byUserId(targetID);
	}

}
