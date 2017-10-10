package com.emmanuel.bookclub.models;

public class Book {
    private String title, author, isbn;
    private int numberOfCopies, borrowCount;

    /**
     * Constructor for book class
     * @param title title of book
     * @param author author of book
     * @param isbn book isbn
     * @param numberOfCopies number of book copies
     */
    public Book(String title, String author, String isbn, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.numberOfCopies = numberOfCopies;
        this.borrowCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        if(borrowCount > 0 && ((this.borrowCount + borrowCount) <= this.numberOfCopies)) {
            this.borrowCount = borrowCount;
        }
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void incrementBookCopies(int numberOfCopiesToAdd){
        if(numberOfCopiesToAdd > 0) {
            this.numberOfCopies += numberOfCopiesToAdd;
        }
    }

    public void decrementBookCopies(int numberOfCopiesToDecrement){
        if(numberOfCopiesToDecrement < 0 || (numberOfCopies - numberOfCopiesToDecrement < 0)){
            return;
        }
        this.numberOfCopies -= numberOfCopiesToDecrement;
    }

    public void incrementBorrowCount(int numberOfCopiesToBorrow){
        if(numberOfCopiesToBorrow > 0 && ((this.borrowCount + numberOfCopiesToBorrow) <= this.numberOfCopies)) {
            this.borrowCount += numberOfCopiesToBorrow;
        }
    }

    public void decrementBorrowCount(int numberOfCopiesToReturn){
        if(numberOfCopiesToReturn > 0 && (this.borrowCount >= numberOfCopiesToReturn)){
            this.borrowCount -= numberOfCopiesToReturn;
        }
    }
}
