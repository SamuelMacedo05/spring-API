package com.JavaSpring.usuario.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private String rua;
    private String cidade;
    private long numero;
    private String complemento;
    private String cep;
}
