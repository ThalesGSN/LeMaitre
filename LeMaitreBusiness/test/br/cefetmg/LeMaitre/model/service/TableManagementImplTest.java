/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Table;
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
public class TableManagementImplTest {
    
    private Table table;
    private Integer codID;
    private char idtStatus;
    private int nroSeat;
    
    public TableManagementImplTest() {
        codID = 120;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of tableInsert method, of class TableManagementImpl.
     */
    @Test
    public void testTableInsert() throws Exception {
        System.out.println("tableInsert");
        Table table = null;
        TableManagementImpl instance = null;
        Integer expResult = null;
        Integer result = instance.tableInsert(table);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tableUpdate method, of class TableManagementImpl.
     */
    @Test
    public void testTableUpdate() throws Exception {
        System.out.println("tableUpdate");
        Table table = null;
        TableManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.tableUpdate(table);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tableRemove method, of class TableManagementImpl.
     */
    @Test
    public void testTableRemove() throws Exception {
        System.out.println("tableRemove");
        Integer tableID = null;
        TableManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.tableRemove(tableID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableByID method, of class TableManagementImpl.
     */
    @Test
    public void testGetTableByID() throws Exception {
        System.out.println("getTableByID");
        Integer tableID = null;
        TableManagementImpl instance = null;
        Table expResult = null;
        Table result = instance.getTableByID(tableID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsThisTableID method, of class TableManagementImpl.
     */
    @Test
    public void testThisTableIDExists() throws Exception {
        System.out.println("thisTableIDExists");
        Integer tableID = null;
        TableManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.containsThisTableID(tableID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
