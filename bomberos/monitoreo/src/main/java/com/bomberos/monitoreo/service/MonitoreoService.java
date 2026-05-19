package com.bomberos.monitoreo.service;

import com.bomberos.monitoreo.dto.ZonaMonitoreoRequestDTO;
import com.bomberos.monitoreo.dto.ZonaMonitoreoResponseDTO;
import com.bomberos.monitoreo.model.ZonaMonitoreo;
import com.bomberos.monitoreo.repository.MonitoreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitoreoService {

    @Autowired
    private MonitoreoRepository monitoreoRepository;

    private ZonaMonitoreoResponseDTO mapToDTO(ZonaMonitoreo zona) {
        ZonaMonitoreoResponseDTO dto = new ZonaMonitoreoResponseDTO();
        dto.setId(zona.getId());
        dto.setNombreZona(zona.getNombreZona());
        dto.setLatitud(zona.getLatitud());
        dto.setLongitud(zona.getLongitud());
        dto.setNivelRiesgo(zona.getNivelRiesgo());
        dto.setBrigadaActiva(zona.getBrigadaActiva());
        return dto;
    }

    public List<ZonaMonitoreoResponseDTO> listarTodo() {
        return monitoreoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ZonaMonitoreoResponseDTO guardar(ZonaMonitoreoRequestDTO request) {
        ZonaMonitoreo zona = new ZonaMonitoreo();
        zona.setNombreZona(request.getNombreZona());
        zona.setLatitud(request.getLatitud());
        zona.setLongitud(request.getLongitud());
        zona.setNivelRiesgo(request.getNivelRiesgo());
        zona.setBrigadaActiva(request.getBrigadaActiva());

        ZonaMonitoreo guardada = monitoreoRepository.save(zona);
        return mapToDTO(guardada);
    }
}