package com.example.demo.common.encrypt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 共通暗号/復号クラステスト
 * @author nanai
 *
 */
class CommonEncryptTest {

	/**
	 * 暗号化テスト
	 */
	@Test
	void encryptTest() {
		String testWord = "test";
		String expectedWord = "KTkJsygC8rg=";
		String resultWord = "";

		resultWord = CommonEncrypt.encrypt(testWord);
		assertEquals(expectedWord, resultWord);
	}

	/**
	 * 暗号化テスト(Blank)
	 */
	@Test
	void encryptTest_Blank() {
		String resultWord = "";

		resultWord = CommonEncrypt.encrypt("");
		assertEquals("", resultWord);
	}

	/**
	 * 暗号化テスト(null)
	 */
	@Test
	void encryptTest_Null() {
		String resultWord = "";

		resultWord = CommonEncrypt.encrypt(null);
		assertEquals("", resultWord);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 暗号化テスト
	 */
	@Test
	void encrypt_byNumberTest() {
		int testNumber = 1;
		String expectedWord = "if2NGDmSZO0=";
		String resultWord = "";

		resultWord = CommonEncrypt.encrypt(testNumber);
		assertEquals(expectedWord, resultWord);
	}

	/** ------------------------------------------------------------------------------------------------------------- */

	/**
	 * 復号化テスト
	 */
	@Test
	void decryptTest() {
		String testWord = "KTkJsygC8rg=";
		String expectedWord = "test";
		String resultWord = "";

		resultWord = CommonEncrypt.decrypt(testWord);
		assertEquals(expectedWord, resultWord);
	}

	/**
	 * 復号化テスト(Blank)
	 */
	@Test
	void decryptTest_Blank() {
		String resultWord = "";

		resultWord = CommonEncrypt.encrypt("");
		assertEquals("", resultWord);
	}

	/**
	 * 復号化テスト(null)
	 */
	@Test
	void decryptTest_Null() {
		String resultWord = "";

		resultWord = CommonEncrypt.encrypt(null);
		assertEquals("", resultWord);
	}

	/** ------------------------------------------------------------------------------------------------------------- */
}
