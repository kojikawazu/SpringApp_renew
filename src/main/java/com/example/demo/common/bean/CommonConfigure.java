package com.example.demo.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.common.log.LogMessage;

@Configuration
public class CommonConfigure {
 
	@Bean
	public LogMessage getLogMessage() {
		return new LogMessage();
	}
}
