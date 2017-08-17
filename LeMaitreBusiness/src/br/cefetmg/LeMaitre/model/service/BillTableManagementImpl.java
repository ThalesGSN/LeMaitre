/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.BillTableDAO;
import br.cefetmg.LeMaitre.model.dao.TableDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class BillTableManagementImpl implements BillTableManagement {

    private final BillTableDAO DAO;
    private final BillManagement billManagement;
    private final TableManagement tableManagement;
    
    public BillTableManagementImpl(BillTableDAO DAO) {
        this.DAO = DAO;
        this.billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
        this.tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
    }

    @Override
    public boolean billTableInsert(BillTable billTable) throws BusinessException, PersistenceException {
        if(billTable == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "billTable cannot be null");
        
        if(billTable.getCodIDBill() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "CodIDBill cannot be null");
        
        if(!billManagement.containsThisBillID(billTable.getCodIDBill()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "CodIDBill is not in the persistence");
        
        if(billTable.getCodIDTable() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "CodIDTable cannot be null");
        
        if(!tableManagement.containsThisTableID(billTable.getCodIDTable()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "CodIDTablem is not in the persistence");

        return DAO.insert(billTable);
    }

    @Override
    public boolean billTableRemove(Long billID, Long tableID) throws PersistenceException {
        if(billID == null || tableID == null )
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        return DAO.remove(billID, tableID);
    }

    @Override
    public List<Bill> getBillsByTableID(Long tableID) throws PersistenceException {
        if(tableID == null )
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "tableID cant be null");
        
        return DAO.listBillsByTableID(tableID);
    }

    @Override
    public List<Table> getTablesByBillID(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "tableID cant be null");
        
        return DAO.listTablesByBillID(billID);
    }
    
}
