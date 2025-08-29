package com.regresion.regresion.model;

import java.util.List;

public class RegressionResult {
    private List<Double> coefficients;
    private double rSquared;
    private String equation;
    private double[][] matrix;
    private double[][] inverseMatrix;

    // Constructores
    public RegressionResult() {}

    // Getters y Setters
    public List<Double> getCoefficients() { return coefficients; }
    public void setCoefficients(List<Double> coefficients) { this.coefficients = coefficients; }

    public double getRSquared() { return rSquared; }
    public void setRSquared(double rSquared) { this.rSquared = rSquared; }

    public String getEquation() { return equation; }
    public void setEquation(String equation) { this.equation = equation; }

    public double[][] getMatrix() { return matrix; }
    public void setMatrix(double[][] matrix) { this.matrix = matrix; }

    public double[][] getInverseMatrix() { return inverseMatrix; }
    public void setInverseMatrix(double[][] inverseMatrix) { this.inverseMatrix = inverseMatrix; }
}