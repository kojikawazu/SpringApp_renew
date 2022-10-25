package com.example.demo.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.app.header.listener.SessionLoginAttributeListener;
import com.example.demo.app.header.listener.SessionLoginListener;
import com.example.demo.common.log.LogMessage;

@Configuration
public class CommonConfigure {
 
	@Bean
	public LogMessage getLogMessage() {
		return new LogMessage();
	}
	
	@Bean
	public SessionLoginListener getSessionLogoutListener() {
		return new SessionLoginListener();
	}
	
	@Bean
	public SessionLoginAttributeListener getSessionLoginUserListener() {
		return new SessionLoginAttributeListener();
	}
}
