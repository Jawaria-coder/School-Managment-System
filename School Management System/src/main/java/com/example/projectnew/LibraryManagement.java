package com.example.projectnew;

import java.util.ArrayList;

public class LibraryManagement {
    ArrayList<Book> bookList;
    StudentManagement studentManagement;
    int capacity = 100;

    public LibraryManagement() {
        bookList = new ArrayList<>();
        bookList.add(new Book("Math Edition 5", "Prof. Yasir", 12, true));
        bookList.add(new Book("Science Edition 6", "Prof. Hussain", 13, true));
        bookList.add(new Book("Geography Edition 7", "Prof. Maryam", 14, true));
        bookList.add(new Book("History Edition 5", "Prof. Ali", 15, true));
        bookList.add(new Book("English Edition 6", "Prof. Aliya", 16, true));
        bookList.add(new Book("Urdu Edition 8", "Prof. Isha", 17, true));
        this.studentManagement = new StudentManagement();
    }
    public String addBook(Book book) {
        if (bookList.size() < capacity) {
            bookList.add(book);
            return "Book added successfully";

        } else {
            return "Library is full";
        }
    }

    public boolean checkoutBook(int ISBN, int id) {
        studentManagement.getStudentById(id);
        for (Book book : bookList) {
            if (book.getISBN() == ISBN) {
                book.setAvailabilty(false);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int ISBN) {
        for (Book book : bookList) {
            if (book.getISBN() == ISBN) {
                book.setAvailabilty(true);
                return true;
            }
        }
        return false;
    }
    public String displayAvailableBooks() {
        String result = "";

        for (Book book : bookList) {
            if (book.getAvailabilty()) {
                result += "Title: " + book.getTitle() + "\n";
                result += "Author: " + book.getAuthor() + "\n";
                result += "ISBN: " + book.getISBN() + "\n\n";
            }
        }

        return result;
    }
    public String displayCheckedOutBooks() {
        String result = "";

        for (Book book : bookList) {
            if (!book.getAvailabilty()) {
                result += "Title: " + book.getTitle() + "\n";
                result += "Author: " + book.getAuthor() + "\n";
                result += "ISBN: " + book.getISBN() + "\n\n";
            }
        }
        return result;
    }
}
