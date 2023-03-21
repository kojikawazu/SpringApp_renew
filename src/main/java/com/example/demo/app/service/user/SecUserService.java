package com.example.demo.app.service.user;

import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーサービスインターフェース
 * @author nanai
 *
 */
public interface SecUserService {

	/**
	 * Eメール、パスワードによる選択
	 * @param  email 	{@link EmailWord}
	 * @param  password	{@link PasswdWord}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link SecUserModel}
	 */
	SecUserModel select(EmailWord email, PasswdWord password);

	/**
	 * Eメールによる選択
	 * @param  email 	{@link EmailWord}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return model {@link SecUserModel}
	 */
	SecUserModel select(EmailWord email);
}
