package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.app.init.controller.InitController;
import com.example.demo.app.service.user.LoginServiceUse;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * SpringAppMainクラス
 * @author nanai
 *
 */
@SpringBootApplication
public class SpringAppRenewApplication extends SpringBootServletInitializer {

	/**
	 * ログインサービスクラス
	 * {@link LoginServiceUse}
	 */
	@Autowired
	LoginServiceUse loginServiceUse;

	/**
	 * Mainメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringAppRenewApplication.class, args);

		// Bean取得
		SpringAppRenewApplication app = ctx.getBean(SpringAppRenewApplication.class);
		// 起動時の処理
		app.exeStartUp(args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringAppRenewApplication.class);
    }

	/**
	 * 起動時の処理
	 * @param args
	 */
	public void exeStartUp(String[] args) {
		IntroAppLogWriter log = IntroAppLogWriter.getInstance();
		log.start("");

		// 初期化処理
		InitController initController = InitController.getInstance();
		initController.setSecurityUserServiceUse(loginServiceUse);
		initController.Init();

		log.successed("");
	}
}
