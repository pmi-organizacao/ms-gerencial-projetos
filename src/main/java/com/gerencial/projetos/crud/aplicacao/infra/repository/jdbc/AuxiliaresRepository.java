package com.gerencial.projetos.crud.aplicacao.infra.repository.jdbc;


import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Cargo;
import com.gerencial.projetos.crud.aplicacao.dto.mapper.ColaboradoresMapper;
import com.gerencial.projetos.crud.aplicacao.dto.mapper.ProjetoAuxMapper;
import com.gerencial.projetos.crud.aplicacao.dto.request.ColaboradorResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoAuxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class AuxiliaresRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<ColaboradorResponse> buscarColaboradoresDe(String cargo) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM tb_colaborador_projeto WHERE nome_cargo = '").append(cargo).append("'");

        return jdbcTemplate.query(query.toString(),
                new ColaboradoresMapper());
    }

    public List<ProjetoAuxResponse> buscarNomeDosProjetos() {
        String query = "SELECT id, nome_do_projeto FROM tb_projeto";
        List<ProjetoAuxResponse> responses = jdbcTemplate.query(query,
                new ProjetoAuxMapper());
        return  responses;
    }



}
