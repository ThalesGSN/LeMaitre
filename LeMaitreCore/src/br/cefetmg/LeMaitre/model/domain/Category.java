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
public class Category {
    private Long seqCategory;
    
    private String nomCategory;

    public Category() { }

    public Category(Long seqCategory, String nomCategory) {
        this.seqCategory = seqCategory;
        this.nomCategory = nomCategory;
    }

    /**
     * Get the value of seqCategory
     *
     * @return the value of seqCategory
     */
    public Long getSeqCategory() {
        return seqCategory;
    }

    /**
     * Set the value of seqCategory
     *
     * @param seqCategory new value of seqCategory
     */
    public void setSeqCategory(Long seqCategory) {
        this.seqCategory = seqCategory;
    }
   
    /**
     * Get the value of nomCategory
     *
     * @return the value of nomCategory
     */
    public String getNomCategory() {
        return nomCategory;
    }

    /**
     * Set the value of nomCategory
     *
     * @param nomCategory new value of nomCategory
     */
    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }

}
