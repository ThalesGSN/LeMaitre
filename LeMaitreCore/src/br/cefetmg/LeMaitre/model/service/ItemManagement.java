/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface ItemManagement {
    public Long itemInsert(Item item) throws BusinessException, PersistenceException;
    public boolean itemUpdate(Item item) throws BusinessException, PersistenceException;
    public boolean itemRemove(Long itemID) throws PersistenceException;
    public Item getItemByID(Long itemID) throws PersistenceException;
    public boolean containsThisItemID(Long itemID) throws PersistenceException;
    public List<Item> getAllItems() throws PersistenceException;
}
