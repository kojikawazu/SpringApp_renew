package com.example.demo.app.dao.user;

import com.example.demo.app.entity.user.UserModel;

/**
 * ユーザーDaoインターフェース
 * @author nanai
 *
 */
public interface UserDao {
	
	/**
	 * 名前orEメールandパスワードに一致するIDは存在する？
	 * @param  target 			名前 or メールアドレス
	 * @param  targetPassword	パスワード
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byNameOrEmailAndPassword(String target, String targetPassword);
	
	/**
	 * 名前orEメールandパスワードに一致するモデルを取得
	 * @param  target 			名前 or メールアドレス
	 * @param  targetPassword	パスワード
	 * @return {@link UserModel}
	 */
	UserModel select_byNameOrEmailAndPassword(String target, String targetPassword);
	
	
}
