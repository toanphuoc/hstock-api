package com.hstock.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

@Entity
@Table(name = "STOCK")
public class Stock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@Column(name = "LOW_PRICE")
	private double lowPrice;
	
	@Column(name = "CLOSE_PRICE")
	private double closePrice;
	
	@Column(name = "VOLUMNE")
	private double volumne;
	
	
}
