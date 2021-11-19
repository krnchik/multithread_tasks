package com.krnchik.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Port {
    private final int maxCargo;
    private final AtomicInteger cargo;
    private final ExecutorService berths;

    public Port(int countBerth, int maxCargo, AtomicInteger cargo) {
        if (!isCargo(maxCargo, cargo.get()))
            throw new IllegalArgumentException();
        this.maxCargo = maxCargo;
        this.cargo = cargo;
        berths = Executors.newFixedThreadPool(countBerth);
    }

    private boolean isCargo(int maxCargo, int cargo) {
        return cargo >= 0 && maxCargo >= cargo;
    }

    private boolean isLoadCargo(int cargo) {
        return (this.cargo.get() + cargo) <= maxCargo;
    }

    private boolean isUnloadCargo(int cargo) {
        return (this.cargo.get() - cargo) >= 0;
    }

    public synchronized boolean load(int cargo) {
        if (!isLoadCargo(cargo) || cargo == 0) {
            return false;
        }
        this.cargo.addAndGet(cargo);
        System.out.println("На порт загрузили " + cargo + ". В порту " + this.cargo.get());
        return true;
    }

    public synchronized boolean unload(int cargo) {
        if (!isUnloadCargo(cargo) || cargo == 0) {
            return false;
        }
        this.cargo.addAndGet((-1) * cargo);
        System.out.println("С порта разгрузили " + cargo + ". В порту " + this.cargo.get());
        return true;
    }

    public ExecutorService getBerths() {
        return berths;
    }
}
