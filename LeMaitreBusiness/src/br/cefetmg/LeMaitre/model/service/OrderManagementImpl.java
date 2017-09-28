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
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OrderManagementImpl implements  OrderManagement {
    OrderDAO DAO;
    private final BillManagement billManagement;
    private final ItemManagement itemManagement;

    public OrderManagementImpl(OrderDAO DAO) {
        this.DAO = DAO;
        this.billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
        this.itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
    }
    
    @Override
    public Long orderInsert(Order order) throws BusinessException, PersistenceException {
        if(order == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null order cannot be inserted.");
        
        if(order.getCodIDBill() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codIDBill cannot be null.");
        
        if(!billManagement.containsThisBillID(order.getCodIDBill()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "billID doesn't exist in the persistence");
        
        if(order.getCodItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(!itemManagement.containsThisItemID(order.getCodItem()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "itemID doesn't exist in the persistence");
        
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
        
        if(order.getCodIDBill() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codIDBill cannot be null.");
        
        if(!billManagement.containsThisBillID(order.getCodIDBill()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "billID doesn't exist in the persistence");
        
        if(order.getCodItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(!itemManagement.containsThisItemID(order.getCodItem()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "itemID doesn't exist in the persistence");
        
        if(String.copyValueOf(Order.IDT_STATUS_POSSIBLE_VALUES)
                .indexOf(order.getIdtStatus()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtAvaliable invalid value.");
        }
        
        if(order.getVlrPrice() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");
        
        return DAO.update(order);
    }

    @Override
    public boolean orderRemove(Long billID, Long itemID) throws PersistenceException {
        if(billID == null || itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameter can be null.");
        
        return DAO.remove(billID, itemID);
    }

    @Override
    public Order getOrderByID(Long billID, Long itemID) throws PersistenceException {
        if(billID == null || itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameter can be null.");
        
        return DAO.getOrderByID(billID, itemID);
    }

    @Override
    public List<Order> getOrdersByBillID(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "billID cannot be null.");
        
        return DAO.listOrdersByBillID(billID);
    }

    @Override
    public List<Item> getItemsByBillID(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "billID cannot be null.");
        
        return DAO.listItemsByBillID(billID);
    }
}
