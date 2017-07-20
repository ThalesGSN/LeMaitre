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
    public static final int NULL_INSERTED_OBJECT  = -12; 
    public static final int NOTNULL_PARAMETER_ISNULL  = -13; 
    public static final int NOTNULL_ATRIBUTE_ISNULL  = -14; 
    public static final int EMPTY_STRING  = -15;
    public static final int INVALID_IDT_VALUE  = -16;
    public static final int INVALID_FOREING_KEY  = -17;
    public static final int INVALID_PARAMETER  = -18;
    
}
