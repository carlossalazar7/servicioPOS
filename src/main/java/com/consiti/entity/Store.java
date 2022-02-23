package com.consiti.entity;

import java.io.Serializable;
import java.time.LocalDate;
//import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Entity
@Table(name="STORE")
@Data
public class Store implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STORE", unique=true, nullable=false)
	private Integer store;

	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name="ACQUIRED_DATE")
	private LocalDate acquiredDate;

	@Column(name="AUTO_APPROVE_ORDERS_IND", length=1)
	private String autoApproveOrdersInd;

	@Column(name="AUTO_RCV", length=1)
	private String autoRcv;

	@Column(name="CHANNEL_ID")
	private Integer channelId;

	@Column(name="CURRENCY_CODE", length=3)
	private String currencyCode;

	@Column(name="DEFAULT_WH")
	private Integer defaultWh;

	@Column(name="DISTRICT")
	private Integer district;

	@Column(name="DUNS_LOC", length=4)
	private String dunsLoc;

	@Column(name="DUNS_NUMBER", length=9)
	private String dunsNumber;

	@Email(message = "Dirección de correo invalida")
	@Column(name="EMAIL", length=100)
	private String email;

	@Column(name="FAX_NUMBER", length=20)
	private String faxNumber;

	@Column(name="INTEGRATED_POS_IND", length=1)
	private String integratedPosInd;

	@Column(name="LANG")
	private Integer lang;

	@Column(name="LINEAR_DISTANCE")
	private Integer linearDistance;

	@Column(name="MALL_NAME", length=120)
	private String mallName;

	@Column(name="ORG_UNIT_ID")
	private Integer orgUnitId;

	@Column(name="ORIG_CURRENCY_CODE", length=3)
	private String origCurrencyCode;

	@Column(name="PHONE_NUMBER", length=20)
	private String phoneNumber;

	@Column(name="REMERCH_IND", length=1)
	private String remerchInd;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name="REMODEL_DATE")
	private LocalDate remodelDate;

	@Column(name="SELLING_SQUARE_FT")
	private Integer sellingSquareFt;

	@Column(name="SISTER_STORE")
	private Integer sisterStore;

	@Column(name="START_ORDER_DAYS")
	private Integer startOrderDays;

	@Column(name="STOCKHOLDING_IND", length=1)
	private String stockholdingInd;

	@Column(name="STOP_ORDER_DAYS")
	private Integer stopOrderDays;

	@Column(name="STORE_CLASS", length=1)
	private String storeClass;

	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name="STORE_CLOSE_DATE")
	private LocalDate storeCloseDate;

	@Column(name="STORE_FORMAT")
	private Integer storeFormat;

	@Column(name="STORE_MGR_NAME", length=120)
	private String storeMgrName;

	@Column(name="STORE_NAME", length=150)
	private String storeName;

	@Column(name="STORE_NAME_SECONDARY", length=150)
	private String storeNameSecondary;

	@Column(name="STORE_NAME10", length=10)
	private String storeName10;

	@Column(name="STORE_NAME3", length=3)
	private String storeName3;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name="STORE_OPEN_DATE")
	private LocalDate storeOpenDate;

	@Column(name="STORE_TYPE", length=6)
	private String storeType;

	@Column(name="TIMEZONE_NAME", length=64)
	private String timezoneName;

	@Column(name="TOTAL_SQUARE_FT")
	private Integer totalSquareFt;

	@Column(name="TRAN_NO_GENERATED", length=6)
	private String tranNoGenerated;

	@Column(name="TRANSFER_ZONE")
	private Integer transferZone;

	@Column(name="TSF_ENTITY_ID")
	private Integer tsfEntityId;

	@Column(name="VAT_INCLUDE_IND", length=1)
	private String vatIncludeInd;

	@Column(name="VAT_REGION")
	private Integer vatRegion;

	@Column(name="WF_CUSTOMER_ID")
	private Integer wfCustomerId;

}
