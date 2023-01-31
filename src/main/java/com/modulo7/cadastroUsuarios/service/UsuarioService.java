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

    public Optional<UsuarioModel> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel cadastrar(UsuarioModel usuarioModel) {
        usuarioModel.setSenha(passwordEncoder().encode(usuarioModel.getSenha()));
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel alterar(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
