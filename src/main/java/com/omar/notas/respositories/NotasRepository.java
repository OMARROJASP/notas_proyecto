package com.omar.notas.respositories;

import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotasRepository  extends CrudRepository<Nota,Long> {

    List<Nota> findByUsuario(Usuario usuario);
}
