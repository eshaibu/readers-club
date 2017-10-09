package com.emmanuel.bookclub.models;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTest {
    private LocalDateTime currentRegDate = LocalDateTime.now();
    private Member student1 = new Student("SU17200", "EmmaTope", currentRegDate, "Cohort 23");
    private Student student2 = new Student("SU17201", "Shaibu", currentRegDate.plusHours(2), "Cohort 22", "Android");

    @Test
    public void testStaffInstance() {
        assertTrue(student1 instanceof Member);
        assertTrue(student1 instanceof Student);
        assertTrue(student2 instanceof Student);
    }

    @Test
    public void testGetLevel() throws Exception {
        assertEquals("Cohort 22", student2.getLevel());
    }

    @Test
    public void testSetLevel() throws Exception {
        student2.setLevel("Cohort 23");
        assertEquals("Cohort 23", student2.getLevel());
    }

    @Test
    public void testGetSubjectOfInterest() throws Exception {
        assertEquals("Android", student2.getSubjectOfInterest());
    }

    @Test
    public void testSetSubjectOfInterest() throws Exception {
        student2.setSubjectOfInterest("Android Java");
        assertEquals("Android Java", student2.getSubjectOfInterest());
    }
}
