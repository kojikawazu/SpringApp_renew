package com.example.demo.app.dao.user;

import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.id.user.UserId;

/**
 * ログインDaoインターフェース
 * @author nanai
 *
 */
public interface LoginDao {

	/**
	 * 追加
	 * @param  model {@link LoginModel}
	 * @return {@link LoginId}
	 */
	LoginId insert_returnId(LoginModel model);
	
	/**
	 * ログイン情報の更新日付更新
	 * @param  loginId ログインID
	 * @return 0以下 失敗 それ以外 成功
	 */
	int updateTime(LoginId loginId);
	
	/**
	 * 選択
	 * @param  userId {@link UserId}
	 * @return {@link LoginModel}
	 */
	LoginModel select(UserId userId);
	
}
