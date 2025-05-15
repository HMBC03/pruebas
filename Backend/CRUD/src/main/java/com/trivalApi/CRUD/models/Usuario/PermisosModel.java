package com.trivalApi.CRUD.models.Usuario;

import org.springframework.aot.generate.GenerationContext;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Permisos")

public class PermisosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, nullable=false, updatable= false)
    private String name;

    
}
