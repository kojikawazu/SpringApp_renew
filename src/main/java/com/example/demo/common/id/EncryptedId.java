package com.example.demo.common.id;

import com.example.demo.common.encrypt.CommonEncrypt;

/**
 * 暗号化済IDクラス
 * <br>
 * implements {@link SuperId}
 * @author nanai
 *
 */
public class EncryptedId implements SuperIdInterface<String> {

	/** デフォルトのIDの値 */
	private static final String DEFAULT_ID_ENCRYPTED = "wfssM4JI4nk=";

	/** ID */
	protected String id;

	/**
	 * コンストラクタ
	 */
	public EncryptedId() {
		this.id = DEFAULT_ID_ENCRYPTED;
	}

	/**
	 * コンストラクタ
	 * @param encryptedId {@link String}
	 */
	public EncryptedId(String encryptedId) {
		this();
		setId(encryptedId);
	}

	/**
	 * getter
	 * @param id {@link String}
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * getter
	 * @return 復号化済id
	 */
	public int getIdDecrypted() {
		String dId = CommonEncrypt.decrypt(this.id);
		int iId = Integer.valueOf(dId);
		return iId;
	}

	/**
	 * setter
	 * @param id {@link String}
	 */
	@Override
	public void setId(String id) {
		if (id == null)	return;
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link EncryptedId}
	 */
	public void setId(EncryptedId id) {
		if (id == null)	return;
		this.id = id.getId();
	}

	/**
	 * setter
	 * @param 原文id
	 */
	public void setIdNoEncrypt(int id) {
		this.id = CommonEncrypt.encrypt(id);
	}
}
