package com.example.demo.app.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.common.exception.DataNotFoundException;

@ControllerAdvice
public class WebMvcControllerAdvice {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
   @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleException(EmptyResultDataAccessException e, Model model) {
    	model.addAttribute("message", e);
    	return "error/CustomPage";
    }
    
	@ExceptionHandler(DataNotFoundException.class)
	public String handleException(DataNotFoundException e, Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}
}
