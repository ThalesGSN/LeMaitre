/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.BillDAO;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class BillManagementImpl implements BillManagement {
    private BillDAO DAO;

    public BillManagementImpl(BillDAO DAO) {
        this.DAO = DAO;
    }
    
    @Override
    public Bill billCreate() throws PersistenceException {
        return DAO.create();
    }

    @Override
    public boolean billUpdate(Bill bill) throws BusinessException, PersistenceException {
        if(bill == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null bill cannot be inserted.");
        
        if(bill.getCodToken() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "Token cannot be null.");
        
        if(bill.getDatUse() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "Date cannot be null.");
        
        if(String.copyValueOf(Bill.IDT_STATUS_POSSIBLE_VALUES)
                .indexOf(bill.getIdtStatus()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtStatus invalid value.");
        }
        
        return DAO.update(bill);
    }

    @Override
    public boolean billRemove(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "codToken cannot be null.");
        
        return DAO.remove(codToken);
    }

    @Override
    public Bill getBillByID(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "codToken cannot be null.");
        
        return DAO.getBillByID(codToken);
    }

    @Override
    public boolean containsThisBillID(String codToken) throws PersistenceException {
         if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "codToken cannot be null.");
        
        return DAO.containsThisBillID(codToken);
    }

    @Override
    public List<Bill> listAll() throws PersistenceException {
        return DAO.listAll();
    }
    
}
