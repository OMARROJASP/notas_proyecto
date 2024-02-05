package com.omar.notas.controllers;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Usuario;
import com.omar.notas.model.request.NotaRequest;
import com.omar.notas.services.NotasService;
import com.omar.notas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notas")
@CrossOrigin(originPatterns = "*")
public class NotasController {
    @Autowired
    private NotasService notasService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<NotasDto> traerTodasNotas(){
        return notasService.encontrarTodasNotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> traerNota(@PathVariable("id") Long id){
        Optional<NotasDto> notasDtoOptional = notasService.encontrarNota(id);

        if(notasDtoOptional.isPresent()){
            return ResponseEntity.ok(notasDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

/*
    @GetMapping("/usuario/{id}")
    public List<Nota> listaUsuarioNota(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.encontrarUsuario(id);
        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            return notasService.encontrarNotasPorUsuario(usuario);
        }else {
            return Collections.emptyList();
        }

    }
 */

    @PostMapping
    public ResponseEntity<?> guardarNotas(@Valid @RequestBody Nota nota, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(notasService.guardarNota(nota));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarNotas(@Valid @RequestBody NotaRequest nota, BindingResult result, @PathVariable("id") Long id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<NotasDto> o = notasService.actualizarNota(nota, id);

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNota(@PathVariable("id") Long id){
        Optional<NotasDto> o = notasService.encontrarNota(id);

        if(o.isPresent()){
            notasService.eliminarNota(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return  ResponseEntity.badRequest().body(errors);
    }


}
