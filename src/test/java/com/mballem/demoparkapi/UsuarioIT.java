package com.mballem.demoparkapi;

import com.mballem.demoparkapi.web.controller.UsuarioController;
import com.mballem.demoparkapi.web.dto.UsuarioCreateDTO;
import com.mballem.demoparkapi.web.dto.UsuarioResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // tomcant do teste ser� executado em porta randomica
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) // executa sql antes do metodo
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) // executa sql ap�s o methodo do teste
public class UsuarioIT {
    // IT por ser integra��o
    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsuarioComUsernameEPasswordvalidosRetornarUsuarioCriadoComStatus201() {
      UsuarioResponseDTO responseBody = testClient
                .post()
                .uri(UsuarioController.URI_USUARIOS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDTO("tody@gmail.com", "12345678910"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDTO.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull(); // checando se a resposta de retorno n�o � null
        Assertions.assertThat(responseBody.getId()).isNotNull(); // checando se a resposta de retorno tem um id n�o � null
        Assertions.assertThat(responseBody.getUsername()).isEqualTo("tody@gmail.com"); // checando se o username de retorno � igual ao paramentro
        Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE"); // checando se o username de retorno � igual ao paramentro
    }

}
