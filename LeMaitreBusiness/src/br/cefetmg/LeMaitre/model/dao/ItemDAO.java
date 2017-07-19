/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface ItemDAO {
    public Long insert(Item item) throws BusinessException, PersistenceException;
    public boolean update(Item item) throws BusinessException, PersistenceException;
    public boolean remove(Long itemID) throws PersistenceException;
    public Item getItemByID(Long itemID) throws PersistenceException;
}
