package com.modulo7.cadastroUsuarios.DTO;

import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDTO {
    @NotBlank   (message = "Nome do usuário deve ser preenchido.")
    @Length(min = 4, max = 20, message = "Nome do usuário deve conter de {min} a {max} caracteres.")
    private String nome;
    @NotBlank(message = "Login deve ser preenchido.")
    @Length(min = 4, max = 12, message = "Login deve conter de {min} a {max} caracteres.")
    private String login;
    @NotBlank(message = "Senha deve ser preenchida.")
    @Length(min = 8, max = 255, message = "Senha deve conter de {min} a {max} caracteres.")
    private String senha;

    public UsuarioModel converterParaObjeto() {
        return new UsuarioModel(nome, login, senha);
    }
}
