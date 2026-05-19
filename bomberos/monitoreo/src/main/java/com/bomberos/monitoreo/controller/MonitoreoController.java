package com.bomberos.monitoreo.controller;

import com.bomberos.monitoreo.dto.ZonaMonitoreoRequestDTO;
import com.bomberos.monitoreo.dto.ZonaMonitoreoResponseDTO;
import com.bomberos.monitoreo.service.MonitoreoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitoreo")
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;

    @GetMapping
    public List<ZonaMonitoreoResponseDTO> listarTodo() {
        return monitoreoService.listarTodo();
    }

    @PostMapping
    public ZonaMonitoreoResponseDTO guardar(@Valid @RequestBody ZonaMonitoreoRequestDTO requestDTO) {
        return monitoreoService.guardar(requestDTO);
    }
}