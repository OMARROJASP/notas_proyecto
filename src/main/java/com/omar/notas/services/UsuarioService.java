package com.omar.notas.services;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.dto.UsuarioDto;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import com.omar.notas.model.request.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

 //List<Nota> obtenerNotasUsuario(Long usuarioId) ;
 List<UsuarioDto> obtenerUsuarios() ;
 Optional<UsuarioDto> encontrarUsuario(Long id);

 UsuarioDto guardarUsuario(Usuario usuario);

 Optional<UsuarioDto> actualizarUsuario(UsuarioRequest user, Long id);

 void eliminarUsuario(Long id);
}
