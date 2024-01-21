package com.mballem.demoparkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSenhaDTO {

    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 6, max = 6, message = "Deve ter 6 caracteres")
    private String senhaAtual;

    @NotBlank(message = "Nova senha é obrigatório")
    @Size(min = 6, max = 6, message = "Deve ter 6 caracteres")
    private String novaSenha;

    @NotBlank(message = "Confirma senha é obrigatório")
    @Size(min = 6, max = 6, message = "Deve ter 6 caracteres")
    private String confirmaSenha;
}
