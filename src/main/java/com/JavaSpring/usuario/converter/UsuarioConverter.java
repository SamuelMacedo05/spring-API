package com.JavaSpring.usuario.converter;

import com.JavaSpring.usuario.dto.EnderecoDTO;
import com.JavaSpring.usuario.dto.TelefoneDTO;
import com.JavaSpring.usuario.dto.UsuarioDTO;
import com.JavaSpring.usuario.infracture.entidy.Endereco;
import com.JavaSpring.usuario.infracture.entidy.Telefone;
import com.JavaSpring.usuario.infracture.entidy.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .name(usuarioDTO.getName())
                .email(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .enderecos(paraListaEnderecos(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();

    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO>  telefonesDTO){
        return telefonesDTO.stream().map(this::paraTelefone).toList();

    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public List<Endereco> paraListaEnderecos(List<EnderecoDTO> enderecosDTO){
        return enderecosDTO.stream().map(this::paraEndereco).toList();

    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .name(usuarioDTO.getName())
                .email(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .enderecos(paraListaEnderecosDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuarioDTO.getTelefones()))
                .build();

    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone>  telefonesDTOS){
        return telefonesDTOS.stream().map(this::paraTelefoneDTO).toList();

    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecosDTO(List<Endereco> enderecosDTOS){
        return enderecosDTOS.stream().map(this::paraEnderecoDTO).toList();

    }

    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .build();
    }
}
