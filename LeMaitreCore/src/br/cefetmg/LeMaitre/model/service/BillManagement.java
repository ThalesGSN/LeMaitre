/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface BillManagement {
    public String billInsert(Bill bill) throws BusinessException, PersistenceException;
    public boolean billUpdate(Bill bill) throws BusinessException, PersistenceException;
    public boolean billRemove(String billID) throws PersistenceException;
    public Bill getBillByID(String billID) throws PersistenceException;
    public boolean containsThisBillID(String billID) throws PersistenceException;
    public List<Bill> listAll() throws PersistenceException;
}
