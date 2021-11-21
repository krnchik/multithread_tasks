package com.krnchik.task2;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderLibrary implements Runnable {
    private final Library library;
    private final BlockingQueue<Runnable> orders;

    public OrderLibrary(Library library) {
        this.library = library;
        orders = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            while (!orders.isEmpty()) {
                for (Runnable order : orders) {
                    library.getEs().submit(order);
                    orders.take();
                }
            }
            library.getEs().shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeBooksOrder(User user, List<Book> books) {
        orders.add(() -> {
            takeBooks(user, books);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void giveAwayBooksOrder(User user, List<Book> books) {
        orders.add(() -> {
            giveAwayBooks(user, books);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean takeBooks(User user, List<Book> books) {
        if (!library.hasFreeBooks(books))
            return false;
        library.takeBooks(user, books);
        user.takeBooks(books);
        return true;
    }

    public boolean giveAwayBooks(User user, List<Book> books) {
        if (!user.hasBooks(books))
            return false;
        library.giveAwayBooks(user, books);
        user.giveAwayBooks(books);
        return true;
    }
}
