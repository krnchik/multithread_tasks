package com.krnchik.task4;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter(2);

        Client client1 = new Client("+7 100 000-00-00", callCenter);
        Client client2 = new Client("+7 200 000-00-00", callCenter);
        Client client3 = new Client("+7 300 000-00-00", callCenter);
        Client client4 = new Client("+7 400 000-00-00", callCenter);
        Client client5 = new Client("+7 500 000-00-00", callCenter);
        Client client6 = new Client("+7 600 000-00-00", callCenter);
        Client client7 = new Client("+7 700 000-00-00", callCenter);

        client1.call();
        client2.call();
        client3.call();
        client4.call();
        client5.call();
        client6.call();
        client7.call();


        Thread.sleep(5000);
        client1.hangUp();
        client2.hangUp();
        client6.hangUp();
        client7.hangUp();


        Thread.sleep(5000);
        client3.hangUp();
        client4.hangUp();

        Thread.sleep(5000);
        client5.hangUp();

        callCenter.close();
    }
}
