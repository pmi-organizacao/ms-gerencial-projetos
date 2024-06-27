package com.gerencial.projetos.crud.aplicacao.infra.repository.jpa;

import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasoDeUsoRepository extends JpaRepository<CasoDeUso, Long> {

    @Query(value = "select * from tb_casos_de_uso " +
            "WHERE responsavel ILIKE :nome", nativeQuery = true)
    List<CasoDeUso> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM tb_casos_de_uso where sprint_id = ?", nativeQuery = true)
    List<CasoDeUso> findByPeloIdSprint(Long id);
}
