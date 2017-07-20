/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface TableDAO {
    public Integer insert(Table table) throws PersistenceException;
    public boolean update(Table table) throws PersistenceException;
    public boolean remove(Integer tableID) throws PersistenceException;
    public Table getTableByID(Integer tableID) throws PersistenceException;
    public boolean thisTableIDExists(Integer tableID) throws PersistenceException;
}
