/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

import java.sql.Timestamp;

/**
 *
 * @author Thalesgsn
 */
public class Order {
    private String codToken;
    
    private Timestamp datOrder;
    
    private Integer codItem;

    private char idtStatus;

    private double vlrPrice;

    private Integer qtdItem;

    public static final char[] IDT_STATUS_POSSIBLE_VALUES = {'T', 'D', 'R'};
    
    public Order() { }

    public Order(String codIDBill, Timestamp datOrder, Integer codItem, char idtStatus, double vlrPrice, Integer qtdItem) {
        this.codToken = codIDBill;
        this.datOrder = datOrder;
        this.codItem = codItem;
        this.idtStatus = idtStatus;
        this.vlrPrice = vlrPrice;
        this.qtdItem = qtdItem;
    }

    /**
     * Get the value of codToken
     *
     * @return the value of codToken
     */
    public String getCodToken() {
        return codToken;
    }

    /**
     * Set the value of codToken
     *
     * @param codToken new value of codToken
     */
    public void setCodToken(String codToken) {
        this.codToken = codToken;
    }
    
    /**
     * Get the value of datOrder
     *
     * @return the value of datOrder
     */
    public Timestamp getDatOrder() {
        return datOrder;
    }

    /**
     * Set the value of datOrder
     *
     * @param datOrder new value of datOrder
     */
    public void setDatOrder(Timestamp datOrder) {
        this.datOrder = datOrder;
    }
    
    /**
     * Get the value of codItem
     *
     * @return the value of codItem
     */
    public Integer getCodItem() {
        return codItem;
    }

    /**
     * Set the value of codItem
     *
     * @param codItem new value of codItem
     */
    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    /**
     * Get the value of idtStatus
     *
     * @return the value of idtStatus
     */
    public char getIdtStatus() {
        return idtStatus;
    }

    /**
     * Set the value of idtStatus
     *
     * @param idtStatus new value of idtStatus
     */
    public void setIdtStatus(char idtStatus) {
        this.idtStatus = idtStatus;
    }
    
    /**
     * Get the value of vlrPrice
     *
     * @return the value of vlrPrice
     */
    public double getVlrPrice() {
        return vlrPrice;
    }

    /**
     * Set the value of vlrPrice
     *
     * @param vlrPrice new value of vlrPrice
     */
    public void setVlrPrice(double vlrPrice) {
        this.vlrPrice = vlrPrice;
    }
    
    /**
     * Get the value of qtdItem
     *
     * @return the value of qtdItem
     */
    public Integer getQtdItem() {
        return qtdItem;
    }

    /**
     * Set the value of qtdItem
     *
     * @param qtdItem new value of qtdItem
     */
    public void setQtdItem(Integer qtdItem) {
        this.qtdItem = qtdItem;
    }
}
