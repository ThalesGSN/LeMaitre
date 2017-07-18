/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Temp
 */
public interface BillTableManagement {
    public Long BillTableInsert(BillTable billTable) throws BusinessException, PersistenceException;
    public boolean BillTableUpdate(BillTable billTable) throws BusinessException, PersistenceException;
    public boolean BillTableRemove(Long CODIdBill, Long DATUse, Long BillTableID) throws PersistenceException;
    public BillTable getbillTableByID(Long CODIdBill, Long DATUse, Long BillTableID) throws PersistenceException;
}
