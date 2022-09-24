package com.example.demo.common.entity;

import java.time.LocalDateTime;

/**
 * 経験モデル
 * @author nanai
 *
 */
public class ExperienceModel {

	/** 経験 */
	private String 			experience;
	
	/** 開始日付 */
	private LocalDateTime 	startTime;
	
	/** 終了日付 */
	private LocalDateTime 	endTime;
	
	/**
	 * コンストラクタ
	 */
	public ExperienceModel() {
		this.experience = "";
		this.startTime	= LocalDateTime.now();
		this.endTime	= LocalDateTime.now();
	}
	
	/**
	 * コンストラクタ
	 * @param experience
	 * @param startTime
	 * @param endTime
	 */
	public ExperienceModel(
			String 			experience,
			LocalDateTime 	startTime,
			LocalDateTime 	endTime) {
		this.experience = (experience == null ?
				"" : experience);
		this.startTime = (startTime == null ?
				LocalDateTime.now() : startTime);
		this.endTime = (endTime == null ?
				LocalDateTime.now() : endTime);
	}
	
	// ------------------------------------------------------------------------------------------
	/** getter */
	public String getExperience() {
		return this.experience;
	}
	
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	public LocalDateTime getEndTime() {
		return this.endTime;
	}	
}
