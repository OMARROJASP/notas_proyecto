package com.omar.notas.services.Impl;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.dto.UsuarioDto;
import com.omar.notas.model.dto.mapper.DtoMapperNota;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import com.omar.notas.model.request.NotaRequest;
import com.omar.notas.respositories.NotasRepository;
import com.omar.notas.services.NotasService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaServiceImpl implements NotasService {

    @Autowired
    private NotasRepository notasRepository;


    @Override
    public List<NotasDto> encontrarTodasNotas() {
       List<Nota> notas = (List<Nota>) notasRepository.findAll();
       return notas
               .stream()
               .map(n-> DtoMapperNota.builder().setNota(n).build())
               .collect(Collectors.toList());
    }

    @Override
    public Optional<NotasDto> encontrarNota(Long id) {
        return notasRepository.findById(id).map(u->DtoMapperNota
                .builder()
                .setNota(u)
                .build());
    }

    @Override
    public NotasDto guardarNota(Nota nota) {
     return DtoMapperNota.builder().setNota(notasRepository.save(nota)).build();

    }

    @Override
    public Optional<NotasDto> actualizarNota(NotaRequest nota, Long id){
        Optional<Nota> n = notasRepository.findById(id);
        Nota notaOptional = null;
        if(n.isPresent()){
            Nota notaDB = n.orElseThrow();
            notaDB.setTitulo(nota.getTitulo());
            notaDB.setDescripcion(nota.getDescripcion());
            notaOptional = notasRepository.save(notaDB);
        }
        return Optional.ofNullable(DtoMapperNota.builder().setNota(notaOptional).build());
    }

    @Override
    public void eliminarNota(Long id) {
        notasRepository.deleteById(id);
    }

    @Override
    public List<Nota> encontrarNotasPorUsuario(Usuario usuario) {

        return notasRepository.findByUsuario(usuario);
    }


}
