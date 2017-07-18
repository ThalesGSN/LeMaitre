/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Temp
 */
public interface BillManagement {
    public Long billInsert(Bill bill) throws BusinessException, PersistenceException;
    public boolean billUpdate(Bill bill) throws BusinessException, PersistenceException;
    public boolean billRemove(Long BillID) throws PersistenceException;
    public Bill getbillByID(Long BillID) throws PersistenceException;
}
