package com.hstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hstock.service.security.SecuityService;
import com.hstock.service.stock.StockService;

@Controller
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private SecuityService securityService;
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value= "/favoriteStock" , method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object getFavoriteStock(@RequestParam(value = "access_token") String accessToken){
		if(!securityService.checkAccessToken(accessToken)){
			return false;
		}
		return stockService.getFavoriteStock(accessToken);
	}
}
