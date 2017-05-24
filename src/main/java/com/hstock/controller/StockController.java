package com.hstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hstock.model.Stock;
import com.hstock.service.stock.StockService;

@Controller
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/getAllStocks", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<Stock> getAllStocks(){
		return stockService.getAllStocks();
	}
	
	@RequestMapping(value = "/getStockById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Stock getStockById(@PathVariable("id") long id){
		return stockService.getStockById(new Long(id));
	}
}
