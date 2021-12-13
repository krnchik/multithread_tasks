package com.krnchik.task6;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Lot implements Comparable<Lot>, Callable<Lot> {
    private final static int MAX_COUNT_WAITING = 5;
    private final static int TIME_WAITING_UPDATE = 1000;//milliseconds
    private final int number;
    private int currentPrice;
    private Participant participant;
    private volatile boolean update = false;

    public Lot(int number) {
        this.number = number;
        currentPrice = 0;
    }

    @Override
    public Lot call() {
        try {
            int count = MAX_COUNT_WAITING;
            while (0 < count) {
                System.out.println(number + " " + count + "...");
                Thread.sleep(TIME_WAITING_UPDATE);
                if (update) {
                    count = MAX_COUNT_WAITING;
                    update = false;
                }
                count--;
            }
            if (participant != null)
                System.out.println(number + " продано" + " за "
                        + currentPrice + " " + participant.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public synchronized boolean fixPrice(Offer offer) {
        if (offer.getLot().equals(this) && currentPrice > offer.getPrice())
            return false;
        currentPrice = offer.getPrice();
        participant = offer.getParticipant();
        update = true;
        return true;
    }

    @Override
    public int compareTo(Lot o) {
        if (number > o.number) {
            return 1;
        } else if (number < o.number) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return number == lot.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return number;
    }
}
