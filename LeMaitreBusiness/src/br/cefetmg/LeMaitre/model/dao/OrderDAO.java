/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface OrderDAO {
    public Long orderInsert(Order order) throws BusinessException, PersistenceException;
    public boolean orderUpdate(Order order) throws BusinessException, PersistenceException;
    public boolean orderRemove(Long billId, Long itemID) throws PersistenceException;
    public Order getOrderByID(Long billId, Long itemID) throws PersistenceException;
    public List<Order> listOrdersByBillID(Long billID) throws PersistenceException;
    public List<Item> listItemsByBillID(Long billID) throws PersistenceException;
}
