package com.krnchik.task5;

import java.util.concurrent.*;

public class Cashier implements Runnable, Comparable<Cashier> {
    private final String name;
    private final BlockingDeque<Client> queue;
    private final ExecutorService es;

    public Cashier(String name) {
        this.name = name;
        queue = new LinkedBlockingDeque<>();
        es = Executors.newFixedThreadPool(1);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                acceptOrder();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void acceptOrder() throws InterruptedException {
        Client client = queue.takeFirst();
        Future<Boolean> answer = es.submit(client);
        while (!answer.isDone()) {

        }
    }

    public void handleOrder(Client client) throws InterruptedException {
        System.out.println("Заказ " + client + " обрабатывается " + name + "...");
        Thread.sleep(1000 + giveRandom(10, 10000));
    }

    private int giveRandom(int min, int max) {
        return min + (int) (Math.random() * (max - min) + 1);
    }

    public void joinQueue(Client client) {
        try {
            client.setCashier(this);
            queue.putLast(client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Client leaveQueue() {
        Client client = queue.pollLast();
        if (client != null)
            client.setCashier(null);
        return client;
    }

    public int giveQueueSize() {
        return queue.size();
    }

    @Override
    public int compareTo(Cashier o) {
        if (this.giveQueueSize() > o.giveQueueSize()) {
            return 1;
        } else if (this.giveQueueSize() < o.giveQueueSize()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return Integer.toString(queue.size());
    }
}
