/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface TableDAO {
    public Long tableInsert(Table table) throws BusinessException, PersistenceException;
    public boolean tableUpdate(Table table) throws BusinessException, PersistenceException;
    public boolean tableRemove(Integer tableID) throws PersistenceException;
    public Table gettableByID(Integer tableID) throws PersistenceException;
}
