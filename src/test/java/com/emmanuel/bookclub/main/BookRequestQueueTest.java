package com.emmanuel.bookclub.main;

import com.emmanuel.bookclub.models.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BookRequestQueueTest {
    private LocalDateTime currentRegDate = LocalDateTime.now();
    private Member student1 = new Student("SU17200", "EmmaTope", currentRegDate, "Cohort 23");
    private Member student2 = new Student("SU17201", "Shaibu", currentRegDate.plusHours(2), "Cohort 23");
    private Member staff1 = new Staff("SF17201", "Ayobami", currentRegDate.plusHours(10), 2000.00, 40);
    private Member staff2 = new Staff("SF17011", "Roseline", "mail@gmail.com",
            "Ilupeju Lagos", currentRegDate.plusHours(15), Gender.FEMALE, 1000.00, 30);

    private Book book1 = new Book("There was country", "Achebe", "1234567", 3);
    private Book book2 = new Book("General Mathematics", "G.U Garba", "346tyu", 1);
    private Book book3 = new Book("Jero Play", "Wole Soyinka", "34ert", 6);

    private BookRequestQueue bookRequestQueue = new BookRequestQueue();

    @Test
    public void testAddMemberToQueue() {
        bookRequestQueue.addMemberToQueue(book1,staff1);
        bookRequestQueue.addMemberToQueue(book2,staff2);
        bookRequestQueue.addMemberToQueue(book2,staff1);

        assertTrue(bookRequestQueue.checkQueueForMember(book1,staff1));
        assertTrue(bookRequestQueue.checkQueueForMember(book2,staff1));
        assertFalse(bookRequestQueue.checkQueueForMember(book1,staff2));
    }

    @Test
    public void testCheckQueueForRequestedBook() {
        bookRequestQueue.addMemberToQueue(book1,student1);

        assertTrue(bookRequestQueue.checkQueueForRequestedBook(book1));
        assertFalse(bookRequestQueue.checkQueueForRequestedBook(book3));
    }

    @Test
    public void testCheckBookQueueEmpty() {
        bookRequestQueue.addMemberToQueue(book1,student1);
        bookRequestQueue.getEligibleMemberToBorrowBook(book1);
        assertTrue(bookRequestQueue.checkBookQueueEmpty(book1));

        bookRequestQueue.addMemberToQueue(book1,student2);
        assertFalse(bookRequestQueue.checkBookQueueEmpty(book1));
    }

    @Test
    public void testGetEligibleMemberToBorrowBook() {
        bookRequestQueue.addMemberToQueue(book1,student1);
        bookRequestQueue.addMemberToQueue(book1,student2);
        bookRequestQueue.addMemberToQueue(book1,staff1);

        Member eligibleMember = bookRequestQueue.getEligibleMemberToBorrowBook(book1);
        assertFalse(eligibleMember.equals(student1));
        assertTrue(eligibleMember.equals(staff1));

        Member eligibleMember2 = bookRequestQueue.getEligibleMemberToBorrowBook(book1);
        assertFalse(eligibleMember2.equals(student2));
        assertTrue(eligibleMember2.equals(student1));
    }

    @Test
    public void testGetQueueSize() {
        bookRequestQueue.addMemberToQueue(book1,student1);
        bookRequestQueue.addMemberToQueue(book1,student2);
        bookRequestQueue.addMemberToQueue(book2,staff1);

        assertEquals(2, bookRequestQueue.getQueueSize(book1));
        assertNotEquals(2, bookRequestQueue.getQueueSize(book2));
    }
}
