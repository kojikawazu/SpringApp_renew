package com.example.demo.app.servey;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SurveyForm {
	
	@NotNull
	@Size(min = 1, max = 20, message = "20文字を超えています。")
	private String name;
	
	@NotNull
	private int age;
	
	@NotNull
	private int profession;
	
	@NotNull
	private int men_or_female;
	
	@NotNull
	private int satisfaction;
	
	@NotNull
	@Size(min = 1, max = 100, message = "100文字を超えています。")
	private String comment;

	public SurveyForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getProfession() {
		return profession;
	}

	public void setProfession(int profession) {
		this.profession = profession;
	}

	public int getMen_or_female() {
		return men_or_female;
	}

	public void setMen_or_female(int men_or_female) {
		this.men_or_female = men_or_female;
	}

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
