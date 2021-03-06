/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface TableManagement {
    public Integer tableInsert(Table table) throws BusinessException, PersistenceException;
    public boolean tableUpdate(Table table) throws BusinessException, PersistenceException;
    public boolean tableRemove(Integer tableID) throws PersistenceException;
    public Table getTableByID(Integer tableID) throws PersistenceException;
    public boolean containsThisTableID(Integer tableID) throws PersistenceException;
    public List<Table> listAll() throws PersistenceException;
}
