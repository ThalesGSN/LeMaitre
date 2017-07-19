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
    public Long BillTableInsert(BillTable billTable) throws BusinessException, PersistenceException;
    public boolean BillTableUpdate(BillTable billTable) throws BusinessException, PersistenceException;
    public boolean BillTableRemove(Long billID, Integer tableID) throws PersistenceException;
    public List<Bill> getBillsByTableID(Integer tableID) throws PersistenceException;
    public List<Table> getTablesByBillID(Long billID) throws PersistenceException;
    
}
