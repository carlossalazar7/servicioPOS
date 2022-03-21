/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consiti.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 *
 * @author aragon
 */
@Data
@Entity
@Table(name = "TRAN_HEAD", catalog = "ConsitiPOS", schema = "")
public class TranHead implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TRAN_SEQ_NO", nullable = false)
    private int tranSeqNo;
    @NotBlank
    @Column(name = "STATUS", length = 6)
    private String status;
    @Column(name = "STORE", nullable = false)
    private int store;
    @Basic(optional = false)
    @Column(name = "DAY", nullable = false)
    private int day;
    
}
