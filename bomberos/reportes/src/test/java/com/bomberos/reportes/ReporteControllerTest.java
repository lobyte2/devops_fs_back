package com.bomberos.reportes;

import com.bomberos.reportes.controller.ReporteController;
import com.bomberos.reportes.dto.ReporteResponseDTO;
import com.bomberos.reportes.service.ReporteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReporteControllerTest {

    @Mock
    private ReporteService reporteService;

    @InjectMocks
    private ReporteController reporteController;

    @Test
    public void testListarReportes() {
        // 1. Arrange (Preparar los datos de prueba usando DTOs)
        ReporteResponseDTO reporteDto = new ReporteResponseDTO();
        reporteDto.setId("12345");
        reporteDto.setDescripcion("Incendio en prueba unitaria");
        reporteDto.setEstado("ACTIVO");

        List<ReporteResponseDTO> listaSimulada = Arrays.asList(reporteDto);

        // Le decimos al servicio falso (Mock) qué debe responder
        when(reporteService.obtenerTodos()).thenReturn(listaSimulada);

        // 2. Act (Ejecutar el método del controlador)
        ResponseEntity<List<ReporteResponseDTO>> respuesta = reporteController.listarReportes();

        // 3. Assert (Verificar que el resultado es correcto)
        assertNotNull(respuesta.getBody());
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        assertEquals("Incendio en prueba unitaria", respuesta.getBody().get(0).getDescripcion());
    }
}