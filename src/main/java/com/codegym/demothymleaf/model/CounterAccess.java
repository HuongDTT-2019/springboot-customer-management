package com.codegym.demothymleaf.model;

public class CounterAccess {
    private int count;
    public int increment() {
        return count++;
    }

    public CounterAccess(int count) {
        this.count = count;
    }

    public CounterAccess() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
