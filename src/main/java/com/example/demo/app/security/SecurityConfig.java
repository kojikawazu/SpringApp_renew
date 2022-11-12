package com.example.demo.app.security;

import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * セキュリティ設定
 * @author nanai
 *
 */
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * セキュリティ
	 * @param http
	 * @return
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
				// -----------------------------------------------------------
				// ログイン成功後のリダイレクト先URL
				.defaultSuccessUrl(AppConsts.REQUEST_MAPPING_HOME)
				// -----------------------------------------------------------
				// ログイン失敗後のリダイレクト先URL
				.failureUrl(
						AppConsts.REQUEST_MAPPING_SECURITY_FORM +
						"?" + WebConsts.ATTRIBUTE_ERROR)
				// -----------------------------------------------------------
				// ログイン画面は未ログインでもアクセス可能
				.permitAll()
		).logout(logout -> logout
				// ログアウトの設定記述開始
				// -----------------------------------------------------------
				.logoutUrl(AppConsts.REQUEST_MAPPING_SECURITY_LOGOUT)
				// ログアウト成功後のリダイレクト先URL
				.logoutSuccessUrl(AppConsts.REQUEST_MAPPING_HOME)
		).authorizeHttpRequests(authz -> authz
				// URLごとの認可設定記述開始
				// -----------------------------------------------------------
				// css, images, js, jsonはアクセス可能
				.mvcMatchers("/css/**", "/images/**", "/js/**", "/json/**")
					.permitAll()
				// -----------------------------------------------------------
				// 誰でもアクセス可能
                .mvcMatchers(AppConsts.REQUEST_MAPPING_HOME, "/h2-console")
                	.permitAll()
                // -----------------------------------------------------------
                // /generalはROLE_GENERALのみアクセス可能
                .mvcMatchers("/general")
                	.hasRole("GENERAL")
                // -----------------------------------------------------------
                // /adminはROLE_ADMINのみアクセス可能
                .mvcMatchers("/admin")
                	.hasRole("ADMIN")
                // -----------------------------------------------------------
                // 他のURLはログイン後のみアクセス可能
                .anyRequest().authenticated()
		);
		
		return http.build();
	}

	/**
	 * パスワード暗号化
	 * @return
	 */
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}
