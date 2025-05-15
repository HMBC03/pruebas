package com.trivalApi.CRUD.data_Transfer_Object;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginRequestDTO {
    private String correo;
    private String password;
}
