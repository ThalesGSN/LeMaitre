/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface OrderDAO {
    public Long insert(Order order) throws PersistenceException;
    public boolean update(Order order) throws PersistenceException;
    public boolean remove(Long billID, Long itemID) throws PersistenceException;
    public Order getOrderByID(Long billID, Long itemID) throws PersistenceException;
    public List<Order> listOrdersByBillID(Long billID) throws PersistenceException;
    public List<Item> listItemsByBillID(Long billID) throws PersistenceException;
}
