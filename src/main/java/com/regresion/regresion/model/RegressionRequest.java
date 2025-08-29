package com.regresion.regresion.model;

import java.util.List;

public class RegressionRequest {
    private List<DataPoint> data;

    // Constructores
    public RegressionRequest() {}

    public RegressionRequest(List<DataPoint> data) {
        this.data = data;
    }

    // Getters y Setters
    public List<DataPoint> getData() { return data; }
    public void setData(List<DataPoint> data) { this.data = data; }
}