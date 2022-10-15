package com.example.demo.app.service.intro;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.entity.IntroJSONModel;
import com.example.demo.json.IntroReader;

/**
 * JSON読取サービスクラス
 * @author nanai
 *
 */
@Service
public class IntroServiceUse implements IntroService {

	/** 自己紹介読取クラス */
	private final IntroReader reader;
	
	/**
	 * コンストラクタ
	 * @param reader
	 */
	@Autowired
	public IntroServiceUse(IntroReader reader) {
		this.reader = reader;
	}
	
	/**
	 * 自己紹介データをJSONデータから読取
	 * @param  path
	 * @return JSON読取モデル
	 */
	@Override
	public IntroJSONModel readerIntroData_byJSON(Path path) {
		return this.reader.reader(path);
	}

}
