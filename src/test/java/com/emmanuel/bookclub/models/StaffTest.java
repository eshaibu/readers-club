package com.emmanuel.bookclub.models;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StaffTest {
    private LocalDateTime currentRegDate = LocalDateTime.now();
    private Member staff1 = new Staff("SF17201", "Ayobami", currentRegDate.plusHours(10), 2000.00, 40);
    private Staff staff2 = new Staff("SF17011", "Roseline", "mail@gmail.com",
            "Ilupeju Lagos", currentRegDate.plusHours(15), Gender.FEMALE, 1000.00, 30);

    @Test
    public void testStaffInstance() {
        assertTrue(staff1 instanceof Member);
        assertTrue(staff1 instanceof Staff);
        assertTrue(staff2 instanceof Staff);
    }

    @Test
    public void testGetSalary() throws Exception {
        assertEquals(1000.00, staff2.getSalary(), 0);
    }

    @Test
    public void testSetSalary() throws Exception {
        staff2.setSalary(3500.00);
        assertEquals(3500.00, staff2.getSalary(), 0.00);
        assertThat(staff2.getSalary(), is(3500.00));
    }

    @Test
    public void testGetWorkHour() throws Exception {
        assertEquals(30, staff2.getWorkHour());
    }

    @Test
    public void testSetWorkHour() throws Exception {
        staff2.setWorkHour(35);
        assertEquals(35, staff2.getWorkHour());
    }
}
