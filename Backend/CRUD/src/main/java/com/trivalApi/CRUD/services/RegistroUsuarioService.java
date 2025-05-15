package com.trivalApi.CRUD.services;

import java.util.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.trivalApi.CRUD.data_Transfer_Object.*;
import com.trivalApi.CRUD.models.Usuario.UsuarioModel;
import com.trivalApi.CRUD.models.Usuario.*;

import com.trivalApi.CRUD.repositories.UsuarioRepository;
import com.trivalApi.CRUD.repositories.RolRepository;

@Service
public class RegistroUsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;

    public RegistroUsuarioService(UsuarioRepository usuarioRepo,
                                  RolRepository rolRepo,
                                  PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo     = rolRepo;
        this.encoder     = encoder;
    }

    public UsuarioModel registrar(RegistroUsuarioDTO dto) {
        if (usuarioRepo.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new RuntimeException("Correo ya registrado");
        }
        RolModel rolUser = rolRepo.findByRolEnum(RoleEnum.USER)
            .orElseThrow(() -> new RuntimeException("Rol USER no existe"));

        UsuarioModel u = UsuarioModel.builder()
            .nombre(dto.getNombre())
            .apellidos(dto.getApellidos())
            .telefono(dto.getTelefono())
            .correo(dto.getCorreo())
            .password(encoder.encode(dto.getPassword()))
            .isEnabled(true)
            .accountNoExpired(true)
            .accountNoLocked(true)
            .credentialNoExpired(true)
            .roles(Set.of(rolUser))
            .build();

        return usuarioRepo.save(u);
    }
}
