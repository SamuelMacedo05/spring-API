package com.JavaSpring.usuario.infracture.entidy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "telefone")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "numero", length = 10)
    private String numero;
    @Column(name = "ddd", length = 10)
    private String ddd;

}
