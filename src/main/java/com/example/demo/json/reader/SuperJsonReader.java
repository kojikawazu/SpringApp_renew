package com.example.demo.json.reader;

import java.nio.file.Path;

import com.example.demo.json.model.IntroJSONModel;

/**
 * json読み読みインターフェース
 * @author nanai
 *
 */
public interface SuperJsonReader {
	
	public IntroJSONModel reader(Path path);

}
