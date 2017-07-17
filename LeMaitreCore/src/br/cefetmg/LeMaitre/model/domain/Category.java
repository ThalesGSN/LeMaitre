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
    private String codCategory;
    
    private String nomCategory;

    public Category() { }

    public Category(String codCategory, String nomCategory) {
        this.codCategory = codCategory;
        this.nomCategory = nomCategory;
    }
    
    /**
     * Get the value of codCategory
     *
     * @return the value of codCategory
     */
    public String getCodCategory() {
        return codCategory;
    }

    /**
     * Set the value of codCategory
     *
     * @param codCategory new value of codCategory
     */
    public void setCodCategory(String codCategory) {
        this.codCategory = codCategory;
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
