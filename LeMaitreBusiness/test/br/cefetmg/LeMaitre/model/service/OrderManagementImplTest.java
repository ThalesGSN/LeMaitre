/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class OrderManagementImplTest {
    
    private Order order;
    private Long codIDBill;
    private Long codItem;
    private char idtStatus;
    private Double vlrPrice;
    private String codToken;
    private OrderManagement orderManagement;
    
    public OrderManagementImplTest() {
        codIDBill = new Long(1);
        codItem = new Long(1);
        idtStatus = 'T';
        vlrPrice = new Double(30.0);
        codToken = new String("xxx");
        order = new Order(codIDBill, codItem, idtStatus, vlrPrice, codToken);
        orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
    }
    
    
    @Before
    public void setUp() {
        codIDBill = -1L;
        codItem = -1L;
        idtStatus = 'T'; //TODO
        codToken = "xxx";
    }
    
    @After
    public void tearDown() {
        try {
            if (codIDBill != -1L && codItem != -1L) {
                orderManagement.orderRemove(codIDBill, codItem);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove order after test");
        }
    }

    /**
     * Test of orderInsert method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderInsert() throws Exception {
        try {
            codIDBill = orderManagement.orderInsert(order);
            
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert order");
        }
        System.out.println("Passed testOrderInsert test");
    }

    @Test
    public void testItemInsertNull() {
        try {
            order = null;
            codIDBill = orderManagement.orderInsert(order);
            fail("Failed to catch exception when inserting null ID");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testOrderInsertNull test");

        }
    }
    /**
     * Test of orderUpdate method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderUpdateBill() throws Exception {
        try {
            codIDBill = orderManagement.orderInsert(order);
            order.setCodIDBill(codIDBill);
            orderManagement.orderUpdate(order);
            System.out.println("Passed testOrderUpdateBill test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct bill in the order");
        }
    }

    @Test
    public void testOrderUpdateItem() throws Exception {
        try {
            codItem = orderManagement.orderInsert(order);
            order.setCodItem(codItem);
            orderManagement.orderUpdate(order);
            System.out.println("Passed testOrderUpdateItem test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct item in the order");
        }
    }
    
    /**
     * Test of orderRemove method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderRemove() throws Exception {
        try {
            codItem = orderManagement.orderInsert(order);
            codIDBill = orderManagement.orderInsert(order);
            orderManagement.orderRemove(codIDBill, codItem);
            codItem = codIDBill = -1L;
            System.out.println("Correctly removed order");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct order");
        }
    }

    /**
     * Test of getOrderByID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetOrderByID() throws Exception {
        try {
            codIDBill = orderManagement.orderInsert(order);
            codItem = orderManagement.orderInsert(order);
            Order newOrder = orderManagement.getOrderByID(codIDBill, codItem);
            if (newOrder.getCodItem() != codItem && newOrder.getCodIDBill() != codIDBill) {
                fail("Failed to retrieve correct order");
            }
            System.out.println("Correctly retrieved order");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct order");
        }
    }
    

    /**
     * Test of getOrdersByBillID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetOrdersByBillID() throws Exception {
        try {
            codIDBill = orderManagement.orderInsert(order);
            List list = orderManagement.getOrdersByBillID(codIDBill);
            if (list.isEmpty()) {
                fail("Failed to retrieve correct orders");
            }
            System.out.println("Correctly retrieved orders");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct orders");
        }
    }

    
    /**
     * Test of getItemsByBillID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetItemsByBillID() throws Exception {
        try {
            codIDBill = orderManagement.orderInsert(order);
            List list = orderManagement.getItemsByBillID(codIDBill);
            if (list.isEmpty()) {
                fail("Failed to retrieve correct order items");
            }
            System.out.println("Correctly retrieved order items");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct order items");
        }
    }
    
}
