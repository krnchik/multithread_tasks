package com.krnchik.task6;

import java.util.concurrent.BlockingQueue;

public class OffersHandler implements Runnable {
    private final Auction auction;
    private final BlockingQueue<Offer> offers;

    public OffersHandler(Auction auction) {
        this.auction = auction;
        offers = auction.getOffers();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            while (!auction.getExposedLots().isEmpty()) {
                Offer offer = offers.take();
                for (Lot exposedLot : auction.getExposedLots()) {
                    if (exposedLot.equals(offer.getLot())) {
                        exposedLot.fixPrice(offer);
                        System.out.println("За " + offer.getLot().getNumber() + " "
                                + offer.getParticipant().getName() + " предложил " + offer.getPrice());
                        Thread.sleep(500);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
