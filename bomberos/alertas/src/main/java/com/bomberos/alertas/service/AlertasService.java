package com.bomberos.alertas.service;

import com.bomberos.alertas.dto.AlertaRequestDTO;
import com.bomberos.alertas.dto.AlertaResponseDTO;
import com.bomberos.alertas.model.Alerta;
import com.bomberos.alertas.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertasService {

    @Autowired
    private AlertaRepository alertaRepository;

    // Mapeador de Entidad a DTO
    private AlertaResponseDTO mapToDTO(Alerta alerta) {
        AlertaResponseDTO dto = new AlertaResponseDTO();
        dto.setId(alerta.getId());
        dto.setTipoAlerta(alerta.getTipoAlerta());
        dto.setMensaje(alerta.getMensaje());
        dto.setSeveridad(alerta.getSeveridad());
        dto.setFechaCreacion(alerta.getFechaCreacion());
        return dto;
    }

    public List<AlertaResponseDTO> obtenerAlertas() {
        return alertaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AlertaResponseDTO crearAlerta(AlertaRequestDTO request) {
        Alerta alerta = new Alerta();
        alerta.setTipoAlerta(request.getTipoAlerta());
        alerta.setMensaje(request.getMensaje());
        alerta.setSeveridad(request.getSeveridad());
        // No seteamos fechaCreacion, ya que tu Entidad lo hace sola en su constructor

        Alerta guardada = alertaRepository.save(alerta);
        return mapToDTO(guardada);
    }

    public void eliminarAlerta(Long id) { alertaRepository.deleteById(id); }
}