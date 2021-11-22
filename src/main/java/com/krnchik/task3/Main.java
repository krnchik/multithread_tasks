package com.krnchik.task3;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CarParking carParking = new CarParking(4);

        Car car1 = new Car("Volvo", carParking);
        Car car2 = new Car("VAZ", carParking);
        Car car3 = new Car("Mazda", carParking);
        Car car4 = new Car("Toyota", carParking);
        Car car5 = new Car("BMW", carParking);
        Car car6 = new Car("GMG", carParking);
        Car car7 = new Car("Ford", carParking);
        Car car8 = new Car("Pontiac", carParking);
        Car car9 = new Car("VW", carParking);

        car1.park(2000, TimeUnit.MILLISECONDS);
        car2.park(3000, TimeUnit.MILLISECONDS);
        car3.park(4000, TimeUnit.MILLISECONDS);
        car4.park(5000, TimeUnit.MILLISECONDS);
        car5.park(6000, TimeUnit.MILLISECONDS);
        car6.park(7000, TimeUnit.MILLISECONDS);
        car7.park(8000, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(7010);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        car1.driveOff();
        car2.driveOff();
        car3.driveOff();
        car4.driveOff();
        car5.driveOff();
        car6.driveOff();
        car7.driveOff();
        car8.driveOff();
        car9.driveOff();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        car6.park(1000, TimeUnit.MILLISECONDS);
        car7.park(2000, TimeUnit.MILLISECONDS);
        car8.park(3000, TimeUnit.MILLISECONDS);
        car9.park(4000, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        car6.driveOff();
        car7.driveOff();
    }
}
