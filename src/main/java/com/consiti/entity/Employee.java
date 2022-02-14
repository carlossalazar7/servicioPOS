package com.consiti.entity;

//import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//@Data
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


    public Employee() {
    }


    public Employee(String accountName, String userId, String phone, String name, String salesPerson, String cashierInfo, String empType) {
        this.accountName = accountName;
        this.userId = userId;
        this.phone = phone;
        this.name = name;
        this.salesPerson = salesPerson;
        this.cashierInfo = cashierInfo;
        this.empType = empType;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalesPerson() {
        return this.salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getCashierInfo() {
        return this.cashierInfo;
    }

    public void setCashierInfo(String cashierInfo) {
        this.cashierInfo = cashierInfo;
    }

    public String getEmpType() {
        return this.empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", userId='" + getUserId() + "'" +
            ", phone='" + getPhone() + "'" +
            ", name='" + getName() + "'" +
            ", salesPerson='" + getSalesPerson() + "'" +
            ", cashierInfo='" + getCashierInfo() + "'" +
            ", empType='" + getEmpType() + "'" +
            "}";
    }

}
