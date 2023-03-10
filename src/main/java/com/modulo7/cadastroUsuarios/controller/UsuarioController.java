package com.modulo7.cadastroUsuarios.controller;

import com.modulo7.cadastroUsuarios.DTO.UsuarioDTO;
import com.modulo7.cadastroUsuarios.DTO.UsuarioRespostaDTO;
import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import com.modulo7.cadastroUsuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/get")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<UsuarioRespostaDTO>> buscarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping(path = "/usuarios/get/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Optional<UsuarioModel>> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.ttbuscarPorId(id));
    }

    @PostMapping(path = "/usuarios/create")
    public ResponseEntity<UsuarioRespostaDTO> cadastrarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioModel usuario = usuarioService.cadastrar(dto.converterParaObjeto());
        return new ResponseEntity<>(UsuarioRespostaDTO.converterParaDTO(usuario), HttpStatus.CREATED);
    }

    @PutMapping(path = "/usuarios/put/{id}")
    public ResponseEntity<UsuarioRespostaDTO> alterarUsuario(@RequestBody UsuarioDTO novoUsuario, @PathVariable Long id) {
        UsuarioModel usuario = usuarioService.alterar(id, novoUsuario);
        return ResponseEntity.ok(UsuarioRespostaDTO.converterParaDTO(usuario));
    }

    @DeleteMapping(path = "/usuarios/delete/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
