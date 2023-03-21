package com.example.demo.app.entity.user;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.common.entity.SuperModel;

/**
 * ログインモデルクラス
 * <br>
 * implements {@link SuperModel}
 * @author nanai
 *
 */
public class LoginModel implements SuperModel {

	/** 
	 * ログインID
	 * {@link LoginId}
	 */
	private LoginId			id;

	/** 
	 * ユーザーID
	 * {@link LoginId}
	 */
	private UserId			userId;

	/** 
	 * セッションID
	 * {@link SessionId}
	 */
	private SessionId		sessionId;

	/** 
	 * 生成日
	 * {@link LocalDateTime}
	 */
	private LocalDateTime 	created;

	/** 
	 * 更新日
	 * {@link LocalDateTime}
	 */
	private LocalDateTime 	updated;

	/** ------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public LoginModel() {
		this.id 		= new LoginId();
		this.userId 	= new UserId();
		this.sessionId	= new SessionId();
		this.created	= LocalDateTime.now();
		this.updated	= LocalDateTime.now();
	}
	
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
		this();

		this.setId(id);
		this.setUserId(userId);
		this.setSesionId(sessionId);
		this.setCreated(created);
		this.setUpdate(updated);
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
		this();

		this.setUserId(userId);
		this.setSesionId(sessionId);
		this.setCreated(created);
		this.setUpdate(updated);
	}
	
	/**
	 * コンストラクタ
	 * @param model {@link LoginModel}
	 */
	public LoginModel(
			LoginModel model) {
		this();

		if (model != null) {
			this.setId(new LoginId(model.getId()));
			this.setUserId(new UserId(model.getUserId()));
			this.setSesionId(new SessionId(model.getSessionId()));
			this.setCreated(model.getCreated());
			this.setUpdate(model.getUpdated());
		}
	}

	/** ------------------------------------------------------------- */

	/**
	 * getter
	 * @return id
	 */
	public int getId() {
		return this.id.getId();
	}

	/**
	 * setter
	 * @param id {@link LoginId}
	 */
	public void setId(LoginId id) {
		if (id == null)	return;
		this.id.setId(id);
	}

	/**
	 * getter
	 * @return userId
	 */
	public int getUserId() {
		return this.userId.getId();
	}

	/**
	 * setter
	 * @param userId {@link UserId}
	 */
	public void setUserId(UserId userId) {
		if	(userId == null)	return;
		this.userId.setId(userId);
	}

	/**
	 * getter
	 * @return sessionId
	 */
	public String getSessionId() {
		return this.sessionId.getId();
	}

	/**
	 * setter
	 * @param sessionId {@link SessionId}
	 */
	public void setSesionId(SessionId sessionId) {
		if (sessionId == null)	return;
		this.sessionId.setId(sessionId);
	}

	/**
	 * getter
	 * @return {@link LocalDateTime}
	 */
	public LocalDateTime getCreated() {
		return this.created;
	}

	/**
	 * setter
	 * @param created {@link LocalDateTime}
	 */
	public void setCreated(LocalDateTime created) {
		if (created == null)	return;
		this.created = created;
	}

	/**
	 * getter
	 * @return {@link LocalDateTime}
	 */
	public LocalDateTime getUpdated() {
		return this.updated;
	}

	/**
	 * setter
	 * {@link LocalDateTime}
	 */
	public void setUpdate(LocalDateTime updated) {
		if (updated == null)	return;
		this.updated = updated;
	}
}
