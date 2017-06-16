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
@Table(name = "INDICATOR_MACD")
public class IndicatorMACD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable=false, foreignKey=@ForeignKey(name="MACD_STOCK_FK"))
	private Stock stock;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_X_ID", foreignKey=@ForeignKey(name="MACD_PERIOD_X_FK"))
	private Period periodX;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_Y_ID", foreignKey=@ForeignKey(name="MACD_PERIOD_Y_FK"))
	private Period periodY;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_SIGNAL_ID", foreignKey=@ForeignKey(name="MACD_PERIOD_SIGNAL_FK"))
	private Period periodSignal;
	
	@Column(name = "SIGNAL_VALUE")
	private double signal;
	
	@Column(name = "MACD_HISTOGRAM")
	private double MACDHistogram;
	
	@Column(name = "MACD")
	private double MACD;

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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Period getPeriodX() {
		return periodX;
	}

	public void setPeriodX(Period periodX) {
		this.periodX = periodX;
	}

	public Period getPeriodY() {
		return periodY;
	}

	public void setPeriodY(Period periodY) {
		this.periodY = periodY;
	}

	public double getMACD() {
		return MACD;
	}

	public void setMACD(double mACD) {
		MACD = mACD;
	}

	public Period getPeriodSignal() {
		return periodSignal;
	}

	public void setPeriodSignal(Period periodSignal) {
		this.periodSignal = periodSignal;
	}

	public double getSignal() {
		return signal;
	}

	public void setSignal(double signal) {
		this.signal = signal;
	}

	public double getMACDHistogram() {
		return MACDHistogram;
	}

	public void setMACDHistogram(double mACDHistogram) {
		MACDHistogram = mACDHistogram;
	}
}
