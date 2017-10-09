package com.emmanuel.bookclub.models;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemberTest {
    private LocalDateTime currentRegDate = LocalDateTime.now();
    private Member student1 = new Student("SU17200", "EmmaTope", currentRegDate, "Cohort 23");
    private Member student2 = new Student("SU17201", "Shaibu", currentRegDate.plusHours(2), "Cohort 23");
    private Member staff1 = new Staff("SF17201", "Ayobami", currentRegDate.plusHours(10), 2000.00, 40);
    private Member staff2 = new Staff("SF17011", "Roseline", "mail@gmail.com",
            "Ilupeju Lagos", currentRegDate.plusHours(15), Gender.FEMALE, 1000.00, 30);


    @Test
    public void testEquals() throws Exception {
        assertEquals(staff1.equals(staff2), false);
    }
    @Test
    public void testCompareTo() throws Exception {
        assertTrue(staff2.compareTo(staff1) ==  1);
        assertTrue(staff1.compareTo(student1) == -1);
        assertTrue(student2.compareTo(staff2) == 1);
    }

    @Test
    public void testGetIdNumber() throws Exception {
        assertEquals("SU17200", student1.getIdNumber());
    }

    @Test
    public void testSetIdNumber() throws Exception {
        student2.setIdNumber("SU17199");
        assertEquals("SU17199", student2.getIdNumber());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("EmmaTope", student1.getName());
    }

    @Test
    public void testSetName() throws Exception {
        student1.setName("Emmanuel");
        assertEquals("Emmanuel", student1.getName());
    }

    @Test
    public void testGetEmail() throws Exception {
        assertEquals("mail@gmail.com", staff2.getEmail());
    }

    @Test
    public void testSetEmail() throws Exception {
        staff2.setEmail("hello@gmail.com");
        assertEquals("hello@gmail.com", staff2.getEmail());
    }

    @Test
    public void testGetAddress() throws Exception {
        assertEquals("Ilupeju Lagos", staff2.getAddress());
    }

    @Test
    public void testSetAddress() throws Exception {
        staff2.setAddress("Yaba Lagos");
        assertEquals("Yaba Lagos", staff2.getAddress());
    }

    @Test
    public void testGetRegistrationDate() throws Exception {
        assertEquals(currentRegDate.plusHours(15), staff2.getRegistrationDate());
    }

    @Test
    public void testSetRegistrationDate() throws Exception {
        staff2.setRegistrationDate(currentRegDate.plusHours(19));
        assertEquals(currentRegDate.plusHours(19), staff2.getRegistrationDate());
    }

    @Test
    public void testGetGender() throws Exception {
        assertEquals(Gender.FEMALE, staff2.getGender());
    }

    @Test
    public void testSetGender() throws Exception {
        staff2.setGender(Gender.MALE);
        assertEquals(Gender.MALE, staff2.getGender());
    }
}
