package com.trivalApi.CRUD.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.trivalApi.CRUD.data_Transfer_Object.*;
import com.trivalApi.CRUD.models.Usuario.UsuarioModel;
import com.trivalApi.CRUD.services.RegistroUsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistroUsuarioService registroService;
    private final AuthenticationManager authManager;

    public AuthController(RegistroUsuarioService registroService,
                          AuthenticationManager authManager) {
        this.registroService = registroService;
        this.authManager    = authManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroUsuarioDTO dto) {
        UsuarioModel user = registroService.registrar(dto);
        return ResponseEntity.ok("Registrado OK, ID=" + user.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO creds) {
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(creds.getCorreo(), creds.getPassword());
        Authentication auth = authManager.authenticate(token);
        UserDetails ud = (UserDetails) auth.getPrincipal();
        // Aquí podrías devolver un JWT en lugar de este mensaje:
        return ResponseEntity.ok("Login OK: " + ud.getUsername());
    }
}
