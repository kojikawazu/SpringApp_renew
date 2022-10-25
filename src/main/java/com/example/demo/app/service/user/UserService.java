package com.example.demo.app.service.user;

import com.example.demo.app.entity.user.UserModel;
import com.example.demo.app.exception.WebMvcConfig;

/**
 * ユーザーサービスインターフェース
 * @author nanai
 *
 */
public interface UserService {

	/**
	 * 名前orEメールandパスワードに一致するIDは存在する？
	 * @param  target
	 * @param  targetPassword
	 * @return true 存在する false 存在しない
	 */
	boolean isSelect_byNameOrEmailAndPassword(String target, String targetPassword);
	
	/**
	 * 名前orEメールandパスワードに一致するモデルを取得
	 * @param  target 			名前 or メールアドレス
	 * @param  targetPassword	パスワード
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return {@link UserModel}
	 */
	UserModel select_byNameOrEmailAndPassword(String target, String targetPassword);
}
