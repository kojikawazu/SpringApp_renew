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
public class UserServiceUse implements SuperService<UserModel, UserId>, UserService {
	
	/** 
	 * Daoクラス
	 * {@link UserDaoSql}
	 */
	@Autowired
	private final UserDaoSql dao;
	
	/**
	 * コンストラクタ
	 * @param dao {@link UserDaoSql}
	 */
	public UserServiceUse(UserDaoSql dao) {
		this.dao = dao;
	}

	/**
	 * 保存
	 * @param model {@link UserModel}
	 */
	@Override
	public void save(UserModel model) {
		this.dao.insert(model);
	}

	/**
	 * 更新
	 * @param  model {@link UserModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(UserModel model) {
		if (this.dao.update(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 削除
	 * @param id {@link UserId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(UserId id) {
		if (this.dao.delete(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全て選択
	 * @return list {@link List}({@link UserModel})
	 */
	@Override
	public List<UserModel> getAll() {
		return this.dao.getAll();
	}

	/**
	 * IDによる選択
	 * @param  id {@link UserId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link UserModel}
	 */
	@Override
	public UserModel select(UserId id) {
		UserModel model = this.dao.select(id);
		
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
	 * 名前orEメールandパスワードに一致するIDは存在する？
	 * @param  target
	 * @param  targetPassword
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byNameOrEmailAndPassword(String target, String targetPassword) {
		return this.dao.isSelect_byNameOrEmailAndPassword(target, targetPassword);
	}

	/**
	 * 名前orEメールandパスワードに一致するモデルを取得
	 * @param  target 			名前 or メールアドレス
	 * @param  targetPassword	パスワード
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel select_byNameOrEmailAndPassword(String target, String targetPassword) {
		UserModel model = this.dao.select_byNameOrEmailAndPassword(target, targetPassword);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

}
