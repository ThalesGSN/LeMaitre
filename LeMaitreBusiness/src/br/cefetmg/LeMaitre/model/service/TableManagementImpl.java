/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.TableDAO;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public class TableManagementImpl implements TableManagement {

    TableDAO DAO;

    public TableManagementImpl(TableDAO DAO) {
        this.DAO = DAO;
    }
    
    @Override
    public Integer tableInsert(Table table) throws BusinessException, PersistenceException {
        if(table == null)
            throw new BusinessException(BusinessException.NULL_INSERTED_OBJECT, "Null table cannot be inserted.");
        
        if(String.copyValueOf(Table.IDT_STATUS_POSSIBLE_VALUES)
                .indexOf(table.getIdtStatus()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtStatus invalid value.");
        }
        
        if(table.getNroSeat() < 1)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "Number of seats cannot be 0 or negative.");
        
        return DAO.insert(table);
    }

    @Override
    public boolean tableUpdate(Table table) throws BusinessException, PersistenceException {
        if(table == null)
            throw new BusinessException(BusinessException.NULL_INSERTED_OBJECT, "Null table cannot be inserted.");
        
        if(table.getCodID() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "CodID cannot be null.");
        
        if(String.copyValueOf(Table.IDT_STATUS_POSSIBLE_VALUES)
                .indexOf(table.getIdtStatus()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtStatus invalid value.");
        }
        
        if(table.getNroSeat() < 1)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "Number of seats cannot be 0 or negative.");
        
        return DAO.update(table);
    }

    @Override
    public boolean tableRemove(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "tableID cannot be null.");
        
        return DAO.remove(tableID);
    }

    @Override
    public Table getTableByID(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "tableID cannot be null.");
        
        return DAO.getTableByID(tableID);
    }

    @Override
    public boolean thisTableIDExists(Integer tableID) throws PersistenceException {
         if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "tableID cannot be null.");
        
        return DAO.thisTableIDExists(tableID);
    }
    
}
