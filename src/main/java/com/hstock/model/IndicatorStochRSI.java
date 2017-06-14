package com.hstock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INDICATOR_STOCH_RSI")
public class IndicatorStochRSI {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Column(name = "rsi")
	private double rsi;
	
	@Column(name = "stoch_rsi")
	private double stochRsi;
	
	@Column(name = "sk")
	private double sk;
	
	@Column(name = "sd")
	private double sd;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_RSI_ID", foreignKey=@ForeignKey(name="STOCH_RSI_PERIOD_RSI_FK"))
	private Period periodRsi;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_STOCH_RSI_ID", foreignKey=@ForeignKey(name="STOCH_RSI_PERIOD_STOCH_RSI_FK"))
	private Period periodStochRsi;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_SK", foreignKey=@ForeignKey(name="STOCH_RSI_PERIOD_SK_FK"))
	private Period periodSK;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_SD", foreignKey=@ForeignKey(name="STOCH_RSI_PERIOD_SD_FK"))
	private Period periodSD;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable=false, foreignKey=@ForeignKey(name="STOCH_RSI_STOCK_FK"))
	private Stock stock;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRsi() {
		return rsi;
	}

	public void setRsi(double rsi) {
		this.rsi = rsi;
	}

	public double getStochRsi() {
		return stochRsi;
	}

	public void setStochRsi(double stochRsi) {
		this.stochRsi = stochRsi;
	}

	public Period getPeriodRsi() {
		return periodRsi;
	}

	public void setPeriodRsi(Period periodRsi) {
		this.periodRsi = periodRsi;
	}

	public Period getPeriodStochRsi() {
		return periodStochRsi;
	}

	public void setPeriodStochRsi(Period periodStochRsi) {
		this.periodStochRsi = periodStochRsi;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public double getSk() {
		return sk;
	}

	public void setSk(double sk) {
		this.sk = sk;
	}

	public double getSd() {
		return sd;
	}

	public void setSd(double sd) {
		this.sd = sd;
	}

	public Period getPeriodSK() {
		return periodSK;
	}

	public void setPeriodSK(Period periodSK) {
		this.periodSK = periodSK;
	}

	public Period getPeriodSD() {
		return periodSD;
	}

	public void setPeriodSD(Period periodSD) {
		this.periodSD = periodSD;
	}
	
	
}
