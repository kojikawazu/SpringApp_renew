package com.example.demo.json;

import java.nio.file.Path;

import com.example.demo.app.intro.IntroJSONModel;

/**
 * json読み読みインターフェース
 * @author nanai
 *
 */
public interface IntroJsonReader {
	
	public IntroJSONModel reader(Path path);

}
