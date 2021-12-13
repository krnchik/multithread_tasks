package com.krnchik.task5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Cashier[] cashiers = {new Cashier("Анна"),
                new Cashier("Борис"),
                new Cashier("Петр")};
        Restaurant restaurant = new Restaurant(Arrays.asList(cashiers));
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        restaurant.addClient(new Client());
        try {
            Thread.sleep(150000);
            restaurant.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
