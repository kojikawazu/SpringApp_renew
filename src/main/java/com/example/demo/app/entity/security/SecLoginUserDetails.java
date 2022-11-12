package com.example.demo.app.entity.security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.app.entity.user.SecUserModel;

/**
 * Details用セキュリティユーザークラス
 * @author nanai
 *
 */
public class SecLoginUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * セキュリティログインユーザーモデル
	 * {@link SecUserModel}
	 */
	private final SecUserModel secUserModel;
	
	/**
	 * 権限
	 */
	private final Collection<? extends GrantedAuthority> authorities;
	
	/**
	 * コンストラクタ
	 * @param secUserModel {@link SecUserModel}
	 */
	public SecLoginUserDetails(
			SecUserModel secUserModel) {
		this.secUserModel 	= secUserModel;
		this.authorities 	= secUserModel.getRoleList()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}
	
	public SecUserModel getSecUserModel() {
		return this.secUserModel;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.secUserModel.getPassword();
	}

	@Override
	public String getUsername() {
		return this.secUserModel.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
