/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
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
public class ItemManagementImplTest {
    
    private Item item;
    private Long codItem;
    private double vlrPrice;
    private String nomItem;
    private String desItem;
    private boolean isAvailable;
    private Long codCategory;
    private ItemManagement itemManagement;

    
    public ItemManagementImplTest() {
        codItem = new Long(1);
        vlrPrice = 1.0;
        nomItem = new String("Brócolis");
        desItem = new String("Verde e crocante");
        isAvailable = true;
        codCategory = new Long(1);

        item = new Item(codItem, vlrPrice, nomItem, desItem, isAvailable, codCategory);
        itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
    }

    
    @Before
    public void setUp() {
        codItem = -1L;
        nomItem = new String("Brócolis");
        desItem = new String("Verde e crocante");
        item.setNomItem(nomItem);
        item.setDesItem(desItem);
        item.setCodItem(null);
    }
    
    @After
    public void tearDown() {
        try {
            if (codItem != -1L) {
                itemManagement.itemRemove(codItem);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove item after test");
        }
    }

    /**
     * Test of itemInsert method, of class ItemManagementImpl.
     */
    @Test
    public void testItemInsert() throws Exception {
        try {
            codItem = itemManagement.itemInsert(item);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert item");
        }
        System.out.println("Passed testItemInsert test");
    }
    
    /**
     * Test of itemInsertNull method, of class EmployeeManagementImpl.
     */
    @Test
    public void testItemInsertNull() {
        try {
            item = null;
            codItem = itemManagement.itemInsert(item);
            fail("Failed to catch exception when inserting null item");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemInsertNull test");

        }
    }   
    
    @Test
    public void testItemInsertIsAvailable() {
        try {
            isAvailable = true;
            item.setIsAvaliable(isAvailable);
            codItem = itemManagement.itemInsert(item);
            System.out.println("Passed testItemInsertIsAvailable");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to catch exception when inserting invalid boolean");
        }
    }

    /**
     * Test of itemUpdate method, of class ItemManagementImpl.
     */
    @Test
    public void testItemUpdate() throws Exception {
        try {
            codItem = itemManagement.itemInsert(item);
            item.setCodItem(codItem);
            itemManagement.itemUpdate(item);
            System.out.println("Passed testItemUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct item");
        }
    }
    
    @Test
    public void testItemUpdateNullId() {
        try {
            codItem = itemManagement.itemInsert(item);
            itemManagement.itemUpdate(item);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemUpdateNullId test");
        }
    }
    
    /**
     * Test of itemRemove method, of class ItemManagementImpl.
     */
    @Test
    public void testItemRemove() throws Exception {
        try {
            codItem = itemManagement.itemInsert(item);
            itemManagement.itemRemove(codItem);
            codItem = -1L;
            System.out.println("Correctly removed item");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct item");
        }
    }

    @Test
    public void testItemInsertNullnomName() {
        try {
            item.setNomItem(null);
            itemManagement.itemInsert(item);
            fail("Failed to catch exception when inserting  null NomItem");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemInsertNullnomName test");
        }
    }
    
    @Test
    public void testItemUpdateNullnomName() {
        try {
            codItem = itemManagement.itemInsert(item);
            item.setNomItem(null);
            item.setCodItem(codItem);
            itemManagement.itemUpdate(item);
            fail("Failed to catch exception when updating  null NomItem");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemInsertNullnomName test");
        }
    }
    
    @Test
    public void testItemInsertNulldesItem() {
        try {
            item.setDesItem(null);
            itemManagement.itemInsert(item);
            fail("Failed to catch exception when inserting  null desItem");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemInsertNulldesItem test");
        }
    }

    @Test
    public void testItemUpdateNulldesItem() {
        try {
            codItem = itemManagement.itemInsert(item);
            item.setDesItem(desItem);
            item.setCodItem(codItem);
            itemManagement.itemUpdate(item);
            fail("Failed to catch exception when updating  null desItem");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemUpdatedesItem test");
        }
    }
    
    /**
     * Test of getItemByID method, of class ItemManagementImpl.
     */
    @Test
    public void testGetItemByID() throws Exception {
        try {
            codItem = itemManagement.itemInsert(item);
            Item newItem = itemManagement.getItemByID(codItem);
            if (newItem.getCodItem() != codItem) {
                fail("Failed to retrieve correct item");
            }
            System.out.println("Correctly retrieved item");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct item");

        }
    }
    
    /**
     * Test of getAllItems method, of class ItemManagementImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        try {
            itemManagement.itemInsert(item);
            List list = itemManagement.getAllItems();
            if (list.isEmpty()) {
                fail("Failed to retrieve correct itens");
            }
            System.out.println("Correctly retrieved itens");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct itens");
        }
    }
    
}
