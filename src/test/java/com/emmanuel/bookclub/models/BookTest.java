package com.emmanuel.bookclub.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import static org.junit.Assert.*;

public class BookTest {
    private Book book = new Book("There was country", "Achebe", "1234567", 3);

    @Test
    public void testGetTitle() throws Exception {
        assertEquals("There was country", book.getTitle());
    }

    @Test
    public void testSetTitle() throws Exception {
        book.setTitle("There was a country");
        assertEquals("There was a country", book.getTitle());
    }

    @Test
    public void testGetIsbn() throws Exception {
        assertNotNull(book.getIsbn());
    }

    @Test
    public void testSetIsbn() throws Exception {
        book.setIsbn("235yu");
        assertEquals("235yu", book.getIsbn());
    }

    @Test
    public void testGetAuthor() throws Exception {
        assertEquals("Achebe", book.getAuthor());
    }

    @Test
    public void testSetAuthor() throws Exception {
        book.setAuthor("Chinua Achebe");
        assertEquals("Chinua Achebe", book.getAuthor());
    }

    @Test
    public void testGetNumberOfCopies() throws Exception {
        assertEquals(3, book.getNumberOfCopies());
    }

    @Test
    public void testSetNumberOfCopies() throws Exception {
        book.setNumberOfCopies(2);
        assertEquals(2, book.getNumberOfCopies());
    }

    @Test
    public void testGetNumberOfBorrowedCopies() throws Exception {
        assertEquals(0, book.getBorrowCount());
    }

    @Test
    public void testSetNumberOfBorrowedCopies() throws Exception {
        book.setBorrowCount(50);
        assertEquals(0, book.getBorrowCount());
        book.setBorrowCount(2);
        assertEquals(2, book.getBorrowCount());
    }

    @Test
    public void testIncrementBookCopies() throws Exception {
        book.incrementBookCopies(4);
        assertEquals(7,book.getNumberOfCopies());
    }

    @Test
    public void testDecrementBookCopies() throws Exception {
        book.incrementBookCopies(4);
        int num = book.getNumberOfCopies();
        book.decrementBookCopies(4);
        assertEquals(3, (num - 4));
    }

    @Test
    public void testIncrementBorrowedBookCopies() throws Exception {
        book.incrementBorrowCount(4);
        assertEquals(0,book.getBorrowCount());

        book.incrementBorrowCount(1);
        assertEquals(1,book.getBorrowCount());
    }

    @Test
    public void testDecrementBorrowedBookCopies() throws Exception {
        book.incrementBorrowCount(3);
        int num = book.getBorrowCount();

        book.decrementBorrowCount(2);
        assertEquals(1, (num - 2));
    }

}
