package com.gerencial.projetos.crud.aplicacao.dto.mapper;

import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoAuxResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjetoAuxMapper implements RowMapper<ProjetoAuxResponse> {
    @Override
    public ProjetoAuxResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjetoAuxResponse response = new ProjetoAuxResponse();
        response.setCodigo(rs.getString("id"));
        response.setNomeDoProjeto(rs.getString("nome_do_projeto"));
        return response;
    }
}
