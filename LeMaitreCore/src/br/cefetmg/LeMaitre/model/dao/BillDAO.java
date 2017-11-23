/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface BillDAO {
    public String insert(Bill bill) throws PersistenceException;
    public boolean update(Bill bill) throws PersistenceException;
    public boolean remove(String codToken) throws PersistenceException;
    public Bill getBillByID(String codToken) throws PersistenceException;
    public boolean containsThisBillID(String codToken) throws PersistenceException;
    public List<Bill> listAll() throws PersistenceException;
}
