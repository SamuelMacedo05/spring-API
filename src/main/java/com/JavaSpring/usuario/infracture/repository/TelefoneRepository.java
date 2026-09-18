package com.JavaSpring.usuario.infracture.repository;


import com.JavaSpring.usuario.infracture.entidy.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long > {
}
