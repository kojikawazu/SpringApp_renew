package com.example.demo.app.service.intro;

import java.nio.file.Path;

import com.example.demo.common.entity.IntroJSONModel;

/**
 * 自己紹介サービスインターフェース
 * @author nanai
 *
 */
public interface IntroService {

	/**
	 * 自己紹介データをJSONデータから読取
	 * @param  path {@link Path}
	 * @return JSON読取モデル {@link IntroJSONModel}
	 */
	public IntroJSONModel readerIntroData_byJSON(Path path);
	
}
