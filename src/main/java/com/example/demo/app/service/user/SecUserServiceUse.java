package com.example.demo.app.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.user.SecUserDaoSql;
import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーサービスクラス
 * <br>
 * implements 	{@link UserDetailsService}<br>
 * 				{@link SuperService}<{@link SecUserModel}, {@link UserId}><br>
 * 				{@link SecUserService}
 * @author nanai
 *
 */
@Component
@Service
public class SecUserServiceUse implements UserDetailsService, SuperService<SecUserModel, UserId>, SecUserService {

	/** 
	 * Daoクラス
	 * {@link SecUserDaoSql}
	 */
	@Autowired
	private final SecUserDaoSql dao;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ----------------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param dao {@link SecUserDaoSql}
	 */
	public SecUserServiceUse(SecUserDaoSql dao) {
		this.dao 		= dao;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ----------------------------------------------------------------------------------------------- */

	/**
	 * 保存
	 * @param model {@link SecUserModel}
	 */
	@Override
	public void save(SecUserModel model) {
		try {
			this.dao.insert(model);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
		}
	}

	/** ----------------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param  model {@link SecUserModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(SecUserModel model) {
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

	/** ----------------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param id {@link UserId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(UserId id) {
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

	/** ----------------------------------------------------------------------------------------------- */

	/**
	 * 全て選択
	 * @return list {@link List}({@link SecUserModel})
	 */
	@Override
	public List<SecUserModel> getAll() {
		return this.dao.getAll();
	}

	/**
	 * IDによる選択
	 * @param  id {@link UserId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link SecUserModel}
	 */
	@Override
	public SecUserModel select(UserId id) {
		if (id == null)	throw WebMvcConfig.NOT_FOUND();
		SecUserModel model = null;

		try {
			model = this.dao.select(id);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}

		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		return model;
	}

	/**
	 * Eメールによる選択
	 * @param  email 	{@link EmailWord}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link SecUserModel}
	 */
	@Override
	public SecUserModel select(EmailWord email) {
		if (email == null) throw WebMvcConfig.NOT_FOUND();
		SecUserModel model = null;

		try {
			model = this.dao.select(email);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}

		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		return model;
	}

	/**
	 * Eメール、パスワードによる選択
	 * @param  email 	{@link EmailWord}
	 * @param  password	{@link PasswdWord}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link SecUserModel}
	 */
	@Override
	public SecUserModel select(EmailWord email, PasswdWord password) {
		if (email == null || password == null)	throw WebMvcConfig.NOT_FOUND();
		SecUserModel model = null;

		try {
			model = this.dao.select(email, password);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			throw WebMvcConfig.NOT_FOUND();
		}

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

	/** ----------------------------------------------------------------------------------------------- */

	/**
	 * 入力文字からセキュリティユーザーモデルの取得
	 * @param  username {@link String}
	 * @throws {@link UsernameNotFoundException}
	 * @return {@link UserDetails}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null) throw new UsernameNotFoundException("ユーザーが存在しません");

		SecUserModel model = this.dao.select(new EmailWord(username));
		Optional<SecUserModel> optional = Optional.ofNullable(model);

		return optional.map(secLoginUserModel -> new SecLoginUserDetails(model))
				.orElseThrow(() -> new UsernameNotFoundException("ユーザーが一致しません"));
	}
}
