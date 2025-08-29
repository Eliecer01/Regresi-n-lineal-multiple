package com.regresion.regresion.Util;

import java.util.List;
import java.util.stream.Collectors;

import com.regresion.regresion.model.DataPoint;

public class MatrixUtils {

    // Método para calcular la matriz inversa usando Gauss-Jordan
    public static double[][] invertMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] augmented = new double[n][2 * n];

        // Crear matriz aumentada [A | I]
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmented[i], 0, n);
            augmented[i][n + i] = 1;
        }

        // Aplicar eliminación de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Pivoteo parcial
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(augmented[k][i]) > Math.abs(augmented[maxRow][i])) {
                    maxRow = k;
                }
            }

            // Intercambiar filas
            double[] temp = augmented[i];
            augmented[i] = augmented[maxRow];
            augmented[maxRow] = temp;

            // Hacer 1 el elemento diagonal
            double pivot = augmented[i][i];
            if (Math.abs(pivot) < 1e-10) {
                throw new ArithmeticException("Matriz singular o casi singular");
            }

            for (int j = 0; j < 2 * n; j++) {
                augmented[i][j] /= pivot;
            }

            // Hacer 0 los otros elementos de la columna
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmented[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmented[k][j] -= factor * augmented[i][j];
                    }
                }
            }
        }

        // Extraer la matriz inversa
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmented[i], n, inverse[i], 0, n);
        }

        return inverse;
    }

    // Método para multiplicar matrices
    public static double[] multiplyMatrixVector(double[][] matrix, double[] vector) {
        int n = matrix.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    // Método para calcular el coeficiente de determinación R²
    public static double calculateRSquared(List<DataPoint> data, double[] coefficients) {
        // Filtrar datos válidos
        List<DataPoint> validData = data.stream()
                .filter(point -> point.getY() != null && point.getX1() != null && point.getX2() != null)
                .collect(Collectors.toList());

        double meanY = validData.stream().mapToDouble(DataPoint::getY).average().orElse(0);
        double ssTotal = validData.stream().mapToDouble(point -> Math.pow(point.getY() - meanY, 2)).sum();

        double ssResidual = validData.stream().mapToDouble(point -> {
            double predicted = coefficients[0] + coefficients[1] * point.getX1() + coefficients[2] * point.getX2();
            return Math.pow(point.getY() - predicted, 2);
        }).sum();

        return 1 - (ssResidual / ssTotal);
    }

    // Método para formatear la ecuación
    public static String formatEquation(double[] coefficients) {
        // USAR PUNTO decimal en lugar de coma
        return String.format("ŷ = %.4f + %.4f*X₁ + %.4f*X₂",
                coefficients[0], coefficients[1], coefficients[2])
                .replace(',', '.'); // ← Convertir comas a puntos
    }
}