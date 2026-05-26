package com.bomberos.usuarios.controller;

import com.bomberos.usuarios.dto.AuthResponseDTO; //Importación para manejar el Token
import com.bomberos.usuarios.dto.LoginRequestDTO;
import com.bomberos.usuarios.dto.UsuarioRequestDTO;
import com.bomberos.usuarios.dto.UsuarioResponseDTO;
import com.bomberos.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping
    public UsuarioResponseDTO crearUsuario(@RequestBody UsuarioRequestDTO requestDTO) {
        return usuarioService.crearUsuario(requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequestDTO credenciales) {
        // AUTENTICACIÓN: Recibe la credencial digital generada por el servicio
        AuthResponseDTO response = usuarioService.login(credenciales);

        if (response != null) {
            // Retorna el objeto AuthResponseDTO con el Token en formato JSON
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}