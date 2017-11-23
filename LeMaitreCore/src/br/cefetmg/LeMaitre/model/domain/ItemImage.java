/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

/**
 *
 * @author thales
 */
public class ItemImage {    
    private Integer codItem;
    
    private Long codImage;

    public ItemImage() {
    }

    
    public ItemImage(Integer codItem, Long codImage) {
        this.codItem = codItem;
        this.codImage = codImage;
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
     * Get the value of codImage
     *
     * @return the value of codImage
     */
    public Long getCodImage() {
        return codImage;
    }

    /**
     * Set the value of codImage
     *
     * @param codImage new value of codImage
     */
    public void setCodImage(Long codImage) {
        this.codImage = codImage;
    }
}
