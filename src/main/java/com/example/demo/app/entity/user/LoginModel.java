package com.example.demo.app.entity.user;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.common.id.user.UserId;

/**
 * ログインモデルクラス
 * @author nanai
 *
 */
public class LoginModel {

	/** 
	 * ログインID
	 * {@link LoginId}
	 */
	private LoginId		id;
	
	/** 
	 * ユーザーID
	 * {@link LoginId}
	 */
	private UserId		userId;
	
	/** セッションID */
	private SessionId	sessionId;
	
	/** 生成日 */
	private LocalDateTime 	created;
	
	/** 更新日 */
	private LocalDateTime 	updated;
	
	/** ------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param id		{@link LoginId}
	 * @param userId	{@link UserId}
	 * @param sessionId	{@link SessionId}
	 * @param created	{@link LocalDateTime}
	 * @param updated	{@link LocalDateTime}
	 */
	public LoginModel(
			LoginId			id,
			UserId			userId,
			SessionId		sessionId,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.id = (id == null ?
				new LoginId(0) : id);
		
		this.userId = (userId == null ?
				new UserId(0) : userId);
		
		this.sessionId = (sessionId == null ?
				new SessionId("") : sessionId);
		
		this.created = (created == null ?
				LocalDateTime.now() : created);
		
		this.updated = (updated == null ?
				LocalDateTime.now() : updated);
	}
	
	/**
	 * コンストラクタ
	 * @param userId	{@link UserId}
	 * @param sessionId	{@link SessionId}
	 * @param created	{@link LocalDateTime}
	 * @param updated	{@link LocalDateTime}
	 */
	public LoginModel(
			UserId			userId,
			SessionId		sessionId,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.id = new LoginId(0);
		
		this.userId = (userId == null ?
				new UserId(0) : userId);
		
		this.sessionId = (sessionId == null ?
				new SessionId("") : sessionId);
		
		this.created = (created == null ?
				LocalDateTime.now() : created);
		
		this.updated = (updated == null ?
				LocalDateTime.now() : updated);
	}
	
	/**
	 * コンストラクタ
	 * @param model {@link LoginModel}
	 */
	public LoginModel(
			LoginModel model) {
		if (model == null) {
			this.id 		= new LoginId(0);
			this.userId 	= new UserId(0);
			this.sessionId 	= new SessionId("");
			this.created 	= LocalDateTime.now();
			this.updated 	= LocalDateTime.now();
		} else {
			this.id 		= new LoginId(model.getId());
			this.userId 	= new UserId(model.getUserId());
			this.sessionId 	= new SessionId(model.getSessionId());
			this.created 	= model.getCreated();
			this.updated 	= model.getUpdated();
		}
	}
	
	/** ------------------------------------------------------------- */
	
	public int getId() {
		return this.id.getId();
	}
	
	public int getUserId() {
		return this.userId.getId();
	}
	
	public String getSessionId() {
		return this.sessionId.getId();
	}
	
	public LocalDateTime getCreated() {
		return this.created;
	}
	
	public LocalDateTime getUpdated() {
		return this.updated;
	}
	
}
