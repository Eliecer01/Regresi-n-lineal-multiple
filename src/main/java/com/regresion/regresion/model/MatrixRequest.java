package com.regresion.regresion.model;

public class MatrixRequest {
    private int n;
    private double sumX1;
    private double sumX2;
    private double sumX1Squared;
    private double sumX2Squared;
    private double sumX1x2;

    // Constructores
    public MatrixRequest() {}

    // Getters y Setters
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    public double getSumX1() { return sumX1; }
    public void setSumX1(double sumX1) { this.sumX1 = sumX1; }

    public double getSumX2() { return sumX2; }
    public void setSumX2(double sumX2) { this.sumX2 = sumX2; }

    public double getSumX1Squared() { return sumX1Squared; }
    public void setSumX1Squared(double sumX1Squared) { this.sumX1Squared = sumX1Squared; }

    public double getSumX2Squared() { return sumX2Squared; }
    public void setSumX2Squared(double sumX2Squared) { this.sumX2Squared = sumX2Squared; }

    public double getSumX1x2() { return sumX1x2; }
    public void setSumX1x2(double sumX1x2) { this.sumX1x2 = sumX1x2; }
}