package com.omar.notas.respositories;

import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

    @Query("select u from Nota u where u.usuario.id =?1")
    List<Nota> findNotasByUsuarioId(Long id);

}
