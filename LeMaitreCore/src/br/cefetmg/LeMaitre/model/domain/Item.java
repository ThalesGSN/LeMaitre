/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

/**
 *
 * @author Temp
 */
public class Item {
    private Long codItem;

    private Double vlrPrice;

    private String nomItem;

    private String desItem;

    private char idtAvaliable;

    private Integer codCategory;

    public Item() { }

    public Item(Long codItem, Double vlrPrice, String nomItem, String desItem,
            char idtAvaliable, Integer codCategory) {
        this.codItem = codItem;
        this.vlrPrice = vlrPrice;
        this.nomItem = nomItem;
        this.desItem = desItem;
        this.idtAvaliable = idtAvaliable;
        this.codCategory = codCategory;
    }



    /**
     * Get the value of codItem
     *
     * @return the value of codItem
     */
    public Long getCodItem() {
        return codItem;
    }

    /**
     * Set the value of codItem
     *
     * @param codItem new value of codItem
     */
    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }
    
    /**
     * Get the value of vlrPrice
     *
     * @return the value of vlrPrice
     */
    public Double getVlrPrice() {
        return vlrPrice;
    }

    /**
     * Set the value of vlrPrice
     *
     * @param vlrPrice new value of vlrPrice
     */
    public void setVlrPrice(Double vlrPrice) {
        this.vlrPrice = vlrPrice;
    }
    
    /**
     * Get the value of nomItem
     *
     * @return the value of nomItem
     */
    public String getNomItem() {
        return nomItem;
    }

    /**
     * Set the value of nomItem
     *
     * @param nomItem new value of nomItem
     */
    public void setNomItem(String nomItem) {
        this.nomItem = nomItem;
    }
    
    /**
     * Get the value of desItem
     *
     * @return the value of desItem
     */
    public String getDesItem() {
        return desItem;
    }

    /**
     * Set the value of desItem
     *
     * @param desItem new value of desItem
     */
    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }
    
    /**
     * Get the value of idtAvaliable
     *
     * @return the value of idtAvaliable
     */
    public char getIdtAvaliable() {
        return idtAvaliable;
    }

    /**
     * Set the value of idtAvaliable
     *
     * @param idtAvaliable new value of idtAvaliable
     */
    public void setIdtAvaliable(char idtAvaliable) {
        this.idtAvaliable = idtAvaliable;
    }
    
    /**
     * Get the value of codCategory
     *
     * @return the value of codCategory
     */
    public Integer getCodCategory() {
        return codCategory;
    }

    /**
     * Set the value of codCategory
     *
     * @param codCategory new value of codCategory
     */
    public void setCodCategory(Integer codCategory) {
        this.codCategory = codCategory;
    }
}
