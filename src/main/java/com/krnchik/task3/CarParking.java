package com.krnchik.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CarParking {
    private final Semaphore parkingLots;
    private final List<Car> cars;

    public CarParking(int countParkingLots) {
        if (!isCountParkingLots(countParkingLots))
            throw new  IllegalArgumentException();
        parkingLots = new Semaphore(countParkingLots, true);
        cars = Collections.synchronizedList(new ArrayList<>());
    }

    private boolean isCountParkingLots(int countParkingLots) {
        return countParkingLots > 0;
    }

    public boolean takeParkingLot(Car car, long timeout, TimeUnit unit) {
        try {
            if (cars.contains(car))
                return false;

            if (parkingLots.tryAcquire(timeout, unit)) {
                cars.add(car);
                System.out.println(car.getMark() + " припарковалась" + "\n"
                        + "Парковка: " + cars);
                Thread.sleep(2000);
                return true;
            } else {
                System.out.println(car.getMark() + " уехала на другую парковку" + "\n"
                        + "Парковка: " + cars);
                Thread.sleep(2000);
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

    public boolean freeParkingLot(Car car) {
        if (!cars.contains(car))
            return false;
        cars.remove(car);
        parkingLots.release();
        System.out.println(car.getMark() + " освободила парковку" + "\n"
                + "Парковка: " + cars);
        return true;
    }
}
