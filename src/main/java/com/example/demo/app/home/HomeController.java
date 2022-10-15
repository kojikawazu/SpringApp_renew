package com.example.demo.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.header.HeaderController;
import com.example.demo.app.header.form.HeaderForm;
import com.example.demo.app.service.user.UserServiceUse;
import com.example.demo.app.session.user.SessionLoginUser;
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
	
	/** コントローラー */
	/** --------------------------------------------------------------- */
	
	/** 
	 * ヘッダーコントローラー 
	 * {@link HeaderController} 
	 */
	private final HeaderController	headerController;
	
	/** --------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param userServiceUse	{@link UserServiceUse}
	 * @param sessionLoginUser	{@link SessionLoginUser}
	 */
	public HomeController(
			UserServiceUse 		userServiceUse,
			SessionLoginUser	sessionLoginUser) {
		this.headerController	= new HeaderController(userServiceUse, 
														sessionLoginUser);
	}

	/**
	 * index
	 * @param  headerForm	{@link HeaderForm}
	 * @param  model		{@link Model}
	 * @return {@value AppConsts#URL_HOME_INDEX}
	 */
	@RequestMapping
	public String index(
			HeaderForm headerForm,
			Model model) {
		
		/** attributeの設定 */
		this.setAttribute(model);
		
		/** ヘッダーの設定 */
		this.headerController.setHeader(model);
		
		return AppConsts.URL_HOME_INDEX;
	}
	
	/**
	 * Attribute設定
	 * @param model {@link Model}
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
