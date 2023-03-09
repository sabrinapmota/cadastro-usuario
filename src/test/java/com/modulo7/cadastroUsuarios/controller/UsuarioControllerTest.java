package com.modulo7.cadastroUsuarios.controller;

import com.modulo7.cadastroUsuarios.DTO.UsuarioRespostaDTO;
import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import com.modulo7.cadastroUsuarios.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class UsuarioControllerTest {
    @Mock
    private UsuarioService service;

    @InjectMocks
    private UsuarioController controller;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveriaRetornarUm200() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setNome("Antonio");
        usuario.setLogin("antonio");
        usuario.setSenha("12345678");
        List<UsuarioModel> usuariosModel = new ArrayList<>();
        usuariosModel.add(usuario);
        List<UsuarioRespostaDTO> usuarios = UsuarioRespostaDTO.converterLista(usuariosModel);

        Mockito.when(service.buscarTodos()).thenReturn(usuarios);

        Assertions.assertFalse(controller.buscarTodosUsuarios().getBody().isEmpty());
}

@Test
    public void deveriaAtivarODeletar() {
        controller.deletarUsuario(1L);

        Mockito.verify(service, Mockito.times(1)).deletar(1L);

}

}