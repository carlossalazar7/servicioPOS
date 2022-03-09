package com.consiti.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "PARAMETROS")
public class Parametros implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PARAMETRO_ID", nullable = false, length = 25)
    private String parametro_id;

    @NotBlank
    @Column(name = "NOMBRE_PARAMETRO",nullable = false, length = 250)
    private String nombre_parametro;

    @NotBlank
    @Column(name = "VALOR", nullable = false, length = 50)
    private String valor;

    @Column(name = "FECHA_CREACION")
    private LocalDate fecha_creacion;

    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDate fecha_actualizacion;

    @Column(name = "ACTIVO")
    private boolean activo;

}
