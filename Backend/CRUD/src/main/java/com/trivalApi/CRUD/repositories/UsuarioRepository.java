package com.trivalApi.CRUD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trivalApi.CRUD.models.Usuario.UsuarioModel;




@Repository
public interface  UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    //Create method 
    Optional<UsuarioModel> findByCorreo(String correo);

    
}
