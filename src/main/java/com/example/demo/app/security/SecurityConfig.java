package com.example.demo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.app.common.AppConsts;
import com.example.demo.app.security.handler.CustomAuthenticationFailureHandler;
import com.example.demo.app.security.handler.CustomAuthenticationSuccessHandler;
import com.example.demo.app.security.handler.CustomLogoutSuccessHandler;
import com.example.demo.app.security.provider.AuthenticationProviderImpl;

/**
 * セキュリティ設定
 * @author nanai
 *
 */
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * セキュリティ
	 * @param http {@link HttpSecurity}
	 * @return {@link SecurityFilterChain}
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {

		http.formLogin(login -> login
				// ログインの設定記述開始
				// -----------------------------------------------------------
				// ユーザー名、パスワードの送信先URL
				.loginProcessingUrl(AppConsts.REQUEST_MAPPING_LOGIN)
				// -----------------------------------------------------------
				// ログイン画面のURL
				// -----------------------------------------------------------
				.loginPage(AppConsts.REQUEST_MAPPING_SECURITY_FORM)
					.permitAll()
				// -----------------------------------------------------------
				// ログイン成功後のリダイレクト先URL
				.successHandler(loginSuccessHandler())
				// -----------------------------------------------------------
				// ログイン失敗後のリダイレクト先URL
				.failureHandler(loginFailureHandler())
				// -----------------------------------------------------------
				// ログイン画面は未ログインでもアクセス可能
				.permitAll()
		).logout(logout -> logout
				// ログアウトの設定記述開始
				// -----------------------------------------------------------
				.logoutUrl(AppConsts.REQUEST_MAPPING_SECURITY_LOGOUT)
					.permitAll()
				// ログアウト成功後のリダイレクト先URL
				.logoutSuccessHandler(logoutSuccessHandler())
		).authorizeHttpRequests(authz -> authz
				// URLごとの認可設定記述開始
				// -----------------------------------------------------------
				// css, images, js, jsonはアクセス可能
				.mvcMatchers("/css/**", "/images/**", "/js/**", "/json/**")
					.permitAll()
				// -----------------------------------------------------------
				// 誰でもアクセス可能
                .mvcMatchers(AppConsts.REQUEST_MAPPING_HOME,
								AppConsts.REQUEST_MAPPING_SECURITY_FORM,
								AppConsts.REQUEST_MAPPING_SECURITY_LOGOUT
                			)
                	.permitAll()
                // -----------------------------------------------------------
                // /generalはROLE_GENERALのみアクセス可能
                .mvcMatchers("/general")
                	.hasRole(AppConsts.ROLE_GENERAL)
                // -----------------------------------------------------------
                // /adminはROLE_ADMINのみアクセス可能
                .mvcMatchers("/admin")
                	.hasRole(AppConsts.ROLE_ADMIN)
                // -----------------------------------------------------------
                // 他のURLはログイン後のみアクセス可能
                .anyRequest().authenticated()
		);

		return http.build();
	}

	/** -------------------------------------------------------------------------------------------------------- */

	/**
	 * 認証設定の内部クラス
	 * extends {@link GlobalAuthenticationConfigurerAdapter}
	 * @author nanai
	 *
	 */
	@Configuration
    protected static class AuthenticationonfigurerAdapter extends GlobalAuthenticationConfigurerAdapter {
        
		/**
		 * 認証のプロバイダー
		 * {@link AuthenticationProviderImpl}
		 */
	    @Autowired
        private AuthenticationProviderImpl authenticationProvider;
        
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
        	// 認証方法を設定する
        	auth.authenticationProvider(authenticationProvider);
        }
    }

	/** -------------------------------------------------------------------------------------------------------- */

	/**
	 * ログイン成功ハンドラークラスBean登録
	 * @return {@link AuthenticationSuccessHandler}
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
	    return new CustomAuthenticationSuccessHandler();
	}

	/**
	 * ログイン失敗ハンドラークラスBean登録
	 * @return {@link AuthenticationFailureHandler}
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	/**
	 * ログアウトハンドラークラスBean登録
	 * @return {@link LogoutSuccessHandler}
	 */
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

	/**
	 * パスワード暗号化Bean登録
	 * @return {@link PasswordEncoder}
	 */
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	 /** -------------------------------------------------------------------------------------------------------- */
}
