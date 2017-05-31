package com.hstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hstock.service.indicator.EMAService;
import com.hstock.service.indicator.RSIService;
import com.hstock.service.indicator.SMAService;

@Controller
@RequestMapping(value = "/indicator")
public class IndicatorController {

	@Autowired
	private SMAService smaService;
	
	@Autowired
	private EMAService emaService;
	
	@Autowired
	private RSIService rsiService;
	
	@RequestMapping(value = "/sma", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object SMA(@RequestParam(value = "ticket") String ticket, 
					@RequestParam(value = "date", required = false) String date,
					@RequestParam(value = "period") int period, 
					@RequestParam(value = "type", required = false, defaultValue= "daily") String type){
		return smaService.SMA(ticket, date, period, type);
	}
	
	@RequestMapping(value = "/ema", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object EMA(@RequestParam(value = "ticket") String ticket, 
					@RequestParam(value = "date", required = false) String date,
					@RequestParam(value = "period") int period, 
					@RequestParam(value = "type", required = false, defaultValue= "daily") String type){
		return emaService.EMA(ticket, date, period, type);
	}
	
	@RequestMapping(value = "/rsi", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object RSI(@RequestParam(value = "ticket") String ticket, 
					@RequestParam(value = "date", required = false) String date,
					@RequestParam(value = "period") int period, 
					@RequestParam(value = "type", required = false, defaultValue= "daily") String type){
		return rsiService.RSI(ticket, date, period, type);
	}
}