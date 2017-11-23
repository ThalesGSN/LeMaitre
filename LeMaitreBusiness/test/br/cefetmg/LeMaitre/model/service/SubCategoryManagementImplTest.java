/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;
import br.cefetmg.LeMaitre.model.dao.SubcategoryDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Subcategory;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Breno Mariz
 */
public class SubCategoryManagementImplTest {
    private Integer codSubCategory;
    
    private Integer codCategory;
    
    private final String nomSubCategory;
    
    private Subcategory subCategory;
    
    private final SubcategoryManagement subCategoryManagement;
    
    public SubCategoryManagementImplTest() {
        nomSubCategory = "404 TEST";
        subCategoryManagement = new SubcategoryManagementImpl(SubcategoryDAOImpl.getInstance());
        subCategory = new Subcategory();
    }

    @Before
    public void setUp() {
        codCategory = -1;
        codSubCategory = -1;   
        subCategory.setNomSubcategory(nomSubCategory);
        subCategory.setSeqCategory(codCategory);
        subCategory.setSeqSubcategory(codSubCategory);
    }

    @After
    public void tearDown() {
        try {
            if (codCategory != -1L || codSubCategory != -1L) {
                subCategoryManagement.subcategoryRemove(codCategory, codSubCategory);
            }
        } catch (Exception ex) {
            System.out.println("Failed to remove subCategory after test");
        }
    }
    
    @Test
    public void testCategoryInsert() {
        try {
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct subCategory");
        }
        System.out.println("Passed testCategoryInsert test");
    }

    /**
     * Test of subCategoryInsert method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryInsertNull() {
        try {
            subCategory = null;
            
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            fail("Failed to catch exception when inserting null subCategory");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testSubCategoryInsertNull test");
        }
    }

    /**
     * Test of subCategoryInsert method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryInsertNullNomCategory() {
        try {
            subCategory.setNomSubcategory(null);
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            fail("Failed to catch exception when inserting null date");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testSubCategoryInsertNullDatUse test");
        }

    }

    @Test
    public void testSubCategoryInsertEmptyNomCategory() {
        try {
            subCategory.setNomSubcategory("");
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            fail("Failed to catch exception when inserting Empty nomSubCategory");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryInsertEmptyNomSubCategory test");
        }

    }

    /**
     * Test of subCategoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdate() {
        try {
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            subCategory.setSeqSubcategory(codSubCategory);
            subCategoryManagement.subcategoryUpdate(subCategory);
            System.out.println("Passed testSubCategoryUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct subCategory");
        }
    }

    /**
     * Test of subCategoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdateNullId() {
        try {
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            subCategoryManagement.subcategoryUpdate(subCategory);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }

    /**
     * Test of subCategoryUpdate method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryUpdateNullNomCategory() {
        try {
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            subCategory.setSeqSubcategory(codSubCategory);
            subCategory.setNomSubcategory(null);
            subCategoryManagement.subcategoryUpdate(subCategory);
            fail("Failed to catch exception when updating null date");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullDatUse test");
        }
    }

    @Test
    public void testCategoryUpdateEmptyNomCategory() {
        try {
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            subCategory.setSeqSubcategory(codSubCategory);
            subCategory.setNomSubcategory("");
            subCategoryManagement.subcategoryUpdate(subCategory);
            fail("Failed to catch exception when inserting Empty nomSubCategory");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryInsertEmptyNomSubCategory test");
        }

    }

    /**
     * Test of subCategoryRemove method, of class CategoryManagementImpl.
     */
    @Test
    public void testCategoryRemove() {
        try {
            codCategory = subCategoryManagement.subcategoryInsert(subCategory);
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            subCategoryManagement.subcategoryRemove(codCategory, codSubCategory);
            codCategory = -1;
            codSubCategory = -1;
            System.out.println("Correctly removed subCategory");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct subCategory");
        }
    }

    /**
     * Test of getCategoryByID method, of class CategoryManagementImpl.
     */
    @Test
    public void testGetSubCategoryByID() {
        try {
            codCategory = subCategoryManagement.subcategoryInsert(subCategory);
            codSubCategory = subCategoryManagement.subcategoryInsert(subCategory);
            Subcategory newSubCategory = subCategoryManagement.getSubcategoryByID(codCategory, codSubCategory);
            if (!Objects.equals(newSubCategory.getSeqCategory(), codCategory) || !!Objects.equals(newSubCategory.getSeqSubcategory(), codSubCategory)) {
                fail("Failed to retrieve correct SubCategory");
            }
            System.out.println("Correctly retrieved SubCategory");
        } catch (BusinessException | PersistenceException ex) {

            fail("Failed to retrieve correct SubCategory");

        }
    }
  
}
