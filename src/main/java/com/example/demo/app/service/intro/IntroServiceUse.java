package com.example.demo.app.service.intro;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.json.model.IntroJSONModel;
import com.example.demo.json.reader.IntroJsonReader;

/**
 * JSON読取サービスクラス
 * @author nanai
 *
 */
@Service
public class IntroServiceUse implements IntroService {

	/** 
	 * 自己紹介読取クラス 
	 * {@link IntroJsonReader} 
	 */
	private final IntroJsonReader reader;
	
	/**
	 * コンストラクタ
	 * @param reader {@link IntroJsonReader} 
	 */
	@Autowired
	public IntroServiceUse(IntroJsonReader reader) {
		this.reader = reader;
	}
	
	/**
	 * 自己紹介データをJSONデータから読取
	 * @param  path {@link Path}
	 * @return JSON読取モデル {@link IntroJSONModel}
	 */
	@Override
	public IntroJSONModel readerIntroData_byJSON(Path path) {
		return this.reader.reader(path);
	}

}
