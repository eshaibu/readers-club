package com.emmanuel.bookclub.main;

import com.emmanuel.bookclub.models.Book;
import com.emmanuel.bookclub.models.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadersClub {
    List<Book> readersClubBooks;
    Map<Member, List<Book>> readersClubMembers;
//    Map<String, List<Book>> readersClubBooks;

    public ReadersClub() {
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
}
