package com.example.demo.app.intro;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.service.IntroService;

@Controller
@RequestMapping("/intro")
public class IntroController {
	
	// サービス
	private final IntroService introService;

	@Autowired
	public IntroController(IntroService introService) {
		// TODO コンストラクタ
		this.introService = introService;
	}
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("title", "川津宏司");
		model.addAttribute("cont", "～life is smile always～");
		
		IntroJSONModel jsonModel = this.introService.readerIntroData_byJSON(Paths.get("src/main/resources/static/json/intro.json"));
		
		// 紹介文
		model.addAttribute("introTitle", "紹介文");
		model.addAttribute("intro", jsonModel.getIntro());
		
		// 経験
		model.addAttribute("experTitle", "経験");
		model.addAttribute("experList", jsonModel.getExperienceList());
		
		// 今後やりたいこと
		model.addAttribute("afterTitle", "今後やりたいこと");
		model.addAttribute("after", jsonModel.getAfter());
		
		// スキル言語
		model.addAttribute("skill1Title", "スキル(言語)");
		model.addAttribute("skill1List", jsonModel.getSkill1List());
		
		// スキルライブラリ
		model.addAttribute("skill2Title", "スキル(ライブラリ)");
		model.addAttribute("skill2List", jsonModel.getSkill2List());
		
		
		// スキルフレームワーク
		model.addAttribute("skill3Title", "スキル(フレームワーク)");
		model.addAttribute("skill3List", jsonModel.getSkill3List());
		
		// スキルその他
		model.addAttribute("skill4Title", "スキル(その他)");
		model.addAttribute("skill4List", jsonModel.getSkill4List());
		
		// URL
		model.addAttribute("urlTitle", "URL");
		model.addAttribute("url", jsonModel.getUrl());
		
		// 趣味
		model.addAttribute("hobbyTitle", "趣味");
		model.addAttribute("hobbyList", jsonModel.getHobbyList());
		
		// 最後に一言
		model.addAttribute("wordTitle", "最後に一言");
		model.addAttribute("word", jsonModel.getWord());
		
		return "intro/index";
	}
	
}
