package com.example.demo.common.list;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.entity.ExperienceModel;

/**
 * 経験リストクラス
 * @author nanai
 *
 */
public class ExperienceList implements ListInterfaceS {

	/** 経験リスト */
	private List<ExperienceModel> experienceList;
	
	/**
	 * コンストラクタ
	 */
	public ExperienceList() {
		this.experienceList = new ArrayList<ExperienceModel>();
	}
	
	/**
	 * コンストラクタ
	 * @param 経験リストクラス
	 */
	public ExperienceList(List<ExperienceModel> list) {
		this.experienceList = (list == null ?
				new ArrayList<ExperienceModel>() :
				list);
	}
	
	/** getter */
	public List<ExperienceModel> getList(){
		return experienceList;
	}
}
