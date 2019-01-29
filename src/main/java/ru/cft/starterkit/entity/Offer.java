package ru.cft.starterkit.entity;

public class Offer {
    private final double sum;
    private final double percent;

    public Offer(double sum, double percent){
        this.sum = sum;
        this.percent = percent;
    }

    public double getSum(){
        return sum;
    }

    public double getPercent(){
        return percent;
    }
}
