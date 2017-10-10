package com.emmanuel.bookclub.main;

import com.emmanuel.bookclub.models.Book;
import com.emmanuel.bookclub.models.Member;

import java.util.*;

public class ReadersClub {
    List<Book> readersClubBooks;
    Map<Member, List<Book>> readersClubMembers;
    BookRequestQueue bookRequestQueue;

    public ReadersClub(BookRequestQueue bookRequestQueue) {
        this.bookRequestQueue = bookRequestQueue;
        readersClubBooks = new ArrayList<Book>();
        readersClubMembers = new HashMap<Member, List<Book>>();
    }

    public ReadersClub(List<Book> readersClubBooks) {
        this.readersClubBooks = readersClubBooks;
        readersClubMembers = new HashMap<Member, List<Book>>();
    }

    public ReadersClub(List<Book> readersClubBooks, Map<Member, List<Book>> readersClubMembers) {
        this.readersClubBooks = readersClubBooks;
        this.readersClubMembers = readersClubMembers;
    }

    protected boolean checkBookExistInClub(Book book){
        return readersClubBooks.contains(book);
    }

    protected boolean checkMemberExistInClub(Member member){
        return readersClubMembers.containsKey(member);
    }

    public void addBooksToClub(Book[] books) {
        for(Book book : books){
            addBookToClub(book);
        }
    }

    public void addBookToClub(Book book) {
        if(!checkBookExistInClub(book)){
            readersClubBooks.add(book);
        } else {
            book.incrementBookCopies(1);
        }
    }

    public void addMembersToClub(Member[] members) {
        for(Member member : members){
            addMemberToClub(member);
        }
    }

    public void addMemberToClub(Member member) {
        if(!checkMemberExistInClub(member)){
            ArrayList memberBooksBorrowed = new ArrayList();
            readersClubMembers.put(member, memberBooksBorrowed);
        }
    }

    public void requestForBook(Book bookRequestedFor, Member memberRequesting) {
        if(checkBookExistInClub(bookRequestedFor) && checkMemberExistInClub(memberRequesting)) {
            bookRequestQueue.addMemberToQueue(bookRequestedFor, memberRequesting);
        }
    }

    protected boolean checkBookAvailableForLending(Book bookRequested){
        return checkBookExistInClub(bookRequested) && ((bookRequested.getNumberOfCopies() - bookRequested.getBorrowCount()) > 0);
    }

    public Member giveBookToEligibleMember(Book bookRequested){
        if(checkBookAvailableForLending(bookRequested)){
            Member memberWhoGetsBook = bookRequestQueue.getEligibleMemberToBorrowBook(bookRequested);
            bookRequested.incrementBorrowCount(1);
            updateListOfMemberBorrowedBooks(memberWhoGetsBook, bookRequested);
            return memberWhoGetsBook;
        }
        return null;
    }

    private void updateListOfMemberBorrowedBooks (Member memberWhoGetsBook, Book bookRequested ) {
        if(checkMemberExistInClub(memberWhoGetsBook)){
            List<Book> booksBorrowedByMember = readersClubMembers.get(memberWhoGetsBook);
            booksBorrowedByMember.add(bookRequested);
            readersClubMembers.put(memberWhoGetsBook, booksBorrowedByMember);
        } else {
            List<Book> booksBorrowedByMember = new ArrayList<Book>();
            booksBorrowedByMember.add(bookRequested);
            readersClubMembers.put(memberWhoGetsBook, booksBorrowedByMember);
        }
    }

    public void returnBookToClub(Member memberReturningBook, Book bookMemberIsReturning){
        List<Book> booksBorrowedByMember = readersClubMembers.get(memberReturningBook);

        if(booksBorrowedByMember.contains(bookMemberIsReturning)){
            bookMemberIsReturning.decrementBorrowCount(1);
            booksBorrowedByMember.remove(bookMemberIsReturning);
            readersClubMembers.put(memberReturningBook, booksBorrowedByMember);
        }
    }
}
