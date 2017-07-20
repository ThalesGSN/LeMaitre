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
public interface PersistenceErrorCodes {
    /**
     * Used when the inserted object comes to the DAO as null.
     */
    public static final int INSERT_OBJECT_ISNULL = 4004;
    
    /**
     * Used when the inserted object comes to the DAO as null.
     */
    public static final int UPDATE_OBJECT_ISNULL = 4004;
    
    /**
     * Used when the inserted object comes to the DAO as null.
     */
    public static final int PARAMETER_ISNULL = 4004;
    
    /**
     * Used when the inserted object has duplicatedKey in the persistence.
     */
    public static final int DUPLICATED_KEY = 1062;
    
    /**
     *  Used when JDBC driver was not found.
     */
    public static final int DRIVER_NOT_FOUND = 1001;
    
    /**
     *  Used when some error Occourred when updating Like updating more then
     * one touple. Or not updating any touples.
     */
    public static final int NOT_A_UPDATE = 1002;
    
    /**
     *  Used when some error Occourred when deleting Like updating more then
     * one touple. Or not deletion any touples.
     */
    public static final int NOT_A_DELETE = 1003;
    
}
