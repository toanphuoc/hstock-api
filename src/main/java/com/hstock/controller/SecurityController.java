package com.hstock.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hstock.service.security.SecuityService;

@Controller
@RequestMapping(value = "/security")
public class SecurityController {

	@Autowired
	private SecuityService securityService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest requestContext, 
			@RequestParam(value = "username") String userName, 
			@RequestParam(value = "password")String password){
		
		String ip = requestContext.getRemoteAddr().toString();
		return securityService.login(ip, userName, password);
	}
}
