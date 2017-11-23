/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.dao.ImageDAOImpl;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.Objects;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Breno Mariz
 */
public class ImageManagementImplTest {
    
    private Image image;
    
    private Long codImage;
    
    private String location;
    
    private ImageManagement imageManagement;
    
    public ImageManagementImplTest() {
        image = new Image();
        imageManagement = new ImageManagementImpl(ImageDAOImpl.getInstance());
    }
    
    @Before
    public void setUp() {
        codImage = new Long(-1);
        location = null;
        image.setLocation(location);
    }
    
    @After
    public void tearDown() {
        try {
            if (codImage != -1) {
                imageManagement.imageRemove(codImage);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to image table after test");
        }
    }

    /**
     * Test of imageInsert method, of class ImageManagementImpl.
     */
    @Test
    public void testImageInsert() {
        try {
            codImage = imageManagement.imageInsert(image);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct table");
        }
        System.out.println("Passed testTableInsert test");
    }

    /**
     * Test of imageUpdate method, of class ImageManagementImpl.
     */
    @Test
    public void testImageInsertNull() {
        try {
            image = null;
            codImage = imageManagement.imageInsert(image);
            fail("Failed to catch exception when inserting null image");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testImageInsertNull test");

        }
    }
    
    
    @Test
    public void testImageLocationNull() {
        try {
        image.setLocation(null);
        codImage = imageManagement.imageInsert(image);
        fail ("Failed to catch exception with null LocationImage");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testImageInsertLocationNull test");
        }
    }
    
    @Test
    public void testImageInsertEmptyLocation() {
        try {
            image.setLocation("");
            codImage = imageManagement.imageInsert(image);
            fail("Failed to catch exception with empty LocationImage");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testImageInsertLocationEmpty test");
        }
    }

    
    @Test
    public void testImageUpdate() {
        try {
            codImage = imageManagement.imageInsert(image);
            image.setCodImage(codImage);
            imageManagement.imageUpdate(image);
            System.out.println("Passed testImageUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct image");
        }
    }
    
    
    @Test
    public void testImageUpdateNullId() {
        try {
            codImage = imageManagement.imageInsert(image);
            imageManagement.imageUpdate(image);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testImageUpdateNullId test");
        }
    }
    
    
    /**
     * Test of imageRemove method, of class ImageManagementImpl.
     */
    @Test
    public void testImageRemove() {
        try {
            codImage = imageManagement.imageInsert(image);
            imageManagement.imageRemove(codImage);
            codImage = new Long(-1);
            System.out.println("Correctly removed image");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct image");
        }
    }

    /**
     * Test of getImageByID method, of class ImageManagementImpl.
     */
    @Test
    public void testGetImageByID() {
        try {
            codImage = imageManagement.imageInsert(image);
            Image newImage = imageManagement.getImageByID(codImage);
            if (!Objects.equals(newImage.getCodImage(), codImage)) {
                fail("Failed to retrieve correct image");
            }
            System.out.println("Correctly retrieved image");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct image");

        }
    }
    
}
