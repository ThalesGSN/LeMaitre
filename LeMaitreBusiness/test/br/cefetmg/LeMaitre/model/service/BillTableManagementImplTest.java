/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.domain.Table;
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
public class BillTableManagementImplTest {
    
    public BillTableManagementImplTest() {
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
     * Test of billTableInsert method, of class BillTableManagementImpl.
     */
    @Test
    public void testBillTableInsert() throws Exception {
        System.out.println("billTableInsert");
        BillTable billTable = null;
        BillTableManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.billTableInsert(billTable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of billTableRemove method, of class BillTableManagementImpl.
     */
    @Test
    public void testBillTableRemove() throws Exception {
        System.out.println("billTableRemove");
        Long billID = null;
        Integer tableID = null;
        BillTableManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.billTableRemove(billID, tableID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBillsByTableID method, of class BillTableManagementImpl.
     */
    @Test
    public void testGetBillsByTableID() throws Exception {
        System.out.println("getBillsByTableID");
        Integer tableID = null;
        BillTableManagementImpl instance = null;
        List<Bill> expResult = null;
        List<Bill> result = instance.getBillsByTableID(tableID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTablesByBillID method, of class BillTableManagementImpl.
     */
    @Test
    public void testGetTablesByBillID() throws Exception {
        System.out.println("getTablesByBillID");
        Long billID = null;
        BillTableManagementImpl instance = null;
        List<Table> expResult = null;
        List<Table> result = instance.getTablesByBillID(billID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
