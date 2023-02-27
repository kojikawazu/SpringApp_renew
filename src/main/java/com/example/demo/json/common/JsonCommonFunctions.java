package com.example.demo.json.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.common.list.WordList;
import com.example.demo.common.word.NormalWord;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * JSON共通関数群
 * @author nanai
 *
 */
public class JsonCommonFunctions {

	/**
	 * String型 → LocalDateTime型へ変換
	 * @param  timeString {@link String} 日付(YYYY/MM) or (YYYY/M)
	 * @return {@link LocalDateTime}型の時間
	 */
	public static LocalDateTime changeLocalDateTime(String timeString) {
		if (timeString == null || timeString.equals(""))	return LocalDateTime.now();

		// nowは現在の日付を返す
		if (timeString.equals(JsonCommonConstants.JSON_WORD_NOW)) {
			return LocalDateTime.now();
		}
		// 正規表現の範囲外も日付を返す
		String pattern = "^[0-9]{4}/[0-9]{1,2}$";
		String pattern2 = "^[0-9]{4}/[0]{1,2}$";
		if (!(timeString.matches(pattern)) || (timeString.matches(pattern2))) {
			return LocalDateTime.now();
		}

		DateTimeFormatter 	formatPatternA 	= DateTimeFormatter.ofPattern("uuuu/M/dd");
		DateTimeFormatter 	formatPatternB 	= DateTimeFormatter.ofPattern("uuuu/MM/dd");
		String 				dayFormatString = "/01";
		int 				timeLen 		= timeString.length();
		int 				lengthStandard  = 6;
		LocalTime			timeFormat		= LocalTime.of(0, 0);

		// uuuu/MM + /01
		timeString = timeString + dayFormatString;
		// uuuu/MM/01 or uuuu/M/01
		LocalDate localDate = LocalDate.parse(timeString, 
								(timeLen == lengthStandard ? 
									formatPatternA : 
									formatPatternB));
		// uuuu/MM/01 mm:ss
		LocalDateTime localDateTime = LocalDateTime.of(localDate, timeFormat);

		return localDateTime;
	}

	/**
	 * データの読み込み
	 * @param  rootJsonNode	{@link JsonNode}	jsonノード 
	 * @param  keyword		{@link String}		検索キーワード
	 * @return 				{@link NormalWord}	データ(文字列) 
	 */
	public static NormalWord readData(JsonNode rootJsonNode, String keyword) {
		if (rootJsonNode == null || keyword == null || keyword.equals("")) return new NormalWord();
		NormalWord normalWord = new NormalWord();

		try {
			// JSONキーワードによる取り出し
			String resultWord = rootJsonNode.get(keyword).get(0).get(JsonCommonConstants.JSON_SECTION_KEYWORD).asText();
			normalWord.setWord(resultWord);
		} catch(NullPointerException ex) {
			// 取得失敗
			//ex.printStackTrace();
		}

		return normalWord;
	}

	/**
	 * データリストの読み込み + 長文へ変換
	 * @param  rootJsonNode {@link JsonNode}	jsonノード
	 * @param  keyword		{@link String}		検索キーワード
	 * @return 				{@link NormalWord}	連結ワード
	 */
	public static NormalWord readDataLongString(JsonNode rootJsonNode, String keyword) {
		if(rootJsonNode == null || keyword == null || keyword.equals(""))	return new NormalWord();

		String resultDataLong = "";
		try {
			for (JsonNode node : rootJsonNode.get(keyword)) {
				// JSONキーワードによる取り出し
				String resultText = node.get(JsonCommonConstants.JSON_SECTION_KEYWORD).asText();
				resultDataLong = resultDataLong + resultText;
			}
		} catch(NullPointerException ex) {
			// 取得失敗
			//ex.printStackTrace();
		}

		return new NormalWord(resultDataLong);
	}

	/**
	 * データの読み込み
	 * @param rootJsonNode	{@link JsonNode} 		jsonノード
	 * @param keyword		{@link String}			キーワード
	 * @return 				{@link WordList} 		データリスト
	 */
	public static WordList readData_returnList(JsonNode rootJsonNode, String keyword) {
		if (rootJsonNode == null || keyword == null || keyword.equals(""))	return new WordList();

		WordList	 resultWordList	= new WordList();
		List<String> resultList 	= resultWordList.getList();

		try {
			// 複数のデータをリスト化
			for (JsonNode node : rootJsonNode.get(keyword)) {
				// JSONキーワードによる取り出し
				String resultWord = node.get(JsonCommonConstants.JSON_SECTION_KEYWORD).asText();
				resultList.add(resultWord);
			}
		} catch(NullPointerException ex) {
			// 取得失敗はスキップ
			//ex.printStackTrace();
		}

		return resultWordList;
	}

	/**
	 * データの読み込み
	 * @param rootJsonNode	{@link JsonNode} 		jsonノード
	 * @param headerList	{@link WordList}		ヘッダーリスト
	 * @param headerIndex	int						ヘッダーリストのインデックス番号
	 * @return 				{@link WordList} 		データリスト
	 */
	public static WordList readData_returnList(JsonNode rootJsonNode, WordList headerList, int headerIndex) {
		if (rootJsonNode == null || headerList == null)	return new WordList();

		List<String> 			headerStringList	= headerList.getList();
		String					Keyword				= "";

		// キーワード取得
		try {
			Keyword = headerStringList.get(headerIndex);
			if (Keyword == null || Keyword.equals("")) 	return new WordList();
		} catch(NullPointerException | IndexOutOfBoundsException ex) {
			// 取得失敗
			return new WordList();
		}

		// キーワードによるデータの取得
		WordList resultWordList = JsonCommonFunctions.readData_returnList(rootJsonNode, Keyword);
		return resultWordList;
	}
}
