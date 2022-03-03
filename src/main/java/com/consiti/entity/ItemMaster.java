/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consiti.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 *
 * @author aragon
 */
@Data
@Entity
@Table(name = "ITEM_MASTER", catalog = "ConsitiPOS", schema = "")
public class ItemMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "DIFF_1", length = 10)
    private String diff1;

    
    @Column(name = "ITEM_AGGREGATE_IND", nullable = false, length = 1)
    private String itemAggregateInd;

    
    @Column(name = "TRAN_LEVEL", nullable = false)
    private Integer tranLevel;

    
    @Column(name = "ITEM_LEVEL", nullable = false)
    private Integer itemLevel;

    
    @Column(name = "PACK_IND", nullable = false, length = 1)
    private String packInd;    

    @Column(name = "ITEM_GRANDPARENT", length = 25)
    private String itemGrandparent;  

    @Column(name = "ITEM_PARENT", length = 25)
    private String itemParent;

    @Column(name = "PREFIX")
    private Integer prefix;    

    @Column(name = "FORMAT_ID", length = 1)
    private String formatId;
    
    
    @NotNull    
    @Column(name = "ITEM_INT_TYPE", nullable = false, length = 6)
    private String itemIntType;

    @Id  
    @Column(name = "ITEM", nullable = false, length = 25)
    private String item;  

    @Column(name = "CATCH_WEIGHT_UOM", length = 4)
    private String catchWeightUom;

    
    @Column(name = "NOTIONAL_PACK_IND", nullable = false, length = 1)
    private String notionalPackInd;

    
    @Column(name = "SOH_INQUIRY_AT_PACK_IND", nullable = false, length = 1)
    private String sohInquiryAtPackInd;

    
    @Column(name = "PERISHABLE_IND", nullable = false, length = 19)
    private String perishableInd;    

    @Column(name = "CATCH_WEIGHT_TYPE", length = 1)
    private String catchWeightType;    

    @Column(name = "BANDED_ITEM_IND", length = 1)
    private String bandedItemInd;    

    @Column(name = "AIP_CASE_TYPE", length = 6)
    private String aipCaseType;    

    @Column(name = "DEPOSIT_IN_PRICE_PER_UOM", length = 6)
    private String depositInPricePerUom;    

    @Column(name = "CONTAINER_ITEM", length = 25)
    private String containerItem;    

    @Column(name = "DEPOSIT_ITEM_TYPE", length = 6)
    private String depositItemType;    

    @Column(name = "SALE_TYPE", length = 6)
    private String saleType;    

    @Column(name = "ORDER_TYPE", length = 6)
    private String orderType;

    
    @Column(name = "INVENTORY_IND", nullable = false, length = 1)
    private String inventoryInd;

    
    @Column(name = "ITEM_XFORM_IND", nullable = false, length = 1)
    private String itemXformInd;    

    @Column(name = "CHECK_UDA_IND", length = 1)
    private String checkUdaInd;

    
    @Column(name = "LAST_UPDATE_DATETIME", nullable = false)
    private LocalDate lastUpdateDatetime;

    
    @Column(name = "LAST_UPDATE_ID", nullable = false, length = 30)
    private String lastUpdateId;

    
    @Column(name = "CREATE_DATETIME", nullable = false)
    private LocalDate createDatetime;

   
    
    @Column(name = "SHIP_ALONE_IND", nullable = false, length = 1)
    private String shipAloneInd;

    
    @Column(name = "GIFT_WRAP_IND", nullable = false, length = 1)
    private String giftWrapInd;    

    @Column(name = "ITEM_SERVICE_LEVEL", length = 6)
    private String itemServiceLevel;    

    @Column(name = "COMMENTS", length = 2000)
    private String comments;    

    @Column(name = "ORDER_AS_TYPE", length = 1)
    private String orderAsType;    

    @Column(name = "PACK_TYPE", length = 1)
    private String packType;

    
    @Column(name = "ORDERABLE_IND", nullable = false, length = 1)
    private String orderableInd;

    
    @Column(name = "SELLABLE_IND", nullable = false, length = 1)
    private String sellableInd;

    
    @Column(name = "CONTAINS_INNER_IND", nullable = false, length = 1)
    private String containsInnerInd;

    
    @Column(name = "SIMPLE_PACK_IND", nullable = false, length = 1)
    private String simplePackInd;

    
    @Column(name = "CONST_DIMEN_IND", nullable = false, length = 1)
    private String constDimenInd;

    @Column(name = "DEFAULT_WASTE_PCT")
    private Integer defaultWastePct;

    @Column(name = "WASTE_PCT")
    private Integer wastePct;    

    @Column(name = "WASTE_TYPE", length = 6)
    private String wasteType;

    
    @Column(name = "CATCH_WEIGHT_IND", nullable = false, length = 1)
    private String catchWeightInd;    

    @Column(name = "HANDLING_SENSITIVITY", length = 6)
    private String handlingSensitivity;    
    
    @Column(name = "HANDLING_TEMP", length = 6)
    private String handlingTemp;

    @Column(name = "RETAIL_LABEL_VALUE")
    private Integer retailLabelValue;    

    @Column(name = "RETAIL_LABEL_TYPE", length = 6)
    private String retailLabelType;

    @Column(name = "MFG_REC_RETAIL")
    private Integer mfgRecRetail;

    @Column(name = "ORIGINAL_RETAIL")
    private Integer originalRetail;

    
    @Column(name = "FORECAST_IND", nullable = false, length = 1)
    private String forecastInd;

    
    @Column(name = "STORE_ORD_MULT", nullable = false, length = 1)
    private String storeOrdMult;

    
    @Column(name = "MERCHANDISE_IND", nullable = false, length = 1)
    private String merchandiseInd;    

    @Column(name = "PACKAGE_UOM", length = 4)
    private String packageUom;

    @Column(name = "PACKAGE_SIZE")
    private Integer packageSize;

    @Column(name = "UOM_CONV_FACTOR")
    private Integer uomConvFactor;
   
    
    @NotNull    
    @Column(name = "STANDARD_UOM", nullable = false, length = 4)
    private String standardUom;
    
    @Column(name = "COST_ZONE_GROUP_ID")
    private Integer costZoneGroupId;

    @Column(name = "RETAIL_ZONE_GROUP_ID")
    private Integer retailZoneGroupId;

    
    @NotNull    
    @Column(name = "PRIMARY_REF_ITEM_IND", nullable = false, length = 1)
    private String primaryRefItemInd;

    
    @NotNull    
    @Column(name = "DESC_UP", nullable = false, length = 250)
    private String descUp;

    
    @NotNull    
    @Column(name = "SHORT_DESC", nullable = false, length = 120)
    private String shortDesc;    

    @Column(name = "ITEM_DESC_SECONDARY", length = 250)
    private String itemDescSecondary;
   
    
    @NotNull    
    @Column(name = "ITEM_DESC", nullable = false, length = 250)
    private String itemDesc;

    
    @NotNull    
    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    
    @NotNull
    @Column(name = "SUBCLASS", nullable = false)
    private String subclass;

    
    @NotNull
    @Column(name = "CLASS", nullable = false)
    private String class1;

    
    @NotNull
    @Column(name = "DEPT", nullable = false)
    private Integer dept;

    
    @NotNull    
    @Column(name = "DIFF_4_AGGREGATE_IND", nullable = false, length = 1)
    private String diff4AggregateInd;    

    @Column(name = "DIFF_4", length = 10)
    private String diff4;

    
    @NotNull    
    @Column(name = "DIFF_3_AGGREGATE_IND", nullable = false, length = 1)
    private String diff3AggregateInd;    

    @Column(name = "DIFF_3", length = 10)
    private String diff3;

    
    @NotNull    
    @Column(name = "DIFF_2_AGGREGATE_IND", nullable = false, length = 1)
    private String diff2AggregateInd;    

    @Column(name = "DIFF_2", length = 10)
    private String diff2;

    
    @NotNull    
    @Column(name = "DIFF_1_AGGREGATE_IND", nullable = false, length = 1)
    private String diff1AggregateInd;
    
}
