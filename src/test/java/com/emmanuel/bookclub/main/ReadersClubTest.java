package com.emmanuel.bookclub.main;

import com.emmanuel.bookclub.models.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReadersClubTest {
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
    //    List<Book> bookList = new ArrayList<Book>(Arrays.asList(book1, book2));
    private ReadersClub readersClub = new ReadersClub(bookRequestQueue);

    @Test
    public void testAddBooksToClub() throws Exception {
        readersClub.addBooksToClub(new Book[] { book1, book2 });
        assertTrue(book1.getNumberOfCopies() == 3);
        assertTrue(book2.getNumberOfCopies() == 1);
        assertTrue(readersClub.readersClubBooks.size() == 2);

        readersClub.addBookToClub(book1);
        assertTrue(book1.getNumberOfCopies() == 4);
        assertTrue(readersClub.readersClubBooks.size() == 2);

        readersClub.readersClubBooks.clear();
        assertFalse(readersClub.readersClubBooks.size() == 2);
    }

    @Test
    public void testAddBookToClub() throws Exception {
        readersClub.addBookToClub(book1);
        assertTrue(book1.getNumberOfCopies() == 3);
        assertTrue(readersClub.readersClubBooks.size() == 1);

        readersClub.addBookToClub(book1);
        assertTrue(book1.getNumberOfCopies() == 4);
        assertTrue(readersClub.readersClubBooks.size() == 1);

        readersClub.addBookToClub(book2);
        assertTrue(readersClub.readersClubBooks.size() == 2);
    }

    @Test
    public void testAddMembersToClub() throws Exception {
        Member[] memberArray = {student1, staff1};

        readersClub.addMembersToClub(memberArray);
        assertEquals(readersClub.readersClubMembers.size(),2);
    }

    @Test
    public void testAddMemberToClub() throws Exception {
        readersClub.addMemberToClub(staff2);
        assertEquals(readersClub.readersClubMembers.size(),1);

        readersClub.addMemberToClub(staff2);
        assertEquals(readersClub.readersClubMembers.size(),1);

        readersClub.addMemberToClub(student2);
        assertEquals(readersClub.readersClubMembers.size(),2);
    }

    @Test
    public void testCheckMemberExistInClub() throws Exception {
        assertFalse(readersClub.checkMemberExistInClub(staff1));
        assertFalse(readersClub.checkMemberExistInClub(student1));

        readersClub.addMembersToClub(new Member[] {staff1, student1});
        assertTrue(readersClub.checkMemberExistInClub(staff1));
        assertTrue(readersClub.checkMemberExistInClub(student1));
    }

    @Test
    public void testCheckBookExistInClub() throws Exception {
        assertFalse(readersClub.checkBookExistInClub(book3));
        assertFalse(readersClub.checkBookExistInClub(book2));

        readersClub.addBooksToClub(new Book[] {book3, book2});
        assertTrue(readersClub.checkBookExistInClub(book3));
        assertTrue(readersClub.checkBookExistInClub(book2));
    }

    @Test
    public void testRequestForBook() throws Exception {
        readersClub.addBooksToClub(new Book[] {book3, book2});
        readersClub.addMembersToClub(new Member[] {staff1, student1});
        readersClub.requestForBook(book3,staff1);
        readersClub.requestForBook(book3,student1);
        readersClub.requestForBook(book2,staff1);

        assertTrue(bookRequestQueue.checkQueueForMember(book3,staff1));
        assertTrue(bookRequestQueue.checkQueueForMember(book3,student1));
        assertFalse(bookRequestQueue.checkQueueForMember(book2,staff2));
        assertFalse(bookRequestQueue.checkQueueForMember(book2,student1));
    }

    @Test
    public void testCheckBookAvailableForLending() {
        book1.setBorrowCount(1);
        book3.setBorrowCount(2);
        readersClub.addBooksToClub(new Book[] {book2, book3});

        assertTrue(readersClub.checkBookAvailableForLending(book3));
        assertFalse(readersClub.checkBookAvailableForLending(book1));
    }

    @Test
    public void testGiveBookToEligibleMember() {
        readersClub.addBooksToClub(new Book[] {book1, book2});
        readersClub.addMembersToClub(new Member[] {staff1, student1, student2});
        readersClub.requestForBook(book1,staff1);
        readersClub.requestForBook(book1,student1);
        readersClub.requestForBook(book2,student1);
        readersClub.requestForBook(book2,student2);

        Member memberGivenBook = readersClub.giveBookToEligibleMember(book1);
        Member memberGivenBook2 = readersClub.giveBookToEligibleMember(book2);

        assertFalse(memberGivenBook.equals(student1));
        assertTrue(memberGivenBook.equals(staff1));
        assertTrue(readersClub.giveBookToEligibleMember(book1).equals(student1));
        assertFalse(memberGivenBook2.equals(student2));
        assertTrue(memberGivenBook2.equals(student1));

        // checks for situation book not available to be given to member if number of copies available in club library less than 1
        // takes into consideration if boot borrowed count equals to book copy
        assertEquals(null, readersClub.giveBookToEligibleMember(book2));
        assertEquals(1, book2.getBorrowCount());
    }

    @Test
    public void testReturnBookToClub() {
        readersClub.addBooksToClub(new Book[] {book1});
        readersClub.addMembersToClub(new Member[] {staff2, student1});
        readersClub.requestForBook(book1,staff2);
        readersClub.requestForBook(book1,student1);

        assertEquals(0,book1.getBorrowCount());
        readersClub.giveBookToEligibleMember(book1);
        assertEquals(1,book1.getBorrowCount());
        readersClub.giveBookToEligibleMember(book1);
        assertEquals(2,book1.getBorrowCount());

        readersClub.returnBookToClub(staff2,book1);
        assertEquals(1, book1.getBorrowCount());
        readersClub.returnBookToClub(student1,book1);
        assertTrue(book1.getBorrowCount() == 0);
    }
}
