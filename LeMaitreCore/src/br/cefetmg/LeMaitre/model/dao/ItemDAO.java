/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface ItemDAO {
    public Long insert(Item item) throws PersistenceException;
    public boolean update(Item item) throws PersistenceException;
    public boolean remove(Long itemID) throws PersistenceException;
    public Item getItemByID(Long itemID) throws PersistenceException;
    public boolean containsThisItemID(Long itemID) throws PersistenceException;
    public List<Item> listAllItems() throws PersistenceException;
    
}
