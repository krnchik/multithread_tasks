package com.krnchik.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Library {
    private final CopyOnWriteArrayList<Book> freeBooks;
    private final List<Book> booksOnHand;
    private final List<Book> booksReadingRoom;
    private final ExecutorService es;

    public Library(int countLibrarians, List<Book> freeBooks) {
        if (!isFreeBooks(freeBooks))
            throw new IllegalArgumentException();
        this.freeBooks = new CopyOnWriteArrayList<>(freeBooks);
        es = Executors.newFixedThreadPool(countLibrarians);
        booksOnHand = new ArrayList<>();
        booksReadingRoom = new ArrayList<>();
    }

    private boolean isFreeBooks(List<Book> freeBooks) {
        for (Book book : freeBooks) {
            if (!book.isAvailable())
                return false;
        }
        return true;
    }

    public synchronized boolean hasFreeBooks(List<Book> takenBooks) {
        return freeBooks.containsAll(takenBooks);
    }

    public synchronized void takeBooks(User user, List<Book> books) {
        for (Book book : freeBooks) {
            if (books.contains(book) && book.hold(user)) {
                takeBook(book);
            }
        }
        Collections.sort(booksOnHand);
        Collections.sort(booksReadingRoom);
        System.out.println("Свободные: " + freeBooks + "\n" +
                "Читальный зал: " + booksReadingRoom + "\n" +
                "На руках: " + booksOnHand + "\n");
    }

    private void takeBook(Book book) {
        freeBooks.remove(book);
        if (book.isValuable()) {
            booksReadingRoom.add(book);
        } else {
            booksOnHand.add(book);
        }
    }

    public synchronized void giveAwayBooks(User user, List<Book> books) {
        for (Book book : books) {
            giveAwayBook(user, book);
        }
        Collections.sort(booksOnHand);
        Collections.sort(booksReadingRoom);
        System.out.println("Свободные: " + freeBooks + "\n" +
                "Читальный зал: " + booksReadingRoom + "\n" +
                "На руках: " + booksOnHand + "\n");
    }

    private void giveAwayBook(User user, Book book) {
        if (book.isValuable()) {
            releaseBook(user, book, booksReadingRoom);
        } else {
            releaseBook(user, book, booksOnHand);
        }
    }

    private void releaseBook(User user, Book book, List<Book> place) {
        int i = Collections.binarySearch(place, book);
        if (place.get(i).release(user)) {
            freeBooks.add(place.get(i));
            place.remove(i);
        }
    }

    public ExecutorService getEs() {
        return es;
    }
}
