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
public class Table {
    private Integer codID;

    private char idtStatus;

    private int nroSeat;

    public Table() { }

    public Table(Integer codID, char idtStatus, int nroSeat) {
        this.codID = codID;
        this.idtStatus = idtStatus;
        this.nroSeat = nroSeat;
    }

    /**
     * Get the value of codID
     *
     * @return the value of codID
     */
    public Integer getCodID() {
        return codID;
    }

    /**
     * Set the value of codID
     *
     * @param codID new value of codID
     */
    public void setCodID(Integer codID) {
        this.codID = codID;
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
    
    /**
     * Get the value of nroSeat
     *
     * @return the value of nroSeat
     */
    public int getNroSeat() {
        return nroSeat;
    }

    /**
     * Set the value of nroSeat
     *
     * @param nroSeat new value of nroSeat
     */
    public void setNroSeat(int nroSeat) {
        this.nroSeat = nroSeat;
    }
}
