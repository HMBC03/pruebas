package com.trivalApi.CRUD;

import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.trivalApi.CRUD.models.Usuario.PermisosModel;
import com.trivalApi.CRUD.models.Usuario.RolEnum;
import com.trivalApi.CRUD.models.Usuario.RolModel;
import com.trivalApi.CRUD.repositories.PermisoRepository;
import com.trivalApi.CRUD.repositories.RolRepository;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(
        PermisoRepository permisoRepo,
        RolRepository rolRepo
    ) {
        return args -> {
            // 1) Crear los permisos si no existen
            List<String> names = List.of("READ", "CREATE", "UPDATE", "DELETE", "REFACTOR");
            names.forEach(name -> {
                if (!permisoRepo.existsByName(name)) {
                    PermisosModel permiso = PermisosModel.builder()
                        .name(name)
                        .build();
                    permisoRepo.save(permiso);
                }
            });

            // 2) Crear rol USER con READ, CREATE, UPDATE
            if (rolRepo.findByRolEnum(RolEnum.USER).isEmpty()) {
                var permisos = permisoRepo.findByNameIn(List.of("READ", "CREATE", "UPDATE"));
                RolModel rolUser = RolModel.builder()
                    .rolEnum(RolEnum.USER)
                    .permisoList(new HashSet<>(permisos))
                    .build();
                rolRepo.save(rolUser);
            }

            
        };
    }
}
