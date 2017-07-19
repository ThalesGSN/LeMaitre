/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface BillDAO {
    public Long Insert(Bill bill) throws BusinessException, PersistenceException;
    public boolean Update(Bill bill) throws BusinessException, PersistenceException;
    public boolean Remove(Long billID) throws PersistenceException;
    public Bill listBillByID(Long billID) throws PersistenceException;
}
