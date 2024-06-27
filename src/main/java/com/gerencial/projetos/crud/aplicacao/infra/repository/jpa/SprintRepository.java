package com.gerencial.projetos.crud.aplicacao.infra.repository.jpa;

import com.gerencial.projetos.crud.aplicacao.dominio.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    @Query(value = "SELECT * FROM tb_sprint where ordem_de_Servico_id = ?", nativeQuery = true)
    List<Sprint> findByCodigoDaOs(Long id);

}
