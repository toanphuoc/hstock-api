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
@Table(name = "INDICATOR_BB")
public class IndicatorBB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_ID", foreignKey=@ForeignKey(name="BB_PERIOD_FK"))
	private Period period;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable=false, foreignKey=@ForeignKey(name="BB_STOCK_FK"))
	private Stock stock;
	
	@Column(name = "standardDeviation")
	private int standardDeviation;
	
	@Column(name = "sma")
	private double sma;
	
	@Column(name = "upper_band")
	private double upperBand;
	
	@Column(name = "lower_band")
	private double lowerBand;
	
	@Column(name = "bb")
	private double bb;

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

	public int getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(int standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double getSma() {
		return sma;
	}

	public void setSma(double sma) {
		this.sma = sma;
	}

	public double getUpperBand() {
		return upperBand;
	}

	public void setUpperBand(double upperBand) {
		this.upperBand = upperBand;
	}

	public double getLowerBand() {
		return lowerBand;
	}

	public void setLowerBand(double lowerBand) {
		this.lowerBand = lowerBand;
	}

	public double getBb() {
		return bb;
	}

	public void setBb(double bb) {
		this.bb = bb;
	}
}
