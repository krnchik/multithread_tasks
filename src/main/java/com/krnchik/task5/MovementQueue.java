package com.krnchik.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovementQueue implements Runnable {
    private final Restaurant restaurant;

    public MovementQueue(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException();
        }
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        List<Cashier> cashiers = new ArrayList<>(restaurant.getCashiers());
        while (true) {
            while (restaurant.hasClients()) {
                Collections.sort(cashiers);
                for (int i = 0; i < cashiers.size(); i++) {
                    while (isBigQueueCashier(cashiers, i)) {
                        addClientInRestaurant(cashiers, i);
                    }
                    if (cashiers.get(i).giveQueueSize() == 1) {
                        addClientInRestaurant(cashiers, i);
                        break;
                    }
                }
            }
        }
    }

    private boolean isBigQueueCashier(List<Cashier> cashiers, int i) {
        int currentQueueSize = cashiers.get(i).giveQueueSize();
        return currentQueueSize - cashiers.get(0).giveQueueSize() > 1;
    }

    private void addClientInRestaurant(List<Cashier> cashiers, int index) {
        Client client = cashiers.get(index).leaveQueue();
        if (client != null) {
            restaurant.addClient(client);
        }
    }
}
