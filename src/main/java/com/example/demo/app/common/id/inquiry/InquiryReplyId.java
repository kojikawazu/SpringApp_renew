package com.example.demo.app.common.id.inquiry;

import com.example.demo.common.id.NormalId;

/**
 * 問い合わせ返信IDクラス
 * <br>
 * extends {@link NormalId}
 * @author nanai
 *
 */
public class InquiryReplyId extends NormalId {

	/**
	 * コンストラクタ
	 */
	public InquiryReplyId() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public InquiryReplyId(int id) {
		super();
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link InquiryReplyId}
	 */
	public void setId(InquiryReplyId id) {
		if (id == null)	return;
		this.id = id.getId();
	}
}
