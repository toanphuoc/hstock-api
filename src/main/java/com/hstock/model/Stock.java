package com.hstock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

@Entity
@Table(name = "STOCK")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "TICKET")
	private String ticket;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "OPEN_PRICE")
	private double openPrice;
	
	@Column(name = "HIGH_PRICE")
	private double highPrice;
	
	
	private double lowPrice;
	
	private double closePrice;
	
	private double volumne;
	
	
}
