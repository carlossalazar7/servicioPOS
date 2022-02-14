package com.consiti.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EMP_ID", nullable = false, length = 10)
    private  String id;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String accountName;

    @Column(name = "USER_ID", nullable = false, length = 30)
    private String userId;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;

    @Column(name = "SALESPERSON_IND", nullable = false, length = 1)
    private String salesPerson;

    @Column(name = "CASHIER_IND", nullable = false, length = 1)
    private String cashierInfo;

    @Column(name = "EMP_TYPE", nullable = false, length = 20)
    private String empType;

}