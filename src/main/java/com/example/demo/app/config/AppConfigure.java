package com.example.demo.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.app.header.listener.SessionLoginAttributeListener;
import com.example.demo.app.header.listener.SessionLoginListener;

/**
 * App側Configure
 * @author nanai
 *
 */
@Configuration
public class AppConfigure {

	@Bean
	public SessionLoginListener getSessionLogoutListener() {
		return new SessionLoginListener();
	}
	
	@Bean
	public SessionLoginAttributeListener getSessionLoginUserListener() {
		return new SessionLoginAttributeListener();
	}
}
