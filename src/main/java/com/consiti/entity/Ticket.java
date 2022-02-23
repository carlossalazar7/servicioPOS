package com.consiti.entity;

import java.util.Date;

public class Ticket  {

	/**
	 * 
	 */
	
	private String entity;
	private Date key;
	private Integer kpi;
	private Double progress;
	public Ticket(String entity, Date key, Integer kpi, Double progress) {
		super();
		this.entity = entity;
		this.key = key;
		this.kpi = kpi;
		this.progress = progress;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public Date getKey() {
		return key;
	}
	public void setKey(Date key) {
		this.key = key;
	}
	public Integer getKpi() {
		return kpi;
	}
	public void setKpi(Integer kpi) {
		this.kpi = kpi;
	}
	public Double getProgress() {
		return progress;
	}
	public void setProgress(Double progress) {
		this.progress = progress;
	}
	
	
	
}
