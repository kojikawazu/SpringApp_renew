package com.example.demo.app.entity.user;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.common.entity.SuperModel;
import com.example.demo.common.list.WordList;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーモデル
 * <br>
 * implements {@link SuperModel}
 * @author nanai
 *
 */
public class SecUserModel implements SuperModel {

	/** ユーザーID */
	private UserId 			id;

	/** ユーザー名 */
	private NameWord 		name;

	/** ユーザーEmail */
	private EmailWord 		email;

	/** ユーザーパスワード */
	private PasswdWord 		password;

	/** ロールリスト */
	private WordList		roleList;

	/** ユーザー生成日 */
	private LocalDateTime 	created;

	/** ユーザー更新日 */
	private LocalDateTime 	updated;

	/**
	 * コンストラクタ
	 */
	public SecUserModel() {
		this.id 		= new UserId();
		this.name 		= new NameWord();
		this.email 		= new EmailWord();
		this.password 	= new PasswdWord();
		this.roleList 	= new WordList();
		this.created 	= LocalDateTime.now();
		this.updated 	= LocalDateTime.now();
	}

	/**
	 * コンストラクタ
	 * @param id		{@link UserId}
	 * @param name		{@link NameWord}
	 * @param email		{@link EmailWord}
	 * @param password	{@link PasswdWord}
	 * @param roleList	{@link WordList}
	 * @param created	{@link LocalDateTime}
	 * @param updated	{@link LocalDateTime}
	 */
	public SecUserModel(
			UserId 			id,
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			WordList		roleList,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this();

		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setRoleList(roleList);
		this.setCreated(created);
		this.setUpdated(updated);
	}

	/**
	 * コンストラクタ
	 * @param name		{@link NameWord}
	 * @param email		{@link EmailWord}
	 * @param password	{@link PasswdWord}
	 * @param roleList	{@link WordList}
	 * @param created	{@link LocalDateTime}
	 * @param updated	{@link LocalDateTime}
	 */
	public SecUserModel(
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			WordList		roleList,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this();

		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setRoleList(roleList);
		this.setCreated(created);
		this.setUpdated(updated);
	}

	/**
	 * コンストラクタ
	 * @param id		{@link UserId}
	 * @param name		{@link NameWord}
	 * @param email		{@link EmailWord}
	 * @param password	{@link PasswdWord}
	 * @param created	{@link LocalDateTime}
	 * @param updated	{@link LocalDateTime}
	 */
	public SecUserModel(
			UserId 			id,
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this();

		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setCreated(created);
		this.setUpdated(updated);
	}

	/**
	 * コンストラクタ
	 * @param model {@link SecUserModel}
	 */
	public SecUserModel(SecUserModel model) {
		this();

		if (model != null) {
			this.setId(model.getId());
			this.setName(model.getName());
			this.setEmail(model.getEmail());
			this.setPassword(model.getPassword());
			this.setRoleList(model.getRoleList());
			this.setCreated(model.getCreated());
			this.setUpdated(model.getUpdated());
		}
	}

	/** -------------------------------------------------------------------- */

	/**
	 * getter
	 * @return id {@link UserId}
	 */
	public UserId getId() {
		return this.id;
	}

	/**
	 * setter
	 * @param id
	 */
	public void setId(int id) {
		this.id.setId(id);
	}

	/**
	 * setter
	 * @param userId {@link UserId}
	 */
	public void setId(UserId id) {
		if (id == null)	return ;
		this.id.setId(id.getId());
	}

	/**
	 * getter
	 * @return {@link String}
	 */
	public String getName() {
		return this.name.getWord();
	}

	/**
	 * setter
	 * @param name {@link String}
	 */
	public void setName(String name) {
		if (name == null)	return ;
		this.name.setWord(name);
	}

	/**
	 * setter
	 * @param name {@link NameWord}
	 */
	public void setName(NameWord name) {
		if (name == null)	return ;
		this.name.setWord(name);
	}

	/**
	 * getter
	 * @return {@link String}
	 */
	public String getEmail() {
		return this.email.getWord();
	}

	/**
	 * setter
	 * @param email {@link String}
	 */
	public void setEmail(String email) {
		if (email == null)	return ;
		this.email.setWord(email);
	}

	/**
	 * setter
	 * @param email {@link EmailWord}
	 */
	public void setEmail(EmailWord email) {
		if (email == null)	return ;
		this.email.setWord(email);
	}

	/**
	 * getter
	 * @return {@link String}
	 */
	public String getPassword() {
		return this.password.getWord();
	}

	/**
	 * setter
	 * @param password {@link String}
	 */
	public void setPassword(String password) {
		if (password == null)	return;
		this.password.setWord(password);
	}

	/**
	 * setter
	 * @param password {@link PasswdWord}
	 */
	public void setPassword(PasswdWord password) {
		if (password == null)	return ;
		this.password.setWord(password);
	}

	/**
	 * getter
	 * @return {@link WordList}
	 */
	public WordList getRoleList() {
		return this.roleList;
	}

	/**
	 * setter
	 * @param roleList {@link List}<{@link String}>
	 */
	public void setRoleList(List<String> roleList) {
		if (roleList == null)	return;
		this.roleList.setList(roleList);
	}

	/**
	 * setter
	 * @param roleList {@link WordList}
	 */
	public void setRoleList(WordList roleList) {
		if (roleList == null)	return ;
		this.roleList.setList(roleList);
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
	 * @param updated {@link LocalDateTime}
	 */
	public void setUpdated(LocalDateTime updated) {
		if (updated == null)	return;
		this.updated = updated;
	}
}
