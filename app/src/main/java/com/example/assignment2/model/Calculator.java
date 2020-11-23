package com.example.assignment2.model;

import static java.lang.Float.POSITIVE_INFINITY;

public class Calculator {
    private double Hyperfocal; //in m
    private double Nearfocal; // in m
    private double Farfocal; //in m
    private double DepthofField; // in m
    private double distance;

    public double getHyperfocal() {
        return Hyperfocal;
    }

    public double getNearfocal() {
        return Nearfocal;
    }

    public double getFarfocal() {
        if(Hyperfocal < distance){
            return POSITIVE_INFINITY;
            //return positive infinity if less than distance;
        }else{
            return Farfocal;
        }
    }

    public double getDepthofField() {
        return DepthofField;
    }

    public Calculator(double focalLengthInMM, double distance, double aperture, double CircleofCondusion){
        this.distance = distance;

        this.Hyperfocal = CalculateHyperfocal(focalLengthInMM,aperture,CircleofCondusion);

        this.Nearfocal = CalculateNearfocal(focalLengthInMM,distance,aperture,CircleofCondusion);

        this.Farfocal = CalculateFarfocal(focalLengthInMM,distance,aperture,CircleofCondusion);

        if(Farfocal == POSITIVE_INFINITY){
            this.DepthofField = POSITIVE_INFINITY;
        }else{
            this.DepthofField = getFarfocal() - Nearfocal;
        }
    }

    public double CalculateHyperfocal(double focalLengthInMM,double aperture,double CircleofCondusion){
        double top = focalLengthInMM * focalLengthInMM;
        double bot = aperture * CircleofCondusion;
        double result = top / bot ;

        return Mmtom(result);
    }

    public double CalculateNearfocal(double focalLengthInMM,double distance,double aperture,double CircleofCondusion){
        double top = CalculateHyperfocal(focalLengthInMM, aperture,CircleofCondusion) * distance ;
        double bot = CalculateHyperfocal(focalLengthInMM, aperture,CircleofCondusion) + Mmtom(mtoMm(distance) - focalLengthInMM);
        return top / bot;
    }

    public double CalculateFarfocal(double focalLengthInMM,double distance,double aperture,double CircleofCondusion){
        double top = CalculateHyperfocal(focalLengthInMM, aperture,CircleofCondusion) * distance;
        double bot = CalculateHyperfocal(focalLengthInMM, aperture,CircleofCondusion) - Mmtom(mtoMm(distance) - focalLengthInMM);
        return top / bot;
    }

    public double Mmtom(double mm){
        return mm / 1000;
    }

    public double mtoMm(double m){
        return m * 1000;
    }
}
