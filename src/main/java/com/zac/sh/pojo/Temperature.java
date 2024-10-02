package com.zac.sh.pojo;

public class Temperature {
    private double value;

    public Temperature() {
    }

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Temprature{" +
                "value=" + value +
                '}';
    }
}
