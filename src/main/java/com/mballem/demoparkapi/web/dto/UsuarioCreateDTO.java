package com.mballem.demoparkapi.web.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class UsuarioCreateDTO {
    private String username;
    private String password;
}
