package com.modulo7.cadastroUsuarios.service;

import com.modulo7.cadastroUsuarios.DTO.UsuarioDTO;
import com.modulo7.cadastroUsuarios.DTO.UsuarioRespostaDTO;
import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import com.modulo7.cadastroUsuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<UsuarioRespostaDTO> buscarTodos() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return UsuarioRespostaDTO.converterLista(usuarios);
    }

    public Optional<UsuarioModel> ttbuscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel cadastrar(UsuarioModel usuarioModel) {
        usuarioModel.setSenha(passwordEncoder().encode(usuarioModel.getSenha()));
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel alterar(long id, UsuarioDTO novoUsuario) {
        UsuarioModel antigoUsuario = usuarioRepository.getById(id);
        UsuarioModel usuarioASalvar = new UsuarioModel();
        usuarioASalvar.setId(antigoUsuario.getId());
        usuarioASalvar.setNome(novoUsuario.getNome());
        usuarioASalvar.setLogin(novoUsuario.getLogin());
        usuarioASalvar.setSenha(passwordEncoder().encode(novoUsuario.getSenha()));
        return usuarioRepository.save(usuarioASalvar);
    }


    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
