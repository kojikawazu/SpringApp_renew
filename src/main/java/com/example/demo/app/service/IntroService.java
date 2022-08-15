package com.example.demo.app.service;

import java.nio.file.Path;

import com.example.demo.common.entity.IntroJSONModel;

public interface IntroService {

	public IntroJSONModel readerIntroData_byJSON(Path path);
	
}
