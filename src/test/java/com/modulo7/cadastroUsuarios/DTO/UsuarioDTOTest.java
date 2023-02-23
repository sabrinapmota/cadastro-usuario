package com.modulo7.cadastroUsuarios.DTO;

import com.modulo7.cadastroUsuarios.model.UsuarioModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDTOTest {
    @Test
    public  void deveriaInstanciarOObjeto(){
    UsuarioDTO dto = new UsuarioDTO();
    dto.setNome("sabrina");
    dto.setLogin("sabrina");
    dto.setSenha("12345678");

    Assertions.assertEquals("sabrina", dto.getNome());
        Assertions.assertEquals("sabrina", dto.getLogin());
        Assertions.assertEquals("12345678", dto.getSenha());
        }

        @Test
        public void deveriaConverterParaUsuarioModel() {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setNome("sabrina");
            dto.setLogin("sabrina");
            dto.setSenha("12345678");

            Assertions.assertEquals(new UsuarioModel().getClass(), dto.converterParaObjeto().getClass());
        }


}