package com.regresion.regresion.model;

import java.util.List;

public class MatrixResponse {
    private double[][] matrix;
    private List<Double> coefficients;
    private String equation;

    // Constructores
    public MatrixResponse() {}

    public MatrixResponse(double[][] matrix, List<Double> coefficients, String equation) {
        this.matrix = matrix;
        this.coefficients = coefficients;
        this.equation = equation;
    }

    // Getters y Setters
    public double[][] getMatrix() { return matrix; }
    public void setMatrix(double[][] matrix) { this.matrix = matrix; }

    public List<Double> getCoefficients() { return coefficients; }
    public void setCoefficients(List<Double> coefficients) { this.coefficients = coefficients; }

    public String getEquation() { return equation; }
    public void setEquation(String equation) { this.equation = equation; }
}