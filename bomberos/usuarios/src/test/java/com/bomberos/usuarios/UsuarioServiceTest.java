package com.bomberos.usuarios;



import com.bomberos.usuarios.model.Usuario;

import com.bomberos.usuarios.dto.UsuarioRequestDTO;

import com.bomberos.usuarios.dto.UsuarioResponseDTO;

import com.bomberos.usuarios.repository.UsuarioRepository;

import com.bomberos.usuarios.service.UsuarioService;

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

public class UsuarioServiceTest {



    @Mock

    private UsuarioRepository usuarioRepository;



    @InjectMocks

    private UsuarioService usuarioService;



    @Test

    void obtenerUsuarios_RetornaDatos() {

        // Arrange

        Usuario usuario = new Usuario();

        usuario.setId(UUID.randomUUID()); // <-- CORRECCIÓN: Le asignamos un ID válido para evitar el NullPointerException



        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));



        // Act

        // Usamos obtenerUsuarios() tal como lo tienes definido en UsuarioService.java

        List<UsuarioResponseDTO> resultado = usuarioService.obtenerUsuarios();



        // Assert

        assertNotNull(resultado);

        assertEquals(1, resultado.size());

        verify(usuarioRepository).findAll();

    }



    @Test

    void crearUsuario_PersisteEnBaseDeDatos() {

        // Arrange

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();

        Usuario usuarioEntidad = new Usuario();

        usuarioEntidad.setId(UUID.randomUUID()); // <-- CORRECCIÓN: Le asignamos un ID al objeto simulado



        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEntidad);



        // Act

        var resultado = usuarioService.crearUsuario(requestDTO);



        // Assert

        assertNotNull(resultado);

        verify(usuarioRepository).save(any(Usuario.class));

    }



    @Test

    void eliminarUsuario_EjecutaBorradoPorId() {

        UUID id = UUID.randomUUID();



        usuarioService.eliminarUsuario(String.valueOf(id));



        verify(usuarioRepository, times(1)).deleteById(id);

    }

}