package com.example.demo.app.dao.security;

import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーDaoインターフェース
 * @author nanai
 *
 */
public interface SecUserDao {

	/**
	 * Eメール、パスワードによる選択
	 * @param  email	{@link EmailWord}
	 * @param  password	{@link PasswdWord}
	 * @return 			{@link SecUserModel}
	 */
	SecUserModel select(EmailWord email, PasswdWord password);

	/**
	 * Eメールによる選択
	 * @param  email	{@link EmailWord}
	 * @return 			{@link SecUserModel}
	 */
	SecUserModel select(EmailWord email);
}
