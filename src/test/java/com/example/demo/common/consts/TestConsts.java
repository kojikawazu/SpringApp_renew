package com.example.demo.common.consts;

import java.time.LocalDateTime;

/**
 * テスト用定数
 * @author nanai
 *
 */
public class TestConsts {
	
	/** OK番号 */
	public static final int RESULT_NUMBER_OK = 1;
	
	// ---------------------------------------------------------------------------------
	/** [SQL] */
	
	/** テスト用時間 01 */
	public static final LocalDateTime TEST_TIME_01 = 
			LocalDateTime.of(2000, 01, 01, 00, 00, 00);
	
	/** テスト用時間 02 */
	public static final LocalDateTime TEST_TIME_02 = 
			LocalDateTime.of(2000, 01, 02, 00, 00, 00);
	
	/** テスト用ネーム */
	public static final String TEST_NAME_NAME       = "テストネーム";
	
	/** テスト用メールアドレス */
	public static final String TEST_EMAIL_NAME      = "テストメールアドレス";
	
	/** テスト用タイトル */
	public static final String TEST_TITLE_NAME      = "テストタイトル";
	
	/** テスト用タグ */
	public static final String TEST_TAG_NAME        = "テストタグ";
	
	/** テスト用コメント */
	public static final String TEST_COMMENT_NAME    = "テストコメント";
	
	/** テスト用パスワード */
	public static final String TEST_PASSWORD_NAME    = "テストパスワード";
	
}
