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
	
	@Column(name = "MACD")
	private double MACD;
}
