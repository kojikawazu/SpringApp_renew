package com.example.demo.app.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.entity.security.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.security.exception.AuthenticationLoginedUserException;
import com.example.demo.app.service.security.SecurityUserServiceUse;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.word.EmailWord;

/**
 * 認可プロバイダー[カスタム版]<br>
 * implements {@link AuthenticationProvider}
 * @author nanai
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	/** エラーメッセージ[誤り] */
	private final String ERROR_LOGIN_MESSAGE	= "ログイン情報に誤りがあります。";
	
	/** エラーメッセージ[ログイン中] */
	private final String ERROR_LOGIN_NOW 		= "そのユーザーは既にログイン中です。";
	
	/** 認証権限のマッパー */
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	
	/** -------------------------------------------------------------------------------------- */
	
	/**
	 * セキュリティユーザーサービス
	 * {@link SecurityUserServiceUse}
	 */
	@Autowired
	private SecurityUserServiceUse 	securityUserService;
	
	/**
	 * セキュリティユーザーサービス
	 * {@link LoginServiceUse}
	 */
	@Autowired
	private LoginServiceUse			loginService;
	
	/** -------------------------------------------------------------------------------------- */
	
	@Override
	public Authentication authenticate(
			Authentication authentication) throws AuthenticationException {
		// ユーザーとパスワードを取得
		String email 	= authentication.getName();
		String password = authentication.getCredentials().toString();
		
		// ユーザーとパスワードチェック
		if (email.equals("") || password.equals("")) {
			// [認証失敗]入力情報なし 
			throw new AuthenticationCredentialsNotFoundException(ERROR_LOGIN_MESSAGE);
		}
		
		// ユーザー情報取得
		SecUserModel model = null;
		SecLoginUserDetails userDetails = null;
		try {
			model = securityUserService.select(new EmailWord(email));
			userDetails        = new SecLoginUserDetails(model);
		} catch(Exception ex) {
			// [認証失敗]ユーザー情報なし
			throw new AuthenticationCredentialsNotFoundException(ERROR_LOGIN_MESSAGE);
		}
		
		// ログインされているか = ログイン情報が存在するかチェック
		if (this.loginService.isSelect_byUserId(new UserId(model.getId()))) {
			// 既にログイン済
			throw new AuthenticationLoginedUserException(ERROR_LOGIN_NOW);
		}
		
		// パスワードチェック
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if(!bcrypt.matches(password, userDetails.getPassword())) {
			// [認証失敗]パスワードが一致しなかった・・・
			throw new AuthenticationCredentialsNotFoundException(ERROR_LOGIN_MESSAGE);
		}
		
		// [認証成功]
		UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(
														userDetails,
														authentication.getCredentials(), 
														this.authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
		// トークンを返却
		return result;
	}

	@Override
	public boolean supports(
			Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
