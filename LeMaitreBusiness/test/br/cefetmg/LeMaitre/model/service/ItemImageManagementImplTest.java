/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;
import br.cefetmg.LeMaitre.model.dao.ItemImageDAOImpl;
import br.cefetmg.LeMaitre.model.domain.ItemImage;
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
 * @author Breno Mariz
 */
public class ItemImageManagementImplTest {
    private ItemImage itemImage;
    
    private ItemImageManagement itemImageManagement;
    
    private Long codImage;
    
    private Integer codItem;
    
    public ItemImageManagementImplTest() {
        
        itemImage = new ItemImage();
        itemImageManagement = new ItemImageManagementImpl(ItemImageDAOImpl.getInstance());
        
    }
    
    
    @Before
    public void setUp() {
        codImage = new Long(-1);
        codItem = -1;
    }

    @After
    public void tearDown() {
        try {
            if (codImage != -1 || codItem != -1) {
                itemImageManagement.itemImageRemove(codItem, codImage);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to itemImage table after test");
        }
    }
    
    /**
     * Test of itemImageInsert method, of class ImageManagementImpl.
     */
    @Test
    public void testItemImageInsert() {
        try {
            itemImageManagement.itemImageInsert(itemImage);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct ItemImage");
        }
        System.out.println("Passed itemImageInsert test");
    }

    /**
     * Test of itemImageUpdate method, of class ImageManagementImpl.
     */
    @Test
    public void testItemImageInsertNull() {
        try {
            itemImage = null;
            itemImageManagement.itemImageInsert(itemImage);
            fail("Failed to catch exception when inserting null itemImage");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testItemImageInsertNull test");
        }
    }


    /**
     * Test of itemImageRemove method, of class ImageManagementImpl.
     */
    @Test
    public void testImageRemove() {
        try {
            itemImageManagement.itemImageInsert(itemImage);
            itemImageManagement.itemImageRemove(codItem, codImage);
            codImage = new Long(-1);
            System.out.println("Correctly removed itemImage");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct itemImage");
        }
    }
    
    
    @Test
    public void testGetImagesByItemId() {
        try {
            
            itemImageManagement.itemImageInsert(itemImage);
            List newItemImage = itemImageManagement.getImagesByItemID(codItem);
            if (!Objects.equals(newItemImage.get(1), codItem)) {
                fail("Failed to retrieve correct image");
            }
            System.out.println("Correctly retrieved image");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct image");
        }
    }
    
}
