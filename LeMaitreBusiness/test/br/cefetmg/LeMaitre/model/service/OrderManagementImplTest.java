/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
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
    
    public OrderManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of orderInsert method, of class OrderManagementImpl.
     */
    @Test
    public void testOrderInsert() throws Exception {
        System.out.println("orderInsert");
        Order order = null;
        OrderManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.orderInsert(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
