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
    private  String emp_id;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "USER_ID", nullable = false, length = 30)
    private String user_id;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;

    @Column(name = "SALESPERSON_IND", nullable = false, length = 1)
    private String salesperson_ind;

    @Column(name = "CASHIER_IND", nullable = false, length = 1)
    private String cashier_ind;

    @Column(name = "EMP_TYPE", nullable = false, length = 20)
    private String emp_type;

}