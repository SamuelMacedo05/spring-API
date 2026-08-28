package com.JavaSpring.usuario.business;

import com.JavaSpring.usuario.converter.UsuarioConverter;
import com.JavaSpring.usuario.dto.UsuarioDTO;
import com.JavaSpring.usuario.exception.ConflictException;
import com.JavaSpring.usuario.exception.ResourceNotException;
import com.JavaSpring.usuario.infracture.entidy.Usuario;
import com.JavaSpring.usuario.infracture.repository.UsuarioRepository;
import com.JavaSpring.usuario.infracture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


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

    public void deletarUsuarioPorEmail (String email){

        usuarioRepository.deleteByEmail(email);
    }
    public boolean verificarExisteEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotException("Email não encrontrado "));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }


}
