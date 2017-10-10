package com.emmanuel.bookclub.main;

import com.emmanuel.bookclub.models.Book;
import com.emmanuel.bookclub.models.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BookRequestQueue {
    private Map<Book, PriorityQueue<Member>> bookQueue;

    public BookRequestQueue() {
        bookQueue = new HashMap<Book, PriorityQueue<Member>>();
    }

    protected boolean checkQueueForRequestedBook(Book requestedBook){
        return bookQueue.containsKey(requestedBook);
    }

    protected boolean checkQueueForMember(Book book, Member member){
        return bookQueue.get(book).contains(member);
    }

    protected boolean checkBookQueueEmpty(Book book){
        return bookQueue.get(book).isEmpty();
    }

    protected Member getEligibleMemberToBorrowBook(Book requestedBook){
        return bookQueue.get(requestedBook).poll();
    }

    protected int getQueueSize(Book book){
        return bookQueue.get(book).size();
    }

    protected void addMemberToQueue(Book bookRequestedFor, Member memberRequesting){
        if(checkQueueForRequestedBook(bookRequestedFor)){
            if(!checkQueueForMember(bookRequestedFor, memberRequesting)){
                bookQueue.get(bookRequestedFor).add(memberRequesting);
            }
        } else {
            PriorityQueue<Member> newBookQueue = new PriorityQueue<Member>();
            newBookQueue.add(memberRequesting);
            bookQueue.put(bookRequestedFor, newBookQueue);
        }
    }
}
