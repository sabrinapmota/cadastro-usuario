package com.modulo7.cadastroUsuarios.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(length = 12, nullable = false)
    private String login;

    @Column(length = 20, nullable = false)
    private String senha;

    public UsuarioModel(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
