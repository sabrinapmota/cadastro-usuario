package com.modulo7.cadastroUsuarios.controller;

import com.modulo7.cadastroUsuarios.DTO.UsuarioDTO;
import com.modulo7.cadastroUsuarios.DTO.UsuarioRespostaDTO;
import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import com.modulo7.cadastroUsuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> buscarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<UsuarioModel>> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> cadastrarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioModel usuario = usuarioService.cadastrar(dto.converterParaObjeto());
        return new ResponseEntity<>(UsuarioRespostaDTO.converterParaDTO(usuario), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioRespostaDTO> alterarUsuario(@RequestBody UsuarioDTO novoUsuario, @PathVariable Long id) {
        UsuarioModel usuario = usuarioService.alterar(id, novoUsuario);
        return ResponseEntity.ok(UsuarioRespostaDTO.converterParaDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
