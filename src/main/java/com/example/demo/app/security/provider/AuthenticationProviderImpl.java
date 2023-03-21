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

import com.example.demo.app.entity.user.SecLoginUserDetails;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.app.security.exception.AutenticationArgumentsException;
import com.example.demo.app.security.exception.AuthenticationLoginedUserException;
import com.example.demo.app.security.exception.AuthenticationMismatchPasswordException;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.app.service.user.SecUserServiceUse;
import com.example.demo.common.log.IntroAppLogWriter;
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
	 * {@link SecUserServiceUse}
	 */
	@Autowired
	private SecUserServiceUse 			securityUserService;

	/**
	 * セキュリティユーザーサービス
	 * {@link LoginServiceUse}
	 */
	@Autowired
	private LoginServiceUse					loginService;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  		logWriter = IntroAppLogWriter.getInstance();

	/** -------------------------------------------------------------------------------------- */

	/**
	 * 認証受信
	 */
	@Override
	public Authentication authenticate(
			Authentication authentication) throws AuthenticationException {
		this.logWriter.start("");

		// ユーザーとパスワードを取得
		String email 	= authentication.getName();
		String password = authentication.getCredentials().toString();
		this.logWriter.info("email: " + email + " password: " + password);

		// ユーザーとパスワードチェック
		this.validate_blank(email, password);

		// ユーザー情報取得
		SecUserModel model = null;
		SecLoginUserDetails userDetails = null;
		try {
			model 			= this.securityUserService.select(new EmailWord(email));
			userDetails		= new SecLoginUserDetails(model);
			this.logWriter.info("user: " + userDetails.getUsername());
		} catch(Exception ex) {
			// [認証失敗]ユーザー情報なし
			this.logWriter.error(ex.getMessage());
			throw new AuthenticationCredentialsNotFoundException(ERROR_LOGIN_MESSAGE);
		}

		// ログインされているか = ログイン情報が存在するかチェック
		this.validate_login(model);
		// パスワードチェック
		this.validate_password(password, userDetails);

		// [認証成功]
		UsernamePasswordAuthenticationToken result = this.createAutenticationToken(userDetails, authentication);

		this.logWriter.successed("");
		// トークンを返却
		return result;
	}

	/**
	 * 検証(ブランク)
	 * @param email {@link String}
	 * @param password {@link String}
	 * @throws AuthenticationException
	 */
	private void validate_blank(String email, String password) throws AuthenticationException {
		this.logWriter.start("");

		// ユーザーとパスワードチェック
		if (email.equals("") || password.equals("")) {
			// [認証失敗]入力情報なし
			this.logWriter.error("入力情報なし");
			throw new AutenticationArgumentsException(ERROR_LOGIN_MESSAGE);
		}

		this.logWriter.successed("");
	}

	/**
	 * ログインされているかチェック
	 * @param model {@link SecUserModel}
	 * @throws AuthenticationException
	 */
	private void validate_login(SecUserModel model) throws AuthenticationException {
		this.logWriter.start("ログインされているかチェック");

		if (this.loginService.isSelect_byUserId(model.getId())) {
			this.logWriter.error("既にログイン済");
			throw new AuthenticationLoginedUserException(ERROR_LOGIN_NOW);
		}

		this.logWriter.successed("");
	}

	/**
	 * パスワードチェック
	 * @param password 		{@link String}
	 * @param userDetails 	{@link SecLoginUserDetails}
	 * @throws AuthenticationException
	 */
	private void validate_password(String password, SecLoginUserDetails userDetails) throws AuthenticationException {
		this.logWriter.start("パスワードチェック");

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if (!bcrypt.matches(password, userDetails.getPassword())) {
			// [認証失敗]パスワードが一致しなかった・・・
			this.logWriter.error("パスワードの不一致");
			throw new AuthenticationMismatchPasswordException(ERROR_LOGIN_MESSAGE);
		}

		this.logWriter.successed("");
	}

	/**
	 * ユーザーパスワード認可トークン発行
	 * @param userDetails 		{@link SecLoginUserDetails}
	 * @param authentication	{@link Authentication}
	 * @return					{@link UsernamePasswordAuthenticationToken}
	 * @throws AuthenticationException
	 */
	private UsernamePasswordAuthenticationToken createAutenticationToken(
			SecLoginUserDetails userDetails, 
			Authentication authentication) throws AuthenticationException {
		if (userDetails == null || authentication == null) new AutenticationArgumentsException(ERROR_LOGIN_MESSAGE);
		this.logWriter.start("");

		UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.authenticated(
				userDetails,
				authentication.getCredentials(), 
				this.authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

		this.logWriter.successed("");
		return token;
	}

	@Override
	public boolean supports(
			Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
