package com.mballem.demoparkapi.web.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSenhaDTO {

    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;
}
