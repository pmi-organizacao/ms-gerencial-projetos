package com.gerencial.projetos.crud.aplicacao.infra.repository.jpa;

import com.gerencial.projetos.crud.aplicacao.dominio.OrdemDeServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {
    @Query(value = "SELECT * FROM tb_ordem_de_servico " +
            "where projeto_id = ?", nativeQuery = true)
    List<OrdemDeServico> findByCodigoProjeto(String projeto);

}
