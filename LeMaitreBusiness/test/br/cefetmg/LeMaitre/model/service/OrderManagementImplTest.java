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

    /**
     * Test of orderUpdate method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderUpdate() throws Exception {
        System.out.println("orderUpdate");
        Order order = null;
        OrderManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.orderUpdate(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of orderRemove method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderRemove() throws Exception {
        System.out.println("orderRemove");
        Long billID = null;
        Integer itemID = null;
        OrderManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.orderRemove(billID, itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderByID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetOrderByID() throws Exception {
        System.out.println("getOrderByID");
        Long billID = null;
        Integer itemID = null;
        OrderManagementImpl instance = null;
        Order expResult = null;
        Order result = instance.getOrderByID(billID, itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersByBillID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetOrdersByBillID() throws Exception {
        System.out.println("getOrdersByBillID");
        Long billID = null;
        OrderManagementImpl instance = null;
        List<Order> expResult = null;
        List<Order> result = instance.getOrdersByBillID(billID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsByBillID method, of class OrderManagementImpl.
     */
    @Test
    public void testGetItemsByBillID() throws Exception {
        System.out.println("getItemsByBillID");
        Long billID = null;
        OrderManagementImpl instance = null;
        List<Item> expResult = null;
        List<Item> result = instance.getItemsByBillID(billID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
