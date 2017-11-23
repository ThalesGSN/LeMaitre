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
public class Subcategory {
    private Integer seqCategory;
    
    private Integer seqSubcategory;

    private String nomSubcategory;

    public Subcategory() {
    }

    public Subcategory(Integer seqCategory, Integer seqSubcategory, String nomSubcategory) {
        this.seqCategory = seqCategory;
        this.seqSubcategory = seqSubcategory;
        this.nomSubcategory = nomSubcategory;
    }

    /**
     * Get the value of seqCategory
     *
     * @return the value of seqCategory
     */
    public Integer getSeqCategory() {
        return seqCategory;
    }

    /**
     * Set the value of seqCategory
     *
     * @param seqCategory new value of seqCategory
     */
    public void setSeqCategory(Integer seqCategory) {
        this.seqCategory = seqCategory;
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
    
    /**
     * Get the value of nomSubcategory
     *
     * @return the value of nomSubcategory
     */
    public String getNomSubcategory() {
        return nomSubcategory;
    }

    /**
     * Set the value of nomSubcategory
     *
     * @param nomSubcategory new value of nomSubcategory
     */
    public void setNomSubcategory(String nomSubcategory) {
        this.nomSubcategory = nomSubcategory;
    }
}
