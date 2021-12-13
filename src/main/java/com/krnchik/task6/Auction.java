package com.krnchik.task6;

import java.util.*;
import java.util.concurrent.*;

public class Auction implements Comparable<Auction>, Runnable {
    private static final int MAX_COUNT_EXPOSED_LOTS = 3;
    private final String name;
    private final List<Participant> participants;
    private final List<Lot> lots;
    private final BlockingQueue<Lot> exposedLots;
    private final BlockingQueue<Offer> offers;
    private final ExecutorService bidding;

    public Auction(String name, List<Participant> participants, List<Lot> lots) {
        if (!isUniqueLots(lots) || !isUniqueParticipants(participants)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.participants = participants;
        this.lots = lots;
        this.exposedLots = new ArrayBlockingQueue<>(MAX_COUNT_EXPOSED_LOTS);
        this.offers = new LinkedBlockingQueue<>();
        bidding = Executors.newFixedThreadPool(MAX_COUNT_EXPOSED_LOTS);
        startOffersHandler();
        startLotsHandler();
    }

    private void startLotsHandler() {
        Thread lotsHandler = new Thread(new LotsHandler(this));
        lotsHandler.start();
    }

    private void startOffersHandler() {
        Thread offersHandler = new Thread(new OffersHandler(this));
        offersHandler.setDaemon(true);
        offersHandler.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            while (!exposedLots.isEmpty()) {
                List<Future<Lot>> lots = bidding.invokeAll(exposedLots);
                for (Future<Lot> lot : lots) {
                    exposedLots.remove(lot.get());
                }
                Thread.sleep(100);
            }
            bidding.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private boolean isUniqueParticipants(List<Participant> participants) {
        if (participants == null)
            return false;
        Set<Participant> uniqueParticipants = new HashSet<>(participants);
        return uniqueParticipants.size() == participants.size();
    }

    private boolean isUniqueLots(List<Lot> lots) {
        if (lots == null)
            return false;
        Set<Lot> uniqueLots = new HashSet<>(lots);
        return uniqueLots.size() == lots.size();
    }

    public void addOffer(Offer offer) {
        try {
            if (isOffer(offer)) {
                offers.put(offer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isOffer(Offer offer) {
        return offer != null && offer.getAuction().equals(this)
                && participants.contains(offer.getParticipant())
                && exposedLots.contains(offer.getLot());
    }

    @Override
    public int compareTo(Auction o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return Objects.equals(name, auction.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public BlockingQueue<Offer> getOffers() {
        return offers;
    }

    public BlockingQueue<Lot> getExposedLots() {
        return exposedLots;
    }

    public List<Lot> getLots() {
        return lots;
    }
}
