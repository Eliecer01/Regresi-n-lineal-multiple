package com.regresion.regresion.model;

public class DataPoint {
    private Double y;
    private Double x1;
    private Double x2;

    // Constructores
    public DataPoint() {}

    public DataPoint(Double y, Double x1, Double x2) {
        this.y = y;
        this.x1 = x1;
        this.x2 = x2;
    }

    // Getters y Setters
    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }

    public Double getX1() { return x1; }
    public void setX1(Double x1) { this.x1 = x1; }

    public Double getX2() { return x2; }
    public void setX2(Double x2) { this.x2 = x2; }
}