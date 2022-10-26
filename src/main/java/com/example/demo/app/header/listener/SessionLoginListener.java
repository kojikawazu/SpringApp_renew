package com.example.demo.app.header.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.common.log.LogMessage;

/**
 * セッションログインリスナー<br>
 * implements {@link HttpSessionListener}
 * @author nanai
 *
 */
@Component
@WebListener
public class SessionLoginListener implements HttpSessionListener  {

	/**
	 * ログクラス
	 * {@link LogMessage}
	 */
	@Autowired
	private LogMessage	logMessage;
	
	/**
	 * 生成リスナー
	 * @param event {@link HttpSessionEvent}
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event){
		this.logMessage.info("add");
	}
	
	/**
	 * 破棄リスナー
	 * @param event {@link HttpSessionEvent}
	 */
	@Override
    public void sessionDestroyed(HttpSessionEvent event) {
		this.logMessage.info("delete");
    }
}
