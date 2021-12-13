package com.krnchik.task6;

import java.util.Objects;

public class Offer implements Comparable<Offer> {
    private static final int MAX_ATTEMPTS = 3;
    private final Auction auction;
    private final Participant participant;
    private final Lot lot;
    private int currentAttempt;
    private int price;

    public Offer(Auction auction ,Participant participant, Lot lot) {
        if (isIncorrectArgs(auction, participant, lot))
            throw new IllegalArgumentException();
        this.auction = auction;
        this.participant = participant;
        this.lot = lot;
        this.price = 0;
        this.currentAttempt = 0;
    }

    private boolean isIncorrectArgs(Auction auction ,Participant participant, Lot lot) {
        return auction == null || participant == null || lot == null;
    }

    public boolean make(int price) {
        if (isIllegalOffer(price))
            return false;
        this.price = price;
        currentAttempt++;
        auction.addOffer(this);
        return true;
    }

    private boolean isIllegalOffer(int price) {
        return MAX_ATTEMPTS < currentAttempt && this.price > price;
    }

    @Override
    public int compareTo(Offer o) {
        if (auction.compareTo(o.auction) > 0) {
            return 1;
        } else if (auction.compareTo(o.auction) < 0) {
            return -1;
        } else if (participant.compareTo(o.participant)> 0) {
            return 1;
        } else if (participant.compareTo(o.participant) < 0) {
            return -1;
        }
        return lot.compareTo(o.lot);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(auction, offer.auction) && Objects.equals(participant, offer.participant) && Objects.equals(lot, offer.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auction, participant, lot);
    }

    public Auction getAuction() {
        return auction;
    }

    public Lot getLot() {
        return lot;
    }

    public Participant getParticipant() {
        return participant;
    }

    public int getPrice() {
        return price;
    }
}
