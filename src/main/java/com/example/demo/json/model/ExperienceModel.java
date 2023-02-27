package com.example.demo.json.model;

import java.time.LocalDateTime;

import com.example.demo.common.list.WordList;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.NormalWord;

/**
 * 経験モデル
 * @author nanai
 *
 */
public class ExperienceModel {

	/** メインで表示する */
	/** --------------------------------------------------- */

	/** 経験 */
	private NormalWord 		experience;

	/** 開始日付 */
	private LocalDateTime 	startTime;

	/** 終了日付 */
	private LocalDateTime 	endTime;

	/** クリックし表示する */
	/** --------------------------------------------------- */

	/** チームメンバー人数 */
	private NormalNumber	memberSum;

	/** チームポジション */
	private NormalWord		position;

	/** 概要 */
	private NormalWord		overview;

	/** 担当業務リスト */
	private WordList		personChargeList;

	/** 業務ポイント */
	private NormalWord		workPoint;

	/** スキルリスト */
	private WordList		techList;

	/** --------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public ExperienceModel() {
		this.experience 		= new NormalWord();
		this.startTime			= LocalDateTime.now();
		this.endTime			= LocalDateTime.now();
		this.memberSum			= new NormalNumber();
		this.position			= new NormalWord();
		this.overview			= new NormalWord();
		this.personChargeList	= new WordList();
		this.workPoint			= new NormalWord();
		this.techList			= new WordList();
	}

	/**
	 * コンストラクタ
	 * @param experience		{@link NormalWord}		経験
	 * @param startTime			{@link LocalDateTime}	開始日付
	 * @param endTime			{@link LocalDateTime}	終了日付
	 * @param memberSum			{@link NormalNumber}	チームメンバー人数
	 * @param position			{@link NormalWord}		チームポジション
	 * @param overview			{@link NormalWord}		概要
	 * @param personChargeList	{@link WordList}		担当業務リスト
	 * @param workPoint			{@link NormalWord}		業務ポイント
	 * @param techList			{@link WordList}		スキルリスト
	 */
	public ExperienceModel(
			NormalWord 		experience,
			LocalDateTime 	startTime,
			LocalDateTime 	endTime,
			NormalNumber	memberSum,
			NormalWord		position,
			NormalWord		overview,
			WordList		personChargeList,
			NormalWord		workPoint,
			WordList		techList) {
		this();
		this.setExperience(experience);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setMemberSum(memberSum);
		this.setPosition(position);
		this.setOverview(overview);
		this.setPersonChargeList(personChargeList);
		this.setWorkPoint(workPoint);
		this.setWorkPoint(workPoint);
	}

	// ------------------------------------------------------------------------------------------
	/** getter */

	/** 
	 * 経験取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getExperience() {
		return this.experience;
	}

	/**
	 * 経験設定
	 * @param experience {@link NormalWord} 経験
	 */
	public void setExperience(NormalWord experience) {
		if (experience == null)	return ;
		this.experience.setWord(experience);
	}

	/** 
	 * 開始日付取得
	 * @return {@link LocalDateTime}
	 */
	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	/**
	 * 開始日付設定
	 * @param startTime {@link LocalDateTime}
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = (startTime == null ?
			LocalDateTime.now() : 
			startTime);
	}

	/** 
	 * 終了日付取得
	 * @return LocalDateTime {@link LocalDateTime}
	 */
	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	/**
	 * 終了日付設定
	 * @param endTime {@link LocalDateTime}
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = (endTime == null ?
			LocalDateTime.now() : 
			endTime);
	}

	/** 
	 * メンバー人数合計取得
	 * @return {@link NormalNumber}
	 */
	public NormalNumber getMemberSum() {
		return this.memberSum;
	}

	/**
	 * メンバー人数合計設定
	 * @param memberSum {@link NormalNumber}
	 */
	public void setMemberSum(NormalNumber memberSum) {
		if (memberSum == null) return;
		this.memberSum.setNumber(memberSum);
	}

	/** 
	 * ポジション取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getPosition() {
		return this.position;
	}

	/**
	 * ポジション設定
	 * @param position {@link NormalWord}
	 */
	public void setPosition(NormalWord position) {
		if (position == null)	return ;
		this.position.setWord(position);
	}

	/** 
	 * 概要取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getOverview() {
		return this.overview;
	}

	/**
	 * 概要設定
	 * @param overview {@link NormalWord}
	 */
	public void setOverview(NormalWord overview) {
		if (overview == null)	return;
		this.overview.setWord(overview);
	}

	/** 
	 * 担当業務リスト取得
	 * @return IntroList {@link WordList}
	 */
	public WordList getPersonChargeList() {
		return this.personChargeList;
	}

	/**
	 * 担当業務リスト設定
	 * @param personChargeList {@link WordList}
	 */
	public void setPersonChargeList(WordList personChargeList) {
		if (personChargeList == null)	return ;
		this.personChargeList.setList(personChargeList);
	}

	/** 
	 * ポイント取得
	 * @return {@link NormalWord}
	 */
	public NormalWord getWorkPoint() {
		return this.workPoint;
	}

	/**
	 * ポイント設定
	 * @param workPoint {@link NormalWord}
	 */
	public void setWorkPoint(NormalWord workPoint) {
		if (workPoint == null)	return ;
		this.workPoint.setWord(workPoint);
	}

	/** 
	 * 技術リスト取得
	 * @return IntroList {@link WordList}
	 */
	public WordList getTechList() {
		return this.techList;
	}

	/**
	 * 技術リスト設定
	 * @param techList {@link WordList}
	 */
	public void setTechList(WordList techList) {
		if (techList == null) 	return ;
		this.techList.setList(techList);
	}
}
