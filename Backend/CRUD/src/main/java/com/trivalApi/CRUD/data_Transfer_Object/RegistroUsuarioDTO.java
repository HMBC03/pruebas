package com.trivalApi.CRUD.data_Transfer_Object;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegistroUsuarioDTO {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String password;
}
