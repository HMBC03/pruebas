package com.trivalApi.CRUD.models.Usuario;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="rol")

public class RolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rol_nombre")
    @Enumerated(EnumType.STRING)
    private RolEnum rolEnum;

    @ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinTable(name="rol_permisos", joinColumns = @JoinColumn(name="rol_id"), inverseJoinColumns = @JoinColumn(name="permiso_id"))
    private Set<PermisosModel> permisoList = new HashSet<>();
    
}
