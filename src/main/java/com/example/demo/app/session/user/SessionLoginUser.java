package com.example.demo.app.session.user;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionLoginUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** セッションID */
	private int sessionId 		= 0;
	
	/** ユーザーID */
	private int userId 			= 0;
	
	/** ユーザー名 */
	private String userName 	= "";
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}

}
