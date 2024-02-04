package com.omar.notas.model.dto.mapper;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.entities.Nota;

public class DtoMapperNota {
    private Nota nota;

    private DtoMapperNota(){

    }

    public static DtoMapperNota builder(){
        return new DtoMapperNota();
    }

    public DtoMapperNota setNota(Nota nota){
        this.nota = nota;
        return this;
    }

    public NotasDto build(){
        if(nota == null){
            throw  new RuntimeException("DEBE PASAR EL ENTITY NOTA!");
        }
        return new NotasDto(this.nota.getId(), nota.getTitulo(), nota.getDescripcion(), nota.getUsuario().getId());
    }
}
