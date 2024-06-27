package com.gerencial.projetos.crud.aplicacao.infra.repository.jpa;

import com.gerencial.projetos.crud.aplicacao.dominio.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, String> {
}
