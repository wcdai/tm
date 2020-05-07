package com.dwc.tranmanagement.entity;

import java.io.Serializable;

/**
 * (Transrecord)实体类
 *
 * @author makejava
 * @since 2020-04-29 09:27:14
 */
public class Transrecord implements Serializable {
    private static final long serialVersionUID = -84794465484055195L;
    
    private Integer transactionid;
    /**
    * 交易员ID
    */
    private Integer tradeid;
    /**
    * 版本号，关联到每个交易员，从1开始计数
    */
    private Integer version;
    /**
    * 证券代码，例如：INF代表 Infosys
    */
    private String securitycode;
    /**
    * 数量，股票数量，例如：10手Infosys股票
    */
    private Integer quantity;
    /**
    * 操作与交易（Trade）关联， (会产生相关的trade id，但是不同版本号的记录)
    */
    private String operation;
    /**
    * 买或卖
    */
    private String deal;


    public Integer getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public Integer getTradeid() {
        return tradeid;
    }

    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

}