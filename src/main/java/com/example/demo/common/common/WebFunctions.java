package com.example.demo.common.common;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.app.service.SuperService;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.log.LogMessage;

/**
 * Web共通メソッド群
 * @author nanai
 *
 */
public class WebFunctions {
	
	/** ------------------------------------------------------------------------ */
	
	/**
	 * 時間、日付のdiffチェック(分単位)
	 * @param targetTime     チェック日付対象{@link LocalDateTime}
	 * @param minutes        分単位
	 * @return  true 日付過ぎてない false 日付過ぎてる
	 */
	public static final boolean checkDiffMinutes(
			LocalDateTime 	targetTime, 
			int 			minutes) {
		LogMessage		log			= new LogMessage();
		LocalDateTime 	now       	= LocalDateTime.now();
		
		Long diffYears    =  ChronoUnit.YEARS.between(targetTime, now);
		Long diffMonth    =  ChronoUnit.MONTHS.between(targetTime, now);
		Long diffDay      =  ChronoUnit.DAYS.between(targetTime, now);
		Long diffHour     =  ChronoUnit.HOURS.between(targetTime, now);
		Long diffMinute   =  ChronoUnit.MINUTES.between(targetTime, now);
		
		log.info(
				"OK [" + diffYears + "-" + diffMonth + "-" + diffDay + " " 
				+ diffHour + ":" + diffMinute + "]");
		
		// 日付、時間が過ぎてたらアウト
		if (diffYears > 0 || diffMonth > 0 || diffDay > 0 || diffHour > 0) {
			return WebConsts.TIME_RUNNING_OUT;
		}
		
		// [minutes]分過ぎてたらアウト
		if (diffMinute >= minutes) {
			return WebConsts.TIME_RUNNING_OUT;
		}
		
		// 日付、時間共に過ぎてないのでOK
		return WebConsts.TIME_WITHIN;
	}
	
	/** ------------------------------------------------------------------------ */
	
	/**
	 * 時間、日付のdiffチェック(時間単位)
	 * @param targetTime     チェック日付対象{@link LocalDateTime}
	 * @param minutes        分単位
	 * @return  true 日付過ぎてる false 日付過ぎてない
	 */
	public static final boolean checkDiffHour(
			LocalDateTime	targetTime, 
			int 			hours) {
		LogMessage		log			= new LogMessage();
		LocalDateTime 	now			= LocalDateTime.now();
		
		Long diffYears    =  ChronoUnit.YEARS.between(targetTime, now);
		Long diffMonth    =  ChronoUnit.MONTHS.between(targetTime, now);
		Long diffDay      =  ChronoUnit.DAYS.between(targetTime, now);
		Long diffHour     =  ChronoUnit.HOURS.between(targetTime, now);
		
		log.info(
				"OK [" + diffYears + "-" + diffMonth + "-" + diffDay + " " 
				+ diffHour + "]");
		
		// 日付、時間が過ぎてたらアウト
		if (diffYears > 0 || diffMonth > 0 || diffDay > 0) {
			return WebConsts.TIME_RUNNING_OUT;
		}
		
		// [hour]時間過ぎてたらアウト
		if (diffHour >= hours) {
			return WebConsts.TIME_RUNNING_OUT;
		}
		
		// 日付、時間共に過ぎてないのでOK
		return WebConsts.TIME_WITHIN;
	}
	
	/** ------------------------------------------------------------------------ */
	
	/**
	 * Cookieの保存
	 * @param response	{@link HttpServletResponse}
	 * @param key		キー
	 * @param value		値
	 * @param time		タイムアウト時間
	 * @return true 成功 false 失敗
	 */
	public static final boolean saveCookie(
			HttpServletResponse 	response,
			String 					key,
			String					value,
			int 					time) {
		if (response == null || 
				key == null || key.equals("") ||
				value == null || value.equals("") ||
				time == 0) {
			return false;
		}
		
		Cookie cookieData = new Cookie(key, value);
		cookieData.setMaxAge(time);
		cookieData.setPath("/");
		response.addCookie(cookieData);
		
		return true;
	}
	
	/** ------------------------------------------------------------------------ */
	
	/**
	 * Cookieの初期化
	 * @param request				{@link HttpServletRequest}
	 * @param response				{@link HttpServletResponse}
	 * @param keyList		キーリスト	{@link List}({@link String})
	 * @return true 成功 false 失敗
	 */
	public static final boolean deleteCookie(
			HttpServletRequest		request,
			HttpServletResponse 	response,
			List<String>			keyList) {
		Cookie[] cookies =  request.getCookies();
		
		for (Cookie cookie : cookies) {	
			for (String key : keyList) {
				if (key.equals(cookie.getName())) {
					// 一致したので削除
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
			
		}
		
		return true;
	}
	
	/** ------------------------------------------------------------------------ */
}
