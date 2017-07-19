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
public class BillTable {
    private Long codIDBill;

    private Integer codIDTable;

    public BillTable() { }

    public BillTable(Long codIDBill, Integer codIDTable) {
        this.codIDBill = codIDBill;
        this.codIDTable = codIDTable;
    }

    /**
     * Get the value of codIDBill
     *
     * @return the value of codIDBill
     */
    public Long getCodIDBill() {
        return codIDBill;
    }

    /**
     * Set the value of codIDBill
     *
     * @param codIDBill new value of codIDBill
     */
    public void setCodIDBill(Long codIDBill) {
        this.codIDBill = codIDBill;
    }
    
    /**
     * Get the value of codIDTable
     *
     * @return the value of codIDTable
     */
    public Integer getcodIDBillTable() {
        return codIDTable;
    }

    /**
     * Set the value of codIDBillTable
     *
     * @param codIDTable new value of codIDTable
     */
    public void setcodIDBillTable(Integer codIDTable) {
        this.codIDTable = codIDTable;
    }
}
