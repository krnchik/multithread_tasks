package com.krnchik.task2;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String author;
    private final String name;
    /*If book is being read only in the reading room,
     then valuable - true.*/
    private final boolean valuable;
    private volatile boolean available = true;
    private User user = null;

    public Book(String author, String name, boolean valuable) {
        this.author = author;
        this.name = name;
        this.valuable = valuable;
    }

    public synchronized boolean hold(User user) {
        if (!available)
            return false;
        this.user = user;
        available = false;
        System.out.println(user.getName() + " взял книгу " + author + " " + name);
        return true;
    }

    public synchronized boolean release(User user) {
        if (!user.equals(this.user))
            return false;
        this.user = null;
        available = true;
        System.out.println(user.getName() + " отдал книгу " + author + " " + name);
        return true;
    }

    @Override
    public int compareTo(Book o) {
        if (author.compareTo(o.author) > 0) {
            return 1;
        } else if (author.compareTo(o.author) < 0) {
            return -1;
        }
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name);
    }

    public boolean isValuable() {
        return valuable;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return author + " " + name;
    }
}
