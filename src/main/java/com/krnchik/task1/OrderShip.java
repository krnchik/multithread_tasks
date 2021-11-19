package com.krnchik.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderShip implements Runnable {
    private final Port port;
    private final BlockingQueue<Runnable> orders;

    public OrderShip(Port port) {
        this.port = port;
        orders = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            while (!orders.isEmpty()) {
                for (Runnable order : orders) {
                    port.getBerths().submit(order);
                    orders.take();
                }
            }
            port.getBerths().shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void load(Ship ship, int cargo) {
        jointLoadUnload(ship, cargo, 0);
    }

    public void unload(Ship ship, int cargo) {
        jointLoadUnload(ship, 0, cargo);
    }

    public void jointLoadUnload(Ship ship, int loadedCargo, int unloadedCargo) {
        orders.add(() -> {
            if (ship.isLoadCargo(loadedCargo) && port.unload(loadedCargo)) {
                ship.addCargo(loadedCargo);
            }

            if (ship.isUnloadCargo(unloadedCargo) && port.load(unloadedCargo)) {
                ship.reduceCargo(unloadedCargo);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
