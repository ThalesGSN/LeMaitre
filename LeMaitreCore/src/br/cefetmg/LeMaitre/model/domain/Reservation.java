/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.domain;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Thalesgsn
 */
public class Reservation {
    private Integer codIDTable;
    
    private Integer nroPersons;
    
    private String txtContactName;

    private String txtTelephone;

    private String txtCellphone;

    private Date datReservation;

    private Time datHourReservation;

    public Reservation() { }

    public Reservation(Integer codIDTable, Integer nroPersons, String txtContactName,
            String txtTelephone, String txtCellphone, Date datReservation, Time datHourReservation) {
        this.codIDTable = codIDTable;
        this.nroPersons = nroPersons;
        this.txtContactName = txtContactName;
        this.txtTelephone = txtTelephone;
        this.txtCellphone = txtCellphone;
        this.datReservation = datReservation;
        this.datHourReservation = datHourReservation;
    }

    /**
     * Get the value of codID
     *
     * @return the value of codID
     */
    public Integer getCodIDTable() {
        return codIDTable;
    }

    /**
     * Set the value of codID
     *
     * @param codIDTable new value of codIDTable
     */
    public void setCodIDTable(Integer codIDTable) {
        this.codIDTable = codIDTable;
    }

    /**
     * Get the value of nroPersons
     *
     * @return the value of nroPersons
     */
    public Integer getNropersons() {
        return nroPersons;
    }

    /**
     * Set the value of nroPersons
     *
     * @param nroPersons new value of nroPersons
     */
    public void setNropersons(Integer nroPersons) {
        this.nroPersons = nroPersons;
    }
    
    /**
     * Get the value of txtContactName
     *
     * @return the value of txtContactName
     */
    public String getTxtContactName() {
        return txtContactName;
    }

    /**
     * Set the value of txtContactName
     *
     * @param txtContactName new value of txtContactName
     */
    public void setTxtContactName(String txtContactName) {
        this.txtContactName = txtContactName;
    }
    
    /**
     * Get the value of txtTelephone
     *
     * @return the value of txtTelephone
     */
    public String getTxtTelephone() {
        return txtTelephone;
    }

    /**
     * Set the value of txtTelephone
     *
     * @param txtTelephone new value of txtTelephone
     */
    public void setTxtTelephone(String txtTelephone) {
        this.txtTelephone = txtTelephone;
    }
    
    /**
     * Get the value of txtCellphone
     *
     * @return the value of txtCellphone
     */
    public String getTxtCellphone() {
        return txtCellphone;
    }

    /**
     * Set the value of txtCellphone
     *
     * @param txtCellphone new value of txtCellphone
     */
    public void setTxtCellphone(String txtCellphone) {
        this.txtCellphone = txtCellphone;
    }
    
    /**
     * Get the value of datReservation
     *
     * @return the value of datReservation
     */
    public Date getDatReservation() {
        return datReservation;
    }

    /**
     * Set the value of datReservation
     *
     * @param datReservation new value of datReservation
     */
    public void setDatReservation(Date datReservation) {
        this.datReservation = datReservation;
    }
 
    /**
     * Get the value of datHourReservation
     *
     * @return the value of datHourReservation
     */
    public Time getDatHourReservation() {
        return datHourReservation;
    }

    /**
     * Set the value of datHourReservation
     *
     * @param datHourReservation new value of datHourReservation
     */
    public void setDatHourReservation(Time datHourReservation) {
        this.datHourReservation = datHourReservation;
    }
}
