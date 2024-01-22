package com.mballem.demoparkapi.web.controller;

import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.service.UsuarioService;
import com.mballem.demoparkapi.web.dto.UsuarioCreateDTO;
import com.mballem.demoparkapi.web.dto.UsuarioResponseDTO;
import com.mballem.demoparkapi.web.dto.UsuarioSenhaDTO;
import com.mballem.demoparkapi.web.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Contem todas as operações crud de usuario")
@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        List<Usuario> listUsuarios = usuarioService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toListDto(listUsuarios));
    }

    @Operation(summary = "Criar um novo usuario", description = "Recurso para criar um novo usuario",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
                @ApiResponse(responseCode = "409", description = "Email já cadastrado no sistema",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
                @ApiResponse(responseCode = "422", description = "Recurso não processado por dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))
        })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario user = usuarioService.create(UsuarioMapper.toUsuario(usuarioCreateDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDTO dto) {
        Usuario userUpdate = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }
}
