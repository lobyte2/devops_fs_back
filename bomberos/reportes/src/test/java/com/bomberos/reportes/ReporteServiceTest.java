package com.bomberos.reportes;



import com.bomberos.reportes.model.Reporte;

import com.bomberos.reportes.dto.ReporteRequestDTO;

import com.bomberos.reportes.dto.ReporteResponseDTO;

import com.bomberos.reportes.repository.ReporteRepository;

import com.bomberos.reportes.service.ReporteService;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;



import java.util.List;

import java.util.UUID;



import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)

public class ReporteServiceTest {



    @Mock

    private ReporteRepository reporteRepository;



    @InjectMocks

    private ReporteService reporteService;



    @Test

    void obtenerReportes_RetornaLista() {

        // Arrange

        when(reporteRepository.findAll()).thenReturn(List.of(new Reporte()));



        // Act

        List<ReporteResponseDTO> resultado = reporteService.obtenerTodos();



        // Assert

        assertEquals(1, resultado.size());

        verify(reporteRepository).findAll();

    }



    @Test

    void crearReporte_GuardaCorrectamente() {

        // Arrange

        ReporteRequestDTO requestDTO = new ReporteRequestDTO();

        Reporte reporteEntidad = new Reporte();

        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporteEntidad);



        // Act

        var resultado = reporteService.crearReporte(requestDTO);



        // Assert

        assertNotNull(resultado);

        verify(reporteRepository).save(any(Reporte.class));

    }



    @Test

    void eliminarReporte_LlamaMetodoDeleteDelRepositorio() {

        UUID id = UUID.randomUUID();



        reporteService.eliminarReporte(String.valueOf(id));



        verify(reporteRepository, times(1)).deleteById(id);

    }

}