package com.JavaSpring.usuario.business;

import com.JavaSpring.usuario.converter.UsuarioConverter;
import com.JavaSpring.usuario.dto.UsuarioDTO;
import com.JavaSpring.usuario.infracture.entidy.Usuario;
import com.JavaSpring.usuario.infracture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}
