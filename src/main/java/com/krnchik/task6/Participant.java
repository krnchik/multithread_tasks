package com.krnchik.task6;

import java.util.*;

public class Participant implements Comparable<Participant> {
    private final int id;
    private final String name;
    private final List<Offer> offers;

    public Participant(int id, String name) {
        this.id = id;
        this.name = name;
        offers = new ArrayList<>();
    }

    public boolean makeOffer(Auction auction, Lot lot, int price) {
        Offer offer = new Offer(auction, this, lot);
        if (offers.contains(offer)) {
            int i = Collections.binarySearch(offers, offer);
            return offers.get(i).make(price);
        }
        offers.add(offer);
        return offer.make(price);
    }

    @Override
    public int compareTo(Participant o) {
        if (id > o.id) {
            return 1;
        } else if (id < o.id) {
            return -1;
        }
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getName() {
        return name;
    }
}
