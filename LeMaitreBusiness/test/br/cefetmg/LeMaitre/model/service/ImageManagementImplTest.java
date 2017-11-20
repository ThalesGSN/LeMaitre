/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thales
 */
public class ImageManagementImplTest {
    
    public ImageManagementImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of imageInsert method, of class ImageManagementImpl.
     */
    @Test
    public void testImageInsert() throws Exception {
        System.out.println("imageInsert");
        Image image = null;
        ImageManagementImpl instance = null;
        Long expResult = null;
        Long result = instance.imageInsert(image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of imageUpdate method, of class ImageManagementImpl.
     */
    @Test
    public void testImageUpdate() throws Exception {
        System.out.println("imageUpdate");
        Image image = null;
        ImageManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.imageUpdate(image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of imageRemove method, of class ImageManagementImpl.
     */
    @Test
    public void testImageRemove() throws Exception {
        System.out.println("imageRemove");
        Long imageID = null;
        ImageManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.imageRemove(imageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImageByID method, of class ImageManagementImpl.
     */
    @Test
    public void testGetImageByID() throws Exception {
        System.out.println("getImageByID");
        Long imageID = null;
        ImageManagementImpl instance = null;
        Image expResult = null;
        Image result = instance.getImageByID(imageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsThisImageID method, of class ImageManagementImpl.
     */
    @Test
    public void testContainsThisImageID() throws Exception {
        System.out.println("containsThisImageID");
        Long imageID = null;
        ImageManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.containsThisImageID(imageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
