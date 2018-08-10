package com.springboot.web.springbootfirstwebapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
    public boolean validuser(String name, String password) {
    	return name.equalsIgnoreCase("utkarsh") && password.equalsIgnoreCase("pandey");
    }
}
