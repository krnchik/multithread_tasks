package com.krnchik.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book[] books1 = {new Book("Пушкин", "Капитанская дочка", true),
                new Book("Ремарк", "Три товарища", true),
                new Book("Хаксли", "О дивный новый мир", true)};
        Book[] books2 = {new Book("Хаксли", "Жизнь взаймы", true)};
        Book[] books3 = {new Book("Палланик", "Бойцовский клуб", false),
                new Book("Фромм", "Искусство любить", false),
                new Book("Толстой", "Война и мир", false)};
        Book[] books4 = {new Book("Палланик", "Бойцовский клуб", false),
                new Book("Фромм", "Искусство любить", false)};
        Book[] books5 = {new Book("Фромм", "Искусство любить", false),
                new Book("Достоевский", "Идиот", false),
                new Book("Ремарк", "Три товарища", true)};

        Library library = new Library(2, giveBooks());

        User user1 = new User("Вася");
        User user2 = new User("Петя");
        User user3 = new User("Рома");

        OrderLibrary ol = new OrderLibrary(library);
        new Thread(ol).start();
        ol.takeBooksOrder(user1, Arrays.asList(books1));
        ol.takeBooksOrder(user2, Arrays.asList(books1));
        ol.takeBooksOrder(user3, Arrays.asList(books1));
        ol.takeBooksOrder(user3, Arrays.asList(books3));
        ol.takeBooksOrder(user2, Arrays.asList(books1));
        ol.giveAwayBooksOrder(user1, Arrays.asList(books1));
        ol.giveAwayBooksOrder(user2, Arrays.asList(books2));
        ol.giveAwayBooksOrder(user3, Arrays.asList(books4));
        ol.takeBooksOrder(user2, Arrays.asList(books5));
        ol.giveAwayBooksOrder(user2, Arrays.asList(books2));
    }

    private static List<Book> giveBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Пушкин", "Капитанская дочка", true));
        books.add(new Book("Ремарк", "Три товарища", true));
        books.add(new Book("Хаксли", "О дивный новый мир", true));
        books.add(new Book("Хаксли", "Жизнь взаймы", true));
        books.add(new Book("Голдинг", "повелитель мух", true));
        books.add(new Book("Оруэлл", "Скотный двор", false));
        books.add(new Book("Палланик", "Бойцовский клуб", false));
        books.add(new Book("Фромм", "Искусство любить", false));
        books.add(new Book("Толстой", "Война и мир", false));
        books.add(new Book("Достоевский", "Идиот", false));
        return books;
    }
}
