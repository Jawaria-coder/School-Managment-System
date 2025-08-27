package com.example.projectnew;
public class Book
{
    private String title;
    private String author;
    private int ISBN;
    private boolean availabilty;

    public Book(String title, String author, int ISBN, boolean availabilty)
    {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        setAvailabilty(availabilty);
    }

    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public int getISBN()
    {
        return ISBN;
    }

    public void setAvailabilty(boolean availabilty)
    {
        this.availabilty=availabilty;
    }
    public boolean getAvailabilty()
    {
        return availabilty;
    }
}

