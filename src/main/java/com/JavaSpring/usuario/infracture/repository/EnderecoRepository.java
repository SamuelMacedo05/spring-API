package com.JavaSpring.usuario.infracture.repository;


import com.JavaSpring.usuario.infracture.entidy.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
