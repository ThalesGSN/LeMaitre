/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Timestamp;
import java.util.List;


/**
 *
 * @author Thalesgsn
 */
public interface OrderDAO {
    public boolean insert(Order order) throws PersistenceException;
    public boolean update(Order order) throws PersistenceException;
    public boolean remove(String codToken, Timestamp datOrder) throws PersistenceException;
    public Order getOrderByID(String codToken, Timestamp datOrder) throws PersistenceException;
    public List<Order> listOrdersByToken(String codToken) throws PersistenceException;
    public List<Item> listItemsByToken(String codToken) throws PersistenceException;
}
