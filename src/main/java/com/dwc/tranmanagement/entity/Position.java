package com.dwc.tranmanagement.entity;

import java.io.Serializable;

/**
 * (Position)实体类
 *
 * @author makejava
 * @since 2020-04-29 09:27:41
 */
public class Position implements Serializable {
    private static final long serialVersionUID = 990527347544828141L;
    
    private Integer tradeid;
    /**
    * REL股票持有数
    */
    private Integer rel;
    /**
    * ITC股票持有数
    */
    private Integer itc;
    /**
    * INF股票持有数
    */
    private Integer inf;
    /**
    * 交易员姓名
    */
    private String name;


    private String username;

    private String password;


    public Integer getTradeid() {
        return tradeid;
    }

    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    public Integer getRel() {
        return rel;
    }

    public void setRel(Integer rel) {
        this.rel = rel;
    }

    public Integer getItc() {
        return itc;
    }

    public void setItc(Integer itc) {
        this.itc = itc;
    }

    public Integer getInf() {
        return inf;
    }

    public void setInf(Integer inf) {
        this.inf = inf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}