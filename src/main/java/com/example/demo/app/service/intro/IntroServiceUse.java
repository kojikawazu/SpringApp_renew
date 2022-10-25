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

	/** 
	 * 自己紹介読取クラス 
	 * {@link IntroReader} 
	 */
	private final IntroReader reader;
	
	/**
	 * コンストラクタ
	 * @param reader {@link IntroReader} 
	 */
	@Autowired
	public IntroServiceUse(IntroReader reader) {
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
