/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

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
public interface BillTableManagement {
    public boolean billTableInsert(BillTable billTable) throws BusinessException, PersistenceException;
    public boolean billTableRemove(Long billID, Long tableID) throws PersistenceException;
    public List<Bill> getBillsByTableID(Long tableID) throws PersistenceException;
    public List<Table> getTablesByBillID(Long billID) throws PersistenceException;
    
}
