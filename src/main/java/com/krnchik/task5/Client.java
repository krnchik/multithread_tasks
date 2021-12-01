package com.krnchik.task5;

import java.util.concurrent.Callable;

public class Client implements Callable<Boolean> {
    private final Restaurant restaurant;
    private Cashier cashier;

    public Client(Restaurant restaurant) {
        if (!isRestaurant(restaurant))
            throw new IllegalArgumentException();
        this.restaurant = restaurant;
    }

    private boolean isRestaurant(Restaurant restaurant) {
        return restaurant != null;
    }

    @Override
    public Boolean call() {
        try {
            System.out.println("Текущее состояние очереди: " + restaurant.getCashiers());
            cashier.handleOrder(this);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }
}
