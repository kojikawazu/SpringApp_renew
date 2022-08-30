package com.example.demo.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.common.common.AppConsts;
import com.example.demo.common.common.WebConsts;

/**
 * ホームコントローラー
 * @author nanai
 *
 */
@Controller
@RequestMapping(AppConsts.REQUEST_MAPPING_HOME)
public class HomeController {
	
	/** Homeページのタイトル */
	private static final String TITLE_HOME = "Welcome to Practice Home";
	
	/** Homeページの紹介文 */
	private static final String CONT_HOME = "Springフレームワークを使用したWebアプリをテーマに作成したページです。";

	/**
	 * index
	 * @param  model
	 * @return home/index
	 */
	@RequestMapping
	public String index(Model model) {
		
		this.setAttribute(model);
		
		return AppConsts.URL_HOME_INDEX;
	}
	
	/**
	 * Attribute設定
	 * @param model
	 */
	private void setAttribute(Model model) {
		model.addAttribute(
				WebConsts.ATTRIBUTE_TITLE, 
				TITLE_HOME);
		
		model.addAttribute(
				WebConsts.ATTRIBUTE_CONT, 
				CONT_HOME);
	}
}
