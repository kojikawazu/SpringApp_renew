package com.example.demo.app.service.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.security.SecUserDaoSql;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.app.service.SuperService;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーサービスクラス
 * @author nanai
 *
 */
@Component
@Service
public class SecurityUserServiceUse implements UserDetailsService, SuperService<SecUserModel, UserId>, SecurityUserService {

	/** 
	 * Daoクラス
	 * {@link SecUserDaoSql}
	 */
	@Autowired
	private final SecUserDaoSql dao;
	
	/** ----------------------------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param dao {@link SecUserDaoSql}
	 */
	public SecurityUserServiceUse(SecUserDaoSql dao) {
		this.dao = dao;
	}
	
	/** ----------------------------------------------------------------------------------------------- */
	
	/**
	 * 保存
	 * @param model {@link SecUserModel}
	 */
	@Override
	public void save(SecUserModel model) {
		this.dao.insert(model);
	}

	/**
	 * 更新
	 * @param  model {@link SecUserModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(SecUserModel model) {
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
		SecUserModel model = this.dao.select(id);
		
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
		SecUserModel model = this.dao.select(email);
		
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
		SecUserModel model = this.dao.select(email, password);
		
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
	 * @param  username
	 * @throws {@link UsernameNotFoundException}
	 * @return {@link UserDetails}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecUserModel model = this.dao.select(new EmailWord(username));
		Optional<SecUserModel> optional = Optional.ofNullable(model);
		
		return optional.map(secLoginUserModel -> new SecLoginUserDetails(model))
				.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}
