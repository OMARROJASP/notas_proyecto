package com.omar.notas.services.Impl;

import com.omar.notas.model.dto.NotasDto;
import com.omar.notas.model.dto.UsuarioDto;
import com.omar.notas.model.dto.mapper.DtoMapperUsuario;
import com.omar.notas.model.entities.Nota;
import com.omar.notas.model.entities.Role;
import com.omar.notas.model.entities.Usuario;
import com.omar.notas.model.request.UsuarioRequest;
import com.omar.notas.respositories.RoleRepository;
import com.omar.notas.respositories.UsuarioRepository;
import com.omar.notas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UsuarioDto> obtenerUsuarios( ) {

        List<Usuario>  user = (List<Usuario>) usuarioRepository.findAll();
        return user
                .stream()
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public Optional<UsuarioDto> encontrarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build());
    }

    @Override
    public UsuarioDto guardarUsuario(Usuario usuario) {

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        Optional<Role> o = roleRepository.findByName("ROLE_ADMIN");

        List<Role> roles = new ArrayList<>();
        if(o.isPresent()){
            roles.add(o.orElseThrow());
        }

        usuario.setRoles(roles);
        return DtoMapperUsuario.builder().setUsuario(usuarioRepository.save(usuario)).build();

    }

    @Override
    public Optional<UsuarioDto> actualizarUsuario(UsuarioRequest user, Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);

        Usuario usuarioOptional = null;
        if(o.isPresent()){
            Usuario usuarioDb= o.orElseThrow();
            usuarioDb.setNombre(user.getNombre());
            usuarioDb.setCorreo(user.getCorreo());
            usuarioOptional = usuarioRepository.save(usuarioDb);
        }
        return Optional.ofNullable(DtoMapperUsuario.builder().setUsuario(usuarioOptional).build());
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


}
