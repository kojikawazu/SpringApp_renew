package com.example.demo.json.list;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.common.list.ListInterface;
import com.example.demo.json.model.ExperienceModel;

/**
 * 経験リストクラス
 * @author nanai
 *
 */
public class ExperienceList implements ListInterface<ExperienceModel> {

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
		this();
		this.setList(list);
	}

	@Override
	public List<ExperienceModel> getList(){
		return experienceList;
	}

	@Override
	public void setList(List<ExperienceModel> list) {
		if (list == null)	return;

		this.experienceList.clear();
		for (ExperienceModel model : list) {
			this.experienceList.add(model);
		}
	}

	public void setList(ExperienceList obj) {
		if (obj == null)	return;
		this.setList(obj.getList());
	}
}
