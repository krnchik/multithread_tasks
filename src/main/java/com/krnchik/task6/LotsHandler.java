package com.krnchik.task6;

import java.util.List;

public class LotsHandler implements Runnable {
    private final Auction auction;
    private final List<Lot> lots;

    public LotsHandler(Auction auction) {
        this.auction = auction;
        lots = auction.getLots();
    }

    @Override
    public void run() {
        try {
            for (Lot lot : lots) {
                auction.getExposedLots().put(lot);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
