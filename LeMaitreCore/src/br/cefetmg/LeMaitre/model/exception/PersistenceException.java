/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.exception;

/**
 *
 * @author Jota Renan
 */
public class PersistenceException extends Exception {
    private int errorCode;

    public PersistenceException(String message) {
        super(message);
    }
    
    public PersistenceException(Exception ex) {
        super(ex);
    }
    
    /**
     * Get the value of errorCode
     *
     * @return the value of errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Set the value of errorCode
     *
     * @param errorCode new value of errorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
