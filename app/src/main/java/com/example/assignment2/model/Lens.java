package com.example.assignment2.model;

public class Lens {
    private String make;
    private double maxAperture;
    private int focalLengthInMM;

    public Lens(String make, double maxAperture, int focalLengthInMM) {
        this.make = make;
        this.maxAperture = maxAperture;
        this.focalLengthInMM = focalLengthInMM;
    }

    public String getMake() {
        return make;
    }

    public double getMaxAperture() {
        return maxAperture;
    }


    public int getFocalLengthInMM() {
        return focalLengthInMM;
    }
}
