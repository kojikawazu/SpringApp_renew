package com.example.demo.app.service.user;

import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.id.user.UserId;

public interface LoginService {

	/**
	 * 追加
	 * @param  model {@link LoginModel}
	 * @return {@link LoginId}
	 */
	LoginId save_returnId(LoginModel model);
	
	/**
	 * ログイン情報の更新日付更新
	 * @param loginId ログインID
	 */
	void updateTime(LoginId loginId);
	
	/**
	 * 選択
	 * @param  userId {@link UserId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return {@link LoginModel}
	 */
	LoginModel select(UserId userId);
}
