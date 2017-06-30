package com.hstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hstock.service.security.SecuityService;

@Controller
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private SecuityService securityService;
	
	public Object getFavoriteStock(String accessToken){
		if(!securityService.checkAccessToken(accessToken)){
			return false;
		}
		return null;
	}
}
