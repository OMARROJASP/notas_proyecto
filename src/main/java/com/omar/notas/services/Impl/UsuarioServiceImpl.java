package com.omar.notas.services.Impl;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import com.omar.notas.respositories.UsuarioRepository;
import com.omar.notas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Nota> obtenerNotasUsuario(Long usuarioId) {
        return usuarioRepository.findNotasByUsuarioId(usuarioId);
    }

    @Override
    public Optional<Usuario> encontrarUsuario(Long id) {
        return usuarioRepository.findById(id);
    }


}
