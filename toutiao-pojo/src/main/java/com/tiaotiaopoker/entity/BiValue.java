package com.tiaotiaopoker.entity;

public class BiValue<T, E> {
    private T valA;
    private E valB;

    public T getValA () {
        return valA;
    }

    public void setValA (T valA) {
        this.valA = valA;
    }

    public E getValB () {
        return valB;
    }

    public void setValB (E valB) {
        this.valB = valB;
    }

}
