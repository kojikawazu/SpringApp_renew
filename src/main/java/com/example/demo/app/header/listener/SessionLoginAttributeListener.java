package com.example.demo.app.header.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.common.log.LogMessage;

/**
 * セッションログインユーザーリスナー
 * @author nanai
 *
 */
@Component
@WebListener
public class SessionLoginAttributeListener implements HttpSessionAttributeListener {

	@Autowired
	private LogMessage	logMessage;
	
	/**
	 * 生成リスナー
	 * @param event {@link HttpSessionBindingEvent}
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		logMessage.info("add");
	}
	
	/**
	 * 変更リスナー
	 * @param event {@link HttpSessionBindingEvent}
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		logMessage.info("replace");
	}
	
	/**
	 * 破棄リスナー
	 * @param event {@link HttpSessionBindingEvent}
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		logMessage.info("delete");
	}
	
}
