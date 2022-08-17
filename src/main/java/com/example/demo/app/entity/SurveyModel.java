package com.example.demo.app.entity;

import java.time.LocalDateTime;

import com.example.demo.common.id.SurveyId;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NameWord;

/**
 * 調査モデルクラス
 * @author nanai
 *
 */
public class SurveyModel {

	/** 調査ID */
	private SurveyId     id;
	
	/** 調査名 */
	private NameWord     name;
	
	/** 年齢 */
	private NormalNumber  age;
	
	/** 職業番号 */
	private NormalNumber  profession;
	
	/** 男 or 女 */
	private NormalNumber  men_or_female;
	
	/** 満足度 */
	private NormalNumber  satisfaction;
	
	/** コメント */
	private NameWord      comment;
	
	/** 生成日付 */
	private LocalDateTime created;
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param name
	 * @param age
	 * @param profession
	 * @param men_or_female
	 * @param satisfaction
	 * @param comment
	 * @param created
	 */
	public SurveyModel(
			SurveyId     id,
			NameWord     name,
			NormalNumber  age,
			NormalNumber  profession,
			NormalNumber  men_or_female,
			NormalNumber  satisfaction,
			NameWord      comment,
			LocalDateTime created
			) {
		this.id = (id == null ?
				new SurveyId(0) :
				id);
		
		this.name = (name == null ?
				new NameWord("") :
				name);
		
		this.age = (age == null ?
				new NormalNumber(0) :
				age);
		
		this.profession = (profession == null ?
				new NormalNumber(0) :
				profession);
		
		this.men_or_female = (men_or_female == null ?
				new NormalNumber(0) :
				men_or_female);
		
		this.satisfaction = (satisfaction == null ?
				new NormalNumber(0) :
				satisfaction);
		
		this.comment = (comment == null ?
				new NameWord("") :
					comment);
		
		this.created = (created == null ?
				LocalDateTime.now() :
				created);
	}
	
	/**
	 * コンストラクタ
	 * @param name
	 * @param age
	 * @param profession
	 * @param men_or_female
	 * @param satisfaction
	 * @param comment
	 * @param created
	 */
	public SurveyModel(
			NameWord     name,
			NormalNumber  age,
			NormalNumber  profession,
			NormalNumber  men_or_female,
			NormalNumber  satisfaction,
			NameWord      comment,
			LocalDateTime created
			) {
		this.id = new SurveyId(0);
		
		this.name = (name == null ?
				new NameWord("") :
				name);
		
		this.age = (age == null ?
				new NormalNumber(0) :
				age);
		
		this.profession = (profession == null ?
				new NormalNumber(0) :
				profession);
		
		this.men_or_female = (men_or_female == null ?
				new NormalNumber(0) :
				men_or_female);
		
		this.satisfaction = (satisfaction == null ?
				new NormalNumber(0) :
				satisfaction);
		
		this.comment = (comment == null ?
				new NameWord("") :
					comment);
		
		this.created = (created == null ?
				LocalDateTime.now() :
				created);
	}
	
	/**
	 * コンストラクタ
	 * @param model
	 */
	public SurveyModel(
			SurveyModel model) {
		if(model == null) {
			this.id = new SurveyId(0);
			this.name = new NameWord("");
			this.age = new NormalNumber(0);
			this.profession = new NormalNumber(0);
			this.men_or_female = new NormalNumber(0);
			this.satisfaction = new NormalNumber(0);
			this.comment = new NameWord("");
			this.created = LocalDateTime.now();
		} else {
			this.id = new SurveyId(model.getId());
			this.name = new NameWord(model.getName());
			this.age = new NormalNumber(model.getAge());
			this.profession = new NormalNumber(model.getProfession());
			this.men_or_female = new NormalNumber(model.getMen_or_female());
			this.satisfaction = new NormalNumber(model.getSatisfaction());
			this.comment = new NameWord(model.getComment());
			this.created = model.getCreated();
		}
	}
	
	public int getId() {
		return this.id.getId();
	}
	
	protected void setId(int id) {
		this.id = new SurveyId(id);
	}
	
	public String getName() {
		return this.name.getWord();
	}
	
	protected void setName(String name) {
		if(name == null)	return ;
		this.name = new NameWord(name);
	}
	
	public int getAge() {
		return this.age.getNumber();
	}
	
	protected void setAge(int age) {
		this.age = new NormalNumber(age);
	}
	
	public int getProfession() {
		return this.profession.getNumber();
	}
	
	protected void setProfession(int profession) {
		this.profession = new NormalNumber(profession);
	}
	
	public int getMen_or_female() {
		return this.men_or_female.getNumber();
	}
	
	protected void setMen_or_female(int men_or_female) {
		this.men_or_female = new NormalNumber(men_or_female);
	}
	
	public int getSatisfaction() {
		return this.satisfaction.getNumber();
	}
	
	protected void setSatisfaction(int satisfaction) {
		this.satisfaction = new NormalNumber(satisfaction);
	}
	
	public String getComment() {
		return this.comment.getWord();
	}
	
	protected void setComment(String comment) {
		if(comment == null)	return ;
		this.comment = new NameWord(comment);
	}
	
	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( this.created != null ? this.created : dateTime );
	}
	
	protected void setCreated(LocalDateTime created) {
		if(created == null)	return ;
		this.created = created;
	}
}
