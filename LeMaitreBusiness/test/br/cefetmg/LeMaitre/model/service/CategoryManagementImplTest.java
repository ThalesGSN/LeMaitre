/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Category;
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
public class CategoryManagementImplTest {
    
    public CategoryManagementImplTest() {
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
     * Test of categoryInsert method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryInsert() throws Exception {
        System.out.println("categoryInsert");
        Category category = null;
        CategoryManagementImpl instance = null;
        Integer expResult = null;
        Integer result = instance.categoryInsert(category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdate() throws Exception {
        System.out.println("categoryUpdate");
        Category category = null;
        CategoryManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.categoryUpdate(category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoryRemove method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryRemove() throws Exception {
        System.out.println("categoryRemove");
        Integer categoryID = null;
        CategoryManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.categoryRemove(categoryID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryByID method, of class CategoryManagementImpl.
     */
    @Test
    public void testGetCategoryByID() throws Exception {
        System.out.println("getCategoryByID");
        Integer categoryID = null;
        CategoryManagementImpl instance = null;
        Category expResult = null;
        Category result = instance.getCategoryByID(categoryID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of thisCategoryIDExists method, of class CategoryManagementImpl.
     */
    @Test
    public void testThisCategoryIDExists() throws Exception {
        System.out.println("thisCategoryIDExists");
        Integer categoryID = null;
        CategoryManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.thisCategoryIDExists(categoryID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAllCategories method, of class CategoryManagementImpl.
     */
    @Test
    public void testListAllCategories() throws Exception {
        System.out.println("listAllCategories");
        CategoryManagementImpl instance = null;
        List<Category> expResult = null;
        List<Category> result = instance.listAllCategories();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
