package com.bomberos.usuarios.service;

import com.bomberos.usuarios.dto.AuthResponseDTO; // <-- Agregado para enviar el Token
import com.bomberos.usuarios.dto.LoginRequestDTO;
import com.bomberos.usuarios.dto.UsuarioRequestDTO;
import com.bomberos.usuarios.dto.UsuarioResponseDTO;
import com.bomberos.usuarios.model.Usuario;
import com.bomberos.usuarios.repository.UsuarioRepository;
import com.bomberos.usuarios.security.JwtUtil; // <-- Agregado para usar el generador de Token
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil; // <-- Inyectamos el componente JWT

    private UsuarioResponseDTO mapToDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId().toString()); // Convertimos UUID a String
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        return dto;
    }

    public List<UsuarioResponseDTO> obtenerUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setPassword(request.getPassword());
        if (request.getRol() != null) {
            usuario.setRol(request.getRol());
        }

        Usuario guardado = usuarioRepository.save(usuario);
        return mapToDTO(guardado);
    }

    public void eliminarUsuario(String id) {
        usuarioRepository.deleteById(UUID.fromString(id));
    }

    // <-- MÉTODO LOGIN MODIFICADO PARA DEVOLVER EL TOKEN
    public AuthResponseDTO login(LoginRequestDTO credenciales) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(credenciales.getEmail());

        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(credenciales.getPassword())) {
            Usuario usuario = usuarioOpt.get();

            // AUTENTICACIÓN EXITOSA: Ya sabemos quién es.
            // Generamos la credencial digital (Token) que incluye su rol (Autorización)
            String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol());

            // Retornamos el DTO que incluye el email, el rol y el Token
            return new AuthResponseDTO(usuario.getEmail(), usuario.getRol(), token);
        }
        return null; // Retorna nulo si fallan las credenciales
    }
}