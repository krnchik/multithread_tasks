package com.krnchik.task4;

import java.util.Objects;

public class Client implements Runnable {
    private final String phoneNumber;
    private final CallCenter callCenter;
    private volatile boolean calling = false;

    public Client(String phoneNumber, CallCenter callCenter) {
        if (callCenter == null)
            throw new IllegalArgumentException();
        this.phoneNumber = phoneNumber;
        this.callCenter = callCenter;
    }

    @Override
    public void run() {
        if (!calling) {
            System.out.println(phoneNumber + " звонок был сброшен. Клиент не дождался.");
            return;
        }

        boolean message = false;
        while (calling) {
            if (!message) {
                System.out.println(phoneNumber + " звонок обрабатывается...");
                message = true;
            }
        }

        System.out.println(phoneNumber + " звонок завершен");
    }

    public void call() {
        if (calling)
            return;
        calling = true;
        callCenter.handleCall(this);
    }

    public void hangUp() {
        if (!calling)
            return;
        calling = false;
        callCenter.handleCall(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    public boolean isCalling() {
        return calling;
    }
}
