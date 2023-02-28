package com.modulo7.cadastroUsuarios.service;

import com.modulo7.cadastroUsuarios.DTO.UsuarioDTO;
import com.modulo7.cadastroUsuarios.DTO.UsuarioRespostaDTO;
import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import com.modulo7.cadastroUsuarios.repository.UsuarioRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveriaRetornarAListaDeUsuarios() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setNome("Antonio");
        usuario.setLogin("antonio");
        usuario.setSenha("12345678");

        List<UsuarioModel> listaDeResposta = new ArrayList<UsuarioModel>();
        listaDeResposta.add(usuario);

        Mockito.when(repository.findAll())
                .thenReturn(listaDeResposta);

        List<UsuarioRespostaDTO> respostaFinal = service.buscarTodos();

        Assertions.assertFalse(respostaFinal.isEmpty());
    }


    @Test
    public void deveriaSalvarUmUsuario() {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(1L);
        usuarioModel.setNome("Antonio");
        usuarioModel.setLogin("antonio");
        usuarioModel.setSenha("12345678");

        Mockito.when(repository.save(usuarioModel))
                .thenReturn(usuarioModel);

        Assertions.assertNotEquals(usuarioModel.getSenha(), service.cadastrar(usuarioModel).getSenha());
    }

}