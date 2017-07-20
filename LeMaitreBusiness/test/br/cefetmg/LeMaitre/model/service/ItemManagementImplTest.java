/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Item;
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
public class ItemManagementImplTest {
    
    public ItemManagementImplTest() {
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
     * Test of itemInsert method, of class ItemManagementImpl.
     */
    @Test
    public void testItemInsert() throws Exception {
        System.out.println("itemInsert");
        Item item = null;
        ItemManagementImpl instance = null;
        Integer expResult = null;
        Integer result = instance.itemInsert(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of itemUpdate method, of class ItemManagementImpl.
     */
    @Test
    public void testItemUpdate() throws Exception {
        System.out.println("itemUpdate");
        Item item = null;
        ItemManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.itemUpdate(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of itemRemove method, of class ItemManagementImpl.
     */
    @Test
    public void testItemRemove() throws Exception {
        System.out.println("itemRemove");
        Integer itemID = null;
        ItemManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.itemRemove(itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemByID method, of class ItemManagementImpl.
     */
    @Test
    public void testGetItemByID() throws Exception {
        System.out.println("getItemByID");
        Integer itemID = null;
        ItemManagementImpl instance = null;
        Item expResult = null;
        Item result = instance.getItemByID(itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of thisItemIDExists method, of class ItemManagementImpl.
     */
    @Test
    public void testThisItemIDExists() throws Exception {
        System.out.println("thisItemIDExists");
        Integer itemID = null;
        ItemManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.thisItemIDExists(itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItems method, of class ItemManagementImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        System.out.println("getAllItems");
        ItemManagementImpl instance = null;
        List<Item> expResult = null;
        List<Item> result = instance.getAllItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
