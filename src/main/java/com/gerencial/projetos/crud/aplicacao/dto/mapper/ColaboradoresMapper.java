package com.gerencial.projetos.crud.aplicacao.dto.mapper;


import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Cargo;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.EStatusProjeto;
import com.gerencial.projetos.crud.aplicacao.dto.request.ColaboradorResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColaboradoresMapper implements RowMapper<ColaboradorResponse> {

    @Override
    public ColaboradorResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColaboradorResponse colaborador = new ColaboradorResponse();
        colaborador.setId(rs.getLong("id"));
        colaborador.setNomeCompleto(rs.getString("nome_completo"));
        colaborador.setNomeCargo(rs.getString("nome_cargo"));
        colaborador.setStatus(rs.getLong("status"));
        colaborador.setOs(rs.getLong("id_ordem_de_servico"));
        colaborador.setUc(rs.getLong("id_caso_de_uso"));
        colaborador.setTarefa(rs.getLong("id_tarefa"));
        return colaborador;
    }
}
