package com.bomberos.reportes.service;

import com.bomberos.reportes.dto.ReporteRequestDTO;
import com.bomberos.reportes.dto.ReporteResponseDTO;
import com.bomberos.reportes.model.Reporte;
import com.bomberos.reportes.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    private ReporteResponseDTO mapToDTO(Reporte reporte) {
        ReporteResponseDTO dto = new ReporteResponseDTO();
        dto.setId(reporte.getId().toString());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setLatitud(reporte.getLatitud());
        dto.setLongitud(reporte.getLongitud());
        dto.setEstado(reporte.getEstado());
        return dto;
    }

    // 1. Obtener todos (Retorna DTOs)
    public List<ReporteResponseDTO> obtenerTodos() {
        return reporteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 2. Crear (Recibe RequestDTO, Retorna ResponseDTO)
    public ReporteResponseDTO crearReporte(ReporteRequestDTO request) {
        Reporte reporte = new Reporte();
        reporte.setDescripcion(request.getDescripcion());
        reporte.setLatitud(request.getLatitud());
        reporte.setLongitud(request.getLongitud());
        reporte.setEstado(request.getEstado());

        Reporte guardado = reporteRepository.save(reporte);
        return mapToDTO(guardado);
    }

    // 3. Actualizar (Recibe DTO, Retorna DTO)
    public ReporteResponseDTO actualizarReporte(String id, ReporteRequestDTO request) {
        Reporte reporteExistente = reporteRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));

        reporteExistente.setDescripcion(request.getDescripcion());
        reporteExistente.setLatitud(request.getLatitud());
        reporteExistente.setLongitud(request.getLongitud());
        reporteExistente.setEstado(request.getEstado());

        Reporte guardado = reporteRepository.save(reporteExistente);
        return mapToDTO(guardado);
    }

    // 4. Eliminar
    public void eliminarReporte(String id) {
        UUID uuid = UUID.fromString(id);
        if (!reporteRepository.existsById(uuid)) {
            throw new RuntimeException("Reporte no encontrado con ID: " + id);
        }
        reporteRepository.deleteById(uuid);
    }
}