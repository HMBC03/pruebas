package com.trivalApi.CRUD.models.Usuario;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;
//Añadir anotaciones de lombok
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)//
    private Long id;
    
    
    private String telefono;
    private String nombre;
    private String apellidos;
    @Column(unique=true)
    private String correo;
    private String password;

    //Necesarios para Spring Security

    @Column(name="is_enabled")
    private boolean isEnabled;

    @Column(name="account_No_Expired")
    private boolean accountNoExpired;

    @Column(name="account_No_Locked")
    private boolean accountNoLocked;

    @Column(name="credential_No_Expired")
    private boolean credentialNoExpired;

    //Contruyendo las relaciones en la BD

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_rol", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="rol_id"))
    private Set<RolModel> roles = new HashSet<>();





    

    

    




    

}
