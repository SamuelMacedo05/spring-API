package com.JavaSpring.usuario.dto;


import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String name;
    private String email;
    private String password;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;

}
