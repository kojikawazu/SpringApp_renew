package com.example.demo.app.service;

import java.nio.file.Path;

import com.example.demo.app.intro.IntroJSONModel;

public interface IntroService {

	public IntroJSONModel readerIntroData_byJSON(Path path);
	
}
