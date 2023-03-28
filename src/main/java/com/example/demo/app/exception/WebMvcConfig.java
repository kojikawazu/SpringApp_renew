package com.example.demo.app.exception;

import com.example.demo.common.exception.ArgumentsException;
import com.example.demo.common.exception.DataNotFoundException;
import com.example.demo.common.exception.SQLNoDeleteException;
import com.example.demo.common.exception.SQLNoInsertException;
import com.example.demo.common.exception.SQLNoUpdateException;

/**
 * WebMVCの設定クラス
 * @author nanai
 *
 */
public class WebMvcConfig {

	public static String EXCEPTION_NOTFOUND 			= "Can't find the same ID";

	/** 引数エラーメッセージ */
	private static String EXCEPTION_ARGUMENTS_ERROR 	= "Arguments error...";

	/** SQL追加できなかった時のメッセージ */
	private static String EXCEPTION_SQL_NO_INSERT 		= "SQL data not insert...";

	/** SQL更新できなかった時のメッセージ */
	private static String EXCEPTION_SQL_NO_UPDATE 		= "SQL data not update...";

	/** SQL削除できなかった時のメッセージ */
	private static String EXCEPTION_SQL_NO_DELETE 		= "SQL data not delete...";

	/**
	 * なしexception
	 * @return {@link DataNotFoundException}
	 */
	public static DataNotFoundException NOT_FOUND() {
		return new DataNotFoundException(EXCEPTION_NOTFOUND);
	}

	/**
	 * 引数エラーexception
	 * @return {@link ArgumentsException}
	 */
	public static ArgumentsException ARGUMENTS_ERROR() {
		return new ArgumentsException(EXCEPTION_ARGUMENTS_ERROR);
	}

	/**
	 * SQL追加エラーexception
	 * @return {@link SQLNoInsertException}
	 */
	public static SQLNoInsertException SQL_NOT_INSERT() {
		return new SQLNoInsertException(EXCEPTION_SQL_NO_INSERT);
	}

	/**
	 * SQL更新エラーexception
	 * @return {@link SQLNoUpdateException}
	 */
	public static SQLNoUpdateException SQL_NOT_UPDATE() {
		return new SQLNoUpdateException(EXCEPTION_SQL_NO_UPDATE);
	}

	/**
	 * SQL削除エラーexception
	 * @return {@link SQLNoDeleteException}
	 */
	public static SQLNoDeleteException SQL_NOT_DELETE() {
		return new SQLNoDeleteException(EXCEPTION_SQL_NO_DELETE);
	}
}
