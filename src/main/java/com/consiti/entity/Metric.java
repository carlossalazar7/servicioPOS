package com.consiti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "METRIC")
public class Metric implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "METRIC_ID", nullable = false, length = 11)
    private Integer metric_id;

    @NotBlank
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(name = "TYPE_CODE", nullable = false, length = 2)
    private String type_code;

    @NotBlank
    @Column(name = "VALUE", nullable = false, length = 100)
    private String value;

    @Column(name = "RELATED_ENTITY", length = 30)
    private String related_entity;

    @Column(name = "ATTR1", nullable = true, length = 30)
    private String attr1;

    @Column(name = "ATTR2", nullable = true, length = 30)
    private String attr2;

    @Column(name = "ATTR3", nullable = true, length = 30)
    private String attr3;

    @Column(name = "LABEL1", nullable = true, length = 30)
    private String label1;
    
    @Column(name = "LABEL2", nullable = true, length = 30)
    private String label2;

    @Column(name = "LABEL3", nullable = true, length = 30)
    private String label3;

    @Column(name = "LABEL4", nullable = true, length = 30)
    private String label4;
}