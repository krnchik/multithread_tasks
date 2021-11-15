package com.krnchik.task1;

public class Ship {
    private final String name;
    private final int maxCargo;
    private int cargo;

    public Ship(String name, int maxCargo, int cargo) throws IllegalArgumentException {
        if (!isCargo(maxCargo, cargo))
            throw new IllegalArgumentException();
        this.name = name;
        this.maxCargo = maxCargo;
        this.cargo = cargo;
    }

    private boolean isCargo(int maxCargo, int cargo) {
        return cargo >= 0 && maxCargo >= cargo;
    }

    public boolean load(int cargo) {
        if (!isLoadCargo(cargo) || cargo == 0)
            return false;
        this.cargo += cargo;
        System.out.println("На корабль " + name + " загрузили " + cargo +
                ". На корабле " + this.cargo);
        return true;
    }

    public boolean unload(int cargo) {
        if (!isUnloadCargo(cargo) || cargo == 0)
            return false;
        this.cargo -= cargo;
        System.out.println("C корабля " + name + " разгрузили " + cargo +
                ". На корабле " + this.cargo);
        return true;
    }

    public boolean isLoadCargo(int cargo) {
        return (this.cargo + cargo) <= maxCargo;
    }

    public boolean isUnloadCargo(int cargo) {
        return (this.cargo - cargo) >= 0;
    }
}
