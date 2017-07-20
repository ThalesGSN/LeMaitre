/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface BillTableDAO {
    public boolean insert(BillTable billTable) throws PersistenceException;
    public boolean remove(Long billID, Integer tableID) throws PersistenceException;
    public List<Table> listTablesByBillID(Long billID) throws PersistenceException;
    public List<Bill> listBillsByTableID(Integer tableID) throws PersistenceException;
    
}
