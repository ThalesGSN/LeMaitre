/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

import java.util.Date;

/**
 *
 * @author Thalesgsn
 */
public class Bill {
    
    private Long codID;
    
    private Date datUse;

    private char idtStatus;

    public Bill() { }

    public Bill(Long codID, Date datUse, char idtStatus) {
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
     * Get the value of seqUse
     *
     * @return the value of seqUse
     */
    public Date getSeqUse() {
        return datUse;
    }

    /**
     * Set the value of seqUse
     *
     * @param seqUse new value of seqUse
     */
    public void setSeqUse(Date seqUse) {
        this.datUse = seqUse;
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
