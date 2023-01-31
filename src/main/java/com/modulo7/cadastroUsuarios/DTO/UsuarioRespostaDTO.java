package com.modulo7.cadastroUsuarios.DTO;

import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UsuarioRespostaDTO {
    private Long id;

    private String nome;

    private String login;

    private String senha;

    public static UsuarioRespostaDTO converterParaDTO(UsuarioModel usuario) {
        return new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha());
    }

    public static List<UsuarioRespostaDTO> converterLista(List<UsuarioModel> usuarios) {
        List<UsuarioRespostaDTO> novaLista = new ArrayList<>();
        for (UsuarioModel usuario : usuarios) {
            UsuarioRespostaDTO novoUsuario = new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha());
            novaLista.add(novoUsuario);
        }
        return novaLista;
    }
}
