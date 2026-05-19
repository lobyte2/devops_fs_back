package com.bomberos.historial.controller;

import com.bomberos.historial.dto.HistorialRequestDTO;
import com.bomberos.historial.dto.HistorialResponseDTO;
import com.bomberos.historial.service.HistorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public List<HistorialResponseDTO> listar() {
        return historialService.listar();
    }

    @PostMapping
    public HistorialResponseDTO guardar(@Valid @RequestBody HistorialRequestDTO requestDTO) {
        return historialService.guardar(requestDTO);
    }
}