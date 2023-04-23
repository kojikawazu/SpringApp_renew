package com.example.demo.common.id;

/**
 * スーパーIDインターフェース(T)
 * @author nanai
 *
 */
public interface SuperIdInterface<T> {

	/**
	 * 取得
	 * @return T
	 */
	public T getId();

	/**
	 * setter
	 * @param id
	 */
	void setId(T id);
}
