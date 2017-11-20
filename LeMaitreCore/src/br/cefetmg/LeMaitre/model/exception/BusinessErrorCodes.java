/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.exception;

/**
 *
 * @author Thalesgsn
 */
public interface BusinessErrorCodes {
    /**
     * Used when a object to be inserted is null in the bussiness class.
     */
    public static final int NULL_INSERT_OBJECT  = -12;
    
    /**
     * Used when a parameter that can't be null in the persistence is null
     * in the business class.
     */
    public static final int NOTNULL_PARAMETER_ISNULL  = -13; 
    
    /**
     * Used when an atribute is null in the bussiness class.
     * (This error code doesn't apply for the Inserts or Updates method.)
     */
    public static final int NOTNULL_ATRIBUTE_ISNULL  = -14; 
    
    /**
     * Used when an String atribute that can't be null nor empty, 
     * is empty in the business class.
     */
    public static final int EMPTY_STRING  = -15;
    
    /**
     * Used when a char that indicates a state or type, gets an invalid 
     * value in business class.
     */
    public static final int INVALID_IDT_VALUE  = -16;
    
    /**
     * Used when an atribute that is a foreing key in the persistence doesn't
     * exist in the persistence.
     */
    public static final int INVALID_FOREING_KEY  = -17;
    
    /**
     * Used when an atribute gets invalid values in the bussiness class.
     */
    public static final int INVALID_PARAMETER  = -18;
}
