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
@Table(name = "INDICATOR_ADX")
public class IndicatorADX {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private Type type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERIOD_ID", foreignKey=@ForeignKey(name="ADX_PERIOD_FK"))
	private Period period;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STOCK_ID", nullable=false, foreignKey=@ForeignKey(name="ADX_STOCK_FK"))
	private Stock stock;
	
	@Column(name = "PDI")
	private double plusDI = -1.00;
	
	@Column(name = "MDI")
	private double minusDI = -1.00;
	
	@Column(name = "DX")
	private double dx = -1.00;
	
	@Column(name = "ADX")
	private double adx = -1.00;

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

	public double getPlusDI() {
		return plusDI;
	}

	public void setPlusDI(double plusDI) {
		this.plusDI = plusDI;
	}

	public double getMinusDI() {
		return minusDI;
	}

	public void setMinusDI(double minusDI) {
		this.minusDI = minusDI;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getAdx() {
		return adx;
	}

	public void setAdx(double adx) {
		this.adx = adx;
	}
	
	
}
