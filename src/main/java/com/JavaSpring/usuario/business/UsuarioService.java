package com.JavaSpring.usuario.business;

import com.JavaSpring.usuario.converter.UsuarioConverter;
import com.JavaSpring.usuario.dto.UsuarioDTO;
import com.JavaSpring.usuario.exception.ConflictException;
import com.JavaSpring.usuario.exception.ResourceNotException;
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

    public Usuario salvarUsuario(Usuario usuario){
        try {
            verificarEmail(usuario.getEmail());
            return usuarioRepository.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException( "Email já cadastrado ", e.getCause());
        }

    }

    public void verificarEmail(String email ) {
        try {
            boolean existe = existeEmail(email);
            if (existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException( "Email já cadastrado", e.getCause());
        }
    }

    public boolean existeEmail(String email ) {
        return usuarioRepository.existsByEmail(email);
    }

    public void deletarUsuarioporEmail (String email){
        usuarioRepository.deleteByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotException("Email não encontrado" + email));
    }



}
