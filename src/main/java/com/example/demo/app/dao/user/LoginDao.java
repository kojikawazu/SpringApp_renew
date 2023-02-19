package com.example.demo.app.dao.user;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.entity.user.LoginModel;

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
	 * ユーザIDによる削除
	 * @param  userId {@link UserId}
	 * @return 0以下 失敗 それ以外 成功
	 */
	int delete_byUserId(UserId userId);
	
	
	/**
	 * 選択
	 * @param  userId {@link UserId}
	 * @return {@link LoginModel}
	 */
	LoginModel select(UserId userId);
	
	/**
	 * IDは存在する？
	 * @param id {@link UserId}
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byUserId(UserId targetID);
	
}
