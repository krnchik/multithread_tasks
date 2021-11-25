package com.krnchik.task4;

import java.util.concurrent.*;

public class CallCenter implements Runnable {
    private final BlockingQueue<Client> clients;
    private final ExecutorService operators;
    public volatile boolean opening = true;

    public CallCenter(int countOperators) {
        if (countOperators <= 0)
            throw new IllegalArgumentException();
        operators = Executors.newFixedThreadPool(countOperators);
        clients = new LinkedBlockingQueue<>();
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (opening) {
                processingClient();
            }
            operators.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processingClient() throws InterruptedException {
        while (!clients.isEmpty()) {
            if (clients.element().isCalling()) {
                Client client = clients.take();
                operators.submit(client);
            } else {
                clients.take();
            }
        }
    }

    public void handleCall(Client client) {
        try {
            if (clients.contains(client) && !client.isCalling()) {
                System.out.println(clients.remove(client));
                return;
            }

            clients.put(client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        opening = false;
    }
}
