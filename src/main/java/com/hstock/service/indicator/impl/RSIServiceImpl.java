package com.hstock.service.indicator.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hstock.dao.indicator.RSIDao;
import com.hstock.dao.period.PeriodDao;
import com.hstock.dao.stock.StockDao;
import com.hstock.model.IndicatorRsi;
import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;
import com.hstock.service.indicator.NumberOfDay;
import com.hstock.service.indicator.RSIService;

@Service("rsiService")
public class RSIServiceImpl implements RSIService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private PeriodDao periodDao;
	
	@Autowired
	private RSIDao rsiDao;
	
	@Override
	@Transactional
	public Object RSI(String ticket, String date, int period, String type) {
		if(type != null && type.toUpperCase().equals(Type.WEEKLY.toString())){
			if(date != null){
				return RSI(ticket, date, period, Type.WEEKLY);
			}
			return RSI(ticket, period,  Type.WEEKLY);
		}
		
		if(date != null){
			return RSI(ticket, date, period, Type.DAILY);
		}
		
		return RSI(ticket, period,  Type.DAILY);
	}
	
	private Object RSI(String ticket, String date, int period, Type type){
		IndicatorRsi indicatorRsi = rsiDao.getIndicatorRsiAtOneDate(ticket, period, date, type);
		
		if(indicatorRsi != null){
			return indicatorRsi;
		}
		
		Period periodObj = periodDao.getPeriodByValue(period);
		
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		List<Stock> stocks = stockDao.getAllStockToDate(ticket, date, numberOfDay);
		
		List<IndicatorRsi> indicatorRsis = new ArrayList<IndicatorRsi>();
		
		IndicatorRsi firstItem = new IndicatorRsi();
		
		double sumGain = 0;
        double sumLoss = 0;
        double firstValue = 0.00;
        double avgGain = 0.00;
        double avgLoss = 0.00;
        double rs = 0.00;
        
		for (int i = 1; i < period + 1; i++) {
			double difference = stocks.get(i).getClosePrice() - stocks.get(i-1).getClosePrice();
			if(difference >=0){
        		sumGain += difference;
        	}else{
        		sumLoss -= difference;
        	}	
		}
		
		if(sumGain == 0){
			avgGain = firstValue = rs = 0.00;
        }else if(sumLoss == 0){
        	avgLoss = 0;
        	firstValue = 100;
        }else{
        	avgGain = sumGain/period;
        	avgLoss = sumLoss/period;
        	
        	rs  = avgGain/avgLoss;
        	
        	firstValue = 100 - (100/(1 + rs));
        }
		
		firstItem.setStock(stocks.get(period));
		firstItem.setType(type);
		firstItem.setAvgGain(avgGain);
		firstItem.setAvgLoss(avgLoss);
		firstItem.setRs(rs);
		firstItem.setRsi(firstValue);
		if(periodObj != null){
			firstItem.setPeriod(periodObj);
//			int id = (int) rsiDao.save(firstItem);
//			firstItem.setId(id);
		}	
		
		indicatorRsis.add(firstItem);
		
		int k = 1;
        for (int i = period + 1; i < stocks.size(); i++) {
        	indicatorRsi = new IndicatorRsi();
        	
        	double difference = stocks.get(i).getClosePrice() - stocks.get(i-1).getClosePrice();
        	
        	double currentGain = difference >= 0 ? difference : 0;
        	double gain = ((double)indicatorRsis.get(k - 1).getAvgGain() * (period - 1) + currentGain) / period;
        	indicatorRsi.setAvgGain(gain);
        	
        	double currentLoss = difference < 0 ? Math.abs(difference) : 0;
        	double loss = ((double) indicatorRsis.get(k - 1).getAvgLoss() * (period - 1) + currentLoss) / period;
        	indicatorRsi.setAvgLoss(loss);
        	
        	double rsIndex = avgGain/avgLoss;
        	indicatorRsi.setRs(rsIndex);
        	
        	double valueRSI = 100 - (100/(1 + rs));
        	indicatorRsi.setRsi(valueRSI);
        	
        	indicatorRsi.setStock(stocks.get(i));
        	indicatorRsi.setType(type);
        	
        	if(periodObj != null){
        		indicatorRsi.setPeriod(periodObj);
//        		int id = (int) rsiDao.save(indicatorRsi);
//        		indicatorRsi.setId(id);
        	}
        	
        	indicatorRsis.add(indicatorRsi);
        	
        	k++;
		}
		
		return indicatorRsis.get(indicatorRsis.size() - 1);
	}
	
	public Object RSI(String ticket, int period, Type type){
		
		int numberOfDay = type.name().toUpperCase().equals(Type.WEEKLY.toString()) ? NumberOfDay.FRIDAY : -1;
		
		List<Stock> stocks = stockDao.getAllStock(ticket, numberOfDay);
		
		List<IndicatorRsi> indicatorRsis = rsiDao.getListIndicatorRsiByTicketNameAndPeriod(ticket, period, type);
		
		if(indicatorRsis != null && (indicatorRsis.size() == stocks.size() - period)){
			return indicatorRsis;
		}
		
		if(period > stocks.size()){
			return null;
		}
		
		indicatorRsis = new ArrayList<IndicatorRsi>();
		
		Period periodObj = periodDao.getPeriodByValue(period);
		
		IndicatorRsi firstItem = new IndicatorRsi();
		
		double sumGain = 0;
        double sumLoss = 0;
        double firstValue = 0.00;
        double avgGain = 0.00;
        double avgLoss = 0.00;
        double rs = 0.00;
        
		for (int i = 1; i < period + 1; i++) {
			double difference = stocks.get(i).getClosePrice() - stocks.get(i-1).getClosePrice();
			if(difference >=0){
        		sumGain += difference;
        	}else{
        		sumLoss -= difference;
        	}	
		}
		
		if(sumGain == 0){
			avgGain = firstValue = rs = 0.00;
        }else if(sumLoss == 0){
        	avgLoss = 0;
        	firstValue = 100;
        }else{
        	avgGain = sumGain/period;
        	avgLoss = sumLoss/period;
        	
        	rs  = avgGain/avgLoss;
        	
        	firstValue = 100 - (100/(1 + rs));
        }
		
		firstItem.setStock(stocks.get(period));
		firstItem.setType(type);
		firstItem.setAvgGain(avgGain);
		firstItem.setAvgLoss(avgLoss);
		firstItem.setRs(rs);
		firstItem.setRsi(firstValue);
		if(periodObj != null){
			firstItem.setPeriod(periodObj);
			int id = (int) rsiDao.save(firstItem);
			firstItem.setId(id);
		}	
		
		indicatorRsis.add(firstItem);
		
		int k = 1;
        for (int i = period + 1; i < stocks.size(); i++) {
        	IndicatorRsi indicatorRsi = new IndicatorRsi();
        	
        	double difference = stocks.get(i).getClosePrice() - stocks.get(i-1).getClosePrice();
        	
        	double currentGain = difference >= 0 ? difference : 0;
        	double gain = ((double)indicatorRsis.get(k - 1).getAvgGain() * (period - 1) + currentGain) / period;
        	indicatorRsi.setAvgGain(gain);
        	
        	double currentLoss = difference < 0 ? Math.abs(difference) : 0;
        	double loss = ((double) indicatorRsis.get(k - 1).getAvgLoss() * (period - 1) + currentLoss) / period;
        	indicatorRsi.setAvgLoss(loss);
        	
        	double rsIndex = avgGain/avgLoss;
        	indicatorRsi.setRs(rsIndex);
        	
        	double valueRSI = 100 - (100/(1 + rs));
        	indicatorRsi.setRsi(valueRSI);
        	
        	indicatorRsi.setStock(stocks.get(i));
        	indicatorRsi.setType(type);
        	
        	if(periodObj != null){
        		indicatorRsi.setPeriod(periodObj);
        		int id = (int) rsiDao.save(indicatorRsi);
        		indicatorRsi.setId(id);
        	}
        	
        	indicatorRsis.add(indicatorRsi);
        	
        	k++;
		}
		
		
		return indicatorRsis;
	}

}
