package com.omar.notas.model.dto.mapper;

import com.omar.notas.model.dto.UsuarioDto;
import com.omar.notas.model.entities.Usuario;

public class DtoMapperUsuario {

    private Usuario usuario;

    public DtoMapperUsuario() {
    }

 public static DtoMapperUsuario builder(){
        return new DtoMapperUsuario();
 }

 public DtoMapperUsuario setUsuario(Usuario usuario){
        this.usuario = usuario;
        return this;
 }

 public UsuarioDto build(){
        if(usuario == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY USUARIO!");
        }
        return new UsuarioDto(this.usuario.getId(),usuario.getNombre(),usuario.getCorreo());
 }

}
