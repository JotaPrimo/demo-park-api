package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional // usamos essa anotação para indicar que aqui ocorrem operaçõe no banco
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public Usuario editarSenha(Long id, String password) {
        // consulta para pegar o registro
        Usuario usuario = buscarPorId(id);
        // atualiza senha
        usuario.setPassword(password);
        // não usei o update pq no metodo buscarPorId foi usado o findById do JPARepository
        // esse metodo coloca o objeto usuario em estado persistente
        // o hibernate o controla, por isso ao usar o set, o hibernate salva no banco
        // poderia usar o merge
        return usuario;
    }

}
