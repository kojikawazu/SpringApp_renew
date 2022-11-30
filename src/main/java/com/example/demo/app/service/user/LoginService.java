package com.example.demo.app.service.user;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.exception.WebMvcConfig;

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
	 * 削除
	 * @param  userId {@link UserId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	void delete(UserId userId);
	
	/**
	 * 選択
	 * @param  userId {@link UserId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return {@link LoginModel}
	 */
	LoginModel select(UserId userId);
	
	/**
	 * IDは存在する？
	 * @param  targetID {@link UserId}
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byUserId(UserId targetID);
}
