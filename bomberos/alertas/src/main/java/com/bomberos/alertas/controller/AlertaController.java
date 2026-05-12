package com.bomberos.alertas.controller;

import com.bomberos.alertas.dto.AlertaRequestDTO;
import com.bomberos.alertas.dto.AlertaResponseDTO;
import com.bomberos.alertas.service.AlertasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertasService alertaService;

    @GetMapping
    public List<AlertaResponseDTO> obtenerAlertas() {
        return alertaService.obtenerAlertas();
    }

    @PostMapping
    public AlertaResponseDTO crearAlerta(@Valid @RequestBody AlertaRequestDTO requestDTO) {
        return alertaService.crearAlerta(requestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarAlerta(@PathVariable Long id) {
        alertaService.eliminarAlerta(id);
    }
}