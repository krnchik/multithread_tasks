package com.krnchik.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name;
    private final List<Book> books;

    public User(String name) {
        this.name = name;
        books = new ArrayList<>();
    }

    public boolean hasBooks(List<Book> returnedBooks) {
        return books.containsAll(returnedBooks);
    }

    public void takeBooks(List<Book> takenBooks) {
        books.addAll(takenBooks);
    }

    public void giveAwayBooks(List<Book> returnedBooks) {
        books.removeAll(returnedBooks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
