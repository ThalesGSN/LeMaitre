/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Reservation;
import java.sql.Time;
import java.util.Date;
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
public class ReservationManagementImplTest {
    
    public ReservationManagementImplTest() {
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
     * Test of reservationInsert method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationInsert() throws Exception {
        System.out.println("reservationInsert");
        Reservation reservation = null;
        ReservationManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.reservationInsert(reservation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reservationUpdate method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationUpdate() throws Exception {
        System.out.println("reservationUpdate");
        Reservation reservation = null;
        ReservationManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.reservationUpdate(reservation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reservationRemove method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationRemove() throws Exception {
        System.out.println("reservationRemove");
        Integer tableID = null;
        Date datReservation = null;
        Time hourReservation = null;
        ReservationManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.reservationRemove(tableID, datReservation, hourReservation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReservationByID method, of class ReservationManagementImpl.
     */
    @Test
    public void testGetReservationByID() throws Exception {
        System.out.println("getReservationByID");
        Integer tableID = null;
        Date datReservation = null;
        Time hourReservation = null;
        ReservationManagementImpl instance = null;
        Reservation expResult = null;
        Reservation result = instance.getReservationByID(tableID, datReservation, hourReservation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReservationsByTableID method, of class ReservationManagementImpl.
     */
    @Test
    public void testGetReservationsByTableID() throws Exception {
        System.out.println("getReservationsByTableID");
        Integer tableID = null;
        ReservationManagementImpl instance = null;
        List<Reservation> expResult = null;
        List<Reservation> result = instance.getReservationsByTableID(tableID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllReservations method, of class ReservationManagementImpl.
     */
    @Test
    public void testGetAllReservations() throws Exception {
        System.out.println("getAllReservations");
        ReservationManagementImpl instance = null;
        List<Reservation> expResult = null;
        List<Reservation> result = instance.getAllReservations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
