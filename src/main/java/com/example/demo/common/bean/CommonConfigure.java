package com.example.demo.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.common.log.LogMessage;

/**
 * 共通DI登録クラス
 * @author nanai
 *
 */
@Configuration
public class CommonConfigure {

	/**
	 * ログクラス
	 * @return ログクラス{@link LogMessage}
	 */
	@Bean
	public LogMessage getLogMessage() {
		return new LogMessage();
	}
}
