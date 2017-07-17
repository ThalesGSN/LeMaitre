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
public class Bill {
    
    private Long codID;
    
    private Long datUse;

    private char idtStatus;

    public Bill() { }

    public Bill(Long codID, Long datUse, char idtStatus) {
        this.codID = codID;
        this.datUse = datUse;
        this.idtStatus = idtStatus;
    }

    /**
     * Get the value of codID
     *
     * @return the value of codID
     */
    public Long getCodID() {
        return codID;
    }

    /**
     * Set the value of codID
     *
     * @param codID new value of codID
     */
    public void setCodID(Long codID) {
        this.codID = codID;
    }

    /**
     * Get the value of datUse
     *
     * @return the value of datUse
     */
    public Long getDatUse() {
        return datUse;
    }

    /**
     * Set the value of datUse
     *
     * @param datUse new value of datUse
     */
    public void setDatUse(Long datUse) {
        this.datUse = datUse;
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
}
