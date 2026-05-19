package com.bomberos.reportes.controller;

import com.bomberos.reportes.dto.ReporteRequestDTO;
import com.bomberos.reportes.dto.ReporteResponseDTO;
import com.bomberos.reportes.model.Reporte;
import com.bomberos.reportes.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteResponseDTO>> listarReportes() {
        return ResponseEntity.ok(reporteService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<ReporteResponseDTO> crearReporte(@RequestBody ReporteRequestDTO request) {
        return ResponseEntity.ok(reporteService.crearReporte(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable String id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> actualizarReporte(@PathVariable String id, @RequestBody ReporteRequestDTO requestDTO) {
        ReporteResponseDTO actualizado = reporteService.actualizarReporte(id, requestDTO);
        return ResponseEntity.ok(actualizado);
    }
}