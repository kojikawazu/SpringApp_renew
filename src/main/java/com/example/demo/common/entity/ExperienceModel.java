package com.example.demo.common.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.common.list.IntroList;
import com.example.demo.common.number.NormalNumber;

/**
 * 経験モデル
 * @author nanai
 *
 */
public class ExperienceModel {

	/** メインで表示する */
	/** --------------------------------------------------- */
	
	/** 経験 */
	private String 			experience;
	
	/** 開始日付 */
	private LocalDateTime 	startTime;
	
	/** 終了日付 */
	private LocalDateTime 	endTime;
	
	/** クリックし表示する */
	/** --------------------------------------------------- */
	
	/** チームメンバー人数 */
	private NormalNumber	memberSum;
	
	/** チームポジション */
	private String			position;
	
	/** 概要 */
	private String			overview;
	
	/** 担当業務リスト */
	private IntroList		personChargeList;
	
	/** 業務ポイント */
	private String			workPoint;
	
	/** スキルリスト */
	private IntroList		techList;
	
	/** --------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 */
	public ExperienceModel() {
		this.experience 		= "";
		this.startTime			= LocalDateTime.now();
		this.endTime			= LocalDateTime.now();
		this.memberSum			= new NormalNumber(0);
		this.position			= "";
		this.overview			= "";
		this.personChargeList	= new IntroList();
		this.workPoint			= "";
		this.techList			= new IntroList();
	}
	
	/**
	 * コンストラクタ
	 * @param experience
	 * @param startTime
	 * @param endTime
	 * @param memberSum
	 * @param position
	 * @param overview
	 * @param personChargeList
	 * @param workPoint
	 * @param techList
	 */
	public ExperienceModel(
			String 			experience,
			LocalDateTime 	startTime,
			LocalDateTime 	endTime,
			NormalNumber	memberSum,
			String			position,
			String			overview,
			IntroList		personChargeList,
			String			workPoint,
			IntroList		techList) {
		this.experience = (experience == null ?
				"" : experience);
		this.startTime = (startTime == null ?
				LocalDateTime.now() : startTime);
		this.endTime = (endTime == null ?
				LocalDateTime.now() : endTime);
		this.memberSum	= (memberSum == null ?
				new NormalNumber(0) : memberSum);
		this.position = (position == null ?
				"" : position);
		this.overview = (overview == null ?
				"" : overview);
		this.personChargeList = (personChargeList == null ?
				new IntroList() : personChargeList);
		this.workPoint = (workPoint == null ?
				"" : workPoint);
		this.techList = (techList == null ?
				new IntroList() : techList);
	}
	
	// ------------------------------------------------------------------------------------------
	/** getter */
	
	/** 
	 * 経験取得
	 * @return String
	 */
	public String getExperience() {
		return this.experience;
	}
	
	/** 
	 * 開始日付取得
	 * @return LocalDateTime
	 */
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	/** 
	 * 終了日付取得
	 * @return LocalDateTime
	 */
	public LocalDateTime getEndTime() {
		return this.endTime;
	}
	
	/** 
	 * メンバー人数合計取得
	 * @return NormalNumber
	 */
	public NormalNumber getMemberSum() {
		return this.memberSum;
	}
	
	/** 
	 * ポジション取得
	 * @return String
	 */
	public String getPosition() {
		return this.position;
	}
	
	/** 
	 * 概要取得
	 * @return String
	 */
	public String getOverview() {
		return this.overview;
	}
	
	/** 
	 * 担当業務取得
	 * @return IntroList
	 */
	public IntroList getPersonChargeList() {
		return this.personChargeList;
	}
	
	/** 
	 * ポイント取得
	 * @return String
	 */
	public String getWorkPoint() {
		return this.workPoint;
	}
	
	/** 
	 * 技術リスト取得
	 * @return IntroList
	 */
	public IntroList getTechList() {
		return this.techList;
	}
}
