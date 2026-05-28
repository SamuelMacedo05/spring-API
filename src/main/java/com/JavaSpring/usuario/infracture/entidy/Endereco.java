package com.JavaSpring.usuario.infracture.entidy;


import jakarta.persistence.*;
import lombok.*;





@Entity
@Table(name = "endereco")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rua", length = 100)
    private String rua;
    @Column(name = "cidade", length = 100)
    private String cidade;
    @Column(name = "numero", length = 100)
    private long numero;
    @Column(name = "complemento", length = 100)
    private String complemento;
    @Column(name = "cep", length = 100)
    private String cep;


}
