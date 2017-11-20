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
    private String codToken;
    
    private Date datUse;

    private char idtStatus;

    public static final char[] IDT_STATUS_POSSIBLE_VALUES = {'O', 'C'};
    
    public Bill() { }

    public Bill(Date datUse, char idtStatus) {
        this.datUse = datUse;
        this.idtStatus = idtStatus;
    }
    
    public Bill(String codID, Date datUse, char idtStatus) {
        this.codToken = codID;
        this.datUse = datUse;
        this.idtStatus = idtStatus;
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
     * Get the value of seqUse
     *
     * @return the value of seqUse
     */
    public Date getDatUse() {
        return datUse;
    }

    /**
     * Set the value of seqUse
     *
     * @param seqUse new value of seqUse
     */
    public void setDatUse(Date seqUse) {
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
