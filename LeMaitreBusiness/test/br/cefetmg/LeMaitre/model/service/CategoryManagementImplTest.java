/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.CategoryDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;
import java.util.Objects;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class CategoryManagementImplTest {
    private Integer codID;
    
    private final String nomCategory;
    
    private Category category;
    
    private final CategoryManagement categoryManagement;
    
    public CategoryManagementImplTest() {
        nomCategory = "404 TEST";
        categoryManagement = new CategoryManagementImpl(CategoryDAOImpl.getInstance());
        category = new Category();
    }
        
    @Before
    public void setUp() {
        codID = -1;
        category.setNomCategory(nomCategory);
        category.setSeqCategory(codID);
    }
    
    @After
    public void tearDown() {
        try {
            if (codID != -1L) {
                categoryManagement.categoryRemove(codID);
            }
        } catch (Exception ex) {
            System.out.println("Failed to remove category after test");
        }
    }

    /**
     * Test of categoryInsert method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryInsert() {
        try {
            codID = categoryManagement.categoryInsert(category);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct category");
        }
        System.out.println("Passed testCategoryInsert test");
    }
    
    /**
     * Test of categoryInsert method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryInsertNull() {
        try {
            category = null;
            codID = categoryManagement.categoryInsert(category);
            fail("Failed to catch exception when inserting null category");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryInsertNull test");
            
        }
    }
    
    /**
     * Test of categoryInsert method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryInsertNullNomCategory() {
        try {
            category.setNomCategory(null);
            codID = categoryManagement.categoryInsert(category);
            fail("Failed to catch exception when inserting null date");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryInsertNullDatUse test");
        }
        
    }
    
    @Test
    public void testCategoryInsertEmptyNomCategory() {
        try {
            category.setNomCategory("");
            codID = categoryManagement.categoryInsert(category);
            fail("Failed to catch exception when inserting Empty nomCategory");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryInsertEmptyNomCategory test");
        }
        
    }

    /**
     * Test of categoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdate() {
        try {
            codID = categoryManagement.categoryInsert(category);
            category.setSeqCategory(codID);
            categoryManagement.categoryUpdate(category);
            System.out.println("Passed testCategoryUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct category");
        }
    }
    
    /**
     * Test of categoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdateNullId() {
        try {
            codID = categoryManagement.categoryInsert(category);
            categoryManagement.categoryUpdate(category);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }
    
    /**
     * Test of categoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdateNullNomCategory() {
        try {
            codID = categoryManagement.categoryInsert(category);
            category.setSeqCategory(codID);
            category.setNomCategory(null);
            categoryManagement.categoryUpdate(category);
            fail("Failed to catch exception when updating null date");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullDatUse test");
        }
    }
    
    @Test
    public void testCategoryUpdateEmptyNomCategory() {
        try {
            codID = categoryManagement.categoryInsert(category);
            category.setSeqCategory(codID);
            category.setNomCategory("");
            categoryManagement.categoryUpdate(category);
            fail("Failed to catch exception when inserting Empty nomCategory");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryInsertEmptyNomCategory test");
        }
        
    }

    /**
     * Test of categoryRemove method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryRemove() {
        try {
            codID = categoryManagement.categoryInsert(category);
            categoryManagement.categoryRemove(codID);
            codID = -1;
            System.out.println("Correctly removed category");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct category");
        }
    }

    /**
     * Test of getCategoryByID method, of class CategoryManagementImpl.
     */
    @Test
    public void testGetCategoryByID() {
        try {
            codID = categoryManagement.categoryInsert(category);
            Category newCategory = categoryManagement.getCategoryByID(codID);
            if (!Objects.equals(newCategory.getSeqCategory(), codID)) {
                fail("Failed to retrieve correct category");
            }
            System.out.println("Correctly retrieved category");
        } catch (BusinessException | PersistenceException ex) {
            
            fail("Failed to retrieve correct category");
            
        }
    }

    /**
     * Test of listAll method, of class CategoryManagementImpl.
     */
    @Test
    public void testListAll() {
        try {
            codID = categoryManagement.categoryInsert(category);
            List list = categoryManagement.listAll();
            if (list.isEmpty()) {
                fail("Failed to retrieve correct category");
            }
            System.out.println("Correctly retrieved category");
        } catch (BusinessException | PersistenceException | NullPointerException ex) {
            fail("Failed to retrieve correct category");
        }
    }
}
