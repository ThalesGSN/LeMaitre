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
    public Integer itemInsert(Item item) throws BusinessException, PersistenceException;
    public boolean itemUpdate(Item item) throws BusinessException, PersistenceException;
    public boolean itemRemove(Integer itemID) throws PersistenceException;
    public Item getItemByID(Integer itemID) throws PersistenceException;
    public List<Item> getItemsByCategoryID(Integer categoryID) throws PersistenceException;
    public List<Item> getItemsBySubcategoryID(Integer subcategoryID) throws PersistenceException;
    public boolean containsThisItemID(Integer itemID) throws PersistenceException;
    public List<Item> getAllItems() throws PersistenceException;
}
