/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface OrderManagement {
    public Long orderInsert(Order order) throws BusinessException, PersistenceException;
    public boolean orderUpdate(Order order) throws BusinessException, PersistenceException;
    public boolean orderRemove(Long billID, Long itemID) throws PersistenceException;
    public Order getOrderByID(Long billID, Long itemID) throws PersistenceException;
    public List<Order> getOrdersByBillID(Long billID) throws PersistenceException;
    public List<Item> getItemsByBillID(Long billID) throws PersistenceException;
}
