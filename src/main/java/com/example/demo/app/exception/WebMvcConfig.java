package com.example.demo.app.exception;

import com.example.demo.common.exception.DataNotFoundException;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.exception.SQLNoUpdateException;

/**
 * WebMVCの設定クラス
 * @author nanai
 *
 */
public class WebMvcConfig {
	
	public static String EXCEPTION_NOTFOUND = "Can't find the same ID";
	
	/** SQL更新できなかった時のメッセージ */
	private static String EXCEPTION_SQL_NO_UPDATE = "SQL data not update...";
	
	/** SQL削除できなかった時のメッセージ */
	private static String EXCEPTION_SQL_NO_DELETE = "SQL data not delete...";
	
	
	/**
	 * 
	 * @return
	 */
	public static DataNotFoundException NOT_FOUND() {
		return new DataNotFoundException(EXCEPTION_NOTFOUND);
	}
	
	/**
	 * SQL更新エラーexception
	 * @return SQLNoDeleteException
	 */
	public static SQLNoUpdateException SQL_NOT_UPDATE() {
		return new SQLNoUpdateException(EXCEPTION_SQL_NO_UPDATE);
	}
	
	/**
	 * SQL削除エラーexception
	 * @return SQLNoDeleteException
	 */
	public static SQLNoDeleteException SQL_NOT_DELETE() {
		return new SQLNoDeleteException(EXCEPTION_SQL_NO_DELETE);
	}
}
