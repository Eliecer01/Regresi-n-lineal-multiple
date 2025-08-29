package com.regresion.regresion.Service;

import org.springframework.stereotype.Service;
import com.regresion.regresion.Util.MatrixUtils;
import com.regresion.regresion.model.DataPoint;
import com.regresion.regresion.model.MatrixRequest;
import com.regresion.regresion.model.MatrixResponse;
import com.regresion.regresion.model.RegressionResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegressionService {

    public RegressionResult calculateRegression(List<DataPoint> data) {
        // Validar datos
        List<DataPoint> validData = data.stream()
                .filter(point -> point.getY() != null && point.getX1() != null && point.getX2() != null)
                .collect(Collectors.toList());

        if (validData.size() < 3) {
            throw new IllegalArgumentException("Se necesitan al menos 3 puntos de datos válidos");
        }

        // Calcular sumatorias
        double sumY = data.stream().mapToDouble(DataPoint::getY).sum();
        double sumX1 = data.stream().mapToDouble(DataPoint::getX1).sum();
        double sumX2 = data.stream().mapToDouble(DataPoint::getX2).sum();
        double sumX1Squared = data.stream().mapToDouble(point -> point.getX1() * point.getX1()).sum();
        double sumX2Squared = data.stream().mapToDouble(point -> point.getX2() * point.getX2()).sum();
        double sumX1x2 = data.stream().mapToDouble(point -> point.getX1() * point.getX2()).sum();
        double sumX1y = data.stream().mapToDouble(point -> point.getX1() * point.getY()).sum();
        double sumX2y = data.stream().mapToDouble(point -> point.getX2() * point.getY()).sum();
        int n = data.size();

        // Construir matriz del sistema
        double[][] matrix = {
                { n, sumX1, sumX2 },
                { sumX1, sumX1Squared, sumX1x2 },
                { sumX2, sumX1x2, sumX2Squared }
        };

        // Vector de términos independientes
        double[] vector = { sumY, sumX1y, sumX2y };

        try {
            // Calcular matriz inversa
            double[][] inverseMatrix = MatrixUtils.invertMatrix(matrix);

            // Resolver sistema: coeficientes = inverseMatrix * vector
            double[] coefficients = MatrixUtils.multiplyMatrixVector(inverseMatrix, vector);

            // Calcular R²
            double rSquared = MatrixUtils.calculateRSquared(data, coefficients);

            // Formatear ecuación
            String equation = MatrixUtils.formatEquation(coefficients);

            // Preparar resultado
            RegressionResult result = new RegressionResult();
            result.setCoefficients(Arrays.stream(coefficients).boxed().collect(Collectors.toList()));
            result.setRSquared(rSquared);
            result.setEquation(equation);
            result.setMatrix(matrix);
            result.setInverseMatrix(inverseMatrix);

            return result;

        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("No se puede resolver el sistema: " + e.getMessage());
        }
    }

    public MatrixResponse calculateInverseMatrix(MatrixRequest request) {
        // Construir matriz del sistema
        double[][] matrix = {
                { request.getN(), request.getSumX1(), request.getSumX2() },
                { request.getSumX1(), request.getSumX1Squared(), request.getSumX1x2() },
                { request.getSumX2(), request.getSumX1x2(), request.getSumX2Squared() }
        };

        try {
            // Calcular matriz inversa
            double[][] inverseMatrix = MatrixUtils.invertMatrix(matrix);

            // Vector de términos independientes (para ejemplo)
            double[] vector = { request.getSumX1(), request.getSumX1x2(), request.getSumX2Squared() };
            double[] coefficients = MatrixUtils.multiplyMatrixVector(inverseMatrix, vector);

            String equation = MatrixUtils.formatEquation(coefficients);

            return new MatrixResponse(inverseMatrix,
                    Arrays.stream(coefficients).boxed().collect(Collectors.toList()),
                    equation);

        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Matriz singular o no invertible: " + e.getMessage());
        }
    }

    // Método auxiliar para validar datos
    /*
     * private void validateDataPoint(DataPoint point) {
     * if (point.getY() == null || point.getX1() == null || point.getX2() == null) {
     * throw new IllegalArgumentException("Todos los campos deben tener valores");
     * }
     * }
     */
}