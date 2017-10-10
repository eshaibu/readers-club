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

//    List<Book> bookList = new ArrayList<Book>(Arrays.asList(book1, book2));
    ReadersClub readersClub = new ReadersClub();

    @Test
    public void testAddBooksToClub() {
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
    public void testAddBookToClub() {
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
    public void testAddMembersToClub() {
        Member[] memberArray = {student1, staff1};
        readersClub.addMembersToClub(memberArray);

        assertEquals(readersClub.readersClubMembers.size(),2);
    }

    @Test
    public void testAddMemberToClub() {
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
}
