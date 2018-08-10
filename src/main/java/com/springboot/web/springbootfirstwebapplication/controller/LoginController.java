package com.springboot.web.springbootfirstwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.web.springbootfirstwebapplication.service.LoginService;

//Model 
@Controller
@SessionAttributes("name")
public class LoginController {
	@Autowired
	LoginService service = new LoginService();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginpage( ModelMap model){
		//System.out.println("nam is "+ name);
		//model.put("name",name);
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String welcomeMessage(@RequestParam String name,@RequestParam String password, ModelMap model){
		
		boolean isValidUser = service.validuser(name, password);
		
		if (!isValidUser) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		
		model.put("name",name);
		return "welcome";
	}

}
