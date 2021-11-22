package com.krnchik.task3;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private final String mark;
    private final CarParking carParking;
    private volatile boolean parking = false;
    private long timeout;
    private TimeUnit unit;

    public Car(String mark, CarParking parking) {
        if (isIncorrectArgument(mark, parking))
            throw new IllegalArgumentException();
        this.mark = mark;
        this.carParking = parking;
    }

    private boolean isIncorrectArgument(String mark, CarParking parking) {
        return mark == null || parking == null;
    }

    @Override
    public void run() {
        if (parking) {
            carParking.takeParkingLot(this, timeout, unit);
        } else {
            carParking.freeParkingLot(this);
        }
    }

    public synchronized void park(long timeout, TimeUnit unit) {
        if (parking)
            return;
        parking = true;
        this.timeout = timeout;
        this.unit = unit;
        new Thread(this).start();
    }

    public synchronized void driveOff() {
        if (!parking)
            return;
        parking = false;
        new Thread(this).start();
    }

    @Override
    public String toString() {
        return mark;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(mark, car.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }

    public String getMark() {
        return mark;
    }
}
