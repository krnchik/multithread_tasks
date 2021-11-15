package com.krnchik.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Port port = new Port(2, 10000, new AtomicInteger(5000));

        Ship ship1 = new Ship("Аврора", 2000, 1000);
        Ship ship2 = new Ship("Свобода", 2000, 1000);
        Ship ship3 = new Ship("Светлана", 2000, 1000);
        Ship ship4 = new Ship("Москва", 2000, 1000);
        Ship ship5 = new Ship("Бисмарк", 2000, 1000);

        OrderShip order = new OrderShip(port);

        new Thread(order).start();

        order.unload(ship1, 500);
        order.unload(ship2, 500);
        order.unload(ship3, 500);
        order.unload(ship4, 500);
        order.unload(ship5, 500);
        order.load(ship1, 500);
        order.load(ship2, 500);
        order.load(ship3, 500);
        order.load(ship4, 500);
        order.load(ship5, 500);
        order.jointLoadUnload(ship1, 500, 250);
        order.jointLoadUnload(ship2, 500, 250);
        order.jointLoadUnload(ship3, 500, 250);
        order.jointLoadUnload(ship4, 500, 250);
        order.jointLoadUnload(ship5, 500, 250);
    }
}
