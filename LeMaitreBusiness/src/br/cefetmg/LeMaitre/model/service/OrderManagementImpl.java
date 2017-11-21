/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAO;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OrderManagementImpl implements  OrderManagement {
    private OrderDAO DAO;
    private final BillManagement billManagement;
    private final ItemManagement itemManagement;

    public OrderManagementImpl(OrderDAO DAO) {
        this.DAO = DAO;
        this.billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
        this.itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
    }
    
    @Override
    public boolean orderInsert(Order order) throws BusinessException, PersistenceException {
        if(order == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null order cannot be inserted.");
        
        if(order.getCodToken() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codIDBill cannot be null.");
        
        if(!billManagement.containsThisBillID(order.getCodToken()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "codToken doesn't exist in the persistence");
        
        if(order.getCodItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(!itemManagement.containsThisItemID(order.getCodItem()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "datOrder doesn't exist in the persistence");
        
        if(String.copyValueOf(Order.IDT_STATUS_POSSIBLE_VALUES)
                .indexOf(order.getIdtStatus()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtAvaliable invalid value.");
        }
        
        if(order.getVlrPrice() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");
        
        return DAO.insert(order);
    }

    @Override
    public boolean orderUpdate(Order order) throws BusinessException, PersistenceException {
        if(order == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null order cannot be inserted.");
        
        if(order.getCodToken() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codIDBill cannot be null.");
        
        if(!billManagement.containsThisBillID(order.getCodToken()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "codToken doesn't exist in the persistence");
        
        if(order.getCodItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(!itemManagement.containsThisItemID(order.getCodItem()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "datOrder doesn't exist in the persistence");
        
        if(String.copyValueOf(Order.IDT_STATUS_POSSIBLE_VALUES)
                .indexOf(order.getIdtStatus()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtAvaliable invalid value.");
        }
        
        if(order.getVlrPrice() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");
        
        return DAO.update(order);
    }

    @Override
    public boolean orderRemove(String codToken, Timestamp datOrder) throws PersistenceException {
        if(codToken == null || datOrder == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameter can be null.");
        
        return DAO.remove(codToken, datOrder);
    }

    @Override
    public Order getOrderByID(String codToken, Timestamp datOrder) throws PersistenceException {
        if(codToken == null || datOrder == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameter can be null.");
        
        return DAO.getOrderByID(codToken, datOrder);
    }

    @Override
    public List<Order> getOrdersByToken(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "codToken cannot be null.");
        
        return DAO.listOrdersByToken(codToken);
    }

    @Override
    public List<Item> getItemsByToken(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "codToken cannot be null.");
        
        return DAO.listItemsByToken(codToken);
    }
}
