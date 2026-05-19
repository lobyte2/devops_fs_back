package com.bomberos.usuarios;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bomberos.usuarios.controller.UsuarioController;
import com.bomberos.usuarios.dto.UsuarioRequestDTO;
import com.bomberos.usuarios.dto.UsuarioResponseDTO;
import com.bomberos.usuarios.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void testObtenerTodosLosUsuarios() {
        // Arrange
        UsuarioResponseDTO user1 = new UsuarioResponseDTO();
        user1.setId("uuid-1");
        user1.setNombre("Juan");

        UsuarioResponseDTO user2 = new UsuarioResponseDTO();
        user2.setId("uuid-2");
        user2.setNombre("Pedro");

        when(usuarioService.obtenerUsuarios()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UsuarioResponseDTO> resultado = usuarioController.obtenerTodosLosUsuarios();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        verify(usuarioService, times(1)).obtenerUsuarios();
    }

    @Test
    void testCrearUsuario() {
        // Arrange
        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNombre("Maria");
        request.setEmail("maria@mail.com");

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId("uuid-3");
        response.setNombre("Maria");
        response.setEmail("maria@mail.com");

        when(usuarioService.crearUsuario(any(UsuarioRequestDTO.class))).thenReturn(response);

        // Act
        UsuarioResponseDTO resultado = usuarioController.crearUsuario(request);

        // Assert
        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNombre());
        verify(usuarioService, times(1)).crearUsuario(any(UsuarioRequestDTO.class));
    }
}