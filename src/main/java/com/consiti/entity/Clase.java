package com.consiti.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "CLASE")
public class Clase implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASE_ID")
	private Integer id_clase;
	
	@Column(name = "NOMBRE_CLASE", nullable = false, length = 150)
	private String nombre_clase;
	
	@Column(name = "CLASE_PADRE", nullable = false, length = 150)
	private String clase_padre;
	
	@Column(name = "ESTADO", nullable = false, length = 10)
	private String estado;
	
	@Column(name = "FECHA_CREACION",nullable = false)
	private LocalDateTime fecha_creacion;
	
	@Column(name = "FECHA_ACTUALIZACION",nullable = false)
	private LocalDateTime fecha_actualizacion;
}
