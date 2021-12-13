package com.krnchik.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Participant participant1 = new Participant(1, "Роуз");
        Participant participant2 = new Participant(2, "Ник");
        Participant participant3 = new Participant(3, "Мария");
        Participant participant4 = new Participant(4, "Том");
        Participant participant5 = new Participant(5, "Вася");
        List<Participant> participants = new ArrayList<>();
        participants.add(participant1);
        participants.add(participant2);
        participants.add(participant3);
        participants.add(participant4);
        participants.add(participant5);

        Lot[] lotsArr = {new Lot(1),
                new Lot(2),
                new Lot(3),
                new Lot(4),
                new Lot(5),
                new Lot(6),
                new Lot(7),
                new Lot(8),
                new Lot(9),
                new Lot(10),
                new Lot(11)};
        List<Lot> lots = Arrays.asList(lotsArr);

        Auction auction = new Auction("Большой", participants, lots);
        new Thread(auction).start();

        participant1.makeOffer(auction, lots.get(1), 150);
        participant2.makeOffer(auction, lots.get(2), 150);
        participant3.makeOffer(auction, lots.get(3), 150);
        participant4.makeOffer(auction, lots.get(4), 150);
        participant5.makeOffer(auction, lots.get(1), 170);
        participant1.makeOffer(auction, lots.get(1), 100);
        participant2.makeOffer(auction, lots.get(2), 150);
        participant3.makeOffer(auction, lots.get(3), 150);
        participant4.makeOffer(auction, lots.get(1), 200);
        participant5.makeOffer(auction, lots.get(2), 170);
        participant1.makeOffer(auction, lots.get(1), 150);
    }
}
