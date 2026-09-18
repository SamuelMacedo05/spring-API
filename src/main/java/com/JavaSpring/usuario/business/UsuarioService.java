package com.JavaSpring.usuario.business;

import com.JavaSpring.usuario.converter.UsuarioConverter;
import com.JavaSpring.usuario.dto.EnderecoDTO;
import com.JavaSpring.usuario.dto.TelefoneDTO;
import com.JavaSpring.usuario.dto.UsuarioDTO;
import com.JavaSpring.usuario.exception.ConflictException;
import com.JavaSpring.usuario.exception.ResourceNotException;
import com.JavaSpring.usuario.infracture.entidy.Endereco;
import com.JavaSpring.usuario.infracture.entidy.Telefone;
import com.JavaSpring.usuario.infracture.entidy.Usuario;
import com.JavaSpring.usuario.infracture.repository.EnderecoRepository;
import com.JavaSpring.usuario.infracture.repository.TelefoneRepository;
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
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public Usuario salvarUsuario(Usuario usuario) {
        try {
            verificarEmail(usuario.getEmail());
            return usuarioRepository.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }

    }

    public void verificarEmail(String email) {
        try {
            boolean existe = existeEmail(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void deletarUsuarioporEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotException("Email não encontrado" + email)));

        } catch (ResourceNotException e) {
            throw new ResourceNotException("Email não encontrado" + email);

        }

    }


    public void deletarUsuarioPorEmail(String email) {

        usuarioRepository.deleteByEmail(email);
    }

    public boolean verificarExisteEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotException("Email não encrontrado "));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }

    public EnderecoDTO atulizarEndereco(long idEndereco, EnderecoDTO enderecoDTO) {

        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotException("Id não encontrado" + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, entity);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));

    }

    public TelefoneDTO atulizarTelefone(long idtelefone, TelefoneDTO telefoneDTO) {
        Telefone entity = telefoneRepository.findById(idtelefone).orElseThrow(() ->
                new ResourceNotException("Telefone não encrntrado" + idtelefone));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO, entity);
        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

    public EnderecoDTO cadastrarEndereco(EnderecoDTO dto, String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotException("Id não encontrado" + email));

        Endereco endereco = usuarioConverter.paraEnderecoEntity(dto, usuario.getId());
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));

    }

    public TelefoneDTO cadastrarTelefone(String token, TelefoneDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotException("Email não encrontardo" + email));

        Telefone telefone = usuarioConverter.paraTelefoneEntity(dto,usuario.getId());
        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));

    }


}
