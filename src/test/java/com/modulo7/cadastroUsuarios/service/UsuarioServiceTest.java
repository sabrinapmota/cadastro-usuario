package com.modulo7.cadastroUsuarios.service;

import com.modulo7.cadastroUsuarios.DTO.UsuarioDTO;
import com.modulo7.cadastroUsuarios.DTO.UsuarioRespostaDTO;
import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import com.modulo7.cadastroUsuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;


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

    @Test
    public void deveriaBuscarPorId() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setNome("Antonio");
        usuario.setLogin("antonio");
        usuario.setSenha("12345678");

        Mockito.when(repository.findById(1L))
                .thenReturn(of(usuario));

        Optional<UsuarioModel> resposta = service.ttbuscarPorId(1L);

        Assertions.assertEquals(usuario.getNome(), resposta.get().getNome());
    }

    @Test
    public void deveriaAtualizarOUsuario() {
        UsuarioModel usuarioAntigo = new UsuarioModel();
        usuarioAntigo.setId(1L);
        usuarioAntigo.setNome("Sabrina");
        usuarioAntigo.setLogin("sabrina");
        usuarioAntigo.setSenha("12345678");

        Mockito.when(repository.getById(1L)).thenReturn(usuarioAntigo);

        UsuarioModel usuarioRespondido = new UsuarioModel();
        usuarioRespondido.setId(1L);
        usuarioRespondido.setNome("Sabrina Porto");
        usuarioRespondido.setLogin("sabrinap");
        usuarioRespondido.setSenha("12345679");

Mockito.when(repository.save(Mockito.any(UsuarioModel.class)))
        .thenReturn(usuarioRespondido);

        UsuarioDTO usuarioEnviado = new UsuarioDTO();
        usuarioEnviado.setLogin("sabrinap");
        usuarioEnviado.setSenha("12345679");

        UsuarioModel usuarioFinal = service.alterar(1L, usuarioEnviado);

        Assertions.assertEquals("Sabrina Porto", usuarioFinal.getNome());
    }

    @Test
    public void deveriaDeletarPorId() {
        service.deletar(1L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }

}