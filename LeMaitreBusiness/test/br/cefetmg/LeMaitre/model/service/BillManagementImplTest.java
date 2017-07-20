/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Bill;
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
public class BillManagementImplTest {
    
    public BillManagementImplTest() {
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
     * Test of billInsert method, of class BillManagementImpl.
     */
    @Test
    public void testBillInsert() throws Exception {
        System.out.println("billInsert");
        Bill bill = null;
        BillManagementImpl instance = null;
        Long expResult = null;
        Long result = instance.billInsert(bill);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of billUpdate method, of class BillManagementImpl.
     */
    @Test
    public void testBillUpdate() throws Exception {
        System.out.println("billUpdate");
        Bill bill = null;
        BillManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.billUpdate(bill);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of billRemove method, of class BillManagementImpl.
     */
    @Test
    public void testBillRemove() throws Exception {
        System.out.println("billRemove");
        Long billID = null;
        BillManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.billRemove(billID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBillByID method, of class BillManagementImpl.
     */
    @Test
    public void testGetBillByID() throws Exception {
        System.out.println("getBillByID");
        Long billID = null;
        BillManagementImpl instance = null;
        Bill expResult = null;
        Bill result = instance.getBillByID(billID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsThisBillID method, of class BillManagementImpl.
     */
    @Test
    public void testThisBillIDExists() throws Exception {
        System.out.println("thisBillIDExists");
        Long billID = null;
        BillManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.containsThisBillID(billID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
