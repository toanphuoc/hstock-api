package com.hstock.model.indicator;

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

import com.hstock.model.Period;
import com.hstock.model.Stock;
import com.hstock.model.Type;

@Entity
@Table(name = "INDICATOR_RSI")
public class IndicatorRSI {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_ID", foreignKey=@ForeignKey(name="RSI_PERIOD_FK"))
	private Period period;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable=false, foreignKey=@ForeignKey(name="RSI_STOCK_FK"))
	private Stock stock;

	@Column(name="AVG_GAIN")
	private double avgGain;
	
	@Column(name="AVG_LOSS")
	private double avgLoss;
	
	@Column(name="RS")
	private double rs;
	
	@Column(name="RSI")
	private double rsi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getAvgGain() {
		return avgGain;
	}

	public void setAvgGain(double avgGain) {
		this.avgGain = avgGain;
	}

	public double getAvgLoss() {
		return avgLoss;
	}

	public void setAvgLoss(double avgLoss) {
		this.avgLoss = avgLoss;
	}

	public double getRs() {
		return rs;
	}

	public void setRs(double rs) {
		this.rs = rs;
	}

	public double getRsi() {
		return rsi;
	}

	public void setRsi(double rsi) {
		this.rsi = rsi;
	}
}
