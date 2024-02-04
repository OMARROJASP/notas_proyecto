package com.omar.notas.services;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

 List<Nota> obtenerNotasUsuario(Long usuarioId) ;

 Optional<Usuario> encontrarUsuario(Long id);

}
