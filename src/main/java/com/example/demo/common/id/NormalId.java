package com.example.demo.common.id;

/**
 * ノーマルIDクラス
 * <br>
 * implements {@link SuperId}
 * @author nanai
 *
 */
public class NormalId implements SuperId {

	/** デフォルトのIDの値 */
	private static final int DEFAULT_ID_NUMBER = 0;

	/** ID */
	protected int id;

	/**
	 * コンストラクタ
	 */
	public NormalId() {
		this.setId(DEFAULT_ID_NUMBER);
	}

	/**
	 * コンストラクタ
	 * @param id
	 */
	public NormalId(int id) {
		this();
		this.setId(id);
	}

	/**
	 * getter
	 * @return id
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * setter
	 * @param id
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * setter
	 * @param id {@link NormalId}
	 */
	public void setId(NormalId id) {
		if (id == null)	return ;
		this.id = id.getId();
	}
}
