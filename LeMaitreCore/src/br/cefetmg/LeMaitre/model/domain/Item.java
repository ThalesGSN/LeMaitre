/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

/**
 *
 * @author Thalesgsn
 */
public class Item {
    private Integer codItem;

    private Double vlrPrice;

    private String nomItem;

    private String desItem;

    private boolean isAvaliable;

    private Integer seqCategory;
    
    private Integer seqSubcategory;

    public Item() { }

    public Item(Integer codItem, Double vlrPrice, String nomItem, String desItem, boolean isAvaliable, Integer seqCategory, Integer seqSubcategory) {
        this.codItem = codItem;
        this.vlrPrice = vlrPrice;
        this.nomItem = nomItem;
        this.desItem = desItem;
        this.isAvaliable = isAvaliable;
        this.seqCategory = seqCategory;
        this.seqSubcategory = seqSubcategory;
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
     * Get the value of vlrPrice
     *
     * @return the value of vlrPrice
     */
    public Double getVlrPrice() {
        return vlrPrice;
    }

    public Integer getSeqCategory() {
        return seqCategory;
    }

    public void setSeqCategory(Integer seqCategory) {
        this.seqCategory = seqCategory;
    }

    public boolean isIsAvaliable() {
        return isAvaliable;
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
     * Get the value of isAvaliable
     *
     * @return the value of isAvaliable
     */

    public boolean isAvaliable() {
        return isAvaliable;
    }

    /**
     * Set the value of idtAvaliable
     *
     * @param isAvaliable new value of idtAvaliable
     */
        
    public void setIsAvaliable(boolean isAvaliable) {
        this.isAvaliable = isAvaliable;
    }

    /**
     * Get the value of codCategory
     *
     * @return the value of codCategory
     */
    public Integer getCodCategory() {
        return seqCategory;
    }

    /**
     * Set the value of codCategory
     *
     * @param codCategory new value of codCategory
     */
    public void setCodCategory(Integer codCategory) {
        this.seqCategory = codCategory;
    }
    
    /**
     * Get the value of seqSubcategory
     *
     * @return the value of seqSubcategory
     */
    public Integer getSeqSubcategory() {
        return seqSubcategory;
    }

    /**
     * Set the value of seqSubcategory
     *
     * @param seqSubcategory new value of seqSubcategory
     */
    public void setSeqSubcategory(Integer seqSubcategory) {
        this.seqSubcategory = seqSubcategory;
    }
}
