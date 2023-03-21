package com.example.demo.app.common.id.inquiry;

import com.example.demo.common.id.NormalId;

/**
 * 問い合わせIDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class InquiryId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public InquiryId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public InquiryId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link InquiryId}
	 */
	public void setId(InquiryId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
