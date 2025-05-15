package com.trivalApi.CRUD.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Service;

import com.trivalApi.CRUD.models.Usuario.UsuarioModel;
import com.trivalApi.CRUD.repositories.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UsuarioModel usuarioModel = usuarioRepository.findByCorreo(username)
                    .orElseThrow(()-> new UsernameNotFoundException("El Usuario "+username +" no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        
        usuarioModel.getRoles()
                    .forEach(role->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolEnum().name()))));


        usuarioModel.getRoles().stream()
                    .flatMap(role->role.getPermisoList().stream())
                    .forEach(permission-> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(usuarioModel.getCorreo(),
                    usuarioModel.getPassword(),
                    usuarioModel.isEnabled(),
                    usuarioModel.isAccountNoExpired(),
                    usuarioModel.isCredentialNoExpired(),
                    usuarioModel.isAccountNoLocked(),
                    authorityList
                    );
    }

    
}
