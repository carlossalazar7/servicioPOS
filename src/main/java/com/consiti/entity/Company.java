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
@Table(name = "COMPANY")
public class Company implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID", nullable = false, length = 10)
    private Integer company_id;

    @NotBlank
    @Column(name = "COMPANY_NAME", nullable = false, length = 150)
    private String company_name;

    @NotBlank
    @Column(name = "COMPANY_ADDRESS", nullable = false, length = 250)
    private String company_addres;

    @Column(name = "COMPANY_LEGAL_NUM", nullable = true, length = 20)
    private String company_legal_num;

    @Column(name = "COMPANY_TAX_NUM", nullable = true, length = 20)
    private String company_tax_num;

    @Column(name = "COMPANY_LOGO_URL", nullable = true, length = 250)
    private String company_logo_url;

    @Column(name = "COMPANY_DEFAULT_TEMPLATE", nullable = true, length = 250)
    private String company_default_template;

}
