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
@Table(name = "INDICATOR_SMA")
public class IndicatorSMA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Column(name = "VALUE")
	private double value;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_ID", foreignKey=@ForeignKey(name="SMA_PERIOD_FK"))
	private Period period;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable=false, foreignKey=@ForeignKey(name="SMA_STOCK_FK"))
	private Stock stock;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
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
}
