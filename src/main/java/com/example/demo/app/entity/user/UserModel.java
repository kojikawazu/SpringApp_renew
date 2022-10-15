package com.example.demo.app.entity.user;

import java.time.LocalDateTime;

import com.example.demo.common.id.user.UserId;
import com.example.demo.common.id.user.UserKindId;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * ユーザーモデルクラス
 * @author nanai
 *
 */
public class UserModel {

	/** ユーザーID */
	private UserId 			id;
	
	/** ユーザー種別 */
	private UserKindId 		kindId;
	
	/** ユーザー名 */
	private NameWord 		name;
	
	/** ユーザーEmail */
	private EmailWord 		email;
	
	/** ユーザーパスワード */
	private PasswdWord 		password;
	
	/** ユーザー生成日 */
	private LocalDateTime 	created;
	
	/** ユーザー更新日 */
	private LocalDateTime 	updated;
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param kindId
	 * @param name
	 * @param email
	 * @param password
	 * @param created
	 * @param updated
	 */
	public UserModel(
			UserId 			id,
			UserKindId 		kindId,
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.id = (id == null ?
					new UserId(0) :
					id);
		
		this.kindId = (kindId == null ?
					new UserKindId(0) :
					kindId);
		
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
	 * @param kindId
	 * @param name
	 * @param email
	 * @param password
	 * @param created
	 * @param updated
	 */
	public UserModel(
			UserKindId 		kindId,
			NameWord 		name,
			EmailWord 		email,
			PasswdWord 		password,
			LocalDateTime 	created,
			LocalDateTime 	updated) {
		this.id = new UserId(0);
		
		this.kindId = (kindId == null ?
					new UserKindId(0) :
					kindId);
		
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
	public UserModel(UserModel model) {
		if (model == null) {
			this.id 		= new UserId(0);
			this.kindId 	= new UserKindId(0);
			this.name 		= new NameWord("");
			this.email 		= new EmailWord("");
			this.password 	= new PasswdWord("");
			this.created 	= LocalDateTime.now();
			this.updated 	= LocalDateTime.now();
		} else {
			this.id 		= new UserId(model.getId());
			this.kindId 	= new UserKindId(model.getKindId());
			this.name 		= new NameWord(model.getName());
			this.email 		= new EmailWord(model.getEmail());
			this.password 	= new PasswdWord(model.getPassword());
			this.created 	= model.getCreated();
			this.updated 	= model.getUpdated();
		}
	}
	
	/** -------------------------------------------------------------------- */
	
	public int getId() {
		return this.id.getId();
	}
	
	public int getKindId() {
		return this.kindId.getId();
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
	
	public LocalDateTime getCreated() {
		return this.created;
	}
	
	public LocalDateTime getUpdated() {
		return this.updated;
	}
}
