package com.consiti.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Reporte implements Reportes{

	@Override
	public String getEntity() {
		// TODO Auto-generated method stub
		return "0";
	}

	@Override
	public Date getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getKpi() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getProgress() {
		// TODO Auto-generated method stub
		return 0.0;
	}

}
