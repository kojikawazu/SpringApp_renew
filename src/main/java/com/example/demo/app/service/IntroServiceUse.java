package com.example.demo.app.service;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.intro.IntroJSONModel;
import com.example.demo.json.IntroReader;

@Service
public class IntroServiceUse implements IntroService {

	private final IntroReader reader;
	
	@Autowired
	public IntroServiceUse(IntroReader reader) {
		// TODO コンストラクタ
		this.reader = reader;
	}
	
	@Override
	public IntroJSONModel readerIntroData_byJSON(Path path) {
		// TODO 自己紹介文のJSONデータ読込
		return this.reader.reader(path);
	}

}
