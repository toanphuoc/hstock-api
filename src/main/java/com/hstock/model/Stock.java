package com.hstock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "TICKET")
	private String ticket;
	
	@Column(name = "OPEN_DATE")
	private String openDate;
	
	@Column(name = "OPEN_PRICE")
	private double openPrice;
	
	@Column(name = "HIGH_PRICE")
	private double highPrice;
	
	@Column(name = "LOW_PRICE")
	private double lowPrice;
	
	@Column(name = "CLOSE_PRICE")
	private double closePrice;
	
	@Column(name = "VOLUMNE")
	private double volumne;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getVolumne() {
		return volumne;
	}

	public void setVolumne(double volumne) {
		this.volumne = volumne;
	}
	
	
}
