package com.regresion.regresion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.regresion.regresion.Service.RegressionService;
import com.regresion.regresion.model.DataPoint;
import com.regresion.regresion.model.MatrixRequest;
import com.regresion.regresion.model.MatrixResponse;
import com.regresion.regresion.model.RegressionRequest;
import com.regresion.regresion.model.RegressionResult;

import java.util.List;

@RestController
@RequestMapping("/api/regression")
public class RegressionController {

    @Autowired
    private RegressionService regressionService;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateRegression(@RequestBody RegressionRequest request) {
        try {
            RegressionResult result = regressionService.calculateRegression(request.getData());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

    @PostMapping("/inverse")
    public ResponseEntity<?> calculateInverseMatrix(@RequestBody MatrixRequest request) {
        try {
            System.out.println("Received inverse matrix request: " + request.toString());
            MatrixResponse response = regressionService.calculateInverseMatrix(request);
            System.out.println("Sending response: " + response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println("Internal error: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Backend de regresión funcionando correctamente");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateData(@RequestBody List<DataPoint> data) {
        try {
            if (data == null || data.size() < 3) {
                return ResponseEntity.badRequest().body("Se necesitan al menos 3 puntos de datos");
            }

            int validCount = (int) data.stream()
                    .filter(point -> point.getY() != null && point.getX1() != null && point.getX2() != null)
                    .count();

            return ResponseEntity.ok().body("Datos válidos: " + validCount + " de " + data.size());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error validando datos");
        }
    }
}