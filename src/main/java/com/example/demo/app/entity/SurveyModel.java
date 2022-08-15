package com.example.demo.app.entity;

import java.time.LocalDateTime;

public class SurveyModel {

	private int id;
	private String name;
	private int age;
	private int profession;
	private int men_or_female;
	private int satisfaction;
	private String comment;
	private LocalDateTime created;
	
	public SurveyModel() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return ( name != null ? name : "");
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
		return ( comment != null ? comment : "");
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getCreated() {
		LocalDateTime dateTime = LocalDateTime.of(2000, 01, 01, 00, 00, 00);
		return ( created != null ? created : dateTime );
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
}
