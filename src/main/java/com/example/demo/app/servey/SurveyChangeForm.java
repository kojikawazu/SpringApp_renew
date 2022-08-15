package com.example.demo.app.servey;

public class SurveyChangeForm {
	
	private int id;
	
	private String name;
	
	private int allNum;
	private String sxName;
	private String profName;
	private String ageName;
	
	private int satis5;
	private int satis4;
	private int satis3;
	private int satis2;
	private int satis1;
	
	private int answerCnt;

	public SurveyChangeForm() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSatis5() {
		return satis5;
	}

	public void setSatis5(int satis5) {
		this.satis5 = satis5;
	}

	public int getSatis4() {
		return satis4;
	}

	public void setSatis4(int satis4) {
		this.satis4 = satis4;
	}

	public int getSatis3() {
		return satis3;
	}

	public void setSatis3(int satis3) {
		this.satis3 = satis3;
	}

	public int getSatis2() {
		return satis2;
	}

	public void setSatis2(int satis2) {
		this.satis2 = satis2;
	}

	public int getSatis1() {
		return satis1;
	}

	public void setSatis1(int satis1) {
		this.satis1 = satis1;
	}
	
	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public String getSxName() {
		return sxName;
	}

	public void setSxName(String sxName) {
		this.sxName = sxName;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public String getAgeName() {
		return ageName;
	}

	public void setAgeName(String ageName) {
		this.ageName = ageName;
	}

	public int getAnswerCnt() {
		return answerCnt;
	}

	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
	}
	
}
