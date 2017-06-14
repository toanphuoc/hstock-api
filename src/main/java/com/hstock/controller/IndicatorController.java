package com.hstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hstock.service.indicator.EMAService;
import com.hstock.service.indicator.MACDService;
import com.hstock.service.indicator.RSIService;
import com.hstock.service.indicator.SMAService;
import com.hstock.service.indicator.StochRSIService;

@Controller
@RequestMapping(value = "/indicator")
public class IndicatorController {

	@Autowired
	private SMAService smaService;
	
	@Autowired
	private EMAService emaService;
	
	@Autowired
	private RSIService rsiService;
	
	@Autowired
	private MACDService macdService;
	
	@Autowired
	private StochRSIService stochRSIService;
	
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
	
	@RequestMapping(value = "/macd", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object MACD(@RequestParam(value = "ticket") String ticket, 
					@RequestParam(value = "date", required = false) String date, 
					@RequestParam(value = "periodX", defaultValue="12") int periodX, 
					@RequestParam(value = "periodY", defaultValue="26") int periodY, 
					@RequestParam(value = "type", required = false, defaultValue= "daily") String type,
					@RequestParam(value = "periodSignal", defaultValue = "9") int periodSignal){
		return macdService.MACD(ticket, date, periodX, periodY, periodSignal, type);
	}
	
	@RequestMapping(value = "/stochRSI", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Object StochRSI(@RequestParam(value = "ticket") String ticket, 
				@RequestParam(value = "date", required = false) String date, 
				@RequestParam(value = "type", required = false, defaultValue= "daily") String type, 
				@RequestParam(value = "period_rsi", defaultValue="14") int periodRsi, 
				@RequestParam(value = "period_stoch_rsi", defaultValue="14") int periodStochRsi, 
				@RequestParam(value = "period_sk", required = false, defaultValue="0") int periodSk, 
				@RequestParam(value = "period_sd", required = false, defaultValue="0") int periodSd){
		return stochRSIService.StochRSI(ticket, date, periodRsi, periodStochRsi, periodSk, periodSd, type);
	}
}
