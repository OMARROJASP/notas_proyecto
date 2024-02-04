package com.omar.notas.services;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.dto.UsuarioDto;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import com.omar.notas.model.request.NotaRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface NotasService  {
    List<NotasDto> encontrarTodasNotas();

    Optional<NotasDto> encontrarNota(Long id);

    NotasDto guardarNota(Nota nota);

    Optional<NotasDto> actualizarNota(NotaRequest nota, Long id);

    void eliminarNota(Long id);

    List<Nota> encontrarNotasPorUsuario(Usuario usuario);
}
