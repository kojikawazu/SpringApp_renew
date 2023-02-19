package com.example.demo.app.entity.user;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.app.common.id.user.UserId;
import com.example.demo.common.list.WordList;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーモデル
 * @author nanai
 *
 */
public class SecUserModel {

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
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param roleList
	 * @param created
	 * @param updated
	 */
	public SecUserModel(
			UserId 			id,
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			WordList		roleList,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.id = (id == null ?
					new UserId(0) :
					id);
		
		this.name = (name == null ?
					new NameWord("") :
					name);
		
		this.email = (email == null ?
					new EmailWord("") :
					email);
		
		this.password = (password == null ?
					new PasswdWord("") :
					password);
		
		this.roleList = (roleList == null ?
					new WordList() :
					roleList);
		
		this.created = (created == null ?
					LocalDateTime.now() :
					created);
		
		this.updated = (updated == null ?
					LocalDateTime.now() :
					updated);
	}
	
	/**
	 * コンストラクタ
	 * @param name
	 * @param email
	 * @param password
	 * @param roleList
	 * @param created
	 * @param updated
	 */
	public SecUserModel(
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			WordList		roleList,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.id = (id == null ?
				new UserId(0) :
				id);
		
		this.name = (name == null ?
					new NameWord("") :
					name);
		
		this.email = (email == null ?
					new EmailWord("") :
					email);
		
		this.password = (password == null ?
					new PasswdWord("") :
					password);
		
		this.roleList = (roleList == null ?
				new WordList() :
				roleList);
		
		this.created = (created == null ?
					LocalDateTime.now() :
					created);
		
		this.updated = (updated == null ?
					LocalDateTime.now() :
					updated);
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param created
	 * @param updated
	 */
	public SecUserModel(
			UserId 			id,
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.roleList 	= new WordList();
		
		this.id = (id == null ?
				new UserId(0) :
				id);
		
		this.name = (name == null ?
					new NameWord("") :
					name);
		
		this.email = (email == null ?
					new EmailWord("") :
					email);
		
		this.password = (password == null ?
					new PasswdWord("") :
					password);
		
		this.created = (created == null ?
					LocalDateTime.now() :
					created);
		
		this.updated = (updated == null ?
					LocalDateTime.now() :
					updated);
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public SecUserModel(SecUserModel model) {
		if (model == null) {
			this.id 		= new UserId(0);
			this.name 		= new NameWord("");
			this.email 		= new EmailWord("");
			this.password 	= new PasswdWord("");
			this.roleList	= new WordList();
			this.created 	= LocalDateTime.now();
			this.updated 	= LocalDateTime.now();
		} else {
			this.id 		= new UserId(model.getId());
			this.name 		= new NameWord(model.getName());
			this.email 		= new EmailWord(model.getEmail());
			this.password 	= new PasswdWord(model.getPassword());
			this.roleList	= new WordList(model.getRoleList());
			this.created 	= model.getCreated();
			this.updated 	= model.getUpdated();
		}
	}
	
	/** -------------------------------------------------------------------- */
	
	public int getId() {
		return this.id.getId();
	}
	
	public String getName() {
		return this.name.getWord();
	}
	
	public String getEmail() {
		return this.email.getWord();
	}
	
	public String getPassword() {
		return this.password.getWord();
	}
	
	public List<String> getRoleList() {
		return this.roleList.getList();
	}
	
	public LocalDateTime getCreated() {
		return this.created;
	}
	
	public LocalDateTime getUpdated() {
		return this.updated;
	}
}
