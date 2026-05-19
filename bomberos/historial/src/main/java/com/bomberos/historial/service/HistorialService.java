package com.bomberos.historial.service;

import com.bomberos.historial.dto.HistorialRequestDTO;
import com.bomberos.historial.dto.HistorialResponseDTO;
import com.bomberos.historial.model.Historial;
import com.bomberos.historial.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository repository;

    private HistorialResponseDTO mapToDTO(Historial historial) {
        HistorialResponseDTO dto = new HistorialResponseDTO();
        dto.setId(historial.getId());
        dto.setUbicación(historial.getUbicación());
        dto.setCausaProbable(historial.getCausaProbable());
        dto.setFechaInicio(historial.getFechaInicio());
        dto.setFechaFin(historial.getFechaFin());
        dto.setHectareasAfectadas(historial.getHectareasAfectadas());
        return dto;
    }

    public List<HistorialResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public HistorialResponseDTO guardar(HistorialRequestDTO request) {
        Historial historial = new Historial();
        historial.setUbicación(request.getUbicación());
        historial.setCausaProbable(request.getCausaProbable());
        historial.setFechaInicio(request.getFechaInicio());
        historial.setFechaFin(request.getFechaFin());
        historial.setHectareasAfectadas(request.getHectareasAfectadas());

        Historial guardado = repository.save(historial);
        return mapToDTO(guardado);
    }
}