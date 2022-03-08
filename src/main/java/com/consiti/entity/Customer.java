package com.consiti.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @Id
    @Column(name = "CUST_ID", nullable = false, length = 50)
    private  String cust_id;

    @Column(name = "STORE", nullable = false)
    private Integer store;

    @Column(name = "DAY", nullable = false)
    private Integer day;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @Email
    @Column(name = "E_MAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "WORK_PHONE",  length = 50)
    private  String work_phone;

    @Column(name = "HOME_PHONE", length = 20)
    private  String home_phone;

    @Column(name = "COUNTRY", length = 3)
    private  String country;

    @Column(name = "TRAN_SEQ_NO")
    private  Integer tran_seq_no;

    @Column(name = "CUST_ID_TYPE", length = 50)
    private  String cust_id_type;

    @Column(name = "NAME")
    private  String name;

    @Column(name = "ADDR1")
    private  String addr1;

    @Column(name = "ADDR2", length = 240)
    private  String addr2;

    @Column(name = "CITY", length = 120)
    private  String city;

    @Column(name = "STATE", length = 3)
    private  String state;

    @Column(name = "POSTAL_CODE", length = 30)
    private  String postal_code;

}
