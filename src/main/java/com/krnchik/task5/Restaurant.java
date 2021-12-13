package com.krnchik.task5;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Restaurant implements Runnable {
    private final List<Cashier> cashiers;
    private final BlockingDeque<Client> clients;

    public Restaurant(List<Cashier> cashiers) {
        if (!isCashiers(cashiers))
            throw new IllegalArgumentException();
        this.cashiers = cashiers;
        clients = new LinkedBlockingDeque<>();
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    private boolean isCashiers(List<Cashier> cashiers) {
        return cashiers != null && !cashiers.isEmpty();
    }

    @Override
    public void run() {
        Thread thread = new Thread(new MovementQueue(this));
        thread.setDaemon(true);
        thread.start();
        while (true) {
            handleAddClient();
        }
    }

    private void handleAddClient() {
        while (!clients.isEmpty()) {
            giveMinQueueCashier().joinQueue(clients.removeFirst());
        }
    }

    public void addClient(Client client) {
        try {
            client.setRestaurant(this);
            clients.put(client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Cashier giveMinQueueCashier() {
        return Collections.min(cashiers);
    }

    public boolean hasClients() {
        for (Cashier cashier : cashiers) {
            if (cashier.giveQueueSize() != 0)
                return true;
        }
        return false;
    }

    public void close() {
        System.exit(1);
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public BlockingDeque<Client> getClients() {
        return clients;
    }
}
