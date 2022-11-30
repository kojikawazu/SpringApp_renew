package com.example.demo.app.entity.survey;

import java.time.LocalDateTime;

import com.example.demo.app.common.id.survey.SurveyId;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.CommentWord;
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
	private CommentWord   comment;
	
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
			CommentWord   comment,
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
				new CommentWord("") :
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
			CommentWord   comment,
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
				new CommentWord("") :
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
			this.comment = new CommentWord("");
			this.created = LocalDateTime.now();
		} else {
			this.id = new SurveyId(model.getId());
			this.name = new NameWord(model.getName());
			this.age = new NormalNumber(model.getAge());
			this.profession = new NormalNumber(model.getProfession());
			this.men_or_female = new NormalNumber(model.getMen_or_female());
			this.satisfaction = new NormalNumber(model.getSatisfaction());
			this.comment = new CommentWord(model.getComment());
			this.created = model.getCreated();
		}
	}
	
	public int getId() {
		return this.id.getId();
	}
	
	public String getName() {
		return this.name.getWord();
	}
	
	public int getAge() {
		return this.age.getNumber();
	}
	
	public int getProfession() {
		return this.profession.getNumber();
	}
	
	public int getMen_or_female() {
		return this.men_or_female.getNumber();
	}
	
	public int getSatisfaction() {
		return this.satisfaction.getNumber();
	}
	
	public String getComment() {
		return this.comment.getWord();
	}
	
	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( this.created != null ? this.created : dateTime );
	}
}
