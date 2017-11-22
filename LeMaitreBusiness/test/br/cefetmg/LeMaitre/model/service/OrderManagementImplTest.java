/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class OrderManagementImplTest {
    
    private Order order;
    private final String token;
    private Integer codItem;
    private Timestamp data;
    private char idtStatus;
    private final Double vlrPrice;
    private final OrderManagement orderManagement;
    private static BillManagement billManagement;
    private static ItemManagement itemManagement;
    private boolean isInserted;
    
    private static Bill bill;
    private static Item item;
    
    public OrderManagementImplTest() throws BusinessException, PersistenceException {
        idtStatus = 'T';
        vlrPrice = 30.0;
        order = new Order();
        orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
        
        billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
        itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
        
        bill = new Bill(new Date(System.currentTimeMillis()), 'O');
        
        token = billManagement.billInsert(bill);
        bill.setCodToken(token);

        item = new Item(null, vlrPrice, "coe", "que e", true, codItem, codItem);
        codItem = itemManagement.itemInsert(item);
        item.setCodItem(codItem);
        
        order.setCodToken(token);
        order.setDatOrder(data);
        order.setIdtStatus(idtStatus);
        order.setCodItem(codItem);
        order.setVlrPrice(30.0);
        order.setQtdItem(1);
        
    }
    
    
    @Before
    public void setUp() throws BusinessException, PersistenceException {
        idtStatus = 'T'; //TODO
        isInserted = false;
    }
    
    @After
    public void tearDown() {
        try {
            if (isInserted) {
                orderManagement.orderRemove(token, data);
                isInserted = false;
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove order after test");
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws PersistenceException{
        billManagement.billRemove(bill.getCodToken());
        itemManagement.itemRemove(item.getCodItem());
    }
    /**
     * Test of orderInsert method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderInsert() throws Exception {
        try {
            data = orderManagement.orderInsert(order);
            order.setDatOrder(data);
            isInserted = true;
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert order" + ex.getMessage());
        }
        System.out.println("Passed testOrderInsert test");
    }

    @Test
    public void testItemInsertNull() {
        try {
            order = null;
            data = orderManagement.orderInsert(order);
            isInserted = true;
            fail("Failed to catch exception when inserting null ID");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testOrderInsertNull test");

        }
    }
    /**
     * Test of orderUpdate method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderUpdate() throws Exception {
        try {
            data = orderManagement.orderInsert(order);
            order.setVlrPrice(40.0);
            order.setDatOrder(data);
            orderManagement.orderUpdate(order);
            isInserted = true;
            System.out.println("Passed testOrderUpdateBill test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct bill in the order");
        }
    }
    
    /**
     * Test of orderRemove method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderRemove() throws Exception {
        try {
            data = orderManagement.orderInsert(order);
            orderManagement.orderRemove(token, data);
            isInserted = false;
            System.out.println("Correctly removed order");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct order " + ex.getMessage());
        }
    }

    /**
     * Test of getOrderByID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetOrderByID() throws Exception {
        try {
            data = orderManagement.orderInsert(order);
            Order newOrder = orderManagement.getOrderByID(token, data);
            if (!Objects.equals(newOrder.getCodItem(), codItem) && 
                    (newOrder.getCodToken() == null ? token != null : !newOrder.getCodToken().equals(token))) {
                fail("Failed to retrieve correct order");
            }
            isInserted = true; 
            System.out.println("Correctly retrieved order");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct order");
        }
    }
    

    /**
     * Test of getOrdersBytoken method, of class OrderManagementImpl.
     */
    @Test
    public void testGetOrdersByBillID() throws Exception {
        try {
            data = orderManagement.orderInsert(order);
            order.setDatOrder(data);
            List list = orderManagement.getOrdersByToken(token);
            isInserted = true;
            if (list.isEmpty()) {
                fail("Failed to retrieve correct orders");
            }
            System.out.println("Correctly retrieved orders");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct orders " + ex.getMessage());
        }
    }

    
    /**
     * Test of getItemsBytoken method, of class OrderManagementImpl.
     */
    @Test
    public void testGetItemsByBillID() throws Exception {
        try {
            data = orderManagement.orderInsert(order);
            order.setDatOrder(data);
            List list = orderManagement.getItemsByToken(token);
            isInserted = true;
            if (list.isEmpty()) {
                fail("Failed to retrieve correct order items");
            }
            
            System.out.println("Correctly retrieved order items");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct order items");
        }
    } 
    
}
