package com.hstock.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hstock.model.Stock;
import com.hstock.service.stock.StockService;

@Controller
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/ema", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object EMA(@RequestParam("ticket") String ticket, 
					@RequestParam(value = "date", required = false) String date,
					@RequestParam("period") int period,
					@RequestParam(value = "type", required = false, defaultValue= "daily") String type){
		return stockService.EMA(ticket, date, period, type);
	}
	
	@RequestMapping(value = "/sma", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object SMA(@RequestParam(value = "ticket") String ticket, 
					@RequestParam(value = "date", required = false) String date,
					@RequestParam(value = "period") int period, 
					@RequestParam(value = "type", required = false, defaultValue= "daily") String type){
		return stockService.SMA(ticket, date, period, type);
	}
	
	@RequestMapping(value = "/rsi", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object RSI(@RequestParam(value = "ticket") String ticket, @RequestParam(value = "period") int period){
		return stockService.RSI(ticket, period);
	}
}
