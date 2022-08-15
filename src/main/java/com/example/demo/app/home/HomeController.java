package com.example.demo.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping
	public String index(Model model) {
		
		model.addAttribute("title", "Welcome to Practice Home");
		model.addAttribute("cont", "Springフレームワークを使用したWebアプリをテーマに作成したページです。");
		
		return "home/index";
	}
}
