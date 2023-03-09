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
import com.example.demo.common.log.IntroAppLogWriter;

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
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param dao {@link LoginDaoSql}
	 */
	public LoginServiceUse(LoginDaoSql dao) {
		this.dao 		= dao;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 保存
	 * @param model {@link LoginModel}
	 */
	@Override
	public void save(LoginModel model) {
		try {
			this.dao.insert(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}
	}

	/**
	 * 追加
	 * @param  model {@link LoginModel}
	 * @return {@link LoginId}
	 */
	@Override
	public LoginId save_returnId(LoginModel model) {
		LoginId loginId = null;

		try {
			loginId = this.dao.insert_returnId(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			return new LoginId();
		}

		return loginId;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param  model {@link LoginModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(LoginModel model) {
		int result = 0;

		try {
			result = this.dao.update(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}

		if (result <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * ログイン情報の更新日付更新
	 * @param loginId ログインID {@link LoginId}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void updateTime(LoginId loginId) {
		int result = 0;

		try {
			result = this.dao.updateTime(loginId);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}

		if (result <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param  id {@link LoginId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(LoginId id) {
		int result = 0;

		try {
			result = this.dao.delete(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_DELETE();
		}

		if (result <= WebConsts.ERROR_DB_STATUS) {
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
		int result = 0;

		try {
			result = this.dao.delete_byUserId(userId);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_DELETE();
		}

		if (result <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全削除
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void deleteAll() {
		int result = 0;

		try {
			result = this.dao.deleteAll();
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.SQL_NOT_DELETE();
		}

		if (result <= WebConsts.ERROR_DB_STATUS) {
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
