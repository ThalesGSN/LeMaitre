/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface BillDAO {
    public Long insert(Bill bill) throws PersistenceException;
    public boolean update(Bill bill) throws PersistenceException;
    public boolean remove(Long billID) throws PersistenceException;
    public Bill getBillByID(Long billID) throws PersistenceException;
    public boolean containsThisBillID(Long billID) throws PersistenceException;
}
